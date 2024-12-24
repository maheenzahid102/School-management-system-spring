// app.routes.ts
import { Routes } from '@angular/router';
import { HomeComponent } from './auth/home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { noAuthGuard } from './auth/guards/noAuth-guard/no-auth.guard';



export const routes: Routes = [
  {
    path:'login',
    component:LoginComponent,
    canActivate:[noAuthGuard]
  },
  {
    path:'admin',loadChildren:()=>import("./modules/admin/admin.module").then(m =>m.AdminModule)
  },
  {
    path:'student',loadChildren:()=>import("./modules/student/student.module").then(m =>m.StudentModule)
  },
  {
     path:'home',
     component: HomeComponent
  },
  
  {
    path: '',
    redirectTo: '/home',  // Set default route to '/login' if needed
    pathMatch: 'full'
  }
];
