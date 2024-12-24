// import { Component } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { RouterLink, RouterOutlet,Router,NavigationEnd } from '@angular/router';
// import { MatButtonModule } from '@angular/material/button';
// import { MatToolbarModule } from '@angular/material/toolbar';
// import { MatSnackBarModule } from '@angular/material/snack-bar';
// import { StorageService } from './auth/service/storage-service/storage.service';

// @Component({
//   selector: 'app-root',
//   imports: [
//     CommonModule,
//     MatToolbarModule,
//     MatButtonModule,
//     MatSnackBarModule,
//     RouterLink,
//     RouterOutlet, // This is for the router-outlet directive
//   ],
//   templateUrl: './app.component.html',
//   styleUrls: ['./app.component.css'],
// })
// export class AppComponent {
//   title = 'Student-Management-System-Angular';

//   isAdminLoggedIn: boolean;
//   isStudentLoggedIn: boolean;

//   constructor(private router: Router) {
//     // Router service should be injected through constructor, not in imports array
//   }

//   ngOnInit() {
//     this.updateUserLoggedStatus();
//     this.router.events.subscribe((event) => {
//       if (event instanceof NavigationEnd) {
//         this.updateUserLoggedStatus();
//       }
//     });
//   }

//   private updateUserLoggedStatus(): void {
//     this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
//     this.isStudentLoggedIn = StorageService.isStudentLoggedIn();
//   }
// }
// import { Component } from '@angular/core';
// import { Router, NavigationEnd } from '@angular/router';
// import { StorageService } from './auth/service/storage-service/storage.service';

// @Component({
//   selector: 'app-root',
//   templateUrl: './app.component.html',
//   styleUrls: ['./app.component.css'],
//   imports: [
//     Router, // Ensures Router dependencies are included
//   ],
// })
// export class AppComponent {
//   title = 'Student-Management-System-Angular';
//   isAdminLoggedIn: boolean = false;

//   constructor(private router: Router, 
//     private storageService: StorageService) {}

//   ngOnInit() {
//     this.updateUserLoggedStatus();
//     this.router.events.subscribe((event) => {
//       if (event instanceof NavigationEnd) {
//         this.updateUserLoggedStatus();
//       }
//     });
//   }

//   private updateUserLoggedStatus(): void {
//     this.isAdminLoggedIn = this.storageService.isAdminLoggedIn();
//   }

 
// }
import { Component } from '@angular/core';
//import { Router, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet,Router,NavigationEnd } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { StorageService } from './auth/service/storage-service/storage.service';

@Component({
  selector: 'app-root',
    imports: [
    CommonModule,
    MatToolbarModule,
    MatButtonModule,
    MatSnackBarModule,
    RouterLink,
    RouterOutlet, // This is for the router-outlet directive
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Student-Management-System-Angular';
  isAdminLoggedIn: boolean = false;
  isStudentLoggedIn: boolean = false;

  constructor(private router: Router, private storageService: StorageService) {}

  ngOnInit() {
    this.updateUserLoggedStatus();
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.updateUserLoggedStatus();
      }
    });
  }

  private updateUserLoggedStatus(): void {
    // Use the instance of StorageService to access these methods
    this.isAdminLoggedIn = this.storageService.isAdminLoggedIn();
    this.isStudentLoggedIn = this.storageService.isStudentLoggedIn();
  }

  logout(){
    this.storageService.logout();
    this.router.navigateByUrl("/login")
  }
}
