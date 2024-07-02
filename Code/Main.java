import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.IOException;

// Some Important Note:-

// --> If Num Lock is on then Please turn it off first 
// --> For Admin:- Both username and password are "admin".
// --> So if you want to login as Admin enter "admin" in both fields.

public class Main extends StoreToCSV{

    // Method to checking Input Mobile Number is Numeric or not
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidPassword(String password) {
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            }
        }

        boolean isValidLength = password.length() >= 8;
        return hasLowercase && hasUppercase && hasDigit && isValidLength;
    }

    public static void main(String[] args) {

        
        Admin admin = new Admin("admin", "admin");
        Voter voters[] = new Voter[20];

        for (int i = 0; i < 20; i++) {
            voters[i] = new Voter("", "");
        }

        // index to keep track of no. of voters
        int index = 0;
        Scanner sc = new Scanner(System.in);
        boolean exit=false;
        while (!exit) {

            System.out.println("\n");
            System.out.println("------- Welcome to Online Election System --------\n ");
            System.out.println("1. Admin Module");
            System.out.println("2. Voter Module");
            System.out.println("3. Exit");
            System.out.println("\n");
            int choice=0;
            try {
                System.out.print("Enter Your Choice: ");
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\n --> Invalid input. Please enter an integer.\n" + "-->Exception: " + e);
                sc.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    String electionName, endDatestr="";
                    // to keep track of admin logged in or not
                    boolean loggedin = false;
                    while (!loggedin) {
                        System.out.print("\nEnter your username: ");
                        String username = sc.nextLine();
                        System.out.print("Enter your password: ");
                        String password = sc.nextLine();
                        if (admin.checkCredentials(username, password)) {
                            System.out.println("\n---Login successful!---");
                            loggedin = true;
                            // to keep track of admin logged out or not
                            boolean logout = false;
                            while (!logout) {
                                // to validate that user input from 1 to 10 only.If not then to show this menu message again and again
                                boolean validChoice = false;
                                while (!validChoice) {
                                    System.out.println("\n");
                                    System.out.println("-----Welcome to Admin Module-----\n");
                                    System.out.println("1. Add Election");
                                    System.out.println("2. View Election");
                                    System.out.println("3. Add Candidate");
                                    System.out.println("4. Remove Candidate");
                                    System.out.println("5. View Candidates");
                                    System.out.println("6. View Voters");
                                    System.out.println("7. Approve Voter");
                                    System.out.println("8. View Result");
                                    System.out.println("9. Voting Statistics");
                                    System.out.println("10. Log Out");
                                    int choice2=0;
                                    try {
                                        System.out.print("\nEnter Your Choice: ");
                                        choice2 = sc.nextInt();
                                        sc.nextLine();
                                    } catch (InputMismatchException e) {
                                        System.out.println("\n --> Invalid input. Please enter an integer.\n" + "-->Exception: " + e);
                                        sc.nextLine();
                                        continue;
                                    }
                                    
                                    switch (choice2) {
                                        case 1:
                                            System.out.print("\nEnter election name: ");
                                            electionName = sc.nextLine();
                                            boolean validDate = false;
                                            while (!validDate) {
                                                System.out.print("Enter the end date of the election (dd-mm-yyyy):");
                                                endDatestr = sc.nextLine();
                                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                                try {
                                                    LocalDate date = LocalDate.parse(endDatestr, formatter);
                                                    validDate = true;
                                                } catch (DateTimeParseException e) {
                                                    System.out.println("\n ---- Invalid date format. Please enter date in dd-mm-yyyy format ----");
                                                    System.out.println("\n ---> Exception: " + e +"\n");
                                                }
                                            }
                                            
                                            admin.addnewElections(electionName, endDatestr);
                                            break;
                                        case 2:
                                            admin.showelections();
                                            break;
                                        case 3:
                                            if (admin.getElections().size() != 0) {

                                                // First ask the candidate to choose election...

                                                System.out.println("\n ---> Choose in which election candidate want to stand?");
                                                admin.showelections();
                                                
                                                int electionChoice=0;
                                                try {
                                                    System.out.print("\nEnter Your Choice: ");
                                                    electionChoice = sc.nextInt();
                                                    sc.nextLine();
                                                } catch (InputMismatchException e) {
                                                    System.out.println("\n --> Invalid input. Please enter an integer.\n" + "-->Exception: " + e);
                                                    sc.nextLine();
                                                    continue;
                                                }
                                
                                                if (electionChoice < 1 || electionChoice > admin.getElections().size()) {
                                                    System.out.println("\n ---Invalid election choosen!---");
                                                } else {
                                                   
                                                    System.out.print("\nEnter candidate name: ");
                                                    String candidateName = sc.nextLine();
                                                    System.out.print("Enter party name: ");
                                                    String partyName = sc.nextLine();
                                                    String gender;
                                                    while (true) {
                                                        System.out.print("Enter your Gender(M/m/F/f): ");
                                                        gender = sc.nextLine();
                                                        if (gender.equals("M") || gender.equals("m")) {
                                                            break;
                                                        } else if (gender.equals("F") || gender.equals("f")) {
                                                            break;
                                                        } else {
                                                            System.out.println("Invalid input. Please enter 'M' or 'm' or 'F' or 'f'.");
                                                        }
                                                    }
                                                    String mo_no="";
                                                    boolean validmono = false;
                                                    while (!validmono) {
                                                        System.out.print("Enter Mobile Number: ");
                                                        mo_no = sc.nextLine();
                                                        if (mo_no.length() == 10 && isNumeric(mo_no)) {
                                                            validmono = true;
                                                        } else {
                                                            System.out.println("--> Invalid input! Please enter a valid 10-digit mobile number.");
                                                        }
                                                    }
                                                    System.out.print("Enter city: ");
                                                    String city = sc.nextLine();
                                                    System.out.print("Enter state: ");
                                                    String state = sc.nextLine();
                                                    admin.addnewCandidates(electionChoice, candidateName, partyName,gender,mo_no, city, state);
                                                }
                                            } else {
                                                System.out.println("\n --- No election scheduled yet ---");
                                            }
                                            break;
                                        case 4:
                                            admin.deleteCandidate();
                                            break;
                                        case 5:
                                            admin.showcandidates();
                                            break;
                                        case 6:
                                            Voter.showvoters(index,voters);
                                            break;
                                        case 7:
                                            Admin.approveVoter(index,voters);
                                            break;
                                        case 8:
                                            admin.showresult();
                                            break;
                                        case 9:
                                            admin.showstatistics(index,voters);
                                            break;
                                        case 10:
                                            validChoice = true;
                                            logout = true;
                                            break;
                                        default:
                                            System.out.println("\n ----- Invalid Choice -----");
                                            break;
                                    }
                                }
                            }
                            break;
                        } else {
                            System.out.println("\n--- Invalid username or password. Please try again. ---");
                        }
                    }
                    break;
                case 2:
                    String username="", password = "", firstname, middlename, lastname, birthdateStr = "", gender="", city,state;
                    long mo_no = 0;
                    
                    boolean logout = false;
                    while (!logout) {
                        System.out.println("\n");
                        System.out.println("------- Welcome to Voter Module ------- \n");
                        System.out.println("1. Register Yourself");
                        System.out.println("2. Log in");
                        System.out.println("3. Go back to main menu");
                        
                        int choice1;
                        try {
                            System.out.print("\nEnter Your Choice: ");
                            choice1 = sc.nextInt();
                            sc.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("\n --> Invalid input. Please enter an integer.\n" + "-->Exception: " + e);
                            sc.nextLine();
                            continue;
                        }
                        
                        switch (choice1) {
                            case 1:
                                if(index<20){
                                System.out.println("\n");
                                System.out.println("-----Kindly Fill the correct details-----\n");
                                System.out.print("Enter your username: ");
                                username = sc.nextLine();
                                boolean isUsernameTaken;
                                do {
                                    isUsernameTaken = false;
                                    for (int i = 0; i < index; i++) {
                                        Voter voter = voters[i];
                                        String savedUsername = voter.getUsername();
                                        if (savedUsername.equals(username)) {
                                            isUsernameTaken = true;
                                            System.out.println("--> Username already taken. Please choose a different username.");
                                            System.out.print("Enter your username: ");
                                            username = sc.nextLine();
                                            break; 
                                        }
                                    }
                                } while (isUsernameTaken);
                                boolean isValidPassword = false;
                                while (!isValidPassword) {
                                    System.out.print("Enter your password: ");
                                    password = sc.nextLine();
                                    Main obj = new Main();
                                    if (obj.isValidPassword(password)) {
                                        isValidPassword = true;
                                    } else {
                                        System.out.println("--->Invalid password! Password must be at least 8 characters long.\n" + 
                                        "--->And it should contain at least one uppercase letter, one lowercase letter, and one digit.");
                                    }
                                }
                                System.out.print("Enter your First Name: ");
                                firstname = sc.nextLine();
                                System.out.print("Enter your Middle Name: ");
                                middlename = sc.nextLine();
                                System.out.print("Enter your Last Name: ");
                                lastname = sc.nextLine();
                                boolean validDate = false;
                                while (!validDate) {
                                    System.out.print("Please enter your birthdate (dd-mm-yyyy): ");
                                    birthdateStr = sc.nextLine();
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                    try {
                                        LocalDate birthdate = LocalDate.parse(birthdateStr, formatter);
                                        LocalDate today = LocalDate.now();
                                        Period period = Period.between(birthdate, today);
                                        int age = period.getYears();
                                        if (age >= 18) {
                                            validDate = true;
                                        } else {
                                            System.out.println("\n---- Registration failed because you are below 18 ----");
                                            break;
                                        }
                                    } catch (DateTimeParseException e) {
                                        System.out.println("\n ---- Invalid date format. Please enter date in dd-mm-yyyy format ----");
                                        System.out.println("\n ---> Exception: " + e + "\n");
                                    }
                                }
                                if (!validDate) {
                                    break;
                                }
                                boolean validmono = false;

                                while (!validmono) {
                                    System.out.print("Enter your Mobile Number: ");
                                    String input = sc.nextLine();
                                    if (input.length() == 10 && isNumeric(input)) {
                                        mo_no = Long.parseLong(input);
                                        validmono = true;
                                    } else {
                                        System.out.println("--> Invalid input! Please enter a valid 10-digit mobile number.");
                                    }
                                }
                                while (true) {
                                    System.out.print("Enter your Gender(M/m/F/f): ");
                                    gender = sc.nextLine();
                                    if (gender.equals("M") || gender.equals("m")) {
                                        break;
                                    } else if (gender.equals("F") || gender.equals("f")) {
                                        break;
                                    } else {
                                        System.out.println("Invalid input. Please enter 'M' or 'm' or 'F' or 'f'.");
                                    }
                                }
                                System.out.print("Enter your City: ");
                                city = sc.nextLine();
                                System.out.print("Enter your State: ");
                                state = sc.nextLine();
                                voters[index++] = new Voter(username, password, firstname, middlename, lastname,birthdateStr,mo_no,gender, city,state);
                                System.out.println("\n ----- Voter registered successfully -----");
                                
                            }else{
                                System.out.println("Error: Maximum number of voters reached. Cannot register more users.");
                            }
                                break;
                            case 2:
                                boolean loggedin1 = false;
                                while (!loggedin1) {
                                    System.out.print("\nEnter your username: ");
                                    String username1 = sc.nextLine();
                                    System.out.print("Enter your password: ");
                                    String password1 = sc.nextLine();
                                    // username found or not
                                    boolean found = false;
                                    for (int i = 0; i < 20; i++) {
                                        if (voters[i].checkCredentials(username1, password1)) {
                                            if (voters[i].isApproved()) {
                                                System.out.println("\n ----- Login successful -----");
                                                boolean logout2 = false;
                                                while (!logout2) {
                                                    
                                                    System.out.println("\n");
                                                    System.out.println("-------- Welcome to Voter Module --------");
                                                    System.out.println("\n1. View Detail");
                                                    System.out.println("2. Cast Vote");
                                                    System.out.println("3. Log Out");
                                                    
                                                    int choice2;
                                                    try {
                                                        System.out.print("\nEnter Your Choice: ");
                                                        choice2 = sc.nextInt();
                                                        sc.nextLine();
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("\n --> Invalid input. Please enter an integer.\n" + "-->Exception: " + e);
                                                        sc.nextLine();
                                                        continue;
                                                    }
                                                    switch (choice2) {
                                                        case 1:
                                                            if (voters[i] != null) {
                                                                voters[i].showdetails();
                                                            }
                                                            break;
                                                        case 2:
                                                            admin.showelections();
                                                            if(admin.getElections().size()!=0){
                                                            int choice4=0;
                                                            do {
                                                                try {
                                                                    System.out.print("\nChoose election:");
                                                                    choice4 = sc.nextInt();
                                                                    sc.nextLine();
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("\n --> Invalid input. Please enter an integer.\n" + "-->Exception: " + e);
                                                                    sc.nextLine();
                                                                    continue;
                                                                }
                                                                if (choice4 < 1 || choice4 > admin.getElections().size()) {
                                                                    System.out.print("\n---Invalid choice. Please choose again ---\n ");
                                                                }
                                                            } while (choice4 < 1 || choice4 > admin.getElections().size());

                                                            LocalDate endDate = LocalDate.parse(admin.getElection(choice4 - 1)[1],DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                                            LocalDate currentDate = LocalDate.now();
                                                           
                                                            if (currentDate.isAfter(endDate)) {
                                                                System.out.println("\n --- Sorry, the election has ended. You can't vote now. ---");
                                                            } else {
                                                                if (voters[i].hasVotedForElection(choice4 - 1)) {
                                                                    System.out.println("\n---> Voting is allowed only once. \n " + "---> You have already voted. \n");
                                                                } else {
                                                                    if (admin.getCandidates().size() == 0) {
                                                                        System.out.println("\n ------ No candidates registered yet -----");
                                                                    } else {
                                                                        System.out.println("\n---> Enter 0 to vote for NOTA\n");
                                                                        System.out.println("\n------ Candidates ---------\n");
                                                                        for (int j = 0; j < admin.getCandidates().size(); j++) {
                                                                            String[] candidate = admin.getCandidate(j);
                                                                            // Only Print the candidate which are associated with the chosen election
                                                                            if (candidate != null && candidate[0].equals(admin.getElection(choice4 - 1)[0])) {
                                                                                System.out.println("----Candidate " + (j + 1) + " details----");
                                                                                System.out.println("Name : " + candidate[1]);
                                                                                System.out.println("Party : "+ candidate[2]);
                                                                                System.out.println();
                                                                            }
                                                                        }
                                                                        System.out.println("\n--------- End ------------");
                                                                        
                                                                        int candidateNumber;
                                                                        try {
                                                                            System.out.print("\nEnter candidate number to vote: ");
                                                                            candidateNumber  = sc.nextInt();
                                                                            sc.nextLine();
                                                                        } catch (InputMismatchException e) {
                                                                            System.out.println("\n --> Invalid input. Please enter an integer.\n" + "-->Exception: " + e);
                                                                            sc.nextLine();
                                                                            continue;
                                                                        }
                                                                        
                                                                        // Included 0 because of NOTA
                                                                        if (candidateNumber >= 0 && candidateNumber <= admin.getCandidates().size()) {
                                                                            if(candidateNumber == 0){
                                                                                System.out.println("--> Vote casted for NOTA successfully.");
                                                                                voters[i].setHasVotedForElection(choice4 - 1, true);
                                                                                admin.voteForNOTA(choice4 - 1);
                                                                            }
                                                                            else{
                                                                            String[] candidate = admin.getCandidate(candidateNumber - 1);
                                                                            // Check if the candidate is associated with the chosen election
                                                                            if (candidate != null && candidate[0].equals(admin.getElection(choice4 - 1)[0])) {
                                                                                admin.voteForCandidate(candidateNumber - 1);
                                                                                voters[i].setHasVotedForElection(choice4 - 1, true);
                                                                            } else {
                                                                                System.out.println("\n ----- Candidate not associated with the chosen election -----");
                                                                            }
                                                                            }
                                                                        } else {
                                                                            System.out.println("\n ----- Invalid candidate number -----");
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                            break;
                                                        case 3:
                                                            logout2 = true;
                                                            break;
                                                        default:
                                                            System.out.println("\n ----- Invalid Choice -----");
                                                            break;
                                                    }
                                                }
                                                loggedin1 = true;
                                                found = true;
                                                break;
                                            } else {
                                                System.out.println("\n ---- Login Successful ---- \n");
                                                System.out.println("--> Your registration process is under verification.");
                                                System.out.println("--> Kindly wait for approval.");
                                                System.out.println("--> Have a nice day.");
                                                found = true;
                                                logout = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (!found) {
                                        System.out.println("\n --- Invalid username or password. Please try again ---");
                                    }
                                    break;
                                }
                                break;
                            case 3:
                                logout = true;
                                break;
                            default:
                                System.out.println("\n ---- Invalid Choice ----");
                                break;
                        }
                    }
                    break;
                case 3:
                System.out.println("\n--> Do you confirm that you want to exit ? Please enter 'y' for yes or 'n' for no.");
                System.out.print("--> Write here: ");
                String input = sc.nextLine();
    
                if (input.equals("y") || input.equals("Y")) {
                    System.out.println("\n-------- Exiting --------\n");
                    exit = true;
                } else if (input.equals("n") || input.equals("N")) {
                    exit = false;
                } else {
                    System.out.println("\n--> Invalid input. Please enter 'y' for yes or 'n' for no.");
                }
                    break;
                default:
                    System.out.println("\n ---- Invalid Choice ---- ");
                    break;
            }
            System.out.println("\n");
        }

        String filePath1 = "Elections.csv";
        try {
            String[] header = {"Election Name", "End Date","Winner","Voting Statistics","NOTA Votes"};
            writeCSV(admin.getElections(),admin.getNOTAVotes(),header, filePath1,admin.getVotingStatistics(),admin.getWinners());
            System.out.println(" ---> Elections.csv file saved successfully! \n");
        } catch (IOException e) {
            System.err.println("Error while writing CSV: " + e.getMessage());
        }

        String filePath2 = "Candidates.csv";
        try {
            String[] header = {"Election Name", "Candidate Name","Party Name","Gender","Mobile Number","City","State","Earned Votes"};
            writeCSV(admin.getCandidates(),admin.getVotes(),header, filePath2);
            System.out.println("---> Candidates.csv file saved successfully! \n");
        } catch (IOException e) {
            System.err.println("Error while writing CSV: " + e.getMessage());
        }
      
        String filePath3 = "Voters.csv";
        try {
            writeUserDetailsToCSV(voters, filePath3);
            System.out.println("---> Voters.csv file saved successfully! \n\n");
        } catch (IOException e) {
            System.err.println("Error while writing CSV: " + e.getMessage());
        }

        System.out.println("-------------- End -----------------\n\n\n");
    }
} 