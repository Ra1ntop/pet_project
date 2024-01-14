import {Component} from '@angular/core';
import {RouterLink, RouterOutlet} from "@angular/router";
import {MiddleContentProComponent} from "./middle-content/middle-content-pro/middle-content-pro.component";
import {MiddleContentComponent} from "./middle-content/middle-content/middle-content.component";
import {MiddleContentEcoComponent} from "./middle-content/middle-content-eco/middle-content-eco.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    RouterLink,
    RouterOutlet,
    MiddleContentProComponent,
    MiddleContentComponent,
    MiddleContentEcoComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {


}
