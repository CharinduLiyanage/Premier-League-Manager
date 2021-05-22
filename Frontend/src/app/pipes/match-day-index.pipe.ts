import {Pipe, PipeTransform} from '@angular/core';
import {MyDate} from '../entites/myDate';

@Pipe({
  name: 'matchDaysIndex'
})
export class MatchDayIndexPipe implements PipeTransform {
  transform(matchDates: MyDate[], theDate: MyDate): number {
    // Iterating through the array to check which index is equal to the given date.
    for (let i = 0; i < matchDates.length; i++) {
      // Checking year, month, and day separately.
      if (matchDates[i].year === theDate.year &&
        matchDates[i].month === theDate.month &&
        matchDates[i].day === theDate.day) {
        return (i + 1);
      }
    }
  }
}
