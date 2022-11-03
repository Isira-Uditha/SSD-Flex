import { Injectable } from '@angular/core';
import {HttpService} from "../config/http.service";
import {ApiUserService} from "../constant/api-user.service";
import {ApiMessageService} from "../constant/api-message.service";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(
    private httpService: HttpService,
    private apiMessageService: ApiMessageService,
  ){ }

  createMessage(body: any){
    return this.httpService.httpPost(
      this.apiMessageService.createMessage,body
    );
  }
}
