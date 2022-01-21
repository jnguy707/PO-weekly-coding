import java.util.List;

public class TextTwister {

    public static int totalPoints(List<String> guessedWords, String word) {
        int points = 0;
        final int threeLetter = 1;
        final int fourLetter = 2;
        final int fiveLetter = 3;
        final int sixLetter = 54;

        LetterInventory wordInv = new LetterInventory(word);
        for (String guessedWord : guessedWords) {
            LetterInventory guessInv = new LetterInventory(guessedWord);
            LetterInventory diffInv = wordInv.subtract(guessInv);
            switch (diffInv.size()) {
                case 0 -> points += sixLetter;
                case 1 -> points += fiveLetter;
                case 2 -> points += fourLetter;
                case 3 -> points += threeLetter;
                default ->  points += 0;
            }
        }
        return points;
    }

    private static class LetterInventory {
        private static final int ALPHABET_SIZE = 26;
        // Total letters of word
        private int sum = 0;
        // Array: the count of the number of the alphabet
        private int[] count;

        public LetterInventory (String data){
            count = new int[ALPHABET_SIZE];
            data = data.toLowerCase();
            for(int i = 0; i <data.length();i++){
                if(Character.isLetter(data.charAt(i))){
                    count[data.charAt(i)-97]++;
                    sum++;
                }
            }
        }

        public int get(char letter){
            if(!Character.isLetter(letter)){
                throw new IllegalArgumentException("non-alphabetic character: " + letter);
            }
            return count[Character.toLowerCase(letter)-97];
        }

        public void set(char letter, int value){
            if ((int)(letter-'a') < ALPHABET_SIZE && (int) (letter - 'a') >= 0 && value >= 0){
                sum += value - count[(int)(letter - 'a')];
                count[(int) (letter - 'a')] = value;
            } else {
                throw new IllegalArgumentException("Value too low or incorrect character. Letter: " + letter +", Value: " +value);
            }
        }

        public int size(){
            return sum;
        }

        public boolean isEmpty(){
            return sum == 0;
        }

        @Override
        public String toString(){
            String inventory = "[";
            for(int i = 0;i < ALPHABET_SIZE;i++){
                char ch = (char)(i+97);;
                for(int j=0; j<count[i];j++){
                    inventory += ch;
                }
            }
            return inventory +"]";
        }

        // Result is similar adding to two strings
        public LetterInventory add(LetterInventory other) {
            String fusion = "";
            LetterInventory total = new LetterInventory(fusion);
            for(int i = 0; i < ALPHABET_SIZE;i++){
                total.count[i] = this.count[i]+other.count[i];
            }
            total.sum = this.sum + other.sum;
            return total;
        }

        /*this method allows you to subtract one LetterInventory from the other to get a new LetterInventory
        with new sum, toString, etc...*/
        public LetterInventory subtract(LetterInventory other){
            String fusion = "";
            LetterInventory difference = new LetterInventory(fusion);
            for(int i = 0; i < ALPHABET_SIZE;i++){
                difference.count[i] = this.count[i] - other.count[i];
                if(difference.count[i]<0){
                    return null;
                }
            }
            difference.sum = this.sum - other.sum;
            return difference;
        }
    }
        /*
    *
    3-letter words are 1 pt
    4-letter words are 2 pts
    5-letter words are 3 pts
    6-letter words are 4 pts + 50 pt bonus (for unscrambling the word)
    Remember that invalid words (words that cannot be formed from the 6-letter unscrambled words) count as 0 pts.

    Examples
    totalPoints(["cat", "create", "sat"], "caster") ➞ 2
    // Since "create" is an invalid word.

    totalPoints(["trance", "recant"], "recant") ➞ 108
    // Since "trance" and "recant" score 54 pts each.

    totalPoints(["dote", "dotes", "toes", "set", "dot", "dots", "sted"], "tossed") ➞ 13
    // Since 2 + 3 + 2 + 1 + 1 + 2 + 2 = 13

    Notes:
    If a 6-letter word has multiple anagrams, count each 6-letter unscramble as an additional 54 pts.
    For example, if the word is arches, and the player guessed arches and chaser, add 108 pts for both words.
    *
    * */
}
