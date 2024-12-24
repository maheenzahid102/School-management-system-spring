import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DasboardComponent } from './student-components/dasboard/dasboard.component';
import { studentGuard } from '../../auth/guards/student-guard/student.guard';

const routes: Routes = [
  {
    path:'dashboard',
    component:DasboardComponent,
    canActivate:[studentGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
