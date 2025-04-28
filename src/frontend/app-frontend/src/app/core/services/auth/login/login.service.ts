import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private http: HttpClient
  ) { }

  execute(props: LoginProps): Observable<LoginResponse> {
    const headers = new HttpHeaders({
      "Content-Type": "application/json"
    });

    return this.http.post<LoginResponse>(
      "http://localhost:8080/auth/login",
      props,
      { headers }
    )
  }
}

export interface LoginProps {
  email: string,
  password: string
}

interface LoginResponse {
  accessToken: string;
}
