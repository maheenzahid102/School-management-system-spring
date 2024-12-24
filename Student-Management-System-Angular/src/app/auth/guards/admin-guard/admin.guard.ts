import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { Router } from '@angular/router';
import { StorageService } from '../../service/storage-service/storage.service';
import { MatSnackBar } from '@angular/material/snack-bar';

export const adminGuard: CanActivateFn = (route, state) => {
  const storageService = inject(StorageService);
  const router = inject(Router);
  const snackbar=inject(MatSnackBar);

 // const token = storageService.getToken();

  // If token exists, redirect based on the user's role
  if (storageService.isStudentLoggedIn()) {
      router.navigateByUrl("/student/dashboard");
      snackbar.open("You do not have access to this page","Close",{duration:5000})
      return false;
  }
  else if(!storageService.hasToken() ){  
    storageService.logout();
    router.navigateByUrl("login");
    snackbar.open("You are not logged in","Close",{duration:5000})
    return false;
  }
return true;
};
