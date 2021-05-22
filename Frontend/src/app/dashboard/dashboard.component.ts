import {Component, OnInit} from '@angular/core';
import {YearService} from '../services/year.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  // Variable to hold the year of Premier League.
  year: string;

  /**
   * Constructor for DashboardComponent.
   * @param yearService Getting the YearService to handle year selection.
   */
  constructor(private yearService: YearService) {
  }

  /**
   * Initialising the DashboardComponent.
   */
  ngOnInit(): void {
    // Getting the year of the Premier League.
    this.yearService.year.subscribe((year) => {
      this.year = year.slice(1);
    });
  }

}
