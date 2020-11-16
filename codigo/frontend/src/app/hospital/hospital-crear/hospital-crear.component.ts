import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HospitalService } from '../../service/hospital.service'

@Component({
  selector: 'app-hospital-crear',
  templateUrl: './hospital-crear.component.html',
  styleUrls: ['./hospital-crear.component.scss'],
  providers:[
    HospitalService
  ]
})
export class HospitalCrearComponent implements OnInit {

  title = 'Nuevo Hospital';
  validatorForm: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private hospitalService:HospitalService
  ) {
    
  }
  ngOnInit() {
    this.validatorForm = this.formBuilder.group({
        nombre: [null,[ Validators.required,Validators.maxLength(50)]],
        direccion: [null,[ Validators.required,Validators.maxLength(50)]]
    });
  }

  get f() { return this.validatorForm.controls; }

  onSubmit(formData) {

    this.submitted = true;
    if (this.validatorForm.invalid) {
        return;
    }

    this.hospitalService.guardar(formData).subscribe((date) => {
      this.router.navigate(["main/hospital"]);
    })
  }
  
  cancelar(){
    this.router.navigate(["main/hospital"]);
  }

}
