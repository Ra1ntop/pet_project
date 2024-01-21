import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent implements OnInit, OnDestroy {

  private _sub$ = new Subscription();

  constructor(private _authService: AuthService, private _router: Router) {
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
