import {Component, Input} from '@angular/core';
import {Match} from '../entites/Match';

@Component({
  selector: 'app-match-item',
  templateUrl: './match-item.component.html',
  styleUrls: ['./match-item.component.css']
})
export class MatchItemComponent {
  // Taking the match this item will display as an input.
  @Input() match: Match;
}
