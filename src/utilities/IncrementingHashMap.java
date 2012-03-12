package utilities;

import java.util.HashMap;

public class IncrementingHashMap<T> extends HashMap<T, java.lang.Double> {
	
	T max_key;
	double max_value;
	
	public void increment(T key) {
		increment(key, 1.0);
	}
	
	public void increment(T key, double value) {
		double new_count;
		if (!containsKey(key)) {
			new_count = value;
			put(key, new_count);
			
		} else {
			new_count = get(key) + value;
			put(key, new_count);
		}
		if (new_count > max_value) {
			max_key = key;
			max_value = new_count;
		}
	}
	
	
	
	public T getTopKey() {
		if (max_value == 0) return null; 
		return max_key;
	}
	
}	
