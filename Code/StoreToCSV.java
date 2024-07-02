import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StoreToCSV {

    // For Candidates 
    public static void writeCSV(ArrayList<String[]> arrayList,ArrayList<Integer> numarray, String[] header, String filePath) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true));

        StringBuilder headerLine = new StringBuilder();

        for (int i = 0; i < header.length; i++) {
            headerLine.append(header[i]);
            if (i < header.length - 1) {
                headerLine.append(",");
            }
        }

        writer.write(headerLine.toString());
        writer.newLine(); 

        for (int i = 0; i < arrayList.size(); i++) {
            String[] row = arrayList.get(i);
            StringBuilder csvLine = new StringBuilder();
            for (int j = 0; j < row.length; j++) {
                csvLine.append(row[j]).append(","); 
            }
            // numarray is for candidates votes
            csvLine.append(numarray.get(i));
            writer.write(csvLine.toString());
            writer.newLine(); 
        }

        writer.close();
    }

    // For elections 
    // overloading method
    public static void writeCSV(ArrayList<String[]> arrayList,ArrayList<Integer> numarray, String[] header, String filePath,ArrayList<Double> Stat,ArrayList<String> winner) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true));

        StringBuilder headerLine = new StringBuilder();

        for (int i = 0; i < header.length; i++) {
            headerLine.append(header[i]);
            if (i < header.length - 1) {
                headerLine.append(",");
            }
        }
        writer.write(headerLine.toString());
        writer.newLine(); 

        for (int i = 0; i < arrayList.size(); i++) {
            String[] row = arrayList.get(i);
            StringBuilder csvLine = new StringBuilder();
            for (int j = 0; j < row.length; j++) {
                csvLine.append(row[j]).append(",");
            }
            // numarray = NOTA votes
            csvLine.append(winner.get(i));
            csvLine.append(",").append(Stat.get(i));
            csvLine.append(",").append(numarray.get(i));
            writer.write(csvLine.toString());
            writer.newLine();
        }

        writer.close();
    }

    // For Voters
    public static void writeUserDetailsToCSV(Voter[] voterList, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true));

        // Writing header
        writer.write("Username,Name,Date of Birth,Mobile Number,Gender,City,State,Approved");
        writer.newLine();

        // Writing user details
        for (Voter voter : voterList) {
            StringBuilder csvLine = new StringBuilder();
            csvLine.append(voter.getUsername()).append(",");
            csvLine.append(voter.getFullName()).append(",");
            csvLine.append(voter.getDateOfBirth()).append(",");
            csvLine.append(voter.getMobileNumber()).append(",");
            csvLine.append(voter.getGender()).append(",");
            csvLine.append(voter.getCity()).append(",");
            csvLine.append(voter.getState()).append(",");
            csvLine.append(voter.isApproved() ? "Yes" : "No");
            writer.write(csvLine.toString());
            writer.newLine();
        }

        writer.close();
    }

}