import {Injectable, OnDestroy, OnInit} from "@angular/core";
import {httpConfig} from "../app.config";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";
import {CartItems} from "../models/cart-items";
import {AccountData} from "../models/account-data";

@Injectable({
  providedIn: "root"
})
export class PanelService implements OnInit, OnDestroy {

  private _apiUrl: string = `${httpConfig.apiUrl}/admin/panel`;


  constructor(private _http: HttpClient, private _authService: AuthService) {
  }

  loadProducts(): Observable<AccountData[]> {
    let token = this._authService.getToken();
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', `Bearer ${token}`)
    return this._http.get<AccountData[]>(this._apiUrl, {headers});
  }

  ngOnInit(): void {
    throw new Error("Method not implemented.");
  }

  ngOnDestroy(): void {
    throw new Error("Method not implemented.");
  }

}
