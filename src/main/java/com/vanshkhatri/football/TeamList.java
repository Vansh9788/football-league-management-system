package com.vanshkhatri.football;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TeamList {
    private final List<Team> teams = new ArrayList<>();

    public boolean addTeam(String name) {
        if (name == null || name.isBlank() || name.contains(",") || searchTeam(name) != null) return false;
        teams.add(new Team(name));
        return true;
    }

    public Team searchTeam(String name) {
        if (name == null) return null;
        return teams.stream()
                .filter(team -> team.getName().equalsIgnoreCase(name.trim()))
                .findFirst().orElse(null);
    }

    public boolean removeTeam(String name) {
        Team team = searchTeam(name);
        return team != null && teams.remove(team);
    }

    public boolean recordMatch(String team1, String team2, int score1, int score2) {
        if (team1 == null || team2 == null || team1.equalsIgnoreCase(team2) || score1 < 0 || score2 < 0) return false;
        Team first = searchTeam(team1);
        Team second = searchTeam(team2);
        if (first == null || second == null) return false;
        first.recordMatch(score1, score2);
        second.recordMatch(score2, score1);
        return true;
    }

    public void rebuildStatistics(List<Match> matches) {
        teams.forEach(Team::resetStatistics);
        for (Match match : matches) {
            recordMatch(match.getTeam1(), match.getTeam2(), match.getScore1(), match.getScore2());
        }
    }

    public void sortTable() {
        teams.sort(Comparator.comparingInt(Team::getPoints).reversed()
                .thenComparing(Comparator.comparingInt(Team::getGoalDifference).reversed())
                .thenComparing(Comparator.comparingInt(Team::getGoalsFor).reversed())
                .thenComparing(Team::getName));
    }

    public List<Team> getTeams() { return Collections.unmodifiableList(teams); }
    public boolean isEmpty() { return teams.isEmpty(); }

    public void displayTeams() {
        if (teams.isEmpty()) System.out.println("No teams available.");
        else teams.forEach(team -> System.out.println(team.getName()));
    }

    public void displayTable() {
        sortTable();
        System.out.println("Team                 P   W   D   L  GF  GA  GD PTS");
        teams.forEach(System.out::println);
    }
}
