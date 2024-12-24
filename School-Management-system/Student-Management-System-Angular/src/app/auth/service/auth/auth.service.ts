// import { HttpClient } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import { Observable } from 'rxjs';

// const BASIC_URL = 'http://localhost:8080/'; // Use a string instead of an array

// @Injectable({
//   providedIn: 'root'
// })
// export class AuthService {

//   constructor(private http: HttpClient) { }

//   login(loginRequest: any): Observable<any> {
//     return this.http.post(BASIC_URL + 'authenticate', loginRequest); // Correctly concatenate the string
//   }
// }

import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly validEmail = 'test@example.com';
  private readonly validPassword = 'password123';

  constructor() {}

  /**
   * Simulates a login request with hardcoded credentials.
   * @param loginRequest - The login payload containing email and password.
   * @returns Observable with success or error response.
   */
  login(loginRequest: any): Observable<any> {
    const { email, password } = loginRequest;

    if (email === this.validEmail && password === this.validPassword) {
      return of({ message: 'Login successful', token: 'dummy-jwt-token' });
    } else {
      return throwError(() => new Error('Invalid email or password.'));
    }
  }
}

