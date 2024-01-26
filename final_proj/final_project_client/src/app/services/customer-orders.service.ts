import {Injectable} from "@angular/core";
import {httpConfig} from "../app.config";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthService} from "./auth.service";
import {OrderData} from "../models/order-data";

@Injectable({
  providedIn: 'root'
})
export class CustomerOrdersService {
  private _apiUrl: string = `${httpConfig.apiAdminUrl}/customer-orders`;


  constructor(private _http: HttpClient, private _authService: AuthService) {
  }

  loadOrders(productId: string): Observable<OrderData[]> {
    let token = this._authService.getToken();
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', `Bearer ${token}`)
    return this._http.get<OrderData[]>(`${this._apiUrl}/${productId}`, {headers});
  }

}
