import {Component,inject} from '@angular/core'; import {Router,RouterOutlet,RouterLink} from '@angular/router'; import {CommonModule} from '@angular/common'; import {AuthService} from './core/auth.service';
@Component({selector:'app-root',standalone:true,imports:[CommonModule,RouterOutlet,RouterLink],template:`
<div class="app-shell" [class.super-admin-shell]="auth.role()==='SUPER_ADMIN'">
<header><a class="brand" [routerLink]="auth.role()==='SUPER_ADMIN'?'/super-admin':auth.role()==='TEACHER'?'/teacher/dashboard':'/dashboard'"><span class="brand-mark">C</span><span>EduSync</span></a>
<nav><ng-container *ngIf="!auth.logged()"><a routerLink="/features">Home</a><a routerLink="/features">Features</a><a routerLink="/pricing">Pricing</a><a routerLink="/login">Login</a></ng-container><ng-container *ngIf="auth.role()==='COACHING_ADMIN'"><a routerLink="/dashboard">Dashboard</a><a routerLink="/students">Add Student</a><a routerLink="/teachers">Add Teacher</a></ng-container><ng-container *ngIf="auth.role()==='TEACHER'"><a routerLink="/teacher/dashboard">My dashboard</a><a routerLink="/teacher/attendance">Attendance</a></ng-container><a *ngIf="auth.role()==='SUPER_ADMIN'" routerLink="/super-admin">Platform</a></nav>
<div class="user"><span>{{auth.role() || 'Guest'}}</span><button (click)="auth.logout()">Logout</button></div></header>
<main><router-outlet/></main></div>`})
export class AppComponent{auth=inject(AuthService);}
