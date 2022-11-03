import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpRequest} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Observable, of, throwError} from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders()
};


@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(
    private http: HttpClient,
  ) {
  }

  httpGet(url: any) {
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
