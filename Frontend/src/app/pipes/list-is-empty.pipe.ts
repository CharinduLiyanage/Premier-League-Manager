import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'listIsEmpty'
})
export class ListIsEmptyPipe implements PipeTransform {
  transform(list: any[]): boolean {
    // returning as its empty if the input is null
    if (list == null) {
      return true;
    }
    // Checking if the array is empty.
    return list.length === 0;
  }
}
