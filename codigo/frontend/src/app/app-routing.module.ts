import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent} from './main/main.component';
import { HospitalListaComponent } from './hospital/hospital-lista/hospital-lista.component';
import { HospitalCrearComponent } from './hospital/hospital-crear/hospital-crear.component';
import { HospitalEditarComponent } from './hospital/hospital-editar/hospital-editar.component';

import { DoctorListaComponent } from './doctor/doctor-lista/doctor-lista.component';
import { DoctorCrearComponent } from './doctor/doctor-crear/doctor-crear.component';
import { DoctorEditarComponent } from './doctor/doctor-editar/doctor-editar.component';

import { PacienteListaComponent } from './paciente/paciente-lista/paciente-lista.component';
import { PacienteCrearComponent } from './paciente/paciente-crear/paciente-crear.component';
import { PacienteEditarComponent } from './paciente/paciente-editar/paciente-editar.component';


const routes: Routes = [
{
  path:'main', component:MainComponent, children:[
    {path: 'hospital', component:HospitalListaComponent},
    {path: 'hospital/crear', component:HospitalCrearComponent},
    {path: 'hospital/editar/:id', component:HospitalEditarComponent},

    {path: 'doctor/:hospital_id', component:DoctorListaComponent},
    {path: 'doctor/crear/:hospital_id', component:DoctorCrearComponent},
    {path: 'doctor/editar/:id', component:DoctorEditarComponent},

    {path: 'paciente/:doctor_id', component:PacienteListaComponent},
    {path: 'paciente/crear/:doctor_id', component:PacienteCrearComponent},
    {path: 'paciente/doctor/:doctor_id/editar/:id', component:PacienteEditarComponent}

  ]
}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
