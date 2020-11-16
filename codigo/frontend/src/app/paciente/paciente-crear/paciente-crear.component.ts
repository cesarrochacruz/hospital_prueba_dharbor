import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Paciente } from '../../model/paciente';
import { PacienteService } from '../../service/paciente.service';
import {DateAdapter, MAT_DATE_FORMATS} from '@angular/material/core';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../format-datepicker';
import { DatePipe } from '@angular/common';



@Component({
  selector: 'app-paciente-crear',
  templateUrl: './paciente-crear.component.html',
  styleUrls: ['./paciente-crear.component.scss'],
  providers:[
    PacienteService,
    DatePipe,
    {provide: DateAdapter, useClass: AppDateAdapter},
    {provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}
  ]
})
export class PacienteCrearComponent implements OnInit {

  title = 'Nuevo Paciente';
  validatorForm: FormGroup;
  submitted = false;
  doctorId:String;

  

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    public datepipe: DatePipe,
    private activatedRoute:ActivatedRoute,
    private pacienteService:PacienteService
  ) {
    
  }
  ngOnInit() {
    this.validatorForm = this.formBuilder.group({
        nombre: [null,[ Validators.required,Validators.maxLength(50)]],
        apellido: [null,[ Validators.required,Validators.maxLength(50)]],
        fechaNacimiento: [null,[ Validators.required,Validators.maxLength(10)]],
        direccion: [null,[ Validators.required,Validators.maxLength(200)]],
        notaDescripcion: [null,[ Validators.required]],
        notaFecha: [null,[ Validators.required]],
    });

    this.doctorId = this.activatedRoute.snapshot.paramMap.get('doctor_id');
    
  }

  get f() { return this.validatorForm.controls; }

  onSubmit(formData) {

    this.submitted = true;
    if (this.validatorForm.invalid) {
        return;
    }

    
    let fechaNacimientoFormateada =this.datepipe.transform(formData["fechaNacimiento"], 'MM/dd/yyyy');
    //formData["fechaNacimiento"] = fechaFormateada;

    let notaFechaFormateada =this.datepipe.transform(formData["notaFecha"], 'MM/dd/yyyy HH:mm:ss');
    //formData["notaVisita.fecha"] = notaFechaFormateada;

    let pacienteTmp={
      "nombre": formData['nombre'],
      "apellido": formData['apellido'],
      "direccion": formData['direccion'],
      "fechaNacimiento":fechaNacimientoFormateada,
      "notaVisita":{
          "descripcion":formData['notaDescripcion'],
          "fecha":notaFechaFormateada
      }
    }

    

    this.pacienteService.guardar(pacienteTmp, this.doctorId).subscribe((date) => {
      this.router.navigate(["main/paciente/" + this.doctorId]);
    })
  }
  
  cancelar(){
    this.router.navigate(["main/paciente/" + this.doctorId]);
  }

}
