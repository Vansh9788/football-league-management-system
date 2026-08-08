package com.vanshkhatri.football;

public class Team {
    private final String name;
    private int played;
    private int wins;
    private int draws;
    private int losses;
    private int goalsFor;
    private int goalsAgainst;
    private int points;

    public Team(String name) {
        if (name == null || name.isBlank() || name.contains(",")) {
            throw new IllegalArgumentException("Team name must be non-empty and cannot contain commas.");
        }
        this.name = name.trim();
    }

    public void recordMatch(int goalsScored, int goalsConceded) {
        if (goalsScored < 0 || goalsConceded < 0) {
            throw new IllegalArgumentException("Scores cannot be negative.");
        }
        played++;
        goalsFor += goalsScored;
        goalsAgainst += goalsConceded;

        if (goalsScored > goalsConceded) {
            wins++;
            points += 3;
        } else if (goalsScored == goalsConceded) {
            draws++;
            points++;
        } else {
            losses++;
        }
    }

    public void resetStatistics() {
        played = wins = draws = losses = goalsFor = goalsAgainst = points = 0;
    }

    public String getName() { return name; }
    public int getPlayed() { return played; }
    public int getWins() { return wins; }
    public int getDraws() { return draws; }
    public int getLosses() { return losses; }
    public int getGoalsFor() { return goalsFor; }
    public int getGoalsAgainst() { return goalsAgainst; }
    public int getPoints() { return points; }
    public int getGoalDifference() { return goalsFor - goalsAgainst; }

    @Override
    public String toString() {
        return String.format("%-18s %3d %3d %3d %3d %3d %3d %3d %3d",
                name, played, wins, draws, losses, goalsFor, goalsAgainst,
                getGoalDifference(), points);
    }
}
