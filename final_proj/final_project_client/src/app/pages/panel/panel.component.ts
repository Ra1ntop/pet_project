import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {Observable} from "rxjs";
import {PanelService} from "../../services/panel.service";
import {AccountData} from "../../models/account-data";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-panel',
  standalone: true,
  imports: [
    NgIf,
    AsyncPipe,
    NgForOf,
    RouterLink
  ],
  templateUrl: './panel.component.html',
  styleUrl: './panel.component.scss'
})
export class PanelComponent implements OnInit, OnDestroy {
  accounts$: Observable<AccountData[]> = this._panelService.loadProducts();

  constructor(private _router: Router, private _panelService: PanelService) {
  }

  goToCustomerOrders(id: number) {
    this._router.navigateByUrl('/customer-orders/' + id);
  }

  loadAccounts() {

  }

  ngOnInit(): void {

  }

  ngOnDestroy(): void {
  }

}
