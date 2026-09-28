import {CanActivateFn,Router} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from './auth.service';
export const authGuard:CanActivateFn=()=>{const a=inject(AuthService);return a.logged()?true:inject(Router).parseUrl('/login');};
