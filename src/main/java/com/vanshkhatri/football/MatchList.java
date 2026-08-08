package com.vanshkhatri.football;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchList {
    private final List<Match> matches = new ArrayList<>();

    public void addMatch(Match match) { matches.add(match); }
    public boolean removeMatch(int index) {
        if (index < 0 || index >= matches.size()) return false;
        matches.remove(index);
        return true;
    }
    public void removeMatchesForTeam(String teamName) {
        matches.removeIf(match -> match.involves(teamName));
    }
    public List<Match> getMatches() { return Collections.unmodifiableList(matches); }

    public List<Match> findByTeam(String teamName) {
        return matches.stream().filter(match -> match.involves(teamName)).toList();
    }

    public void displayAllMatches() {
        if (matches.isEmpty()) System.out.println("No matches recorded.");
        else matches.forEach(System.out::println);
    }

    public void displayMatchesWithIndex() {
        if (matches.isEmpty()) {
            System.out.println("No matches recorded.");
            return;
        }
        for (int i = 0; i < matches.size(); i++) {
            System.out.println(i + ": " + matches.get(i));
        }
    }
}
