import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";

@Injectable({
  providedIn: 'root'
})
export class ApiFileService {
  private readonly _createFile: string;

  constructor(private _apiService: ApiService) {
    this._createFile =
      _apiService.flexApiGatewayUrl + "/file?userId={userId}";
  }

  get createFile(): string{
    return this._createFile;
  }
}
