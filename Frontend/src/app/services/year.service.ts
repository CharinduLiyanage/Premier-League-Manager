import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class YearService {
  // Checking the BehaviourChange in year dropdown.
  year: BehaviorSubject<string> = new BehaviorSubject('/2020');
}
