import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from 'rxjs/index';
import {ErrorObservable} from 'rxjs-compat/observable/ErrorObservable';
import {CommonService} from "../common/common.service";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/observable/fromPromise';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/retry';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private httpClient: HttpClient,
              private commons: CommonService,
              private router: Router) {
  }

  public get(conf: any, data?: Object): Observable<Object> {
    return this.request(conf.url, {method: 'GET'}, data, conf.headerParam)
      .map(response => {
        return this.responseHandler(response);
      });
  }

  public post(conf: any, data?: Object): Observable<Object> {
    return this.request(conf.url, {method: 'POST'}, data, conf.headerParam)
      .map(response => {
        return this.responseHandler(response);
      });
  }

  private request(url: string, options: any, data?: Object, headerParams?: any): Observable<any> {
    options.headers = {};
    let showToaster = false;
    let excludeList: any = [];

    if (!!headerParams) {
      if (headerParams['showLoading']) {
        this.commons.showLoading();
      }
      if (headerParams['showToast'] && headerParams['showToast'] === true) {
        showToaster = true;
      }
      const excludeErrorHeader = headerParams['excludeError'];
      if (excludeErrorHeader) {
        excludeList = excludeErrorHeader;
      }
    }

    if (headerParams.isFileUpload) {
      options.body = data;
    } else if (headerParams.isFileDownload) {
      options['responseType'] = 'blob';
    } else {
      if (
        options.method === 'POST' ||
        options.method === 'DELETE') {
        options.headers['Content-Type'] = 'application/json';
      }

      if (data) {
        options.body = JSON.stringify(data);
      }
    }

    options.withCredentials = !headerParams.skipAuth;

    // console.log(options.method);
    // console.log(url);
    // console.log(options);

    return this.httpClient.request(options.method, url, options).catch(error => {
      this.handleError(error, showToaster, excludeList);
      return ErrorObservable.create('error');
    });
  }

  private handleError(error: any, showToaster: any, excludeList: any): void {
    // if (showToaster) {
    if (error.status === 401 && excludeList.indexOf(401) == -1) {
      alert("401 Error");
    } else if (error.status === 403 && excludeList.indexOf(403) == -1) {
      alert("Your session is expired");
      // this.router.navigate(['/auth/login']);
    } else if (error.status === 500 && excludeList.indexOf(500) == -1) {
      alert("Please contact system administrator");
    } else if ((error.status === 504 || error.status === 404) && excludeList.indexOf(504) === -1 && excludeList.indexOf(404) === -1) {
      alert("Not found");
      // this.router.navigate(['/auth/login']);
    }
    // }
    // this.commons.resetLoading();
    // this.commons.onApiError();
  }

  private responseHandler(response: any): any {
    this.commons.hideLoading();

    if (response.status == 'FAILED' || response.status == 'PARTIAL_SUCCESS') {
      this.commons.resetLoading();
      alert("Not found");
      this.commons.onApiError();
      throw response;
    } else {

    }
    if (!response.result) {
      return response;
    }
    return response.result;
  }
}
