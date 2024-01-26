import {Injectable, OnDestroy, OnInit} from "@angular/core";
import {httpConfig} from "../app.config";
import {Observable, Subscription} from "rxjs";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {BlockCustomer} from "../models/block-customer";

@Injectable({
  providedIn: "root"
})
export class BlockService implements OnInit, OnDestroy {

  private _apiUrl: string = `${httpConfig.apiAdminUrl}/block-user`;
  private _subscription$ = new Subscription();

  constructor(private _http: HttpClient, private _authService: AuthService) {
  }

  blockCustomer(blockCustomer: BlockCustomer): Observable<string> {
    let token = this._authService.getToken();
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', `Bearer ${token}`)

    return this._http.put<string>(this._apiUrl, blockCustomer, {headers});
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {

  }
}
