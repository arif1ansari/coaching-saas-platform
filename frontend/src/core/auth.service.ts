import {Injectable,inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {tap} from 'rxjs';
@Injectable({providedIn:'root'})
export class AuthService{
 private http=inject(HttpClient); private router=inject(Router);
 login(data:any){return this.http.post<any>('/api/auth/login',data).pipe(tap(x=>{localStorage.setItem('token',x.token);localStorage.setItem('role',x.role);localStorage.setItem('name',x.name);localStorage.setItem('coachingId',x.coachingId||'');}));}
 signup(data:any){return this.http.post<any>('/api/auth/signup',data).pipe(tap(x=>{localStorage.setItem('token',x.token);localStorage.setItem('role',x.role);localStorage.setItem('name',x.name);localStorage.setItem('coachingId',x.coachingId||'');}));}
 logout(){localStorage.clear();this.router.navigateByUrl('/login');}
 token(){return localStorage.getItem('token');}
 role(){return localStorage.getItem('role');}
 logged(){return !!this.token();}
}
