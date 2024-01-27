import {Component, OnDestroy, OnInit} from '@angular/core';
import {BehaviorSubject, Observable, Subscription} from "rxjs";
import {Router} from "@angular/router";
import {CustomerOrdersService} from "../../../services/customer-orders.service";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {OrderData} from "../../../models/order-data";
import {OrderService} from "../../../services/order.service";
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ChangeOrderData} from "../../../models/change-order-data";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-customer-orders',
  standalone: true,
  imports: [
    AsyncPipe,
    NgForOf,
    NgIf,
    ReactiveFormsModule,
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

  form: FormGroup = this._fb.group({
    status: new FormControl(null, [Validators.required]),
    checkBox: new FormControl(null, [Validators.required]),
  })

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
    console.log(this.form.value)
    if (this.form.valid) {
      let data: ChangeOrderData = {...this.form.value}
      data.id = id;
      this._subscription.add(
        this._customerOrdersService.changeOrderStatus(data).subscribe(
          () => {
            this.closeModal();
            location.reload();
          },
          (erro) => {
            console.log(erro);
          }
        )
      );
    }
  }

  closeModal() {
    this.orderId = "";
    this.showModal = false;
  }


  constructor(
    private _router: Router,
    private _fb: FormBuilder,
    private _customerOrdersService: CustomerOrdersService,
    private _orderService: OrderService,
    private _authService: AuthService) {
  }

  ngOnInit(): void {
    this._subscription.add(
      this._authService.isLoginIn()
        .subscribe(isLoginIn => {
          if (!isLoginIn) {
            this._router.navigateByUrl('/login');
          }
        })
    );
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
