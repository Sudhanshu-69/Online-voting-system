// Candidate.java
// Represents a candidate in the election

public class Candidate {

    // Private fields (Encapsulation)
    private String candidateName;
    private int voteCount;

    // Constructor to initialize candidate with a name
    public Candidate(String candidateName) {
        this.candidateName = candidateName;
        this.voteCount = 0; // Initially 0 votes
    }

    // Method to cast a vote for this candidate
    public void vote() {
        voteCount++;
    }

    // Getter for candidate name
    public String getCandidateName() {
        return candidateName;
    }

    // Getter for vote count
    public int getVoteCount() {
        return voteCount;
    }
}