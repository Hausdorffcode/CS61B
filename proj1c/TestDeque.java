
/**
 * Created by huangqiming on 2017/1/17.
 */
public class TestDeque {

    public static void main(String[] args){
        Deque<Character> dc = Palindrome.wordToDeque("word");
        dc.printDeque();

        System.out.println("----------------");
        System.out.println(Palindrome.isPalindrome("racecar"));
        System.out.println(Palindrome.isPalindrome("a"));
        System.out.println(Palindrome.isPalindrome(""));
        System.out.println(Palindrome.isPalindrome("horse"));

        System.out.println("----------------");
        OffByOne obo = new OffByOne();
        System.out.println(obo.equalChars('a', 'b'));
        System.out.println(obo.equalChars('a', 'e'));

        System.out.println("-----------------");
        OffByN obn = new OffByN(5);
        System.out.println(obn.equalChars('a', 'f'));

        System.out.println("-----------------");
        System.out.println(Palindrome.isPalindrome("flask", obo));
        System.out.println(Palindrome.isPalindrome("flake", obo));
    }

}
