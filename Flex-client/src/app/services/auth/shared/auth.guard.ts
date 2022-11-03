import { Injectable } from '@angular/core';
import {
  CanActivate,
  Router,

} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthService} from "./auth.service";
import {ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private auth: AuthService,
    private router: Router,
    private toaster: ToastrService
  ) {
  }

  canActivate(){
    if(this.auth.IsLoggedIn()){
      return true;
    }
    this.toaster.error('You have not logged in.', 'Please Login!',{
      closeButton: true,
    });
    this.router.navigate(['/']);
    return false;
  }
}
