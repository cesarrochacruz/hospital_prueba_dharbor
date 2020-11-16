import { HttpClient,HttpHeaders,HttpParams   } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable  } from 'rxjs';
import {Doctor} from '../model/doctor';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  private url = '/api/doctor';

  constructor(
    private http: HttpClient
  ) { }

  // Http Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  porId(id:String): Observable<Doctor>{
    return this.http.get<Doctor>(this.url + '/id/' + id);
  }

  todosPorIdHospital(hospitalId:String): Observable<Doctor[]>{
    return this.http.get<Doctor[]>(this.url + '/hospital-id/' + hospitalId);
  }

  todos(): Observable<Doctor[]>{
    return this.http.get<Doctor[]>(this.url + '/');
  }

  guardar(doctor:Doctor, hospitalId:String): Observable<any>{
    console.log("ingresss");
    return this.http.post<Doctor>(this.url + '/' + hospitalId, doctor);
  }

  actualizar(doctor:Doctor): Observable<any>{
    return this.http.put<Doctor>(this.url + '/' + doctor.id, doctor);
  }

  eliminar(id:Number): Observable<any>{
    return this.http.delete<any>(this.url + '/' + id);
  }

  todosBusqueda(nombre:string, apellido:string): Observable<Doctor[]>{
    
    const params = new HttpParams()
    .set('nombre', nombre)
    .set('apellido', apellido);
    return this.http.get<Doctor[]>(this.url + '/busqueda/',{params});
  }

  
}
