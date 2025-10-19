package com.dbproject.analisis.dos.controller;



import com.dbproject.analisis.dos.model.dto.PersonaRequestDTO;
import com.dbproject.analisis.dos.model.dto.PersonaResponseDTO;
import com.dbproject.analisis.dos.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "*")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaResponseDTO> createPersona(@RequestBody PersonaRequestDTO personaDTO) {
        try {
            PersonaResponseDTO createdPersona = personaService.createPersona(personaDTO);
            return new ResponseEntity<>(createdPersona, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> updatePersona(@PathVariable Long id, @RequestBody PersonaRequestDTO personaDTO) {
        try {
            PersonaResponseDTO updatedPersona = personaService.updatePersona(id, personaDTO);
            return new ResponseEntity<>(updatedPersona, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) { // Cambiado de EntityNotFoundException
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> getPersonaById(@PathVariable Long id) {
        try {
            PersonaResponseDTO persona = personaService.getPersonaById(id);
            return new ResponseEntity<>(persona, HttpStatus.OK);
        } catch (RuntimeException e) { // Cambiado de EntityNotFoundException
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonaResponseDTO>> getAllPersonas() {
        List<PersonaResponseDTO> personas = personaService.getAllPersonas();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<PersonaResponseDTO>> getClientes() {
        List<PersonaResponseDTO> clientes = personaService.getClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/socios")
    public ResponseEntity<List<PersonaResponseDTO>> getSocios() {
        List<PersonaResponseDTO> socios = personaService.getSocios();
        return new ResponseEntity<>(socios, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PersonaResponseDTO>> searchPersonas(@RequestParam String q) {
        List<PersonaResponseDTO> personas = personaService.searchPersonas(q);
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        try {
            personaService.deletePersona(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) { // Cambiado de EntityNotFoundException
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}