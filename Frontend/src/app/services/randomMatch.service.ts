import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import {Match} from '../entites/Match';

@Injectable({
  providedIn: 'root'
})
export class RandomMatchService {

  // Root usl for Random Match.
  readonly ROOT_URL = 'http://localhost:9000/randomMatch';

  /**
   * Constructor for RandomMatchService.
   * @param http Getting http to make http requests.
   */
  constructor(private http: HttpClient) {
  }

  /**
   * Method to get a Random match.
   * @param year Year of the Premier League.
   */
  get(year: string): Observable<Match> {
    return this.http.get(this.ROOT_URL + year).pipe(
      map((res: RandomMatchResponse) => res.body),
      catchError(this.handleError)
    );
  }

  /**
   * Method to POST a random match to backend.
   * @param year Year of the Premier League.
   * @param match Random match to be added.
   */
  add(year: string, match: Match): any {
    return this.http.post(this.ROOT_URL + year, match).pipe(
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
 * The data type that the Random Match will come in.
 */
interface RandomMatchResponse {
  body: Match;
  status: boolean;
}
