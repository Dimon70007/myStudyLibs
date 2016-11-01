package main.java.homework;

/**
 * Created by OTBA}|{HbIu` on 14.09.16.
 */
import java.util.*;

public class HashSet<T> implements Set<T> {

    private static final Boolean EXIST = true;

    private final Map<T, Boolean> elements = new HashMap<>();

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return elements.size();
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return elements.isEmpty();
        // END
    }

    @Override
    public boolean contains(Object o) {
        // BEGIN (write your solution here)
        return elements.containsKey(o);
        // END
    }

    @Override
    public Iterator<T> iterator() {
        // BEGIN (write your solution here)
        return elements.keySet().iterator();
        // END
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        return elements.keySet().toArray();
        // END
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        return elements.keySet().toArray(a);
        // END
    }

    @Override
    public boolean add(T t) {
        // BEGIN (write your solution here)
        return elements.put(t, EXIST)!=EXIST;
        // END
    }

    @Override
    public boolean remove(Object o) {
        // BEGIN (write your solution here)
        return elements.remove(o)==EXIST;
        // END
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // BEGIN (write your solution here)
        boolean result=true;
        for(Object o:c){
            if(!contains(o))
                result=false;
        }
        return result;
        // END
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        // BEGIN (write your solution here)
        boolean modified=false;
        for(T t:c){
            if(add(t))
                modified=true;
        }
        return modified;
        // END
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // BEGIN (write your solution here)

        boolean result=false;
        for(Object o:c){
            if(remove(o))
                result=true;
        }
        return result;
        // END
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // BEGIN (write your solution here)
        boolean result=false;
        for(Object o:this) {
            if (!contains(o)) {
                remove(o);
                result = true;
            }
        }
        return result;
        // END
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        elements.clear();
        // END
     }

}
