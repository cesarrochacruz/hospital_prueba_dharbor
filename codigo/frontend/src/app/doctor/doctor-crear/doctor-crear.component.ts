import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DoctorService } from '../../service/doctor.service';
import {DateAdapter, MAT_DATE_FORMATS} from '@angular/material/core';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../format-datepicker';
import { DatePipe } from '@angular/common';



@Component({
  selector: 'app-doctor-crear',
  templateUrl: './doctor-crear.component.html',
  styleUrls: ['./doctor-crear.component.scss'],
  providers:[
    DoctorService,
    DatePipe,
    {provide: DateAdapter, useClass: AppDateAdapter},
    {provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}
  ]
})
export class DoctorCrearComponent implements OnInit {

  title = 'Nuevo Doctor';
  validatorForm: FormGroup;
  submitted = false;
  hospitalId:String;

  

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    public datepipe: DatePipe,
    private activatedRoute:ActivatedRoute,
    private doctorService:DoctorService
  ) {
    
  }
  ngOnInit() {
    this.validatorForm = this.formBuilder.group({
        nombre: [null,[ Validators.required,Validators.maxLength(50)]],
        apellido: [null,[ Validators.required,Validators.maxLength(50)]],
        fechaNacimiento: [null,[ Validators.required,Validators.maxLength(10)]],
        direccion: [null,[ Validators.required,Validators.maxLength(200)]],
    });

    this.hospitalId = this.activatedRoute.snapshot.paramMap.get('hospital_id');
    
  }

  get f() { return this.validatorForm.controls; }

  onSubmit(formData) {

    this.submitted = true;
    if (this.validatorForm.invalid) {
        return;
    }

    
    let fechaFormateada =this.datepipe.transform(formData["fechaNacimiento"], 'MM/dd/yyyy');
    formData["fechaNacimiento"] = fechaFormateada;

    this.doctorService.guardar(formData, this.hospitalId).subscribe((date) => {
      this.router.navigate(["main/doctor/" + this.hospitalId]);
    })
  }
  
  cancelar(){
    this.router.navigate(["main/doctor/" + this.hospitalId]);
  }

}
