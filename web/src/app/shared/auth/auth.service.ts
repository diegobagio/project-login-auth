import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  tokenURL: string = environment.apiUrl + environment.tokenUrl;

  jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient, private router: Router) {}

  getToken() {
    const stringToken = localStorage.getItem('access_token');
    if (stringToken) {
      const token = JSON.parse(stringToken);
      return token;
    }
    return null;
  }

  isAuthenticated() {
    const token = this.getToken();
    return !this.jwtHelper.isTokenExpired(token);
  }

  login(username: string, password: string): Observable<any> {
    // const params = new HttpParams()
    //   .set('username', username)
    //   .set('password', password)

    let user = {
      username: username,
      password: password
    }

    const headers = {
      'Content-Type': 'application/json',
    };
    return this.http.post(this.tokenURL, user, { headers,  responseType: 'text' });
  }
}
