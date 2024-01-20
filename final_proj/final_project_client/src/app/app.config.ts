import {ApplicationConfig} from '@angular/core';
import {provideRouter} from '@angular/router';
import {provideHttpClient} from "@angular/common/http";

import {routes} from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(),
  ]
};
export const httpConfig = {
  apiUrl: 'http://localhost:8080/api',
  apiOpenUrl: 'http://localhost:8080/api/opne',
  apiOpenProductsUrl: 'http://localhost:8080/api/open/products',

}
