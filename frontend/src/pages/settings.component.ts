import {Component,inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ApiService} from '../core/api.service';

@Component({standalone:true,imports:[CommonModule,FormsModule],template:`<div class="page"><div class="eyebrow">ACCOUNT</div><h1>Settings</h1><div class="panel"><h2>Change password</h2><form (ngSubmit)="changePassword()" class="grid2"><input name="currentPassword" [(ngModel)]="form.currentPassword" type="password" placeholder="Current password" required><input name="newPassword" [(ngModel)]="form.newPassword" type="password" placeholder="New password, minimum 8 characters" minlength="8" required><button class="primary">Update password</button></form><p class="success" *ngIf="success">Password updated successfully.</p><p class="error" *ngIf="error">{{error}}</p></div></div>`})
export class SettingsComponent{api=inject(ApiService);form={currentPassword:'',newPassword:''};success=false;error='';changePassword(){this.success=false;this.error='';this.api.post('/auth/change-password',this.form).subscribe({next:()=>{this.success=true;this.form={currentPassword:'',newPassword:''}},error:e=>this.error=e.error?.message||'Unable to update password'})}}
