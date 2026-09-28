import {CanActivateFn,Router} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from './auth.service';

export const roleGuard=(roles:string[]):CanActivateFn=>()=>{
 const auth=inject(AuthService); const router=inject(Router); const role=auth.role();
 return roles.includes(role||'')?true:router.parseUrl(role==='SUPER_ADMIN'?'/super-admin':role==='TEACHER'?'/teacher/dashboard':'/dashboard');
};