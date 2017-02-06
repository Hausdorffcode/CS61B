import java.util.HashMap;

/**
 * Created by huangqiming on 2017/2/4.
 */
public class Fibonacci  {
    HashMap<Integer, Integer> map = new HashMap<>();

    /**f(0) = 0, f(1) = 1, f(2) = 1,....*/
    public int fib(int n) {
        map.put(0, 0);
        map.put(1, 1);
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int val = fib(n-2) + fib(n-1);
        map.put(n, val);
        return val;
    }

    public static void main(String[]  arg) {
        Fibonacci f = new Fibonacci();
        System.out.println(f.fib(6));

    }
}
