import { Component, OnDestroy, OnInit} from '@angular/core';
import { PdpService } from "../../services/pdp.service";
import { Router } from "@angular/router";
import { ProductPdp } from "../../models/product-pdp";
import { PriceVariant } from "../../models/priceVariant";
import { BehaviorSubject, Observable, Subscription, filter, map, switchMap } from "rxjs";
import { AsyncPipe, JsonPipe, NgForOf, NgIf } from "@angular/common";
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {AuthService} from "../../services/auth.service";

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

  private _subscription = new Subscription();


  private _productPdpSub$ =
    new BehaviorSubject<ProductPdp | undefined>(undefined);

  private _pricepSub$ =
    new BehaviorSubject<PriceVariant | undefined>(undefined);

  ssdSet: Set<number> = new Set<number>();
  colorSet: Set<string> = new Set<string>();

  readonly productPdp$: Observable<ProductPdp | undefined> = this._productPdpSub$.asObservable();
  readonly price$: Observable<PriceVariant | undefined> = this._pricepSub$.asObservable();
  readonly isLoginIn$: Observable<boolean> = this._authService.isLoginIn();

  form: FormGroup = this._fb.group({
    ssd: new FormControl(null, [Validators.required]),
    color: new FormControl(null, [Validators.required]),
  })

  constructor(private _router: Router, private _fb: FormBuilder, private _pdpService: PdpService, private _authService: AuthService) {
  }

  ngOnInit(): void {
    let url = this._router.routerState.snapshot.url;
    let productId = url.slice(5);
    if (productId) {
      this._subscription.add(
        this._pdpService.loadProductVariants(productId)
          .subscribe(product => {
            console.log('product ', product)
            if (product) {
              product.variantDtos.forEach(variant =>{
                this.ssdSet.add(variant.ssd);
              })
              this._productPdpSub$.next(product)
            }
          })
      );
    }
    this._subscription.add(
      this.form.valueChanges
        .pipe(
          filter(value => value.ssd),
          switchMap(value => {
            return this.productPdp$
              .pipe(
                map(product => {
                  console.log('value', value);
                  let variants = product?.variantDtos;
                  variants?.forEach(variant=>{
                    if (variant.ssd === value.ssd){
                      console.log('variant', variant)
                      this.colorSet.add(variant.productColor);
                    }
                  })
                  if (variants){
                    for (let i = 0; i < variants?.length; i++) {
                      if(variants[i].ssd === value.ssd && variants[i].productColor === value.color){
                        this._pricepSub$.next({
                          productId: variants[i].id,
                          price: variants[i].price
                        });
                        break;
                      }else {
                        this._pricepSub$.next(undefined);
                      }
                    }
                  }
                  return value;
                }),

              )
          })
        )
        .subscribe()
    );
  }
  addSsd(ssd: number):void {
    this.colorSet.clear();
    this.form.controls['ssd'].setValue(ssd);
  }

  addColor(color: string):void{
    this.form.controls['color'].setValue(color);
  }
  shopNow(productId: number | undefined){
    console.log(productId);
  }
  ngOnDestroy(): void {
    this._subscription.unsubscribe();
    this._pricepSub$.complete();
    this._productPdpSub$.complete();
  }
}

