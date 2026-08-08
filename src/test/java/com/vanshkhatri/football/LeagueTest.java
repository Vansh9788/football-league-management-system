package com.vanshkhatri.football;

public class LeagueTest {
    public static void main(String[] args) {
        TeamList teams = new TeamList();
        MatchList matches = new MatchList();

        assert teams.addTeam("Arsenal");
        assert teams.addTeam("Chelsea");
        assert !teams.addTeam("arsenal");
        assert teams.recordMatch("Arsenal", "Chelsea", 2, 1);
        matches.addMatch(new Match("Arsenal", "Chelsea", 2, 1));
        assert teams.searchTeam("Arsenal").getPoints() == 3;
        assert teams.searchTeam("Chelsea").getPoints() == 0;

        assert matches.removeMatch(0);
        teams.rebuildStatistics(matches.getMatches());
        assert teams.searchTeam("Arsenal").getPlayed() == 0;
        assert !teams.recordMatch("Arsenal", "Arsenal", 1, 0);
        assert !teams.recordMatch("Arsenal", "Chelsea", -1, 0);

        matches.addMatch(new Match("Arsenal", "Chelsea", 0, 0));
        teams.rebuildStatistics(matches.getMatches());
        assert teams.searchTeam("Arsenal").getPoints() == 1;
        assert teams.searchTeam("Chelsea").getPoints() == 1;

        matches.removeMatchesForTeam("Chelsea");
        assert matches.getMatches().isEmpty();
        System.out.println("All league tests passed.");
    }
}
