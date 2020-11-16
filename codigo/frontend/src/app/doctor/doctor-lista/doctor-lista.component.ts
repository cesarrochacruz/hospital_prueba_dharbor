import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute} from '@angular/router';
import { Doctor } from '../../model/doctor';
import { DoctorService } from '../../service/doctor.service'


@Component({
  selector: 'app-doctor-lista',
  templateUrl: './doctor-lista.component.html',
  styleUrls: ['./doctor-lista.component.scss'],
  providers:[
    DoctorService
  ]
})
export class DoctorListaComponent implements OnInit {

  doctores:Doctor[];
  hospitalId:String;

  nombreBusqueda:string;
  apellidoBusqueda:string;

  constructor(
    private router: Router,
    private activatedRoute:ActivatedRoute,
    private doctorService:DoctorService
  ) { }

  ngOnInit() {
    this.nombreBusqueda="";
    this.apellidoBusqueda="";
    this.hospitalId = this.activatedRoute.snapshot.paramMap.get('hospital_id');
    if( !this.hospitalId || this.hospitalId=="0" ){
      this.obtenerDoctores();
    }else{
      this.obtenerDoctoresPorHopsitalId(this.hospitalId);
    }
    
  }

  obtenerDoctoresPorHopsitalId(hospitalId:String):void{
    this.doctorService.todosPorIdHospital(hospitalId).subscribe((data) => {
      this.doctores = data;
    })
  }
  obtenerDoctores():void{
    this.doctorService.todos().subscribe((data) => {
      this.doctores = data;
    })
  }

  crear():void{
    this.router.navigate(["/main/doctor/crear/" + this.hospitalId]);
  }

  editar(doctor:Doctor):void{
    this.router.navigate(["/main/doctor/editar/" + doctor.id]);
  }
  
  eliminar(doctor:Doctor):void{
    
    this.doctorService.eliminar(doctor.id).subscribe((data) =>{
      this.obtenerDoctoresPorHopsitalId(this.hospitalId);
    })
  }

  mostrarPacientes(doctor:Doctor):void{
    this.router.navigate(["/main/paciente/" + doctor.id]);
  }

  buscar(){
    console.log("valores:","nombre:" + this.nombreBusqueda+ "  apellido:" + this.apellidoBusqueda);

    this.buscarTodosDoctores(this.nombreBusqueda, this.apellidoBusqueda);
  }
  buscarTodosDoctores(nombre:string,apellido:string):void{
    this.doctorService.todosBusqueda(nombre, apellido).subscribe((data) => {
      this.doctores = data;
    })
  }

}
