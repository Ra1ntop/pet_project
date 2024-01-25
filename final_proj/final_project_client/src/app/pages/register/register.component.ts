import {Component} from '@angular/core';
import {NgIf} from "@angular/common";
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {RegisterData} from "../../models/register-data";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  private _sub = new Subscription();

  form: FormGroup = this._fb.group({
    email: new FormControl(null, [Validators.required, Validators.email]),
    firstName: new FormControl(null, [Validators.required, Validators.min(3)]),
    lastName: new FormControl(null, [Validators.required, Validators.min(3)]),
    age: new FormControl(null, [Validators.required, Validators.min(18)]),
    password: new FormControl(null, [Validators.required, Validators.min(5)]),
    country: new FormControl(null, [Validators.required, Validators.min(3)]),
    checkBox: new FormControl(null, [Validators.requiredTrue]),
  });

  constructor(private _fb: FormBuilder, private _auth: AuthService, private _router: Router) {
  }

  register(): void {
    if (this.form.valid) {
      console.log(this.form);
      let data: RegisterData = {...this.form.value};
      console.log(data);
      this._sub.add(
        this._auth.register(data).subscribe(
          (auth) => {
            this._auth.setToken(JSON.stringify(auth));
            this._router.navigateByUrl('/cart')
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
