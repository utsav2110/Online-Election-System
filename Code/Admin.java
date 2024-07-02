import java.util.*;

class Admin extends LogIn {

    Scanner scanner = new Scanner(System.in);

    // constructor
    Admin(String username, String password) {
        super(username, password);
    }

    private ArrayList<String[]> elections = new ArrayList<>();
    private ArrayList<Double> votingstatistics = new ArrayList<>();
    private ArrayList<String> winnereslist = new ArrayList<>();
   
    // method for adding new elections
    public void addnewElections(String name, String date) {
        String[] newElection = { name, date };
        elections.add(newElection);
        votingstatistics.add(0.0);
        winnereslist.add("temp");
        System.out.println("\n--- " + name + " Election added successfully ---");
    }

    // method for accessing particular election
    public String[] getElection(int index) {
        if (index >= 0 && index < elections.size()) {
            return elections.get(index);
        } else {
            System.out.println("Invalid index!");
            return null;
        }
    }

    // print the details of the all election
    public void showelections() {
        if (elections.size() == 0) {
            System.out.println("\n --- No election scheduled yet ---");
        } else {
            for (int i = 0; i < elections.size(); i++) {
                String[] election = getElection(i);
                if (election != null) {
                    System.out.println("\n ----Election " + (i + 1) + " details---- ");
                    System.out.println(" Name: " + election[0]);
                    System.out.println(" End Date: " + election[1]);
                }
            }
            System.out.println("\n ---------- End ----------");
        }
    }

    // to access elections size in for loop in main code
    public ArrayList<String[]> getElections() {
        return elections;
    }
    public ArrayList<Double> getVotingStatistics() {
        return votingstatistics;
    }
    public ArrayList<String> getWinners() {
        return winnereslist;
    }

    // -------------- for adding candidates ------------//

    private ArrayList<String[]> candidates = new ArrayList<>();
    private ArrayList<Integer> votes = new ArrayList<>();

    // method for adding new candidate
    public void addnewCandidates(int inputchoice, String candidateName, String partyName, String gender, String mo_no,
            String city, String state) {
        int choice = inputchoice - 1;
        // getting election name from String array as per candidate choice
        String electionName = getElection(choice)[0];
        String[] newCandidate = { electionName, candidateName, partyName, gender, mo_no, city, state };
        votes.add(0);
        candidates.add(newCandidate);
        System.out.println("\n --- Candidate " + candidateName + " added successfully ---");
    }

    // method to access a particular candidate
    public String[] getCandidate(int index) {
        if (index >= 0 && index < candidates.size()) {
            return candidates.get(index);
        } else {
            System.out.println("\n --- Invalid index! ---");
            return null;
        }
    }

    // to access candidates size in for loop in main code
    public ArrayList<String[]> getCandidates() {
        return candidates;
    }

    public ArrayList<Integer> getVotes() {
        return votes;
    }

    // delete a candidate 
    public void deleteCandidate() {
        boolean isdeleted = false;
        showcandidates();
        if (candidates.size() != 0) {
            while (!isdeleted) {
               
                int candidateChoice = 0;
                try {
                    System.out.print("\nEnter Your Choice: ");
                    candidateChoice  = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("\n --> Invalid input. Please enter an integer.\n" + "-->Exception: " + e);
                    scanner.nextLine();
                    continue;
                }
               
                if (candidateChoice < 1
                        || candidateChoice > candidates.size()) {
                    System.out.println("\n --- Invalid candidate choosen! --- \n");
                } else {
                    candidates.remove(candidateChoice - 1);
                    System.out.println("\n --- Candidate removed successfully ---");
                    isdeleted = true;
                }
            }
        }
    }

    // print details of all candidates
    void showcandidates() {
        if (candidates.size() == 0) {
            System.out.println("\n --- No candidates registered yet ---\n");
        } else {
            for (int i = 0; i < candidates.size(); i++) {
                String[] candidate = getCandidate(i);
                if (candidate != null) {
                    System.out.println("\n ---- Candidate " + (i + 1) + " details----");
                    System.out.println("Election: " + candidate[0]);
                    System.out.println("Candidate Name: " + candidate[1]);
                    System.out.println("Party Name: " + candidate[2]);
                    System.out.println("Gender: " + candidate[3]);
                    System.out.println("Mobile Number: " + candidate[4]);
                    System.out.println("City: " + candidate[5]);
                    System.out.println("State: " + candidate[6]);
                    System.out.println();
                }
            }
            System.out.println("\n ----------------- End -----------------");
        }
    }

