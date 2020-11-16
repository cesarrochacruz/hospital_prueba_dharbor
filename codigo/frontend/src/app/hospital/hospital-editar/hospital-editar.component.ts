import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl,FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute,Router } from '@angular/router';
import { Hospital } from '../../model/hospital';
import { HospitalService } from '../../service/hospital.service'

@Component({
  selector: 'app-hospital-editar',
  templateUrl: './hospital-editar.component.html',
  styleUrls: ['./hospital-editar.component.scss'],
  providers:[
    HospitalService
  ]
})
export class HospitalEditarComponent implements OnInit {

  title = 'Editando Hospital';
  validatorForm: FormGroup;
  submitted = false;
  hospital:Hospital;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private activatedRoute:ActivatedRoute,
    private hospitalService:HospitalService
  ) {
    
  }
  ngOnInit() {
    this.validatorForm = new FormGroup({
      id: new FormControl(''),
      nombre: new FormControl(''),
      direccion: new FormControl(''),
   });

    let idHospital = this.activatedRoute.snapshot.paramMap.get('id');
    this.hospitalService.porId(idHospital).subscribe(h => {
      this.hospital = h;

      this.validatorForm = this.formBuilder.group({
          id: [this.hospital.id, [ Validators.required]],
          nombre: [this.hospital.nombre, [ Validators.required,Validators.maxLength(50)]],
          direccion: [this.hospital.direccion, [ Validators.required,Validators.maxLength(50)]]
      });
    })

  }

  get f() { return this.validatorForm.controls; }

  onSubmitActualizar(formData) {
    this.submitted = true;
    if (this.validatorForm.invalid) {
        return;
    }

    this.hospitalService.actualizar(formData).subscribe((date) => {
      this.router.navigate(["main/hospital"]);
    })
  }

  cancelar(){
    this.router.navigate(["main/hospital"]);
  }

}
