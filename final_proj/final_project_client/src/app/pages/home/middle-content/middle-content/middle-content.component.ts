import {Component} from '@angular/core';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-middle-content',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './middle-content.component.html',
  styleUrl: './middle-content.component.scss'
})
export class MiddleContentComponent {

}
