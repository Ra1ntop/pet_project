import {Injectable, OnDestroy, OnInit} from "@angular/core";
import {httpConfig} from "../app.config";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Observable, Subscription} from "rxjs";
import {OrderData} from "../models/order-data";

@Injectable({
  providedIn: "root"
})
export class OrderService implements OnInit, OnDestroy {

  private _subscription$ = new Subscription();


  private _apiUrlOrder: string = `${httpConfig.apiCustomerUrl}/order`;
  private _apiUrlOrderCancel: string = `${httpConfig.apiCustomerUrl}/order/cancel-order`;

  constructor(private _http: HttpClient, private _authService: AuthService) {
  }

  loadOrders(): Observable<OrderData[]> {
    let token = this._authService.getToken();
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', `Bearer ${token}`)
    return this._http.get<OrderData[]>(this._apiUrlOrder, {headers});
  }

  cancelOrder(id: number): Observable<string> {
    let token = this._authService.getToken();
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', `Bearer ${token}`)
    let params = new HttpParams();
    params = params.set('orderId', id)
    return this._http.put<string>(this._apiUrlOrderCancel, null, {params, headers});
  }

  ngOnInit(): void {

  }

  ngOnDestroy(): void {
    this._subscription$.unsubscribe();
  }

}
