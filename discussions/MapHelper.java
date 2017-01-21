import java.util.List;
import java.util.LinkedList;

public class MapHelper{
	public static <J, D> D get(ArrayMap<J, D> am, J k){
		if(am.containsKey(k)){
			return am.get(k);
		}
		return null;
	}
	
	public static <K extends Comparable<K>, V> K maxKey(ArrayMap<K, V> am){
		List<K> keys = am.keys();
		K maxKey = keys.get(0);
		for (int i = 1; i < keys.size(); i++){
			int cmp;
			cmp = keys.get(i).compareTo(maxKey);
			if(cmp > 0){
				maxKey = keys.get(i);
			}
		}
		return maxKey;
	}
	
	public static void main(String[] args){
		ArrayMap<Integer, String> ismap = new ArrayMap<Integer, String>();
		ismap.put(5, "hello");
		ismap.put(10, "ketchep");
		System.out.println(MapHelper.get(ismap, 5));
	}
}