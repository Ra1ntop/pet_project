import {Routes} from "@angular/router";
import {CustomerOrdersComponent} from "./customer-orders.component";

export const CUSTOMER_ORDERS_ROUTES: Routes = [
  {
    path: ':id',
    component: CustomerOrdersComponent
  }
]
