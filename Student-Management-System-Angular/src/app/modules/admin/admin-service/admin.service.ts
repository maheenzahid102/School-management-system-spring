// import { Injectable } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { StorageService } from '../../../auth/service/storage-service/storage.service';

// // Define the base URL for your API
// const BASIC_URL = 'http://localhost:8080/';

// @Injectable({
//   providedIn: 'root'
// })
// export class AdminService {

//   constructor(private http: HttpClient,
//               private storageService: StorageService) { }

//   // Adding a student and sending a POST request
//   addStudent(studentDto: any) {
//     return this.http.post<any>(BASIC_URL + 'api/admin/student', studentDto, {
//       headers: this.createAuthorizationHeader(),
//     });
//   }

//   // Function to create Authorization header with JWT Token
//   createAuthorizationHeader(): HttpHeaders {
//     let authHeaders: HttpHeaders = new HttpHeaders();
//     const token = this.storageService.getToken();
//     console.log("JWT Token:", token); // Debugging (remove in production)
    
//     if (token) {
//       console.log("Token is present, entering the if block."); // Debugging
//       return authHeaders.set("Authorization", " Bearer " + token);
//     } else {
//       console.log("No token found, returning empty headers."); // Debugging
//       return authHeaders; // Return empty headers if no token
//     }
//   }
  
// }
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, map, tap } from 'rxjs';
import { StorageService } from '../../../auth/service/storage-service/storage.service';

// Define the base URL for your API
const BASIC_URL = ["http://localhost:8080/"];
export const AUTH_HEADER = 'Authorization';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient,
              private storageService: StorageService) { }

  // Adding a student and sending a POST request
  addStudent(studentDto: any): Observable<any> {
    return this.http.post<[]>(BASIC_URL + "api/admin/student", studentDto, {
      observe: 'response',
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap(() => this.log("Student added")),
      map((res: HttpResponse<any>) => {
        // You can perform any further logic based on the response if needed
        return res.body;
      })
    );
  }

  // Function to create Authorization header with JWT Token
  createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    const token = this.storageService.getToken(); // Get token from StorageService
    console.log("JWT Token:", token); // Debugging (remove in production)

    if (token) {
      // Extract the Bearer token and set it in the header
      console.log("Token is present, entering the if block."); // Debugging
      return authHeaders.set("Authorization", "Bearer " + token);
    } else {
      console.log("No token found, returning empty headers."); // Debugging
      return authHeaders; // Return empty headers if no token
    }
  }

  // Log method for debugging
  log(message: string) {
    console.log(message);
  }
}
