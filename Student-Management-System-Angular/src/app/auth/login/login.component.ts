import { Component } from '@angular/core';
import { AuthService } from '../service/auth/auth.service';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NgIf } from '@angular/common';
import { response } from 'express';
import { Router } from '@angular/router';
import { StorageService } from '../service/storage-service/storage.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  imports: [FormsModule,ReactiveFormsModule,MatInputModule,MatFormFieldModule,NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {

  loginForm: FormGroup;
  constructor(
    private  service: AuthService,
    private storageService: StorageService,

    private fb: FormBuilder,
    private router:Router,
    private snackbar:MatSnackBar

  ){ }

  // loginForm: FormGroup=new FormGroup({
  //   email: new FormControl(''),
  //   password: new FormControl('')
  // });
  ngOnInit(){
    this.loginForm=this.fb.group({
      email:['',Validators.required],
      password:['',Validators.required]

    })
  }

 
//   login() {
//     console.log(this.loginForm.value);
//     this.service.login(
//       this.loginForm.get(['email'])!.value,
//       this.loginForm.get(['password'])!.value

//     ).subscribe((response)=>{
//       if(StorageService.isAdminLoggedIn()){
//         this.router.navigateByUrl("admin/dashboard");
//       }
//       else if(StorageService.isStudentLoggedIn()){
//         this.router.navigateByUrl("student/dashboard");
//       }

//     }),
//     error => {
//       if(error.status==406){
//         this.snackbar.open("User is not Active","Close",{
//             duration:5000
//         });

//       }else{
//         this.snackbar.open("Bad Credentials","Close",{
//           duration:5000
//         });
//       }
//     }
//     // if (this.loginForm?.valid) {
//     //   this.service.login(this.loginForm.value).subscribe({
//     //     next: (response) => {
//     //       console.log("Login successful:", response);
//     //       alert("Login successful!");
//     //       console.log(response);
//     //     },
//     //     error: (error) => {
//     //       console.error("Login failed:", error);
//     //       alert("Login failed. Please check your credentials.");
//     //     },
//     //     complete: () => {
//     //       console.log("Login observable completed");
//     //     }
//     //   });
//     // }
//   }
// }
login() {
  console.log("Login form values:", this.loginForm.value);

  this.service.login(
    this.loginForm.get('email')!.value,
    this.loginForm.get('password')!.value
  ).subscribe({
    next: (response) => {
      console.log("Login response:", response); // Full response for debugging
      
      // Extract token from response body
      const token = response.body?.jwt; // 'jwt' is the property that holds the token
      
      if (token) {
        // Save the token in localStorage
        // StorageService.saveToken(token);
        // StorageService.saveUser(response.body); // Save the entire user object
  
        // Navigate based on role
        if (this.storageService.isAdminLoggedIn()) {
          this.router.navigateByUrl("admin/dashboard");
        } else if (this.storageService.isStudentLoggedIn()) {
          this.router.navigateByUrl("student/dashboard");
        }
      } else {
        console.error("No token received in the response.");
        this.snackbar.open("Login failed: No token received", "Close", {
          duration: 5000,
        });
      }
    },
    error: (error) => {
      console.error("Login failed with error:", error);
      if (error.status === 406) {
        this.snackbar.open("User is not Active", "Close", {
          duration: 5000,
        });
      } else {
        this.snackbar.open("Bad Credentials", "Close", {
          duration: 5000,
        });
      }
      this.snackbar.open("User is not found", "Close", {
        duration: 5000,
      });
    }
  });
}





}
// import { Component } from '@angular/core';
// import { AuthService } from '../service/auth/auth.service';
// import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
// import { MatInputModule } from '@angular/material/input';
// import { MatFormFieldModule } from '@angular/material/form-field';
// import { NgIf } from '@angular/common';
// import { firstValueFrom } from 'rxjs';

// @Component({
//   selector: 'app-login',
//   imports: [FormsModule, ReactiveFormsModule, MatInputModule, MatFormFieldModule, NgIf],
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.css'], // Fixed typo: styleUrl -> styleUrls
// })
// export class LoginComponent {
//   loginForm: FormGroup;

//   constructor(private service: AuthService, private fb: FormBuilder) {
//     // Initialize the form with hardcoded credentials
//     this.loginForm = this.fb.group({
//       email: ['test@example.com', [Validators.required, Validators.email]], // Hardcoded email
//       password: ['password123', Validators.required], // Hardcoded password
//     });
//   }

//   login() {
//     if (this.loginForm.valid) {
//       console.log("Attempting login with:", this.loginForm.value); // Debug payload
//       this.service.login(this.loginForm.value).subscribe({
//   next: (response) => {
//     console.log("Login successful:", response);
//     alert("Login successful!");
//     // Navigate or handle token
//   },
//   error: (error) => {
//     console.error("Login failed:", error);
//     alert("Login failed. Please check your credentials.");
//   },
//   complete: () => {
//     console.log("Login observable completed");
//   },
// })
//     }
//   }
// }
//   // async login() {
//   //   if (this.loginForm.valid) {
//   //     try {
//   //       const response = await firstValueFrom(this.service.login(this.loginForm.value));
//   //       console.log("Login successful:", response);
//   //       alert("Login successful!");
//   //       // Handle success (e.g., navigate to dashboard)
//   //     } catch (error) {
//   //       console.error("Login failed:", error);
//   //       alert("Login failed. Please check your credentials.");
//   //     }
//   //   } else {
//   //     console.warn("Login form is invalid");
//   //     alert("Please fill in all required fields.");
//   //   }
//   // }


