import {Injectable, OnDestroy, OnInit} from "@angular/core";
import {httpConfig} from "../app.config";
import {Observable, Subscription} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {AccountData} from "../models/account-data";

@Injectable({
  providedIn: "root"
})
export class AccountService implements OnInit, OnDestroy {

  private _apiUrl: string = `${httpConfig.apiUrl}/account`;
  private _subscription$ = new Subscription();

  constructor(private _http: HttpClient, private _authService: AuthService) {
  }

  ngOnInit(): void {

  }

  loadInfo(): Observable<AccountData> {
    let token = this._authService.getToken();
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', `Bearer ${token}`)
    return this._http.get<AccountData>(this._apiUrl, {headers});
  }

  ngOnDestroy(): void {
    this._subscription$.unsubscribe();
  }

}
