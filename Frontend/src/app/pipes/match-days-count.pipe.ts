import {Pipe, PipeTransform} from '@angular/core';
import {MyDate} from '../entites/myDate';

@Pipe({
  name: 'matchDaysCount'
})
export class MatchDaysCountPipe implements PipeTransform {
  transform(matchDates: MyDate[]): number {
    // Returning the length of the array.
    return matchDates.length;
  }
}
