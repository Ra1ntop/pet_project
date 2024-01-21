import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {BehaviorSubject, config, Observable, Subscription} from "rxjs";
import {Router} from "@angular/router";
import {CartService} from "../../services/cart.service";
import {ProductPdp} from "../../models/product-pdp";
import {ProductPlp} from "../../models/product-plp";
import {AsyncPipe, NgIf} from "@angular/common";

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    AsyncPipe,
    NgIf
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent implements OnInit, OnDestroy {

  private _sub$ = new Subscription();


  constructor(private _authService: AuthService, private _router: Router, private _cartService: CartService) {
  }


  cartItems: { name: string; price: number }[] = [];

  addItemToCart(item: { name: string; price: number }) {
    this.cartItems.push(item);
  }

  getTotal() {
    return this.cartItems.reduce((total, item) => total + item.price, 0);
  }

  ngOnInit(): void {
    this._sub$.add(
      this._authService.isLoginIn()
        .subscribe(isLoginIn => {
          if (!isLoginIn) {
            this._router.navigateByUrl('/login');
          }
        })
    );


  }

  ngOnDestroy(): void {
    this._sub$.unsubscribe();
  }


  protected readonly config = config;
}
