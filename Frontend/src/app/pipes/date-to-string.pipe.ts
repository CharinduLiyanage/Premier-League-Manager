import {Pipe, PipeTransform} from '@angular/core';
import {MyDate} from '../entites/myDate';

@Pipe({
  name: 'dateToString'
})
export class DateToStringPipe implements PipeTransform {
  transform(date: MyDate): string {
    // Returning the date in 'YYYY-MM-DD' format.
    return date.year.toString() + '-' + date.month.toString() + '-' + date.day.toString();
  }
}
