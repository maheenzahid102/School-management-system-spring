// import { Injectable } from '@angular/core';

// const USER="c_user";
// const TOKEN="c_token";

// @Injectable({
//   providedIn: 'root'
// })
// export class StorageService {

//   constructor() {
//    }

//    public saveUser(user:any){
//     window.localStorage.removeItem(USER);
//     window.localStorage.setItem(USER,JSON.stringify(user));

//    }

//    public saveToken(token:string){
//     window.localStorage.removeItem(TOKEN);
//     window.localStorage.setItem(TOKEN,token);
//    }
  

// // storage.service.ts
//  getToken(): string | null {
//   return localStorage.getItem('jwt'); // Retrieve the JWT token
// }


//    getUser():any{
//      return JSON.parse(localStorage.getItem(USER));
//    }
//     getUserRole():string{
//     const user=this.getUser();
//     if(user==null){
//        return'';
//     }
//     return user.role;
//    }

//    isAdminLoggedIn(): boolean{
//      if(this.getToken()==null)
//      {
//       return true;
//      }
//      const role:string =this.getUserRole();

//      return role=="ADMIN";
//    }
 

//   isStudentLoggedIn():boolean{
//     if(this.getToken()==null)
//       {
//        return true;
//       }
//       const role:string =this.getUserRole();
 
//       return role=="STUDENT";

//    }

//    logout(){
//     window.localStorage.removeItem(TOKEN);
//     window.localStorage.removeItem(USER);
//    }


// }
import { Injectable } from '@angular/core';

const USER = "c_user";
const TOKEN = "c_token";

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() {}

  private isLocalStorageAvailable(): boolean {
    try {
      // Attempt to access localStorage to check if it's available
      if (typeof window !== 'undefined' && window.localStorage) {
        // Try setting and removing an item in localStorage to check if it's functional
        const testKey = '__test__';
        window.localStorage.setItem(testKey, 'test');
        window.localStorage.removeItem(testKey);
        return true;
      }
      return false;
    } catch (e) {
      // If an error occurs, it means localStorage is not available or accessible
      return false;
    }
  }

  public saveUser(user: any) {
    if (this.isLocalStorageAvailable()) {
      window.localStorage.removeItem(USER);
      window.localStorage.setItem(USER, JSON.stringify(user));
    }
  }

  public saveToken(token: string) {
    if (this.isLocalStorageAvailable()) {
      window.localStorage.removeItem(TOKEN);
      window.localStorage.setItem(TOKEN, token);
    }
  }

  public getToken(): string | null {
    return this.isLocalStorageAvailable() ? localStorage.getItem(TOKEN) : null;
  }

  public getUser(): any {
    return this.isLocalStorageAvailable() ? JSON.parse(localStorage.getItem(USER)) : null;
  }

  public getUserRole(): string {
    const user = this.getUser();
    return user ? user.role : '';
  }

  public isAdminLoggedIn(): boolean {
    const token = this.getToken();
    console.log(token)
    if (token === null) {
      return false;
    }
    const role = this.getUserRole();
    console.log(role);
    return role.toLowerCase() === 'admin';
  }
   
  public hasToken():boolean{
    if(this.getToken()===null)
    {
         return false;
    }
    return true;

  }
  public isStudentLoggedIn(): boolean {
    const token = this.getToken();
    if (token === null) {
      return false;
    }
    const role = this.getUserRole();
    return role.toLowerCase() === 'student';
  }

  public logout() {
    if (this.isLocalStorageAvailable()) {
      localStorage.removeItem(TOKEN);
      localStorage.removeItem(USER);
    }
  }
}

