package piazza;

import java.sql.*;

public class SearchController extends DBConn{

/** 
 * The Searches throw the database for an keyword 
 * and return the corresponding Thread or Post
 */

        /** Usecase 4
         *  A student searches for posts with a specific keyword “WAL”. 
         * The return value of this should be a list of ids of posts matching the keyword.
        */

    public void searchForKeyword() {

        try {   
                Statement stmt = conn.createStatement();
                // In accordance to use case 4, the funtion searches for the keyword "WAL"
                String query = "SELECT 'Thread' as type, TID as ID, text from Thread where text like '%WAL%' union select 'Post' as type, PID, text  from Post  where text like '%WAL%'";
                ResultSet rs = stmt.executeQuery(query);

                // Prints all posts that contains the searchword 'WAL', while there is any result
                System.out.println("All posts that contains the searchword 'WAL':");
                while (rs.next()) {
                    System.out.println(rs.getString("type")+" "+rs.getInt("ID")+": "+rs.getString("text"));
                }
        } catch (Exception e) {
                System.out.println("error" + e);
        }

}

}
