import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { Router } from '@angular/router';
import { StorageService } from '../../service/storage-service/storage.service';


export const noAuthGuard: CanActivateFn = () => {
  const storageService = inject(StorageService);
  const router = inject(Router);

 // const token = storageService.getToken();

  // If token exists, redirect based on the user's role
  if (storageService.hasToken() && storageService.isStudentLoggedIn()) {
      router.navigateByUrl("/student/dashboard");
      return false;
  }
  else if(storageService.hasToken() && storageService.isAdminLoggedIn())
  {
    router.navigateByUrl("/admin/dashboard");
    return false;
  }
return true;
  
};
