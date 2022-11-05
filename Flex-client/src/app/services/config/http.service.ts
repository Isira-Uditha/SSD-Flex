import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpRequest} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Store} from "../auth/store";
import {AUTH} from "../auth/constants";
import {throwError} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders()
};


@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(
    private http: HttpClient,
    private store: Store,
  ) {
  }

  httpGet(url: any) {
    if(!!this.store.getData(AUTH.token)){
      // @ts-ignore
      httpOptions.headers.headers.set('Authorization',"Bearer " + this.store.getData(AUTH.token))
    }
    return this.http.get(url, httpOptions)
      .pipe(
        tap(res => {
          return res;
        }),
        catchError(res => {
          return throwError(res.error);
        })
      );
  }

  httpPost(url: any, request: any) {
    if(!!this.store.getData(AUTH.token)){
      // @ts-ignore
      httpOptions.headers.headers.set('Authorization',"Bearer " + this.store.getData(AUTH.token))
    }
    return this.http.post(url, request, httpOptions).pipe(
      tap(res => {
        return res;
      }),
      catchError(res => {
        return throwError(res.error);
      })
    );
  }

  httpPut(url: any, request: any) {
    if(!!this.store.getData(AUTH.token)){
      // @ts-ignore
      httpOptions.headers.headers.set('Authorization',"Bearer " + this.store.getData(AUTH.token))
    }
    return this.http.put(url, request, httpOptions).pipe(
      tap(res => {
        return res;
      }),
      catchError(res => {
        return throwError(res.error);
      })
    );
  }


}
