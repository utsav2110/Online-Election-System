import java.util.*;

class Voter extends LogIn {
       
    // constructor for log in
    Voter(String username, String password) {
        super(username, password);
    }

    private String FirstName="";
    private String MiddleName="";
    private String LastName="";
    private String DateOfBirth="";
    private long mobilenumber;
    private String gender="";
    private String city="";
    private String state="";
    private boolean approved;
    private ArrayList<Boolean> hasVotedForElection;
    

    // constructor for register user
    Voter(String username, String password, String FirstName, String MiddleName, String LastName, String DateOfBirth,
            long mobilenumber, String gender, String city, String state) {
        super(username, password);
        this.FirstName = FirstName;
        this.MiddleName = MiddleName;
        this.LastName = LastName;
        this.DateOfBirth=DateOfBirth;
        this.mobilenumber = mobilenumber;
        this.gender = gender;
        this.city = city;
        this.state = state;
        this.approved=false;
        this.hasVotedForElection = new ArrayList<>(100);
        // Initially he/she has not voted for election so setting status as false
        for (int i = 0; i < 100; i++) {
            hasVotedForElection.add(false);
        }
    }

    public String getFullName() {
        return FirstName + " " + MiddleName + " " + LastName;
    }

    public String getDateOfBirth(){
        return DateOfBirth;
    }

    public long getMobileNumber(){
        return mobilenumber;
    }

    public String getGender(){
        return gender;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }
    // method to show all voters with their details for admin
    public static void showvoters(int index,Voter[] voters){
        if (index == 0) {
            System.out.println("\n --- No voter has registered ---");
        } else {
            for (int i = 0; i < index; i++) {
                System.out.println("\n----- Voter " + (i + 1) + " details ------");
                if (voters[i] != null) {
                    voters[i].showdetails();
                }
            }
            System.out.println("\n ---------- End ----------");
        }
    }

    // method to return "approv status"
    public boolean isApproved() {
       return approved;
    }

    // method to set "approv status"
    public void setApprovedStatus(boolean status){
        this.approved=status;
    }

    public void setHasVotedForElection(int electionIndex, boolean hasVoted) {
        if (electionIndex >= 0 && electionIndex < hasVotedForElection.size()) {
            hasVotedForElection.set(electionIndex, hasVoted);
        } else {
            System.out.println("Invalid election index.");
        }
    }

    public boolean hasVotedForElection(int electionIndex) {
        if (electionIndex >= 0 && electionIndex < hasVotedForElection.size()) {
            return hasVotedForElection.get(electionIndex);
        } else {
            System.out.println("Invalid election index.");
            return false;
        }
    }

    // method to show details of particular voter 
    public void showdetails() {
        System.out.println("\n" + "---------------------------------\n"+" Username: " + getUsername());
        System.out.println("Name: " + FirstName + " " + MiddleName + " " + LastName);
        System.out.println("Date of Birth: " + DateOfBirth);
        System.out.println("Mobile Number: " + mobilenumber);
        System.out.println("Gender: " + gender);
        System.out.println("City: " + city);
        System.out.println("State: " + state);
        System.out.println("Approved: " + (approved ? "Yes" : "No"));
        System.out.println("---------------------------------");
    }

}