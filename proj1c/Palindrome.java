/**
 * Created by huangqiming on 2017/1/17.
 */
public class Palindrome {
    public static Deque<Character> wordToDeque(String word){
        Deque<Character> dq = new LinkedListDequeSolution<>();
        for(int i = 0; i < word.length(); i++){
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public static boolean isPalindrome(String word){
        int n = word.length();
        if (n <= 1){
            return true;
        }
        int mid = n / 2;
        boolean isTrue = true;
        for (int i = 0; i < mid; i++){
            if (word.charAt(i) != word.charAt(n-1-i)){
                isTrue = false;
            }
        }
        return isTrue;
    }

    public static boolean isPalindrome(String word, CharacterComparator cc){
        int n = word.length();
        if (n <= 1){
            return true;
        }
        int mid = n / 2;
        boolean isTrue = true;
        for (int i = 0; i < mid; i++){
            if (!cc.equalChars(word.charAt(i) ,word.charAt(n-1-i))){
                isTrue = false;
            }
        }
        return isTrue;
    }
}
