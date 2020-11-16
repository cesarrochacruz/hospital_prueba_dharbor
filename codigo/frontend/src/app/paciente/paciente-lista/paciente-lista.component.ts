import { Component, OnInit, Inject } from '@angular/core';
import { Router, ActivatedRoute} from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Paciente } from '../../model/paciente';
import { Doctor } from '../../model/doctor';
import { PacienteService } from '../../service/paciente.service'
import { DoctorService } from '../../service/doctor.service';
import { DatePipe } from '@angular/common';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';



export interface DialogData {
  pacienteId:Number;
  doctorId:Number;
  fechaNota: string;
  notaDescripcion: string;
}

@Component({
  selector: 'app-paciente-lista',
  templateUrl: './paciente-lista.component.html',
  styleUrls: ['./paciente-lista.component.scss'],
  providers:[
    PacienteService,
    DoctorService,
    DatePipe
  ]
})
export class PacienteListaComponent implements OnInit {

  notaFecha: string;
  notaDescripcion: string;

  nombreBusqueda:string;
  apellidoBusqueda:string;



  pacientes:Paciente[];
  doctorId:String;
  doctor:Doctor;

  constructor(
    private router: Router,
    private activatedRoute:ActivatedRoute,
    private pacienteService:PacienteService,
    private doctorService:DoctorService,
    public datepipe: DatePipe,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.nombreBusqueda="";
    this.apellidoBusqueda="";
    this.doctorId = this.activatedRoute.snapshot.paramMap.get('doctor_id');
    if(!this.doctorId || this.doctorId=="0"){
      this.obtenerPacientes();
    }else{
      this.obtenerPacientesPorDoctorId(this.doctorId);
      this.doctorService.porId(this.doctorId).subscribe((data) => {
        this.doctor = data;
      });
    }
    
  }

  obtenerPacientesPorDoctorId(doctorId:String):void{
    this.pacienteService.todosPorDoctorId(doctorId).subscribe((data) => {
      this.pacientes = data;
    })
  }

  obtenerPacientes():void{
    this.doctorId = "0";
    this.pacienteService.todos().subscribe((data) => {
      this.pacientes = data;
    })
  }

  crear():void{
    this.router.navigate(["/main/paciente/crear/" + this.doctorId]);
  }

  editar(paciente:Paciente):void{
    this.router.navigate(["/main/paciente/doctor/" + this.doctorId + "/editar/" + paciente.id]);
  }
  
  eliminar(paciente:Paciente):void{
    
    this.pacienteService.eliminar(paciente.id).subscribe((data) =>{
      this.obtenerPacientesPorDoctorId(this.doctorId);
    })
  }


  crearNotaVisita(pacienteId:Number,doctorId:Number,fecha:Date,descripcion:String):void{

    let notaFechaFormateada =this.datepipe.transform(fecha, 'MM/dd/yyyy HH:mm:ss');

    let pacienteTmp={
      "pacienteId":pacienteId,
      "doctorId":doctorId,
      "descripcion":descripcion,
      "fecha":notaFechaFormateada
    }
    this.pacienteService.guardarNotaVisita(pacienteTmp).subscribe((data) =>{
      
    })
  }

  buscar(){
    console.log("valores:","nombre:" + this.nombreBusqueda+ "  apellido:" + this.apellidoBusqueda);

    this.buscarTodosDoctores(this.nombreBusqueda, this.apellidoBusqueda);
  }
  buscarTodosDoctores(nombre:string,apellido:string):void{
    this.pacienteService.todosBusqueda(nombre, apellido).subscribe((data) => {
      this.pacientes = data;
    })
  }






  openDialog(doctoId:String,paciente:Paciente): void {
    const dialogRef = this.dialog.open(NotaVisitaAddDialog, {
      width: '450px',
      data: {doctorId:this.doctorId, pacienteId:paciente.id, notaFecha: this.notaFecha, notaDescripcion: this.notaDescripcion}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log("resp:",result);
      //this.fechaNota = result;
      this.crearNotaVisita(result.pacienteId,result.doctorId,result.notaFecha,result.notaDescripcion);
    });
  }

}


@Component({
  selector: 'nota-visita-add-dialog',
  templateUrl: 'nota-visita-add-dialog.html',
  styleUrls: ['nota-visita-add-dialog.scss'],
})
export class NotaVisitaAddDialog {

  validatorForm: FormGroup;
  submitted = false;
  constructor(
    private formBuilder: FormBuilder,

    public dialogRef: MatDialogRef<NotaVisitaAddDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  cancelarNuevaNotaVisita(): void {
    this.dialogRef.close();
  }
  guardarDatos(formData) {

    this.submitted = true;
    if (this.validatorForm.invalid) {
        return;
    }
    //this.dialogRef.close();
  }

  ngOnInit() {
    this.validatorForm = this.formBuilder.group({
      notaFecha: [null,[ Validators.required]],
      notaDescripcion: [null,[ Validators.required]],
  });
  }

  get f() { return this.validatorForm.controls; }

}
