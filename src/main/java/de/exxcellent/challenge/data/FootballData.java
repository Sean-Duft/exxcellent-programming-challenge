package de.exxcellent.challenge.data;

/**
 * Data class to represent a football teams stats across multiple games
 *
 * @param teamName     The name of the team
 * @param gamesPlayed  Total games played
 * @param wins         Amount of games won
 * @param losses       Amount of games lost
 * @param draws        Amount of games drawn
 * @param goalsScored  Total amount of goals scored across all games played
 * @param goalsAllowed Total amount of goals conceded across all games played
 * @param points       Total points scored
 */
public record FootballData(String teamName, int gamesPlayed, int wins, int losses, int draws, int goalsScored,
                           int goalsAllowed, int points) {
}
