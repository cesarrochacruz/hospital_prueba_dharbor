import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MainHttpInterceptor } from './interceptors/main-http-interceptor'
import { MainComponent } from './main/main.component';
import { MainHeaderComponent } from './snippets/main-header/main-header.component';
import { MenuComponent } from './menu/menu.component';
import { CustomMaterialModule } from './app.material.module'
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HospitalEditarComponent } from './hospital/hospital-editar/hospital-editar.component';
import { HospitalCrearComponent } from './hospital/hospital-crear/hospital-crear.component';
import { HospitalListaComponent } from './hospital/hospital-lista/hospital-lista.component';
import { DoctorEditarComponent } from './doctor/doctor-editar/doctor-editar.component';
import { DoctorCrearComponent } from './doctor/doctor-crear/doctor-crear.component';
import { DoctorListaComponent } from './doctor/doctor-lista/doctor-lista.component';
import { PacienteEditarComponent } from './paciente/paciente-editar/paciente-editar.component';
import { PacienteCrearComponent } from './paciente/paciente-crear/paciente-crear.component';
import { PacienteListaComponent, NotaVisitaAddDialog } from './paciente/paciente-lista/paciente-lista.component';




import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import {
  NgxMatDatetimePickerModule,
  NgxMatNativeDateModule,
  NgxMatTimepickerModule
} from '@angular-material-components/datetime-picker';
import { NotaVisitaListaComponent } from './nota-visita/nota-visita-lista/nota-visita-lista.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    MainHeaderComponent,
    MenuComponent,
    HospitalEditarComponent,
    HospitalCrearComponent,
    HospitalListaComponent,
    DoctorEditarComponent,
    DoctorCrearComponent,
    DoctorListaComponent,
    PacienteEditarComponent,
    PacienteCrearComponent,
    PacienteListaComponent,
    NotaVisitaListaComponent,
    NotaVisitaAddDialog
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

    MatDatepickerModule,
    MatInputModule,

    NgxMatDatetimePickerModule,
    NgxMatTimepickerModule,
    NgxMatNativeDateModule
    
  ],
  entryComponents:[
    NotaVisitaAddDialog
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MainHttpInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
