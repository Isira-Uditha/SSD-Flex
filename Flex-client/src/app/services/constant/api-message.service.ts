import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";

@Injectable({
  providedIn: 'root'
})
export class ApiMessageService {
  private readonly _createMessage: string;
  constructor(private _apiService: ApiService) {
    this._createMessage =
      _apiService.flexApiGatewayUrl + "/message";
  }

  get createMessage(): string{
    return this._createMessage;
  }

}
