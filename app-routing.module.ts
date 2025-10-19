import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuarioListComponent } from './component/usuario-list/usuario-list.component';
import { UsuarioFormularioComponent } from './component/usuario-formulario/usuario-formulario.component';
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
import { TransaccionListComponent } from './component/transaccion-list/transaccion-list.component';
import { TransaccionFormularioComponent } from './component/transaccion-formulario/transaccion-formulario.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // página default
  { path: 'login', component: LoginComponent },
  { path: 'usuarios', component: UsuarioListComponent },
  { path: 'usuarios/nuevo', component: UsuarioFormularioComponent },
  { path: 'usuarios/editar/:id', component: UsuarioFormularioComponent },
  { path: 'usuarios', component: UsuarioListComponent },
  { path: 'roles', component: RoleListComponent },
  { path: 'roles/nuevo', component: RoleCreateComponent },
  { path: 'roles/editar/:id', component: RoleCreateComponent },
  { path: 'generos', component: GeneroListComponent },
  { path: 'generos/nuevo', component: GeneroFormularioComponent },
  { path: 'generos/editar/:id', component: GeneroFormularioComponent },
  { path: 'estado-civil', component: EstadoCivilListComponent },
  { path: 'estado-civil/nuevo', component: EstadoCivilFormularioComponent },
  { path: 'estado-civil/editar/:id', component: EstadoCivilFormularioComponent },
  { path: 'status-cuenta', component: StatusCuentaListComponent },
  { path: 'status-cuenta/nuevo', component: StatusCuentaFormularioComponent },
  { path: 'status-cuenta/editar/:id', component: StatusCuentaFormularioComponent },
  { path: 'tipo-documento', component: TipoDocumentoListComponent },
  { path: 'tipo-documento/nuevo', component: TipoDocumentoFormularioComponent },
  { path: 'tipo-documento/editar/:id', component: TipoDocumentoFormularioComponent },
  { path: 'opciones', component: OpcionListComponent },
  { path: 'transacciones', component: TransaccionListComponent },
  { path: 'transacciones/nueva', component: TransaccionFormularioComponent },
  { path: 'transacciones/editar/:id', component: TransaccionFormularioComponent },
  { path: '', redirectTo: '/transacciones', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
