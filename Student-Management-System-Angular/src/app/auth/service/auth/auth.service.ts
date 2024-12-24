import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, tap } from 'rxjs';
import { StorageService } from '../storage-service/storage.service';

const BASIC_URL = 'http://localhost:8080/'; // Use a string instead of an array
export const AUTH_HEADER='Authorization';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
    private storage:StorageService
  ) { }

  // login(email:string,password:string): Observable<any> {
  //   return this.http.post(BASIC_URL + 'authenticate', {
  //     email,password
  //   },{observe:'response'})
  //   .pipe(
  //     tap(__ => this.log("User Authentication")),
  //     map((res: HttpResponse<any>) =>{
  //          this.storage.saveUser(res.body);
  //          const tokenLength=res.headers.get(AUTH_HEADER).length;
  //          const bearerToken=res.headers.get(AUTH_HEADER).substring(7,tokenLength);
  //          this.storage.saveToken(bearerToken);
  //          return res;

  //     })) // Correctly concatenate the string
  // }
  login(email: string, password: string): Observable<any> {
    return this.http.post(BASIC_URL + 'authenticate', { email, password }, { observe: 'response' })
      .pipe(
        tap(() => this.log("User Authentication")),
        map((res: HttpResponse<any>) => {
          this.storage.saveUser(res.body); // Save the user data
  
          const authHeader = res.headers.get(AUTH_HEADER); // Get Authorization header
  
          if (authHeader) {
            // Extract Bearer token from Authorization header
            const token = authHeader.split(' ')[1]; // Split 'Bearer <token>'
            if (token) {
              this.storage.saveToken(token); // Save the token in StorageService
            } else {
              console.error('No token found in the Authorization header.');
            }
          } else {
            console.error('Authorization header not found.');
          }
  
          return res;
        })
      );
  }
  

  log(message: string){
    console.log(message)
  }
}

// import { Injectable } from '@angular/core';
// import { Observable, of, throwError } from 'rxjs';

// @Injectable({
//   providedIn: 'root',
// })
// export class AuthService {
//   private readonly validEmail = 'test@example.com';
//   private readonly validPassword = 'password123';

//   constructor() {}

//   /**
//    * Simulates a login request with hardcoded credentials.
//    * @param loginRequest - The login payload containing email and password.
//    * @returns Observable with success or error response.
//    */
//   login(loginRequest: any): Observable<any> {
//     const { email, password } = loginRequest;

//     if (email === this.validEmail && password === this.validPassword) {
//       return of({ message: 'Login successful', token: 'dummy-jwt-token' });
//     } else {
//       return throwError(() => new Error('Invalid email or password.'));
//     }
//   }
// }

