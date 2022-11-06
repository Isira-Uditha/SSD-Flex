import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";

@Injectable({
  providedIn: 'root'
})
export class ApiRsaService {
  private readonly _getRsaPublicKey: string;
  constructor(
    private _apiService: ApiService
  ) {
    this._getRsaPublicKey =
      _apiService.flexApiGatewayUrl + "/key?appId={appId}";
  }

  get getRsaPublicKey(): string{
    return this._getRsaPublicKey;
  }
}
