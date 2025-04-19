import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(
    private http: HttpClient
  ) { }

  execute(props: RegisterProps): Observable<void> {
    const headers = new HttpHeaders({
      "Content-Type": "application/json"
    });

    return this.http.post<void>(
      "http://localhost:8080/auth/register",
      props,
      { headers }
    );
  }
}

interface RegisterProps {
  displayName: string,
  email: string,
  password: string
}