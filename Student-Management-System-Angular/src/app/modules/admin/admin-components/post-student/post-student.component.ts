import { Component } from '@angular/core';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgIf ,NgFor} from '@angular/common';

import { AdminService } from '../../admin-service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-post-student',
  imports: [MatProgressSpinnerModule,MatFormFieldModule,NgFor,MatInputModule ,MatButtonModule,MatSelectModule,ReactiveFormsModule,FormsModule, MatNativeDateModule,MatDatepickerModule,NgIf],
  templateUrl: './post-student.component.html',
  styleUrl: './post-student.component.css'
})
export class PostStudentComponent {
  CLASS:string[]=[
    "Play","1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th"

  ];

  GENDER: string[]=[
    "Male","Female","Other"
  ]
  isSpinning: boolean;
  validateForm:FormGroup;

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm?.controls["password"].value) {
      return { confirm: true, error: true };
    }
    return {};
  };

  constructor(
    private service:AdminService,
      private fb:FormBuilder,
      private snackbar:MatSnackBar
    ){}

    ngOnInit(){
      this.validateForm=this.fb.group({
        email:['',Validators.required],
        name:['',Validators.required],
        password:['',Validators.required],
        checkPassword:['',Validators.required],
        fatherName:['',Validators.required],
        motherName:['',Validators.required],
        studentClass:['',Validators.required],
        dob:['',Validators.required],
        address:['',Validators.required],
        gender:['',Validators.required],


      })
    }



    postStudent(){
      console.log(this.validateForm.value);
      this.isSpinning=true;
      this.service.addStudent(this.validateForm.value).subscribe((res)=>{
        this.isSpinning=false;
        if(res.id!=null){
           this.snackbar.open("Student posted Successfully","Close",{duration:5000});
        }
        else{
          this.snackbar.open("Student already exist","Close",{duration:5000});
        }
        console.log(res);
      })
    }

  }



