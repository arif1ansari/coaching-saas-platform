import {bootstrapApplication} from '@angular/platform-browser';
import {provideRouter} from '@angular/router';
import {provideHttpClient,withInterceptors} from '@angular/common/http';
import {AppComponent} from './app.component';
import {routes} from './app.routes';
import {authInterceptor} from './core/auth.interceptor';
bootstrapApplication(AppComponent,{providers:[provideRouter(routes),provideHttpClient(withInterceptors([authInterceptor]))]});