        // method for approving voter
        public static void approveVoter(int index, Voter[] voters) {
            if (index == 0) {
                System.out.println("\n --- No voter has registered ---");
            } else {
                boolean allApproved = true;
                for (int i = 0; i < index; i++) {
                    if (voters[i] != null && !voters[i].isApproved()) {
                        allApproved = false;
                        System.out.println("\n-----Voter " + (i + 1) + " details------");
                        voters[i].showdetails();
                        int choice3 = 0;
                        while (choice3 != 1 && choice3 != 2) {
                            System.out.println("\n--- Enter 1 to approve ---");
                            System.out.println("--- Enter 2 to disapprove ---");
                            
                            Scanner sc = new Scanner(System.in);
                            try {
                                System.out.print("\nEnter Your Choice: ");
                                choice3 = sc.nextInt();
                                sc.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("\n --> Invalid input. Please enter an integer.\n" + "-->Exception: " + e);
                                sc.nextLine();
                                continue;
                            }
                            if (choice3 == 1) {
                                voters[i].setApprovedStatus(true);
                                System.out.println("\n ------ Voter " + (i + 1) + " approved ------");
                            } else if (choice3 == 2) {
                                voters[i].setApprovedStatus(false);
                                System.out.println(
                                        "\n --- Voter " + (i + 1) + " is not approved due to incorrect details ---");
                            } else {
                                System.out.println("\n ---- Invalid choice ----");
                            }
                        }
                    }
                }
                if (allApproved) {
                    System.out.println("\n--- All voters are already approved ---");
                }
            }
        }

    // method for voting a candidate
    public void voteForCandidate(int index) {
        if (index >= 0 && index < candidates.size()) {
            String[] candidate = candidates.get(index);
            if (candidate != null) {
                int currentVotes = votes.get(index);
                // increment vote
                votes.set(index, currentVotes + 1);
                System.out.println("\n ---- Vote registered successfully for " + candidate[1] + " ----");
            } else {
                System.out.println("\n ---- Invalid candidate index ----");
            }
        }
    }

    private ArrayList<Integer> NOTAvotecount = new ArrayList<>(100);
    {
       for (int i = 0; i < 100; i++) {
           NOTAvotecount.add(0);
        }
    }

