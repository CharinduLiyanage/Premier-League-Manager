import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort, Sort} from '@angular/material/sort';
import {MatTable} from '@angular/material/table';
import {LeagueTableDataSource} from './league-table-datasource';
import {FootballClub} from '../entites/footballClub';
import {LeagueTableService} from '../services/league-table.service';
import {YearService} from '../services/year.service';

@Component({
  selector: 'app-league-table',
  templateUrl: './league-table.component.html',
  styleUrls: ['./league-table.component.css']
})
export class LeagueTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<FootballClub>;
  dataSource: LeagueTableDataSource;
  // Variable to store league table.
  data: FootballClub[] = [];
  // Columns displayed in the table.
  displayedColumns = [
    'name',
    'location',
    'matchesPlayed',
    'wins',
    'draws',
    'losses',
    'goalsFor',
    'goalsAgainst',
    'goalDifference',
    'points'
  ];

  /**
   * Constructor for LeagueTableComponent.
   * @param leagueTableService Getting the leagueTableService to GET league table.
   * @param yearService Getting the YearService to handle year selection.
   */
  constructor(private leagueTableService: LeagueTableService, private yearService: YearService) {
  }

  /**
   * Initialising the RandomMatchComponent.
   */
  ngOnInit(): void {
    // Getting the year of the Premier League.
    this.yearService.year.subscribe((year) => {
      // Getting league table.
      this.leagueTableService.get(year).subscribe((res) => {
        // Initialising table dataSource.
        this.dataSource = new LeagueTableDataSource();
        // Updating data source data with GET data.
        this.dataSource.data = res;
        // Updating variable storing league table.
        this.data = res;
        // Setting the sort for data source.
        this.dataSource.sort = this.sort;
        // Setting the paginator for data source.
        this.dataSource.paginator = this.paginator;
        // Setting the table data source to the data source.
        this.table.dataSource = this.dataSource;
      });
    });
  }

  /**
   * Method to sort the table using the buttons.
   * @param column The column needed to be sorted by.
   */
  sortChange(column: string): void {
    // Changing the data source sort.
    const sortState: Sort = {active: column, direction: 'desc'};
    this.sort.active = sortState.active;
    this.sort.direction = sortState.direction;
    this.sort.sortChange.emit(sortState);
    this.dataSource.sort = this.sort;

  }
}
