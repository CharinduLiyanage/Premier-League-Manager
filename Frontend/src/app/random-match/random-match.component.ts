import {Component, OnInit} from '@angular/core';
import {Match} from '../entites/Match';
import {RandomMatchService} from '../services/randomMatch.service';
import {YearService} from '../services/year.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-random-match',
  templateUrl: './random-match.component.html',
  styleUrls: ['./random-match.component.css']
})
export class RandomMatchComponent implements OnInit {

  // Variable to store the random match.
  randomMatch: Match = null;

  /**
   * Constructor for RandomMatchComponent.
   * @param randomMatchService Getting the randomMatchService to GET and POST random match.
   * @param yearService Getting the YearService to handle year selection.
   * @param router to handle url redirecting.
   */
  constructor(private randomMatchService: RandomMatchService, private yearService: YearService, private router: Router) {
  }

  /**
   * Initialising the RandomMatchComponent.
   */
  ngOnInit(): void {
    this.getNewRandomMatch();
  }

  /**
   * Getting a new random match from the backend.
   */
  getNewRandomMatch(): void {
    // Getting the year of the Premier League.
    this.yearService.year.subscribe((year) => {
      // Getting a random match.
      this.randomMatchService.get(year).subscribe((res) => {
        this.randomMatch = res;
      });
    });
  }

  /**
   * POST the random match to the backend.
   */
  postRandomMatch(): void {
    // Getting the year of the Premier League.
    this.yearService.year.subscribe((year) => {
      // Sending a POST request to the backend.
      this.randomMatchService.add(year, this.randomMatch).subscribe((res) => {
        this.router.navigateByUrl('dashboard');
      });
    });
  }
}
