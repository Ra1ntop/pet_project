import {CartItems} from "./cart-items";

export interface OrderData {
  id: number,
  price: number;
  orderId: string,
  cartEntry: CartItems[];
  orderStatus: string;
  createdAt: string;
  updatedAt: string;
}
