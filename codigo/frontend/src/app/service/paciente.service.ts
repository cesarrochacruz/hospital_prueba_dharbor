import { HttpClient,HttpHeaders,HttpParams  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable  } from 'rxjs';
import {Paciente} from '../model/paciente';

@Injectable({
  providedIn: 'root'
})
export class PacienteService {
  private url = '/api/paciente';

  constructor(
    private http: HttpClient
  ) { }

  // Http Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  porId(id:String): Observable<Paciente>{
    return this.http.get<Paciente>(this.url + '/id/' + id);
  }

  todosPorDoctorId(doctorId:String): Observable<Paciente[]>{
    return this.http.get<Paciente[]>(this.url + '/doctor-id/' + doctorId);
  }

  todos(): Observable<Paciente[]>{
    return this.http.get<Paciente[]>(this.url + '/');
  }

  guardar(paciente:any, doctorId:String): Observable<any>{
    return this.http.post<Paciente>(this.url + '/' + doctorId, paciente);
  }

  actualizar(paciente:Paciente): Observable<any>{
    return this.http.put<Paciente>(this.url + '/' + paciente.id, paciente);
  }

  eliminar(id:Number): Observable<any>{
    return this.http.delete<any>(this.url + '/' + id);
  }

  guardarNotaVisita(notaVisita:any): Observable<any>{
    return this.http.post<any>(this.url + '/nota-visita/guardar', notaVisita);
  }

  todosBusqueda(nombre:string, apellido:string): Observable<Paciente[]>{
    
    const params = new HttpParams()
    .set('nombre', nombre)
    .set('apellido', apellido);
    return this.http.get<Paciente[]>(this.url + '/busqueda/',{params});
  }
  
}
