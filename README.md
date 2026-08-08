# Football League Management System

A Java application for managing football teams, recording match results and generating a ranked league table. The project demonstrates object-oriented programming, collections, lambda-based sorting, validation and file persistence.

## Features

- Add, search and remove teams
- Record matches with score validation
- Generate a league table ranked by points, goal difference and goals scored
- View match history and team-specific results
- Delete matches and automatically recalculate league statistics
- Save and reload league data from a local file
- Console interface, with a JavaFX interface included in the original project design

## Technologies

- Java 17
- Java collections and streams
- Object-oriented programming
- JavaFX (graphical interface)
- File I/O

## Project structure

```text
src/
├── main/java/com/vanshkhatri/football/   # Domain and application classes
└── test/java/com/vanshkhatri/football/   # Dependency-free test runner
```

## Run the core tests

```bash
mkdir -p out
javac -d out src/main/java/com/vanshkhatri/football/{Team,Match,TeamList,MatchList,FileHandler}.java \
  src/test/java/com/vanshkhatri/football/LeagueTest.java
java -ea -cp out com.vanshkhatri.football.LeagueTest
```

The `-ea` flag enables Java assertions used by the test runner.

## Run the console application

```bash
mkdir -p out
javac -d out src/main/java/com/vanshkhatri/football/{Team,Match,TeamList,MatchList,FileHandler,ConsoleApp}.java
java -cp out com.vanshkhatri.football.ConsoleApp
```

## Key design decisions

- Team names are matched case-insensitively to prevent duplicates.
- Scores cannot be negative and a team cannot play itself.
- League statistics are rebuilt from match history after a deletion, preventing stale points or goal totals.
- Collections are exposed as read-only views so data cannot be changed outside the responsible classes.
- Saved data is reconstructed from teams and match history to maintain a single source of truth.

## Future improvements

- Package the JavaFX application with Maven or Gradle
- Add JUnit tests and continuous integration
- Replace text-file storage with a relational database
- Add configurable league scoring rules

## Author

**Vansh Khatri**  
[LinkedIn](https://www.linkedin.com/in/vanshkhatrilondon) · [GitHub](https://github.com/Vansh9788)
