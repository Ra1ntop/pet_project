import {httpConfig} from "../app.config";
import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: "root"
})
export class CartService {

  private _apiUrl: string = `${httpConfig.apiCustomerUrl}/cart`;

  constructor(private _http: HttpClient, private _authService: AuthService) {
  }

  addToCart(productVariantId: number, quantity: number = 1): Observable<string> {
    let token = this._authService.getToken();
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', `Bearer ${token}`)
    let params = new HttpParams();
    params = params.set('productVariantId', productVariantId)
    params = params.set('quantity', quantity)
    return this._http.post<string>(this._apiUrl, null, {params, headers});
  }


}
