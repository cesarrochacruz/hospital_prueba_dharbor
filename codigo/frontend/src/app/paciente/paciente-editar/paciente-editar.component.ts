import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl,FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute,Router } from '@angular/router';
import { Paciente } from '../../model/paciente';
import { PacienteService } from '../../service/paciente.service';
import {DateAdapter, MAT_DATE_FORMATS} from '@angular/material/core';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../format-datepicker';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-paciente-editar',
  templateUrl: './paciente-editar.component.html',
  styleUrls: ['./paciente-editar.component.scss'],
  providers:[
    PacienteService,
    DatePipe,
    {provide: DateAdapter, useClass: AppDateAdapter},
    {provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}
  ]
})
export class PacienteEditarComponent implements OnInit {

  title = 'Editando Paciente';
  validatorForm: FormGroup;
  submitted = false;
  doctorId:String;
  paciente:Paciente;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    public datepipe: DatePipe,
    private activatedRoute:ActivatedRoute,
    private pacienteService:PacienteService,
  ) {
    
  }
  ngOnInit() {
    this.validatorForm = new FormGroup({
      id: new FormControl(''),
      nombre: new FormControl(''),
      apellido: new FormControl(''),
      fechaNacimiento: new FormControl(''),
      direccion: new FormControl(''),
   });

    let pacienteId = this.activatedRoute.snapshot.paramMap.get('id');
    this.doctorId = this.activatedRoute.snapshot.paramMap.get('doctor_id');
    
    this.pacienteService.porId(pacienteId).subscribe(p => {
      this.paciente = p;
      
      let convertidoATipoFecha: Date = new Date(this.paciente.fechaNacimiento);
        
      this.validatorForm = this.formBuilder.group({
          id: [this.paciente.id, [ Validators.required]],
          nombre: [this.paciente.nombre, [ Validators.required,Validators.maxLength(50)]],
          apellido: [this.paciente.apellido, [ Validators.required,Validators.maxLength(50)]],
          fechaNacimiento: [convertidoATipoFecha, [ Validators.required,Validators.maxLength(10)]],
          direccion: [this.paciente.direccion, [ Validators.required,Validators.maxLength(200)]]
      });
    })

  }

  get f() { return this.validatorForm.controls; }

  onSubmitActualizar(formData) {
    this.submitted = true;
    if (this.validatorForm.invalid) {
        return;
    }

    let fechaFormateada =this.datepipe.transform(formData["fechaNacimiento"], 'MM/dd/yyyy');
    formData["fechaNacimiento"] = fechaFormateada;

    this.pacienteService.actualizar(formData).subscribe((date) => {
      this.router.navigate(["main/paciente/" + this.doctorId]);
    })
  }

  cancelar(){
    this.router.navigate(["main/paciente/" + this.doctorId]);
  }

}
