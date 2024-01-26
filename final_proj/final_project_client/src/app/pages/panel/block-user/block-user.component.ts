import {Component, OnDestroy} from '@angular/core';
import {Subscription} from "rxjs";
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {RegisterData} from "../../../models/register-data";
import {NgIf} from "@angular/common";
import {BlockService} from "../../../services/block.service";
import {BlockCustomer} from "../../../models/block-customer";

@Component({
  selector: 'app-block-user',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './block-user.component.html',
  styleUrl: './block-user.component.scss'
})
export class BlockUserComponent implements OnDestroy {
  private _sub = new Subscription();

  form: FormGroup = this._fb.group({
    customerMail: new FormControl(null, [Validators.required, Validators.email]),
    reason: new FormControl(null, [Validators.required, Validators.minLength(50)]),
    checkBox: new FormControl(null, [Validators.requiredTrue]),
  });

  constructor(private _fb: FormBuilder, private _router: Router, private _blockService: BlockService) {
  }

  blockCustomer(): void {
    if (this.form.valid) {
      console.log(this.form);
      let data: BlockCustomer = {...this.form.value};
      console.log(data);
      this._sub.add(
        this._blockService.blockCustomer(data).subscribe(
          (auth) => {
            this._router.navigateByUrl('/panel')
          },
          (err) => {
            console.log('err', err);
          }
        )
      );
    }
  }

  ngOnDestroy(): void {
    this._sub.unsubscribe();
  }

}
