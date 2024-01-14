import {Component, OnDestroy, OnInit} from '@angular/core';
import {PdpService} from "../../services/pdp.service";
import {Router} from "@angular/router";
import {ProductPdp} from "../../models/product-pdp";
import {BehaviorSubject, Observable, of} from "rxjs";
import {AsyncPipe, JsonPipe, NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-pdp',
  standalone: true,
  imports: [
    NgIf,
    AsyncPipe,
    JsonPipe,
    NgForOf
  ],
  templateUrl: './pdp.component.html',
  styleUrl: './pdp.component.scss'
})
export class PdpComponent implements OnInit, OnDestroy {

  private _productPdpSub$ =
    new BehaviorSubject<ProductPdp | undefined>(undefined);

  productPdp$: Observable<ProductPdp | undefined> = this._productPdpSub$.asObservable();

  constructor(private _router: Router, private _pdpService: PdpService) {
  }

  ngOnInit(): void {
    let url = this._router.routerState.snapshot.url;
    let productId = url.slice(5);
    if (productId) {
      this._pdpService.loadProductVariants(productId)
        .subscribe(product => {
          console.log('product ', product)
          if (product) {
            this._productPdpSub$.next(product)
          }
        })

    }
  }

  ngOnDestroy(): void {

  }
}

