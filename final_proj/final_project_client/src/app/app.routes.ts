import {Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/home/home.routes').then(r => r.HOME_ROUTES)
  },
  {
    path: 'plp',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/plp/plp.routes').then(r => r.PLP_ROUTES)
  },
];
