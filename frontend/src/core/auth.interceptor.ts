import {HttpInterceptorFn} from '@angular/common/http';
import {Router} from '@angular/router';
import {inject} from '@angular/core';
import {catchError,throwError} from 'rxjs';
export const authInterceptor:HttpInterceptorFn=(req,next)=>{const token=localStorage.getItem('token');const request=token?req.clone({setHeaders:{Authorization:`Bearer ${token}`}}):req;return next(request).pipe(catchError(error=>{if(error.status===401){localStorage.clear();inject(Router).navigateByUrl('/login')}return throwError(()=>error)}));};
