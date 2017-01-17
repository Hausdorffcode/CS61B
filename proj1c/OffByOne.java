/**
 * Created by huangqiming on 2017/1/17.
 */
public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y){
        return Math.abs(x-y) == 1;

    }
}
