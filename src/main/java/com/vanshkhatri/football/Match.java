package com.vanshkhatri.football;

public class Match {
    private final String team1;
    private final String team2;
    private final int score1;
    private final int score2;

    public Match(String team1, String team2, int score1, int score2) {
        if (team1 == null || team2 == null || team1.isBlank() || team2.isBlank()) {
            throw new IllegalArgumentException("Both team names are required.");
        }
        if (team1.equalsIgnoreCase(team2)) {
            throw new IllegalArgumentException("A team cannot play itself.");
        }
        if (score1 < 0 || score2 < 0) {
            throw new IllegalArgumentException("Scores cannot be negative.");
        }
        this.team1 = team1.trim();
        this.team2 = team2.trim();
        this.score1 = score1;
        this.score2 = score2;
    }

    public String getTeam1() { return team1; }
    public String getTeam2() { return team2; }
    public int getScore1() { return score1; }
    public int getScore2() { return score2; }

    public boolean involves(String teamName) {
        return team1.equalsIgnoreCase(teamName) || team2.equalsIgnoreCase(teamName);
    }

    public String getResult() {
        if (score1 > score2) return team1 + " won";
        if (score2 > score1) return team2 + " won";
        return "Draw";
    }

    @Override
    public String toString() {
        return team1 + " " + score1 + " - " + score2 + " " + team2;
    }
}
