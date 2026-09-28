import {Injectable,inject} from '@angular/core'; import {HttpClient} from '@angular/common/http';
@Injectable({providedIn:'root'}) export class ApiService{http=inject(HttpClient);
 get<T>(u:string){return this.http.get<T>('/api'+u)} post<T>(u:string,b:any){return this.http.post<T>('/api'+u,b)}
 put<T>(u:string,b:any){return this.http.put<T>('/api'+u,b)} patch<T>(u:string,b:any){return this.http.patch<T>('/api'+u,b)} delete<T>(u:string){return this.http.delete<T>('/api'+u)}
}
