import {Pipe, PipeTransform} from '@angular/core';
import {Match} from '../entites/Match';
import {MyDate} from '../entites/myDate';

@Pipe({
  name: 'matchDays'
})
export class MatchDaysPipe implements PipeTransform {
  transform(matches: Match[]): MyDate[] {
    // Variable to hold the dates.
    const dates: MyDate[] = [];
    // Iterating through all the matches to get their dates.
    matches.forEach(match => {
      // Variable to check if the date is already been stored.
      let isMatchDayNew = true;
      // Iterating though the stored dates to check if the date has been stored before.
      dates.forEach(date => {
        if (match.date.day === date.day &&
          match.date.month === date.month &&
          match.date.year === date.year) {
          isMatchDayNew = false;
        }
      });
      // Adding the date to the list if the date has not been stored before.
      if (isMatchDayNew) {
        dates.push(match.date);
      }
    });
    // Sorting the Dates in the ascending order.
    dates.sort((a: MyDate, b: MyDate) => {
      return this.compareDate(a, b);
    });
    // Returning all sorted match dates.
    return dates;
  }

  /**
   * Method to compare dates.
   * @param a The Date to compare to.
   * @param b The date to compare with.
   * @private Making the method private.
   */
  private compareDate(a: MyDate, b: MyDate): number {
    // Comparing the year first.
    let compare = this.compareInt(a.year, b.year);
    // If the years are equal.
    if (compare === 0) {
      // Comparing the months.
      compare = this.compareInt(a.month, b.month);
      // If the months are equal.
      if (compare === 0) {
        // Comparing the days.
        compare = this.compareInt(a.day, b.day);
      }
    }
    // Return -1 if its less, 0 if equal, and 1 if greater.
    return compare;
  }

  /**
   * Method to compare Integers.
   * @param a The number to compare to.
   * @param b The number to compare with.
   * @private Making the method private.
   */
  private compareInt(a: number, b: number): number {
    // Initialising the variable.
    let compare = 0;
    // When the number is less.
    if (a < b) {
      compare = -1;
    }
    // When the number is higher.
    else if (a > b) {
      compare = 1;
    }
    // Return the result.
    return compare;
  }
}
