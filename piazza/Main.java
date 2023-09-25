package piazza;

import java.sql.SQLException;

/**
 * The class that runs all the different controllers and solves the 5 usecases
 */

public class Main{

    public static void main(String[] args) throws SQLException {
        // Creating a PostController instance and solving usecase 1, 2 and 3
        PostController postCtrl1 = new PostController();
        postCtrl1.connect();
        postCtrl1.login();
        System.out.println(" ");
        postCtrl1.createThread(1, "Er eksamen Walskelig?", "white", "2021-03-24 11:30:42", "henrik@gmail.com");
        System.out.println(" ");
        postCtrl1.InstructorAnswer(1, "Den er nok ikke Walskelig, men antar du mente vanskelig. Hvis du er godt forberedt skal det nok gå bra!", "2021-03-24 12:42:01", "frode.ansatt@ntnu.no", 1);
        System.out.println(" "); 
        // Creating a SearchController instance and solving usecase 4
        SearchController searchCtrl = new SearchController();
        searchCtrl.connect();
        searchCtrl.searchForKeyword();
        System.out.println(" ");
        // Creating a StatisticsCtrl instance and solving usecase 5
        StatisticsController statCtrl = new StatisticsController();
        statCtrl.connect();
        statCtrl.printStatistics();
    }
    
}
