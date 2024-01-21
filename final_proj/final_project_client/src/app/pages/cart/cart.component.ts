import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Observable, Subscription} from "rxjs";
import {Router} from "@angular/router";
import {CartService} from "../../services/cart.service";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {CartItems} from "../../models/cart-items";

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    AsyncPipe,
    NgIf,
    NgForOf
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent implements OnInit, OnDestroy {

  private _sub$ = new Subscription();
  products$: Observable<CartItems[]> = this._cartService.loadProducts();


  constructor(private _authService: AuthService, private _router: Router, private _cartService: CartService) {
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

}