    public ArrayList<Integer> getNOTAVotes() {
        return NOTAvotecount;
    }
        public void voteForNOTA(int index) {
            try {
                if (index >= 0 && index < elections.size()) {
                    int currentnotaVotes = NOTAvotecount.get(index);
                    // increment vote
                    NOTAvotecount.set(index, currentnotaVotes + 1);
                }else {
                    System.out.println("\n ---- Invalid Election index ----");
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
            
        }

        
    // method to show election details
    public void showresult() {

        showelections();
        if (elections.size() != 0) {    
            int choice5=0;
            do {
                try {
                    System.out.print("\nChoose election:");
                    choice5 = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("\n --> Invalid input. Please enter an integer.\n" + "-->Exception: " + e);
                    scanner.nextLine();
                    continue;
                }
                if (choice5 < 1 || choice5 > elections.size()) {
                    System.out.print("\n---Invalid choice. Please choose again---\n ");
                }
            } while (choice5 < 1 || choice5 > elections.size());
            String chosenElection = getElection(choice5 - 1)[0];
            if (candidates.size() == 0) {
                System.out.println("\n ---- No candidates registered yet ----");
            } else {
                System.out.println("\n ------ Election Results for " + chosenElection + " ---------\n");
                int maxVotes = Integer.MIN_VALUE;
                System.out.println("---------------------------------");
                ArrayList<String[]> tiedCandidates = new ArrayList<>();
                for (int j = 0; j < candidates.size(); j++) {
                    String[] candidate = getCandidate(j);
                    if (candidate != null && candidate[0].equals(chosenElection)) {
                        System.out.println("----Candidate " + (j + 1) + " details----");
                        System.out.println("Name : " + candidate[1]);
                        System.out.println("Party : " + candidate[2]);
                        int candidateVotes = votes.get(j);
                        System.out.println("Votes : " + candidateVotes);
                        System.out.println();

                        if (candidateVotes == maxVotes) {
                            tiedCandidates.add(candidate);
                        } else if (candidateVotes > maxVotes) {
                            maxVotes = candidateVotes;
                            tiedCandidates.clear();
                            tiedCandidates.add(candidate);
                        }
                    }
                }
                System.out.println("---------------------------------");
                // Display winner details
                if (tiedCandidates.isEmpty()) {
                    System.out.println("\n----- No votes cast yet -----");
                } else if (tiedCandidates.size() == 1) {
                    String[] winner = tiedCandidates.get(0);
                    if (winner != null) {
                        System.out.println("\n-------------------------");
                        System.out.println("|        Winner         |");
                        System.out.println("-------------------------");
                        System.out.println("| Name: " + winner[1] + "           |");
                        System.out.println("| Party: " + winner[2] + "           |");
                        System.out.println("| Votes: " + maxVotes + "             |");
                        System.out.println("-------------------------");
                        winnereslist.set(choice5 - 1 ,winner[1]);
                    }
                } else {
                    // show all tied candidates.
                    System.out.println("\n ---- There is a tie between the following candidates ----");
                    for (int i = 0; i < tiedCandidates.size(); i++) {
                        String[] tiedCandidate = tiedCandidates.get(i);
                        if (tiedCandidate != null) {
                            System.out.println(
                                    "--> " + (i + 1) + ". Name: " + tiedCandidate[1] + ", Party: " + tiedCandidate[2]);
                        }
                    }
                    int adminVoteIndex=0;
                    try {
                        System.out.println("\n----> Admin, please vote to break the tie.");
                        System.out.print("----> Enter the index of the candidate you want to vote for: ");
                        adminVoteIndex = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("\n --> Invalid input. Please enter an integer.\n" + "-->Exception: " + e);
                        scanner.nextLine();
                    }
                    // Increment the vote count for the chosen candidate
                    if (adminVoteIndex >= 1 && adminVoteIndex <= tiedCandidates.size()) {
                        String[] winner = tiedCandidates.get(adminVoteIndex - 1);
                        if (winner != null) {
                            System.out.println("\n-------------------------");
                            System.out.println("|        Winner         |");
                            System.out.println("-------------------------");
                            System.out.println("| Name: " + winner[1] + "             |");
                            System.out.println("| Party: " + winner[2] + "             |");
                            System.out.println("| Votes: " + (maxVotes + 1) + "               |");
                            System.out.println("-------------------------");
                            winnereslist.set(choice5 - 1 ,winner[1]);
                            for (int j = 0; j < candidates.size(); j++) {
                                String[] candidate = getCandidate(j);
                                if (candidate != null && candidate[1].equals(winner[1])) {
                                    int candidateVotes = votes.get(j);
                                    votes.set(j,candidateVotes+1);
                                }
                            }
                        }
                    } else {
                        System.out.println("\n --- Invalid candidate index ---");
                    }
                }
            }
        }
    }

        // method to show statistics of election

    
    public void showstatistics(int index, Voter[] voters) {
            showelections();

        if (elections.size() != 0) {    
                int choice6=0;
                do {
                    try {
                        System.out.print("\nChoose election:");
                        choice6 = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("\n --> Invalid input. Please enter an integer.\n" + "-->Exception: " + e);
                        scanner.nextLine();
                        continue;
                    }
                    if (choice6 < 1 || choice6 > elections.size()) {
                        System.out.print("\n---Invalid choice. Please choose again---\n ");
                    }
                } while (choice6 < 1 || choice6 > elections.size());
                String chosenElection = getElection(choice6 - 1)[0];
    
            if (index == 0) {
                System.out.println("\n --- No voter has registered ---");
            } else {
                
                int totalVoters = 0;
                for (int i = 0; i < index; i++) {
                    if (voters[i].isApproved()) {
                        totalVoters++;
                    }
                }
    
                int totalVotesCast = 0;
                for (int i = 0; i < index; i++) {
                    if (voters[i].hasVotedForElection(choice6 - 1)) {
                        totalVotesCast++;
                    }
                }
                double percentageVotingDone = ((double) totalVotesCast / totalVoters) * 100;
                votingstatistics.set(choice6-1,percentageVotingDone);
                System.out.println("\n ---- Percentage of voting done for " + chosenElection + ": " + String.format("%.2f%%", percentageVotingDone) + " ----\n");
                System.out.println("Number of NOTA votes for "+ chosenElection + " is: " + NOTAvotecount.get(choice6 - 1));
            }
        }
    }
}