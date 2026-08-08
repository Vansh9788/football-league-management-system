package com.vanshkhatri.football;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileHandler {
    private static final Path DATA_FILE = Path.of("league.txt");

    private FileHandler() {}

    public static void saveData(TeamList teams, MatchList matches) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(DATA_FILE)) {
            for (Team team : teams.getTeams()) {
                writer.write("TEAM," + team.getName());
                writer.newLine();
            }
            for (Match match : matches.getMatches()) {
                writer.write(String.format("MATCH,%s,%s,%d,%d", match.getTeam1(), match.getTeam2(),
                        match.getScore1(), match.getScore2()));
                writer.newLine();
            }
        }
    }

    public static void loadData(TeamList teams, MatchList matches) throws IOException {
        if (!Files.exists(DATA_FILE)) return;
        try (BufferedReader reader = Files.newBufferedReader(DATA_FILE)) {
            String line;
            while ((line = reader.readLine()) != null) processLine(line, teams, matches);
        }
        teams.rebuildStatistics(matches.getMatches());
    }

    private static void processLine(String line, TeamList teams, MatchList matches) {
        String[] parts = line.split(",");
        try {
            if (parts.length == 2 && parts[0].equals("TEAM")) {
                teams.addTeam(parts[1]);
            } else if (parts.length == 5 && parts[0].equals("MATCH")) {
                matches.addMatch(new Match(parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
            }
        } catch (IllegalArgumentException ignored) {
            // Ignore malformed rows while preserving valid saved data.
        }
    }
}
