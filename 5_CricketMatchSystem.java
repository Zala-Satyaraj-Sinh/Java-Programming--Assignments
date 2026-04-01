// Base class for a Match
class Match {
    private String matchType;
    private int overs;

    public Match(String matchType, int overs) {
        this.matchType = matchType;
        this.overs = overs;
    }

    public void displayMatchInfo() {
        System.out.println("Match Type: " + matchType);
        System.out.println("Overs: " + overs);
    }
}

// Subclass for a Cricket Match
class CricketMatch extends Match {
    private String team1;
    private String team2;
    private int score1;
    private int score2;
    private String result;

    public CricketMatch(String matchType, int overs, String team1, String team2) {
        super(matchType, overs); // Call to superclass constructor
        this.team1 = team1;
        this.team2 = team2;
    }

    public void setScores(int score1, int score2) {
        this.score1 = score1;
        this.score2 = score2;
    }

    public void calculateResult() {
        if (score1 > score2) {
            result = team1 + " wins!";
        } else if (score2 > score1) {
            result = team2 + " wins!";
        } else {
            result = "It's a tie!";
        }
    }

    public void displayResult() {
        super.displayMatchInfo(); // Call superclass method
        System.out.println("Team 1: " + team1 + " (Score: " + score1 + ")");
        System.out.println("Team 2: " + team2 + " (Score: " + score2 + ")");
        System.out.println("Result: " + result);
    }
}

public class CricketMatchSystem {
    public static void main(String[] args) {
        // Check if enough command line arguments are provided
        if (args.length < 6) {
            System.out.println("Usage: java CricketMatchSystem <MatchType> <Overs> <Team1> <Team2> <Score1> <Score2>");
            System.out.println("Example: java CricketMatchSystem T20 20 India Australia 180 175");
            return;
        }

        try {
            // Parse command line arguments
            String matchType = args[0];
            int overs = Integer.parseInt(args[1]);
            String team1 = args[2];
            String team2 = args[3];
            int score1 = Integer.parseInt(args[4]);
            int score2 = Integer.parseInt(args[5]);

            // Create a CricketMatch object
            CricketMatch match = new CricketMatch(matchType, overs, team1, team2);

            // Set the scores
            match.setScores(score1, score2);

            // Calculate the result
            match.calculateResult();

            // Display the final result
            System.out.println("--- Cricket Match Result ---");
            match.displayResult();

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for overs or scores. Please provide integers.");
            System.out.println("Usage: java CricketMatchSystem <MatchType> <Overs> <Team1> <Team2> <Score1> <Score2>");
        }
    }
}
