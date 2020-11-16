import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError  } from 'rxjs';
import { environment } from '../../environments/environment';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MainHttpInterceptor implements HttpInterceptor{
  private _baseUrl: String = environment.url_base;

  constructor() { }

  intercept(request:HttpRequest<any>, next: HttpHandler):Observable<HttpEvent<any>>{
    const _newreq = request.clone({
      url: (request.url && request.url.startsWith('/')) ? this._baseUrl + request.url :request.url,
      withCredentials: true 
    });
    
    return next.handle(_newreq).pipe(
      retry(1),
      catchError(this.errorHandl)
    );;
  }

  errorHandl(error) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
 }
}
