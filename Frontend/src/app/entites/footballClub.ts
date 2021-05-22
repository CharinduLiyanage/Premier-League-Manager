/**
 * Data Type FootballClub to store football clubs.
 */
export interface FootballClub {
  name: string;
  location: string;
  matchesPlayed: number;
  wins: number;
  draws: number;
  losses: number;
  goalsFor: number;
  goalsAgainst: number;
  goalDifference: number;
  points: number;
}
