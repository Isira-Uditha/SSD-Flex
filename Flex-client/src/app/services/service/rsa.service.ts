import { Injectable } from '@angular/core';
import {HttpService} from "../config/http.service";
import {ApiMessageService} from "../constant/api-message.service";
import {ApiRsaService} from "../constant/api-rsa.service";

@Injectable({
  providedIn: 'root'
})
export class RsaService {

  constructor(
    private httpService: HttpService,
    private apiRsaService: ApiRsaService,
  ) { }

  getRsaPublicKey(appId: any){
    return this.httpService.httpGet(
      this.apiRsaService.getRsaPublicKey.replace('{appId}',appId),
    );
  }
}
