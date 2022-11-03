import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import {Store} from "../store";
import {AuthService} from "./auth.service";
import {ToastrService} from "ngx-toastr";
import {AUTH} from "../constants";
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(
    private store: Store,
    private auth: AuthService,
    private toaster: ToastrService,
  ) {
  }
  canActivate() {
    let Role = this.store.getData(AUTH.role);
    if(Role == environment.role){
      return true;
    }
    this.toaster.error('You don\'t have admin rights.', 'Unauthorized Action!',{
      closeButton: true,
    });
    return false;
  }

}
