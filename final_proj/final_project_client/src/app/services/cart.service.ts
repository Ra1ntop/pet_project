import {httpConfig} from "../app.config";
import {Injectable, OnDestroy, OnInit} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, Subscription} from "rxjs";
import {AuthService} from "./auth.service";
import {ProductPlp} from "../models/product-plp";
import {CartItems} from "../models/cart-items";

@Injectable({
  providedIn: "root"
})
export class CartService implements OnInit, OnDestroy {

  private _apiUrl: string = `${httpConfig.apiCustomerUrl}/cart`;
  private _subscription$ = new Subscription();

  constructor(private _http: HttpClient, private _authService: AuthService) {
  }

  ngOnInit(): void {

  }

  loadProducts(): Observable<CartItems[]> {
    let token = this._authService.getToken();
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', `Bearer ${token}`)
    return this._http.get<CartItems[]>(this._apiUrl, {headers});
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


  ngOnDestroy(): void {
    this._subscription$.unsubscribe();
  }

}
