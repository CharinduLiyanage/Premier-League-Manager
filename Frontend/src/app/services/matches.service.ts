import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';
import {Match} from '../entites/Match';
import {Observable, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MatchesService {

  // Root usl for Matches.
  readonly ROOT_URL = 'http://localhost:9000/matches';

  /**
   * Constructor for MatchesService.
   * @param http Getting http to make http requests.
   */
  constructor(private http: HttpClient) {
  }

  /**
   * Method to get played matches data.
   * @param year Year of the Premier League.
   */
  get(year: string): Observable<Match[]> {
    return this.http.get<MatchesResponse>(this.ROOT_URL + year).pipe(
      map((res: MatchesResponse) => res.body),
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
 * The data type that the played matches will come in.
 */
interface MatchesResponse {
  body: Match[];
  status: boolean;
}
