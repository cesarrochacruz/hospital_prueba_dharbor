import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl,FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute,Router } from '@angular/router';
import { Doctor } from '../../model/doctor';
import { DoctorService } from '../../service/doctor.service';
import {DateAdapter, MAT_DATE_FORMATS} from '@angular/material/core';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../format-datepicker';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-doctor-editar',
  templateUrl: './doctor-editar.component.html',
  styleUrls: ['./doctor-editar.component.scss'],
  providers:[
    DoctorService,
    DatePipe,
    {provide: DateAdapter, useClass: AppDateAdapter},
    {provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}
  ]
})
export class DoctorEditarComponent implements OnInit {

  title = 'Editando Doctor';
  validatorForm: FormGroup;
  submitted = false;
  hospitalId:Number;
  doctor:Doctor;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    public datepipe: DatePipe,
    private activatedRoute:ActivatedRoute,
    private doctorService:DoctorService
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

    let doctorId = this.activatedRoute.snapshot.paramMap.get('id');
    
    this.doctorService.porId(doctorId).subscribe(d => {
      this.doctor = d;
      this.hospitalId = d.hospitalId;
      

      
      let convertidoATipoFecha: Date = new Date(this.doctor.fechaNacimiento);
        
      this.validatorForm = this.formBuilder.group({
          id: [this.doctor.id, [ Validators.required]],
          nombre: [this.doctor.nombre, [ Validators.required,Validators.maxLength(50)]],
          apellido: [this.doctor.apellido, [ Validators.required,Validators.maxLength(50)]],
          fechaNacimiento: [convertidoATipoFecha, [ Validators.required,Validators.maxLength(10)]],
          direccion: [this.doctor.direccion, [ Validators.required,Validators.maxLength(200)]]
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

    this.doctorService.actualizar(formData).subscribe((date) => {
      this.router.navigate(["main/doctor/" + this.hospitalId]);
    })
  }

  cancelar(){
    this.router.navigate(["main/doctor/" + this.hospitalId]);
  }

}
