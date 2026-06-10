// VotingSystem.java
// Main class that runs the Online Voting System

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class VotingSystem {

    // ArrayList to store all candidates
    static ArrayList<Candidate> candidates = new ArrayList<>();

    // HashSet to store voter IDs who have already voted (prevents duplicates)
    static HashSet<String> votedVoters = new HashSet<>();

    public static void main(String[] args) {

        // Add candidates to the election
        candidates.add(new Candidate("Alice"));
        candidates.add(new Candidate("Bob"));
        candidates.add(new Candidate("Charlie"));

        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("===================================");
        System.out.println("  Welcome to Online Voting System  ");
        System.out.println("===================================");

        // Main menu loop
        do {
            System.out.println("\n1. Vote");
            System.out.println("2. View Results");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Input validation for menu choice
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter 1, 2, or 3: ");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    castVote(scanner);
                    break;
                case 2:
                    displayResults();
                    break;
                case 3:
                    System.out.println("\nThank you for using the Voting System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 3);

        scanner.close();
    }

    // Method to handle the voting process
    static void castVote(Scanner scanner) {

        System.out.print("\nEnter Voter ID: ");
        String voterID = scanner.next();

        // Check if voter has already voted
        if (votedVoters.contains(voterID)) {
            System.out.println("You have already voted. Duplicate voting is not allowed.");
            return;
        }

        // Display candidates
        System.out.println("\nChoose a Candidate:");
        for (int i = 0; i < candidates.size(); i++) {
            System.out.println((i + 1) + ". " + candidates.get(i).getCandidateName());
        }

        System.out.print("Enter candidate number: ");

        // Input validation for candidate choice
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a valid candidate number: ");
            scanner.next();
        }
        int candidateChoice = scanner.nextInt();

        // Validate candidate number range
        if (candidateChoice < 1 || candidateChoice > candidates.size()) {
            System.out.println("Invalid candidate choice. Vote not cast.");
            return;
        }

        // Cast the vote
        candidates.get(candidateChoice - 1).vote();

        // Record the voter ID to prevent duplicate voting
        votedVoters.add(voterID);

        System.out.println("Vote Cast Successfully for " + candidates.get(candidateChoice - 1).getCandidateName() + "!");
    }

    // Method to display final results and determine winner
    static void displayResults() {

        System.out.println("\n===== Final Results =====");

        Candidate winner = candidates.get(0); // Assume first candidate is winner initially

        for (Candidate c : candidates) {
            System.out.println(c.getCandidateName() + ": " + c.getVoteCount() + " vote(s)");
            // Check if this candidate has more votes than current winner
            if (c.getVoteCount() > winner.getVoteCount()) {
                winner = c;
            }
        }

        System.out.println("=========================");

        // Handle tie case (if all have 0 votes or equal votes)
        int winnerVotes = winner.getVoteCount();
        int tieCount = 0;
        for (Candidate c : candidates) {
            if (c.getVoteCount() == winnerVotes) {
                tieCount++;
            }
        }

        if (winnerVotes == 0) {
            System.out.println("No votes have been cast yet.");
        } else if (tieCount > 1) {
            System.out.println("It's a TIE! Multiple candidates have " + winnerVotes + " vote(s).");
        } else {
            System.out.println("Winner: " + winner.getCandidateName() + " with " + winner.getVoteCount() + " vote(s)!");
        }
    }
}