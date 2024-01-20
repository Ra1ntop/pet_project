import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Router, RouterLink, RouterOutlet} from '@angular/router';
import {AuthService} from "./services/auth.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'final_project_client';

  public isLoginIn$: Observable<boolean> = this._authService.isLoginIn();


  constructor(private _authService: AuthService, private _router: Router) {
  }

  login(): void {
    this._router.navigateByUrl('/login');
  }

  logout(): void {
    this._authService.deleteToken();
  }
}
