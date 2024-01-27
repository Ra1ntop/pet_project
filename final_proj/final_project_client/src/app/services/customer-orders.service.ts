import {Injectable} from "@angular/core";
import {httpConfig} from "../app.config";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthService} from "./auth.service";
import {OrderData} from "../models/order-data";
import {ChangeOrderData} from "../models/change-order-data";

@Injectable({
  providedIn: 'root'
})
export class CustomerOrdersService {
  private _apiUrl: string = `${httpConfig.apiAdminUrl}`;


  constructor(private _http: HttpClient, private _authService: AuthService) {
  }

  loadOrders(productId: string): Observable<OrderData[]> {
    let token = this._authService.getToken();
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', `Bearer ${token}`)
    return this._http.get<OrderData[]>(`${this._apiUrl}/customer-orders/${productId}`, {headers});
  }

  changeOrderStatus(data: ChangeOrderData) {
    let token = this._authService.getToken();
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', `Bearer ${token}`)
    console.log("dawdwadawd");
    return this._http.put<string>(`${this._apiUrl}/change-order`, data, {headers});
  }

}
