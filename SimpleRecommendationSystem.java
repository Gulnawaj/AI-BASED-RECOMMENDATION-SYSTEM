import java.util.HashMap;
import java.util.Map;
                     //THIS IS THE FOURTH TASK(AI BASED RECOMMENDATION SYSTEM) 
                     //IN THIS , WE HAVE MADE A SIMPLE MOVIE RECOMMENDATION SYSTEM.....
public class SimpleRecommendationSystem {

    public static void main(String[] args) {

        // Users aur unke movie ratings
        Map<String, Map<String, Integer>> userRatings = new HashMap<>();

        Map<String, Integer> user1 = new HashMap<>();
        user1.put("Avengers", 5);
        user1.put("Spiderman", 3);
        user1.put("Batman", 4);

        Map<String, Integer> user2 = new HashMap<>();
        user2.put("Avengers", 5);
        user2.put("Spiderman", 1);
        user2.put("Superman", 4);
        user2.put("Deadpool", 5);

        Map<String, Integer> user3 = new HashMap<>();
        user3.put("Spiderman", 4);
        user3.put("Batman", 5);
        user3.put("Deadpool", 4);

        userRatings.put("Gulnawaj", user1);
        userRatings.put("Ravi", user2);
        userRatings.put("Sara", user3);

        // New user data
        Map<String, Integer> newUser = new HashMap<>();
        newUser.put("Avengers", 5);
        newUser.put("Spiderman", 2);

        //  Find most similar user
        String similarUser = null;
        double highestSimilarity = -1;

        for (String user : userRatings.keySet()) {
            double sim = calculateSimilarity(newUser, userRatings.get(user));
            if (sim > highestSimilarity) {
                highestSimilarity = sim;
                similarUser = user;
            }
        }

        System.out.println("Most similar user: " + similarUser);

        // Recommend movies user has not seen
        Map<String, Integer> similarUserRatings = userRatings.get(similarUser);

        System.out.println("\nRecommended Movies:");
        for (String movie : similarUserRatings.keySet()) {
            if (!newUser.containsKey(movie)) {
                System.out.println(movie + " (Rated: " + similarUserRatings.get(movie) + ")");
            }
        }
    }

    //Similarity calculation using simple score match
    private static double calculateSimilarity(Map<String, Integer> userA, Map<String, Integer> userB) {
        double sum = 0;
        int count = 0;

        for (String movie : userA.keySet()) {
            if (userB.containsKey(movie)) {
                sum += userA.get(movie) * userB.get(movie);
                count++;
            }
        }

        return (count == 0) ? 0 : sum / count;
    }
}
