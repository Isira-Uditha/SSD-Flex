import { Injectable } from '@angular/core';
import {Store} from "../store";
import {AUTH} from "../constants";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private store: Store,
  ) { }

  IsLoggedIn(){
    return !!this.store.getData(AUTH.id) && this.store.getData(AUTH.token) && this.store.getData(AUTH.role) && this.store.getData(AUTH.username);
  }
}
