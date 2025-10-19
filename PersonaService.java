package com.dbproject.analisis.dos.service;



import com.dbproject.analisis.dos.model.dto.PersonaRequestDTO;
import com.dbproject.analisis.dos.model.dto.PersonaResponseDTO;
import com.dbproject.analisis.dos.model.dto.TelefonoDTO;
import com.dbproject.analisis.dos.model.entity.EstadoCivil;
import com.dbproject.analisis.dos.model.entity.Genero;
import com.dbproject.analisis.dos.model.entity.Persona;
import com.dbproject.analisis.dos.model.entity.TelefonoPersona;
import com.dbproject.analisis.dos.model.entity.TipoDocumento;
import com.dbproject.analisis.dos.model.repository.PersonaRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @Autowired
    private EstadoCivilService estadoCivilService;

    @Autowired
    private GeneroService generoService;

    @Transactional
    public PersonaResponseDTO createPersona(PersonaRequestDTO personaDTO) {
        // Validar que el número de identificación no exista
        if (personaRepository.existsByNumeroIdentificacion(personaDTO.getNumeroIdentificacion())) {
            throw new IllegalArgumentException("Ya existe una persona con el número de identificación: " + personaDTO.getNumeroIdentificacion());
        }

        Persona persona = new Persona();
        return saveOrUpdatePersona(persona, personaDTO);
    }

    @Transactional
    public PersonaResponseDTO updatePersona(Long id, PersonaRequestDTO personaDTO) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + id));

        // Validar que el número de identificación no esté siendo usado por otra persona
        if (!persona.getNumeroIdentificacion().equals(personaDTO.getNumeroIdentificacion()) &&
            personaRepository.existsByNumeroIdentificacion(personaDTO.getNumeroIdentificacion())) {
            throw new IllegalArgumentException("Ya existe otra persona con el número de identificación: " + personaDTO.getNumeroIdentificacion());
        }

        return saveOrUpdatePersona(persona, personaDTO);
    }

    private PersonaResponseDTO saveOrUpdatePersona(Persona persona, PersonaRequestDTO personaDTO) {
        // Mapear datos básicos
        persona.setNombreCompleto(personaDTO.getNombreCompleto());
        persona.setDireccion(personaDTO.getDireccion());
        persona.setCorreoElectronico(personaDTO.getCorreoElectronico());
        persona.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
        persona.setEsCliente(personaDTO.getEsCliente() != null ? personaDTO.getEsCliente() : false);
        persona.setEsSocio(personaDTO.getEsSocio() != null ? personaDTO.getEsSocio() : false);

        // Mapear relaciones - CORREGIDO
        if (personaDTO.getTipoDocumentoId() != null) {
            TipoDocumento tipoDocumento = tipoDocumentoService.findById(personaDTO.getTipoDocumentoId())
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado con ID: " + personaDTO.getTipoDocumentoId()));
            persona.setTipoDocumento(tipoDocumento);
        }

        if (personaDTO.getEstadoCivilId() != null) {
            EstadoCivil estadoCivil = estadoCivilService.findById(personaDTO.getEstadoCivilId())
                    .orElseThrow(() -> new EntityNotFoundException("Estado civil no encontrado con ID: " + personaDTO.getEstadoCivilId()));
            persona.setEstadoCivil(estadoCivil);
        }
       if (personaDTO.getGeneroId() != null) {
            Genero genero = generoService.findById(personaDTO.getGeneroId())
                    .orElseThrow(() -> new EntityNotFoundException("Género no encontrado con ID: " + personaDTO.getEstadoCivilId()));
            persona.setGenero(genero);
        }


        // Limpiar teléfonos existentes y agregar nuevos
        if (persona.getTelefonos() != null) {
            persona.getTelefonos().clear();
        }

        if (personaDTO.getTelefonos() != null && !personaDTO.getTelefonos().isEmpty()) {
            for (TelefonoDTO telefonoDTO : personaDTO.getTelefonos()) {
                TelefonoPersona telefono = new TelefonoPersona();
                telefono.setNumeroTelefono(telefonoDTO.getNumeroTelefono());
                telefono.setTipoTelefono(telefonoDTO.getTipoTelefono());
                telefono.setPersona(persona);
                if (persona.getTelefonos() != null) {
                    persona.getTelefonos().add(telefono);
                }
            }
        }

        Persona savedPersona = personaRepository.save(persona);
        return convertToDTO(savedPersona);
    }

    @Transactional(readOnly = true)
    public PersonaResponseDTO getPersonaById(Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + id));
        return convertToDTO(persona);
    }

    @Transactional(readOnly = true)
    public List<PersonaResponseDTO> getAllPersonas() {
        return personaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PersonaResponseDTO> getClientes() {
        return personaRepository.findByEsClienteTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PersonaResponseDTO> getSocios() {
        return personaRepository.findByEsSocioTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PersonaResponseDTO> searchPersonas(String searchTerm) {
        return personaRepository.findByNumeroIdentificacionOrNombreCompletoContaining(searchTerm).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePersona(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new EntityNotFoundException("Persona no encontrada con ID: " + id);
        }
        personaRepository.deleteById(id);
    }

    private PersonaResponseDTO convertToDTO(Persona persona) {
        PersonaResponseDTO dto = new PersonaResponseDTO();
        dto.setId(persona.getId());
        dto.setNombreCompleto(persona.getNombreCompleto());
        dto.setDireccion(persona.getDireccion());
        dto.setCorreoElectronico(persona.getCorreoElectronico());
        dto.setNumeroIdentificacion(persona.getNumeroIdentificacion());
        dto.setEsCliente(persona.getEsCliente());
        dto.setEsSocio(persona.getEsSocio());
        dto.setFechaCreacion(persona.getFechaCreacion());
        dto.setFechaActualizacion(persona.getFechaActualizacion());

        // Mapear relaciones
        if (persona.getTipoDocumento() != null) {
            dto.setTipoDocumento(persona.getTipoDocumento().getNombre());
        }

        if (persona.getEstadoCivil() != null) {
            dto.setEstadoCivil(persona.getEstadoCivil().getNombre());
        }

        if (persona.getGenero() != null) {
            dto.setGenero(persona.getGenero().getNombre());
        }

        // Mapear teléfonos
        if (persona.getTelefonos() != null) {
            List<TelefonoDTO> telefonosDTO = persona.getTelefonos().stream()
                    .map(telefono -> new TelefonoDTO(telefono.getNumeroTelefono(), telefono.getTipoTelefono()))
                    .collect(Collectors.toList());
            dto.setTelefonos(telefonosDTO);
        }

        return dto;
    }
}