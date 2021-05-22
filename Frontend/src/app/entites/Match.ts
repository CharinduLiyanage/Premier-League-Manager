import {FootballClub} from './footballClub';
import {MyDate} from './myDate';

/**
 * Data Type Match to store matches.
 */
export interface Match {
  scoreHome: number;
  scoreAway: number;
  date: MyDate;
  clubHome: FootballClub;
  clubAway: FootballClub;
  draw: boolean;
}
