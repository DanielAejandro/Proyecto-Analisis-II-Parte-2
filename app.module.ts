import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UsuarioListComponent } from './component/usuario-list/usuario-list.component';
import { UsuarioFormularioComponent } from './component/usuario-formulario/usuario-formulario.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RoleListComponent } from './component/role-list/role-list.component';
import { RoleCreateComponent } from './component/role-create/role-create.component';
import { LoginComponent } from './component/login/login.component';
import { GeneroListComponent } from './component/genero-list/genero-list/genero-list.component';
import { GeneroFormularioComponent } from './component/genero-formulario/genero-formulario/genero-formulario.component';
import { EstadoCivilListComponent } from './component/estado-civil-list/status-usuario-list/estado-civil-list.component';
import { EstadoCivilFormularioComponent } from './component/estado-civil-formulario/status-usuario-formulario/estado-civil-formulario.component';
import { StatusCuentaListComponent } from './component/status-cuenta-list/status-usuario-list/status-cuenta-list.component';
import { StatusCuentaFormularioComponent } from './component/status-cuenta-formulario/status-usuario-formulario/status-cuenta-formulario.component';
import { TipoDocumentoListComponent } from './component/tipo-documento-list/status-usuario-list/tipo-documento-list.component';
import { TipoDocumentoFormularioComponent } from './component/tipo-documento-formulario/status-usuario-formulario/tipo-documento-formulario.component';
import { OpcionListComponent } from './component/opcion-lis/opcion-list/opcion-list.component';
import { ClasificacionCuentaListComponent } from './component/clasificacion-cuenta-list/clasificacion-cuenta-list.component';
import { ClasificacionCuentaFormularioComponent } from './component/clasificacion-cuenta-formulario/clasificacion-cuenta-formulario.component';
import { TransaccionFormularioComponent } from './component/transaccion-formulario/transaccion-formulario.component';
import { TransaccionListComponent } from './component/transaccion-list/transaccion-list.component';

@NgModule({
  declarations: [
    AppComponent,
    UsuarioListComponent,
    UsuarioFormularioComponent,
    RoleListComponent,
    RoleCreateComponent,
    LoginComponent,
    GeneroListComponent,
    GeneroFormularioComponent,
    EstadoCivilListComponent,
    EstadoCivilFormularioComponent,
    StatusCuentaListComponent,
    StatusCuentaFormularioComponent,
    TipoDocumentoListComponent,
    TipoDocumentoFormularioComponent,
    OpcionListComponent,
    TipoDocumentoFormularioComponent,
    TipoDocumentoListComponent,
    UsuarioFormularioComponent,
    UsuarioListComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    provideClientHydration(withEventReplay())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
