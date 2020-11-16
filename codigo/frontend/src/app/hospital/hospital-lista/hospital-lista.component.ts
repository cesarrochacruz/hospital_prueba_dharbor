import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Hospital } from '../../model/hospital';
import { HospitalService } from '../../service/hospital.service'
import { DoctorService } from '../../service/doctor.service'


@Component({
  selector: 'app-hospital-lista',
  templateUrl: './hospital-lista.component.html',
  styleUrls: ['./hospital-lista.component.scss'],
  providers:[
    HospitalService,
    DoctorService
  ]
})
export class HospitalListaComponent implements OnInit {

  hospitales:Hospital[];

  nombreBusqueda:string;
  

  constructor(
    private router: Router,
    private hospitalService:HospitalService,
    private doctorService:DoctorService
  ) { }

  ngOnInit() {
    this.nombreBusqueda="";
    this.obtenerHospitales();
  }

  obtenerHospitales():void{
    this.hospitalService.todos().subscribe((data) => {
      this.hospitales = data;
    })
  }

  crear():void{
    this.router.navigate(["/main/hospital/crear"]);
  }

  editar(hospital:Hospital):void{
    this.router.navigate(["/main/hospital/editar/" + hospital.id]);
  }
  
  eliminar(hospital:Hospital):void{
    console.log("ELIM");
    this.hospitalService.eliminar(hospital.id).subscribe((data) =>{
      this.obtenerHospitales();
    })
  }

  mostrarDoctores(hospital:Hospital):void{
    this.router.navigate(["/main/doctor/" + hospital.id]);
    
  }

  buscar(){
    console.log("valores:","nombre:" + this.nombreBusqueda);

    this.buscarTodosDoctores(this.nombreBusqueda);
  }
  buscarTodosDoctores(nombre:string):void{
    this.hospitalService.todosBusqueda(nombre).subscribe((data) => {
      this.hospitales = data;
    })
  }

}
