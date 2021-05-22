import {Component, OnInit, SimpleChanges} from '@angular/core';
import {Subject} from 'rxjs';
import {Match} from '../entites/Match';
import {MyDate} from '../entites/myDate';
import {MatchesService} from '../services/matches.service';
import {YearService} from '../services/year.service';

@Component({
  selector: 'app-match-day-item',
  templateUrl: './match-day-item.component.html',
  styleUrls: ['./match-day-item.component.css']
})
export class MatchDayItemComponent implements OnInit {
  // Variable to store the date.
  date: string;
  // Variable to Store the matches that displays.
  matches: Match[];
  // Variable to store all the matches.
  matchesMaster: Match[] = [];

  /**
   * Constructor for MatchDayItemComponent.
   * @param matchesService Getting the matchesService to GET matches.
   * @param yearService Getting the YearService to handle year selection.
   */
  constructor(private matchesService: MatchesService, private yearService: YearService) {
  }

  /**
   * Initialising the MatchDayItemComponent.
   */
  ngOnInit(): void {
    // Getting the year of the Premier League.
    this.yearService.year.subscribe((year) => {
      // Getting the played matches.
      this.matchesService.get(year).subscribe((res) => {
        this.matches = this.matchesMaster = res;
      });
    });
  }

  /**
   * Method to search matches on a day.
   */
  search(): void {
    // When a date is not selected.
    if (this.date == null || this.date === '') {
      // Showing all the played matches.
      this.reset();
    }
    // When a date is selected.
    else {
      // Splitting the String Output.
      const dateString = this.date.split('-');

      // Generating a MyDate object.
      const date: MyDate = {
        year: Number(dateString[0]),
        month: Number(dateString[1]),
        day: Number(dateString[2])
      };

      // Making the match list clear to add items again.
      this.matches = [];
      // Iterating all played matches.
      this.matchesMaster.forEach(match => {
        // Adding matches to the list that displays if tht match is on the same day as the searched date.
        if (match.date.year === date.year &&
          match.date.month === date.month &&
          match.date.day === date.day) {
          this.matches.push(match);
        }
      });
    }
  }

  /**
   * Method to reset all searched matches.
   */
  reset(): void {
    this.matches = this.matchesMaster;
    this.date = '';
  }
}

