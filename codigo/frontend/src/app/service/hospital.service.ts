import { HttpClient,HttpHeaders,HttpParams  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable  } from 'rxjs';
import {Hospital} from '../model/hospital';

@Injectable({
  providedIn: 'root'
})
export class HospitalService {
  private url = '/api/hospital';

  constructor(
    private http: HttpClient
  ) { }

  // Http Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  porId(id:String): Observable<Hospital>{
    return this.http.get<Hospital>(this.url + '/id/' + id);
  }

  todos(): Observable<Hospital[]>{
    return this.http.get<Hospital[]>(this.url + '/');
  }

  guardar(hospital:Hospital): Observable<any>{
    return this.http.post<Hospital>(this.url + '/', hospital);
  }

  actualizar(hospital:Hospital): Observable<any>{
    return this.http.put<Hospital>(this.url + '/' + hospital.id, hospital);
  }

  eliminar(id:Number): Observable<any>{
    return this.http.delete<any>(this.url + '/' + id);
  }

  todosBusqueda(nombre:string): Observable<Hospital[]>{
    
    const params = new HttpParams()
    .set('nombre', nombre)
    return this.http.get<Hospital[]>(this.url + '/busqueda/',{params});
  }

  
}
