package com.vanshkhatri.football;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        TeamList teams = new TeamList();
        MatchList matches = new MatchList();
        try {
            FileHandler.loadData(teams, matches);
        } catch (IOException exception) {
            System.out.println("Saved data could not be loaded: " + exception.getMessage());
        }

        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                printMenu();
                choice = readInteger(scanner, "Enter choice: ");
                switch (choice) {
                    case 1 -> addTeam(scanner, teams);
                    case 2 -> teams.displayTeams();
                    case 3 -> recordMatch(scanner, teams, matches);
                    case 4 -> teams.displayTable();
                    case 5 -> searchTeam(scanner, teams, matches);
                    case 6 -> matches.displayAllMatches();
                    case 7 -> deleteTeam(scanner, teams, matches);
                    case 8 -> deleteMatch(scanner, teams, matches);
                    case 9 -> save(teams, matches);
                    default -> System.out.println("Choose a number from 1 to 9.");
                }
            } while (choice != 9);
        }
    }

    private static void printMenu() {
        System.out.println("\n=== FOOTBALL LEAGUE SYSTEM ===");
        System.out.println("1. Add team\n2. Display teams\n3. Record match\n4. Show league table");
        System.out.println("5. Search team\n6. Show match history\n7. Delete team\n8. Delete match\n9. Save and quit");
    }

    private static int readInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException ignored) { System.out.println("Please enter a valid number."); }
        }
    }

    private static void addTeam(Scanner scanner, TeamList teams) {
        System.out.print("Team name: ");
        System.out.println(teams.addTeam(scanner.nextLine()) ? "Team added." : "Invalid or duplicate team name.");
    }

    private static void recordMatch(Scanner scanner, TeamList teams, MatchList matches) {
        System.out.print("Team 1: "); String first = scanner.nextLine();
        System.out.print("Team 2: "); String second = scanner.nextLine();
        int firstScore = readInteger(scanner, "Score for " + first + ": ");
        int secondScore = readInteger(scanner, "Score for " + second + ": ");
        if (teams.recordMatch(first, second, firstScore, secondScore)) {
            matches.addMatch(new Match(first, second, firstScore, secondScore));
            System.out.println("Match recorded.");
        } else {
            System.out.println("Invalid teams or scores.");
        }
    }

    private static void searchTeam(Scanner scanner, TeamList teams, MatchList matches) {
        System.out.print("Team name: "); String name = scanner.nextLine();
        Team team = teams.searchTeam(name);
        if (team == null) { System.out.println("Team not found."); return; }
        System.out.println("Team                 P   W   D   L  GF  GA  GD PTS\n" + team);
        matches.findByTeam(name).forEach(System.out::println);
    }

    private static void deleteTeam(Scanner scanner, TeamList teams, MatchList matches) {
        System.out.print("Team name to delete: "); String name = scanner.nextLine();
        if (!teams.removeTeam(name)) { System.out.println("Team not found."); return; }
        matches.removeMatchesForTeam(name);
        teams.rebuildStatistics(matches.getMatches());
        System.out.println("Team and related matches removed.");
    }

    private static void deleteMatch(Scanner scanner, TeamList teams, MatchList matches) {
        matches.displayMatchesWithIndex();
        int index = readInteger(scanner, "Match index: ");
        if (!matches.removeMatch(index)) { System.out.println("Invalid match index."); return; }
        teams.rebuildStatistics(matches.getMatches());
        System.out.println("Match removed and table recalculated.");
    }

    private static void save(TeamList teams, MatchList matches) {
        try { FileHandler.saveData(teams, matches); System.out.println("Data saved."); }
        catch (IOException exception) { System.out.println("Data could not be saved: " + exception.getMessage()); }
    }
}
