import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, Subscription} from "rxjs";
import {AccountService} from "../../services/account.service";
import {AccountData} from "../../models/account-data";
import {AuthService} from "../../services/auth.service";
import {Router, RouterLink} from "@angular/router";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {OrderData} from "../../models/order-data";
import {OrderService} from "../../services/order.service";

@Component({
  selector: 'app-account',
  standalone: true,
  imports: [
    AsyncPipe,
    NgIf,
    NgForOf,
    RouterLink
  ],
  templateUrl: './account.component.html',
  styleUrl: './account.component.scss'
})
export class AccountComponent implements OnInit, OnDestroy {

  private _sub$ = new Subscription();

  accountData$: Observable<AccountData> = this._accountService.loadInfo();
  orders$: Observable<OrderData[]> = this._orderService.loadOrders();

  ADMIN: string = "ADMIN";

  constructor(private _accountService: AccountService, private _authService: AuthService, private _router: Router, private _orderService: OrderService) {
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
