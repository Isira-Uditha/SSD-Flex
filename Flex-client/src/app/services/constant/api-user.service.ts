import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";

@Injectable({
  providedIn: 'root'
})
export class ApiUserService {
  private readonly _loginIn: string;
  private readonly _findUserById: string;
  private readonly _findUserByUsernameAndPassword: string;
  private readonly _createUser: string;

  constructor(private _apiService: ApiService) {
    this._loginIn =
      _apiService.flexApiGatewayUrl + "/login?username={username}&password={password}";
    this._findUserById =
      _apiService.flexApiGatewayUrl + "/user/{userId}";
    this._findUserByUsernameAndPassword =
      _apiService.flexApiGatewayUrl + "/user/login?username={username}&password={password}";
    this._createUser =
      _apiService.flexApiGatewayUrl + "/user";
  }

  get loginIn(): string{
    return this._loginIn;
  }

  get findUserById(): string{
    return this._findUserById;
  }

  get findUserByUsernameAndPassword(): string{
    return this._findUserByUsernameAndPassword;
  }

  get createUser(): string{
    return this._createUser;
  }
}
