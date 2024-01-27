import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {NgForOf, NgIf} from "@angular/common";
import {BlockService} from "../../../services/block.service";
import {BlockCustomer} from "../../../models/block-customer";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-block-user',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    NgForOf
  ],
  templateUrl: './block-user.component.html',
  styleUrl: './block-user.component.scss'
})
export class BlockUserComponent implements OnDestroy, OnInit {
  private _sub = new Subscription();

  form: FormGroup = this._fb.group({
    customerMail: new FormControl(null, [Validators.required, Validators.email]),
    reason: new FormControl(null, [Validators.required, Validators.minLength(50)]),
    checkBox: new FormControl(null, [Validators.requiredTrue]),
  });

  constructor(private _fb: FormBuilder, private _router: Router, private _blockService: BlockService, private _authService: AuthService) {
  }

  ngOnInit(): void {
    this._sub.add(
      this._authService.isLoginIn()
        .subscribe(isLoginIn => {
          if (!isLoginIn) {
            this._router.navigateByUrl('/login');
          }
        })
    );
  }



  accountHaveBlocked: boolean = false;
  errorHtml: string = "";

  closeModal() {
    this.accountHaveBlocked = false;
  }

  blockCustomer(): void {
    if (this.form.valid) {
      console.log(this.form);
      let data: BlockCustomer = {...this.form.value};
      console.log(data);
      this._sub.add(
        this._blockService.blockCustomer(data).subscribe(
          (auth) => {
          },
          (err) => {
            if (err.error == "The account has already been blocked") {
              console.log('err', err.error);
              this.accountHaveBlocked = true;
              this.errorHtml = err.error;
            } else {
              console.log('err', err);
              this.accountHaveBlocked = true;

              this.errorHtml = err.error;

            }
          }
        )
      );
    }
  }

  ngOnDestroy(): void {
    this._sub.unsubscribe();
  }

}
