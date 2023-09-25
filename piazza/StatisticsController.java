package piazza;

import java.sql.*;

/**
 * The class that finds statistics over how many post that a user have created
 * and viewed
 */

public class StatisticsController extends DBConn {

        /**
         * Use case 5: "An instructor views statistics for users and how many posts 
         * they have read and how many they have created. 
         * These should be sorted on highest readposting numbers. 
         * The output is “username, number of posts read, number of posts created”. 
         * You don’t need to order by posts created, but the number should be displayed. 
         * The result should also include users which have not read or created posts."
         */

        public void printStatistics() {
                try {
                        Statement stmt = conn.createStatement();
                        // finds all users who created or viewed at least one post.
                        String query = "select * from (select user_mail, count(*) as createdPosts from"
                                        + " (select TID as ID, user_mail from Thread"
                                        + " union all select PID as ID, user_mail from Post) as created"
                                        + " group by user_mail) as noe left join"
                                        + " (select Email, count(*) as viewedPosts from"
                                        + " (select * from ViewThead union all"
                                        + " select * from ViewPost) as views group by Email) as noeannet"
                                        + " on noe.user_mail=noeannet.Email union"
                                        + " select * from (select user_mail, count(*) as createdPosts"
                                        + " from (select TID as ID, user_mail from Thread"
                                        + " union all select PID as ID, user_mail from Post) as created"
                                        + " group by user_mail) as noe right join"
                                        + " (select Email, count(*) as viewedPosts"
                                        + " from (select * from ViewThead union all"
                                        + " select * from ViewPost) as views" + " group by Email) as noeannet"
                                        + " on noe.user_mail=noeannet.Email"
                                        + " order by viewedPosts desc";
                        ResultSet rs = stmt.executeQuery(query);
                        System.out.println("Statistics: ");
                        // prints the user with corresponding statistics
                        while (rs.next()) {
                                if (rs.getString("user_mail") != null) {
                                        System.out.println(rs.getString("user_mail") + " created: "
                                                        + rs.getInt("createdPosts") + ", viewed: "
                                                        + rs.getInt("viewedPosts"));
                                } else if ((rs.getString("Email") != null)) {
                                        System.out.println(
                                                        rs.getString("Email") + " created: " + rs.getInt("createdPosts")
                                                                        + ", viewed: " + rs.getInt("viewedPosts"));
                                }
                        }
                        // Finds and prints users that haven't viewed or created any posts
                        String query2 = "select mail from User where mail not in ("
                                        + "select user_mail from (select TID as ID, user_mail from Thread union all select PID as ID, user_mail from Post) as created group by user_mail)"
                                        + " and mail not in ("
                                        + "select Email from (select * from ViewThead union all select * from ViewPost) as views group by Email)";
                        ResultSet rs2 = stmt.executeQuery(query2);
                        while (rs2.next()) {
                                System.out.println(rs2.getString("mail") + " created: 0, viewed: 0");
                        }
                } catch (Exception e) {
                        System.out.println("error" + e);
                }

        }

}
