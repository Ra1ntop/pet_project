import {Component, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, Observable, Subscription} from "rxjs";
import {Router} from "@angular/router";
import {PdpService} from "../../../services/pdp.service";
import {CustomerOrdersService} from "../../../services/customer-orders.service";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {OrderData} from "../../../models/order-data";
import {OrderService} from "../../../services/order.service";

@Component({
  selector: 'app-customer-orders',
  standalone: true,
  imports: [
    AsyncPipe,
    NgForOf,
    NgIf
  ],
  templateUrl: './customer-orders.component.html',
  styleUrl: './customer-orders.component.scss'
})
export class CustomerOrdersComponent implements OnInit, OnDestroy {

  private _subscription = new Subscription();
  orders$: Observable<OrderData[]> = this._orderService.loadOrders();

  private _productPdpSub$ =
    new BehaviorSubject<OrderData[] | undefined>(undefined);
  readonly productPdp$: Observable<OrderData[] | undefined> = this._productPdpSub$.asObservable();

  showModal = false;
  orderId: string = "";
  Pending: string = "Pending";
  Canceled: string = "Canceled";
  ordersNull: string = "";


  openModal(id: string) {
    this.orderId = id;
    this.showModal = true;
  }

  changeOrderStatus(id: number) {

  }

  closeModal() {
    this.orderId = "";
    this.showModal = false;
  }


  constructor(
    private _router: Router,
    private _pdpService: PdpService,
    private _customerOrdersService: CustomerOrdersService,
    private _orderService: OrderService) {
  }

  ngOnInit(): void {
    let url = this._router.routerState.snapshot.url;
    let customerId = url.slice(17);
    if (customerId) {
      this._subscription.add(
        this._customerOrdersService.loadOrders(customerId)
          .subscribe(product => {
            console.log('product ', product)
            if (product.length > 0) {
              this._productPdpSub$.next(product)
            } else {
              this.ordersNull = "User didn't create any order";
            }
          })
      );
    }

  }

  ngOnDestroy(): void {
    this._subscription.unsubscribe();
  }
}
