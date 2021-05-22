import {Component, OnInit} from '@angular/core';
import {YearService} from './services/year.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Premier League Manager';  // Title for the Header.
  years: number[] = [];  // An array to hold all the years.
  selectedYear = '/2020';  // The default selected year.

  /**
   * Constructor for AppComponent.
   * @param yearService Getting the YearService to handle year selection.
   */
  constructor(private yearService: YearService) {
  }

  /**
   * Initialising the AppComponent.
   */
  ngOnInit(): void {
    // Making all the years.
    this.generateYears();
  }

  /**
   * Method to generate the years.
   */
  generateYears(): void {
    for (let i = 1992; i < 3000; i++) {
      this.years.push(i);
    }
  }

  /**
   * Method to detect year change by user.
   */
  yearChange(): void {
    this.yearService.year.next(this.selectedYear);
  }
}
