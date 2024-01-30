import {Component, OnDestroy} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {NgIf} from "@angular/common";
import {RegisterData} from "../../models/register-data";
import {Subscription} from "rxjs";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf,
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnDestroy {
  private _sub = new Subscription();

  form: FormGroup = this._fb.group({
    email: new FormControl(null, [Validators.required, Validators.email]),
    password: new FormControl(null, [Validators.required, Validators.min(5)]),
    checkBox: new FormControl(null, [Validators.requiredTrue]),
  });

  constructor(private _fb: FormBuilder, private _auth: AuthService, private _router: Router) {
  }

  login(): void {
    if (this.form.valid) {
      console.log(this.form);
      let data: RegisterData = {...this.form.value};
      console.log(data);
      this._sub.add(
        this._auth.login(data).subscribe(
          (auth) => {
            this._auth.setToken(JSON.stringify(auth));
            this._router.navigateByUrl('/home')
          },
          (err) => {
            this._auth.setLoginIn(false);
            localStorage.removeItem('token');
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
