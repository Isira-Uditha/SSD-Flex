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
    return !!this.store.getData(AUTH.id);
  }
}
