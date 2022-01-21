import java.util.ArrayList;
import java.util.List;

class Application {
    public static void main(String[] args) {
        List<String> inputWords = new ArrayList<>();
        inputWords.add("trance");
        inputWords.add("recant");
        String word = "recant";

        int points = TextTwister.totalPoints(inputWords,word);
        System.out.println(points);
    }
}
