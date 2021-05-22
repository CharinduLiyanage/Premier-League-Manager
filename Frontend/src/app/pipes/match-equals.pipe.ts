import {Pipe, PipeTransform} from '@angular/core';
import {MyDate} from '../entites/myDate';

@Pipe({
  name: 'matchEquals'
})
export class MatchEqualsPipe implements PipeTransform {
  transform(thisDate: MyDate, thatDate: MyDate): boolean {
    // Checking if the year, month, and day equals one another individually.
    return thatDate.year === thatDate.year && thisDate.month === thatDate.month && thisDate.day === thatDate.day;
  }
}
