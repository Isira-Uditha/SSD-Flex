import { Injectable } from '@angular/core';
import {HttpService} from "../config/http.service";
import {ApiUserService} from "../constant/api-user.service";
import {ApiFileService} from "../constant/api-file.service";

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(
    private httpService: HttpService,
    private apiFileService: ApiFileService,
  ) { }

  createFile(file: any,userId:any){
    return this.httpService.httpPost(
      this.apiFileService.createFile.replace('{userId}',userId.toString()),file
    );
  }
}
