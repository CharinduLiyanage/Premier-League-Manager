import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LeagueTableComponent} from './league-table/league-table.component';
import {MatchDayItemComponent} from './match-day-item/match-day-item.component';
import {RandomMatchComponent} from './random-match/random-match.component';
import {DashboardComponent} from './dashboard/dashboard.component';

const routes: Routes = [
  {
    path: 'leagueTable',
    pathMatch: 'full',
    component: LeagueTableComponent
  },
  {
    path: 'matches',
    component: MatchDayItemComponent
  },
  {
    path: 'randomMatch',
    component: RandomMatchComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: '**',
    redirectTo: 'dashboard'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
