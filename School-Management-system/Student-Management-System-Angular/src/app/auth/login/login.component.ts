// import { Component } from '@angular/core';
// import { AuthService } from '../service/auth/auth.service';
// import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
// import { MatInputModule } from '@angular/material/input';
// import { MatFormFieldModule } from '@angular/material/form-field';
// import { NgIf } from '@angular/common';
// import { response } from 'express';

// @Component({
//   selector: 'app-login',
//   imports: [FormsModule,ReactiveFormsModule,MatInputModule,MatFormFieldModule,NgIf],
//   templateUrl: './login.component.html',
//   styleUrl: './login.component.css',
// })
// export class LoginComponent {

//   loginForm: FormGroup;
//   constructor(
//     private  service: AuthService,
//     private fb: FormBuilder
//   ){ }

//   // loginForm: FormGroup=new FormGroup({
//   //   email: new FormControl(''),
//   //   password: new FormControl('')
//   // });
//   ngOnInit(){
//     this.loginForm=this.fb.group({
//       email:['',Validators.required],
//       password:['',Validators.required]

//     })
//   }

 
//   login() {
//     if (this.loginForm?.valid) {
//         this.service.login(this.loginForm.value).subscribe(
//             (response: any) => {
//                 console.log("Login successful:", response);
//                 // Handle success (e.g., navigate to a dashboard or store token)
//             },
//             (error) => {
//                 console.error("Login failed:", error);
//                 // Handle error (e.g., display error message)
//             }
//         );
//     } else {
//         console.warn("Login form is invalid");
//     }


//    }
// }
import { Component } from '@angular/core';
import { AuthService } from '../service/auth/auth.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [FormsModule, ReactiveFormsModule, MatInputModule, MatFormFieldModule, NgIf],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'], // Fixed typo: styleUrl -> styleUrls
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private service: AuthService, private fb: FormBuilder) {
    // Initialize the form with hardcoded credentials
    this.loginForm = this.fb.group({
      email: ['test@example.com', [Validators.required, Validators.email]], // Hardcoded email
      password: ['password123', Validators.required], // Hardcoded password
    });
  }

  login() {
    if (this.loginForm.valid) {
      console.log("Attempting login with:", this.loginForm.value); // Debug payload
      this.service.login(this.loginForm.value).subscribe(
        (response: any) => {
          console.log("Login successful:", response);
          alert("Login successful!"); // Notify user
          // Navigate or store token here
        },
        (error) => {
          console.error("Login failed:", error);
          alert("Login failed. Please check your credentials.");
        }
      );
    } else {
      console.warn("Login form is invalid");
      alert("Please fill in all required fields.");
    }
  }
}

