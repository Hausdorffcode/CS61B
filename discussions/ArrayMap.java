import java.util.List;
import java.util.Arrays;
import java.util.Iterator;

/**Goal 1: Create a class ArrayMap with the following methods:
  *put(key, value): Associate key with value.
  *containsKey(key): Checks to see if arraymap contains the key.
  *get(key): Returns value, assuming key exists..
  *keys(): Returns an array of keys.*/
  
public class ArrayMap<K, V>{
	private K[] keys;
	private V[] values;
	private int size;
	
	public ArrayMap(){
		keys = (K[]) new Object[100];
		values = (V[]) new Object[100];
		size = 0;
	}
	
	/*Finds a given key and returns index*/
	private int findKey(K key){
		for (int i = 0; i < size; i++){
			if(keys[i].equals(key)){
				return i;
			}
		}
		return -1;
	}
	public void put(K key, V val){
		int loc = findKey(key);
		if (loc < 0){
			keys[size] = key;
			values[size] = val;
			size++;
		}
		else{
			values[loc] = val;
		}
	}
	
	/*assuming key exists..*/
	public V get(K key){
		if (findKey(key) < 0){
			throw new IllegalArgumentException(key + "was not a vaild key");
		}
		return values[findKey(key)];
	}
	
	public boolean containsKey(K key){
		int i = findKey(key);
		return i > -1;
	}
	
	public List<K> keys(){
		if (size == 0){
			return null;
		}
		return Arrays.asList(keys).subList(0, size);
	}
}