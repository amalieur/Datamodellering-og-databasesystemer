package piazza;

import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.*;

/**
 * The controller for the first three use cases
 */
public class PostController extends DBConn {

    /**
     * Use case 1: "A student logs into the system"
     * 
     * @throws SQLException
     */
    public void login() throws SQLException {

        Scanner myObj = new Scanner(System.in);
        // Creates a Scanner object to make it possible to take input from user
        System.out.println("Enter username");

        String mail = myObj.nextLine(); // Read user input
        System.out.println("Username is: " + mail); // Output user input

        String sql1 = "SELECT mail, passord FROM User WHERE mail ='" + mail + "'";
        // Retrieves the mail from database to check if the user exists

        PreparedStatement stmt = conn.prepareStatement(sql1);
        ResultSet rs1 = stmt.executeQuery(sql1);

        // Extract data from results
        while (rs1.next()) {
            // Retrieves values from database
            int passord = rs1.getInt("passord");
            String stud_mail = rs1.getString("mail");

            System.out.println("Enter password");

            int inputPass = myObj.nextInt(); // Read user input

            if (inputPass == passord) {
                // Checks if the input matches the password in tha database
                System.out.println("User logged in: " + stud_mail);
            } else {
                System.out.println("Sorry, wrong password");
            }
        }
        myObj.close(); // Close the scanner

        System.out.println("Getting your Courses...");
        String sql2 = "SELECT name FROM Course NATURAL JOIN TakesCourse WHERE stud_mail ='" + mail + "'";
        // Finds the courses take by the student that just logged in. 
        // Used for test purposes
        PreparedStatement stmt2 = conn.prepareStatement(sql2);
        ResultSet rs2 = stmt2.executeQuery(sql2);

        while (rs2.next()) {
            // Retrieve by column name
            String name = rs2.getString("name");
            // Display values
            System.out.println("User registered in the following Courses: " + name);
        }
    }

    /**
     * 
     * Use case 2: "A student makes a post belonging to the folder 'Exam' and tagged
     * with 'Question'"
     * 
     * @param TID Thread ID
     * @param color Color
     * @param ThreadTime When the thread was posted
     * @param user_mail The mail of the user who made the thread
     * @param FID ID of the Folder the Thread belongs to
     * @throws SQLException
     */
    public void createThread(int TID, String text, String color, String ThreadTime, String user_mail)
            throws SQLException {

        // Queries to retrieve and insert values
        String folder = "SELECT FID from Folder where name='Exam'";
        String preQueryStatement = "INSERT INTO Thread (TID, text, color, ThreadTime, user_mail, FID) VALUES (?,?,?,?,?,?)";
        String statement1 = "INSERT INTO GroupedBy (FID, TID) VALUES (?, ?)"; 
        String statement2 = "INSERT INTO Tagged (TID, TagID) VALUES (?, ?)";
        String tagging = "SELECT TagID from Tags WHERE name='Question'";

        PreparedStatement prepState = conn.prepareStatement(preQueryStatement);
        PreparedStatement prepState1 = conn.prepareStatement(statement1);
        PreparedStatement prepState2 = conn.prepareStatement(statement2);
        PreparedStatement state = conn.prepareStatement(tagging);
        PreparedStatement state2 = conn.prepareStatement(folder);

        ResultSet rs3 = state2.executeQuery(folder);
        ResultSet rs2 = state.executeQuery(tagging);
        
        int FID = rs3.getInt("FID");

        while (rs2.next()) {
            // Retrieve by column name
            int TagID = rs2.getInt("TagID");

            //setter methods
            prepState.setInt(1, TID);
            prepState.setString(2, text);
            prepState.setString(3, color);
            prepState.setString(4, ThreadTime);
            prepState.setString(5, user_mail);
            prepState.setInt(6, FID);

            prepState1.setInt(1, FID);
            prepState1.setInt(2, TID);

            prepState2.setInt(1, TID);
            prepState2.setInt(2, TagID);

            prepState.execute();
            prepState1.execute();
            prepState2.execute();
            System.out.println("You succesfully posted the Thread to the folder 'Exam' with the tag 'Question'");
            // Executes and gives user feedback
        }
        
    }

    /**
     * Use case 3: "An Instructor replies to a post belonging to the Folder 'Exam'"
     * 
     * @param PID ID of the post the Instructor makes
     * @param text The textual answer
     * @param PostTime The time when the Post was posted
     * @param user_mail The mail of the user that makes the post
     * @param TID The ID of the Thread the Post answers
     * 
     * @throws SQLException
     */

    public void InstructorAnswer(int PID, String text, String PostTime, String user_mail, int TID) throws SQLException {

        // Queries to insert values
        String query = "INSERT INTO ViewThead (Email, TID, liked) VALUES (?,?,?)";
        String preQueryStatement = "INSERT INTO Post (PID, text, PostTime, user_mail, TID) VALUES (?,?,?,?,?)";
        String preQueryStatement2 = "INSERT INTO InstructorAnswer (PID) VALUES (?)";

        PreparedStatement prep = conn.prepareStatement(query);
        PreparedStatement prepState = conn.prepareStatement(preQueryStatement);
        PreparedStatement prepState2 = conn.prepareStatement(preQueryStatement2);

        prep.setString(1, user_mail);
        prep.setInt(2, TID);
        prep.setInt(3, 0);
        prep.execute();
        System.out.println(user_mail + " viewed a Thread " + TID);

        prepState.setInt(1, PID);
        prepState.setString(2, text);
        prepState.setString(3, PostTime);
        prepState.setString(4, user_mail);
        prepState.setInt(5, TID);
        prepState2.setInt(1, PID);
        prepState.execute();
        prepState2.execute();
        System.out.println(user_mail + " replied to the Thread");
        // Execute queries and give user feedback
    }
} 
