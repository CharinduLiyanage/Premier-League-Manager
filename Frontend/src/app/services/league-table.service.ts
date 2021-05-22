import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {FootballClub} from '../entites/footballClub';
import {catchError, map} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LeagueTableService {

  // Root usl for league Table.
  readonly ROOT_URL = 'http://localhost:9000/leagueTable';

  /**
   * Constructor for LeagueTableService.
   * @param http Getting http to make http requests.
   */
  constructor(private http: HttpClient) {
  }

  /**
   * Method to get League Table data.
   * @param year Year of the Premier League.
   */
  get(year: string): Observable<FootballClub[]> {
    return this.http.get<LeagueTableResponse>(this.ROOT_URL + year).pipe(
      map((res: LeagueTableResponse) => res.body),
      catchError(this.handleError)
    );
  }

  /**
   * Method to Handle errors of http requests.
   * @param error Error that needs to be handled.
   * @private Making the method private.
   */
  private handleError(error: HttpErrorResponse): Observable<never> {
    console.error(error.message);
    return throwError('A data error occurred, please try again.');
  }
}

/**
 * The data type that the league table will come in.
 */
interface LeagueTableResponse {
  body: FootballClub[];
  status: boolean;
}
