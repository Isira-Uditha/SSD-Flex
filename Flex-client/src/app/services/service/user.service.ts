import { Injectable } from '@angular/core';
import {HttpService} from "../config/http.service";
import {ApiUserService} from "../constant/api-user.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private httpService: HttpService,
    private apiUserService: ApiUserService,
  ){ }

  loginIn(userName: any, password: any) {
    return this.httpService.httpPost(
      this.apiUserService.loginIn.replace('{username}',userName).replace('{password}',password),''
    );
  }

  findUserById(userId: any) {
    return this.httpService.httpGet(
      this.apiUserService.findUserById.replace('{userId}',userId)
    );
  }

  findUserByUsernameAndPassword(userName: any, password: any){
    return this.httpService.httpGet(
      this.apiUserService.findUserByUsernameAndPassword.replace('{username}',userName).replace('{password}',password)
    );
  }

  createUser(body: any){
    return this.httpService.httpPost(
      this.apiUserService.createUser,body
    );
  }
}
