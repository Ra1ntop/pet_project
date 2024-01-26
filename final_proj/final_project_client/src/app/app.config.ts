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
  apiAdminUrl: 'http://localhost:8080/api/admin',
  apiOpenUrl: 'http://localhost:8080/api/open',
  apiOpenProductsUrl: 'http://localhost:8080/api/open/products',
  apiCustomerUrl: 'http://localhost:8080/api/customer',

}
