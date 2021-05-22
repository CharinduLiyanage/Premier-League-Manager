import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LeagueTableComponent} from './league-table/league-table.component';
import {HttpClientModule} from '@angular/common/http';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatchDayItemComponent} from './match-day-item/match-day-item.component';
import {MatchItemComponent} from './match-item/match-item.component';
import {MatCardModule} from '@angular/material/card';
import {MatchDaysPipe} from './pipes/match-days.pipe';
import {MatchDaysCountPipe} from './pipes/match-days-count.pipe';
import {MatchDayIndexPipe} from './pipes/match-day-index.pipe';
import {RandomMatchComponent} from './random-match/random-match.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {FormsModule} from '@angular/forms';
import {ListIsEmptyPipe} from './pipes/list-is-empty.pipe';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatchEqualsPipe} from './pipes/match-equals.pipe';
import {DateToStringPipe} from './pipes/date-to-string.pipe';

@NgModule({
  declarations: [
    AppComponent,
    LeagueTableComponent,
    MatchDayItemComponent,
    MatchItemComponent,
    MatchDaysPipe,
    MatchDaysCountPipe,
    MatchDayIndexPipe,
    ListIsEmptyPipe,
    RandomMatchComponent,
    DashboardComponent,
    MatchEqualsPipe,
    DateToStringPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatCardModule,
    FormsModule,
    MatIconModule,
    MatButtonModule,
    MatDividerModule,
    MatGridListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
