package collect;

public class IterUtils {
	public static <T> T find(Iterable<T> elems, Predicate<T> p){
		for (T item : elems) {
			if(p.invoke(item))
				return item;
		}
		return null;
	}
	public static <T> int findIndex(Iterable<T> elems, Predicate<T> p){
		int i = 0;
		for (T item : elems) {
			if(p.invoke(item)){
				return i;
			}
			i++;
		}
		return -1;
	}
}
