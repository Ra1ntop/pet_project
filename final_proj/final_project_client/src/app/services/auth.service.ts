import {Injectable} from "@angular/core";
import {httpConfig} from "../app.config";
import {HttpClient} from "@angular/common/http";
import {RegisterData} from "../models/register-data";
import {BehaviorSubject, map, Observable, tap} from "rxjs";
import {AuthData} from "../models/auth-data";

@Injectable({
  providedIn: "root"
})
export class AuthService {

  private _isLoginInSub$ = new BehaviorSubject<boolean>(false);
  private _apiUrl: string = `${httpConfig.apiUrl}/auth`;

  constructor(private _http: HttpClient) {
  }

  login(data: RegisterData): Observable<AuthData> {
    return this._http.post<AuthData>(`${this._apiUrl}/login`, data);
  }

  register(data: RegisterData): Observable<AuthData> {
    return this._http.post<AuthData>(`${this._apiUrl}/register`, data);
  }

  setToken(Token: string): void {
    localStorage.removeItem('token');
    this.setLoginIn(false);
    localStorage.setItem('token', Token);
    this.setLoginIn(true);
  }

  deleteToken(): void {
    localStorage.removeItem('token');
    this.setLoginIn(false);
  }

  setLoginIn(isLoginIn: boolean) {
    this._isLoginInSub$.next(isLoginIn);
  }

  isLoginIn(): Observable<boolean> {
    return this._isLoginInSub$.asObservable()
      .pipe(
        map(isLoginIn => {
          if (!isLoginIn) {
            let token = localStorage.getItem('token');
            if (token) {
              let authData: AuthData = JSON.parse(token);
              return !!authData.token;
            } else {
              return false;
            }
          }
          return isLoginIn;
        })
      );
  }


}
