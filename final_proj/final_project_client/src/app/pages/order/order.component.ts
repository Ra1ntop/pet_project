import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, Subscription} from "rxjs";
import {AuthService} from "../../services/auth.service";
import {Router, RouterLink} from "@angular/router";
import {OrderService} from "../../services/order.service";
import {OrderData} from "../../models/order-data";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [
    AsyncPipe,
    NgIf,
    NgForOf,
    RouterLink
  ],
  templateUrl: './order.component.html',
  styleUrl: './order.component.scss'
})
export class OrderComponent implements OnInit, OnDestroy {
  private _sub$ = new Subscription();
  orders$: Observable<OrderData[]> = this._orderService.loadOrders();

  constructor(private _authService: AuthService, private _router: Router, private _orderService: OrderService) {
  }

  showModal = false;
  orderId: string = "";
  Pending: string = "Pending";
  Canceled: string = "Canceled";

  openModal(id: string) {
    this.orderId = id;
    this.showModal = true;
  }

  cancelOrder(id: number) {
    this._sub$.add(
      this._orderService.cancelOrder(id)
        .subscribe(() => {
          this.orders$ = this._orderService.loadOrders();
        })
    );
  }

  closeModal() {
    this.orderId = "";
    this.showModal = false;
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
