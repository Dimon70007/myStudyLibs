package homework;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayBasedMap<K, V> implements Map<K, V> {

    private double loadFactor;
    private final static double DEFAULT_LOADFACTOR=0.75;
    private int capacity;
    private final static int DEFAULT_CAPACITY=Integer.MAX_VALUE/10000000;
    private final static int MULTIPLIER =10;
    private int threshold;
    private ArrayList<List<Pair>> values;
    private int size;


    public ArrayBasedMap(final int capacity, final double loadFactor){
        this.loadFactor=loadFactor;
        this.capacity=capacity;
        this.createNullsList(capacity);
        this.threshold=(int)(capacity*loadFactor);

    }
    public ArrayBasedMap(final int capacity){
        this(capacity,DEFAULT_LOADFACTOR);
    }

    public ArrayBasedMap(){
        this(DEFAULT_CAPACITY,DEFAULT_LOADFACTOR);
    }


    public ArrayBasedMap(Map<? extends K,? extends V> m){

        this((int)(DEFAULT_CAPACITY*DEFAULT_LOADFACTOR)- m.size() > m.size()*MULTIPLIER?
                DEFAULT_CAPACITY:
                m.size()*MULTIPLIER);
        this.putAll(m);
    }

    private void createNullsList(final int capacity){
        this.size=0;
        this.values=new ArrayList<>(capacity);
        for(int i=0;i<capacity;i++) {
            this.values.add(null);
        }
    }
    @Override
    public int size() {
        // BEGIN (write your solution here)
        return this.size;
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return size()==0;
        // END
    }

    @Override
    public boolean containsKey(Object key) {
        // BEGIN (write your solution here)
        if(!isEmpty()) {
            List<Pair> list = values.get(newIndex((K) key));
            if (list != null) {
                for (Pair pair : list) {
                    if (key == null ? pair.getKey() == null :
                            key.equals(pair.getKey()))
                        return true;
                }
            }
        }
        return false;
        // END
    }

    @Override
    public boolean containsValue( Object value) {
        // BEGIN (write your solution here)
        if(!isEmpty()) {
            for (List<Pair> list : values) {
                if (list != null) {
                    if (containsValueInList((V) value, list))
                        return true;
                }
            }
        }
        return false;
        // END
    }

    private boolean containsValueInList(final V value, final List<Pair> list) {
        for(Pair pair:list){
            if(value==null? pair.getValue()==null://если оба нулы
                    value.equals(pair.getValue()))//если оба не нулы
                return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        // BEGIN (write your solution here)
        if(!isEmpty()) {
            List<Pair> list = values.get(newIndex((K) key));
            if (list != null) {
                for (Pair pair : list) {
                    if (key == null ? pair.getKey() == null :
                            key.equals(pair.getKey()))
                        return pair.getValue();
                }
            }
        }
        return null;
        // END
    }

    @Override
    public V put(final K key, final V value) {
        // BEGIN (write your solution here)
        checkCapacity();
        Pair newPair=new Pair(key,value);
        if(!containsKey(key)) {
            putPairIfNoKey(key,newPair);
            return null;
        }
        return putPairIfContainsKey(key,newPair);
        // END
    }

    private void putPairIfNoKey(final K key, final Pair newPair) {
        List<Pair> list=values.get(newIndex(key));
        if(list==null) {
            values.set(newIndex(key), createNewListPair(newPair));
        }else {
            list.add(newPair);
        }
        size++;
    }

    private V putPairIfContainsKey(final K key, final Pair newPair) {
        List<Pair> listPair=values.get(newIndex((K)key));
        V value;
        for(Pair pair:listPair){
            if(key==null? pair.getKey()==null:
                    key.equals(pair.getKey())){
                value=pair.getValue();
                pair.value=newPair.getValue();
                return value;
            }
        }
        return null;
    }

    private List<Pair> createNewListPair(final Pair newPair) {
        List<Pair> list=new LinkedList<Pair>();
        list.add(newPair);
        return list;
    }

    private  void checkCapacity() {
        if(threshold<=size)
            resize();
    }

    private  void resize() {
        if (Integer.MAX_VALUE / MULTIPLIER < capacity) {
            threshold = Integer.MAX_VALUE;
            capacity = threshold;
        } else {
            capacity *= MULTIPLIER;
            threshold = (int) (capacity * loadFactor);
        }
        List<List<Pair>> oldValues=values;
        transfer(oldValues);
    }

    private void transfer(List<List<Pair>> oldValues) {
        createNullsList(capacity);
        oldValues.stream().filter(list -> list != null).forEach(list -> {
//            for(List<Pair> list:oldValues) { //короче было написано так, а IDE предложила заменить строкой сверху
//                if (list != null) {
            for (Pair pair : list)
                put(pair.getKey(), pair.getValue());
        });

    }


    private int newIndex( K key){
        if(key==null)
            return 0;
        return hashOfKey(key)%capacity;
    }

    private int hashOfKey(final K key){
        int h=key.toString().hashCode();
        h=h ^ (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    @Override
    public V remove(Object key) {
        // BEGIN (write your solution here)
        List<Pair> list=values.get(newIndex((K)key));
        if(list!=null) {
            Pair pair;
            Iterator<Pair> it=list.iterator();
            while (it.hasNext()) {
                pair = it.next();
                if (key==null? pair.getKey()==null:
                        ((K)key).equals(pair.getKey())) {
                    V value = pair.getValue();
                    it.remove();
                    size--;
                    return value;
                }
            }
        }
        return null;
        // END
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : (Set<Entry<K, V>>)(Set)m.entrySet())
            put(e.getKey(), e.getValue());

    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        if(isEmpty()){
            throw new UnsupportedOperationException();
        }
        values=new ArrayList<>(DEFAULT_CAPACITY);
        createNullsList(DEFAULT_CAPACITY);
        capacity=DEFAULT_CAPACITY;
        threshold=(int)(capacity*loadFactor);
        // END
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<>();
        //for (List<Pair> list: values)
        //  if(list!=null){ было через цикл, IDE предложила заменить на лямбду
        values.stream().filter(list -> list != null).forEach(list -> {
        //      for(Pair p:list) было через цикл, IDE предложила заменить на лямбду
        //          keys.add(p.getKey());
            keys.addAll(list.stream().map(Pair::getKey).collect(Collectors.toList()));
        });
        return keys;
    }

    @Override
    public Collection<V> values() {
        // BEGIN (write your solution here)
        Collection<V> vals=new ArrayList<>();
        if (isEmpty())
            return vals;
//        for(List<Pair> list:values) {
//            if (list != null) {
        values.stream().filter(list -> list != null).forEach(list -> {
//              for (Pair pair : list)
//                vals.add(pair.getValue());
            vals.addAll(list.stream().map(Pair::getValue).collect(Collectors.toList()));
        });
        return vals;
//        return new Valls();
        // END
    }

//    private class Valls<V> extends AbstractCollection<V> implements Iterable<V>{//для того чтобы
//        //не имплементить метод foreach для данной коллекции нужно всего то заимплементить интерфейс Iterable<V> при чем <V>
//        // нужно чтобы класс при итерации по нему возвращал не тип Object, а  V
//
//        @Override
//        public int size() {
//            return ArrayBasedMap.this.size();
//        }
//
//        @Override
//        public boolean isEmpty() {
//            return ArrayBasedMap.this.isEmpty();
//        }
//
//        @Override
//        public boolean contains(Object o) {
//            return containsValue(o);
//        }
//
//        @Override
//        public Iterator<V> iterator() {
//
//            return new Iterator<V>() {
//                private Iterator<Pair> it=ArrayBasedMap.this.values.iterator();
//
////                @Override
////                public void forEachRemaining(Consumer<? super V> action) {
////                    Objects.requireNonNull(action);
////                    while (hasNext())
////                        action.accept(next());
////                }
//
//                @Override
//                public boolean hasNext() {
//                    return it.hasNext();
//                }
//
//                @Override
//                public void remove() {
//                    it.remove();
//                }
//
//                @Override
//                public V next() {
//                    return (V) it.next().getValue();
//                }
//            };
//
//        }
//
////        @Override
////        public void forEach(Consumer<? super V> action) {
////            iterator().forEachRemaining(action);
////        }
//
//        @Override
//        public boolean remove(Object o) {
//            V value;
//            Iterator<V> it=iterator();
//            while (it.hasNext()){
//                value=it.next();
//                if(value == null? o==null: value.equals(o)) {
//                    it.remove();
//                    return true;
//                }//if did not return then value does not exist in map
//            }
//            return false;
//        }
//
//        @Override
//        public boolean removeAll(Collection<?> c) {
//            ArrayBasedMap.this.checkNull(c);
//            int modifications=0;
//            for (Object item : c) {
//                if(remove(item))
//                    modifications++;
//                //if did not return then all c items does not contains in map
//            }
//            return modifications>0;
//        }
//
//        @Override
//        public boolean retainAll(Collection<?> c) {
//            ArrayBasedMap.this.checkNull(c);
//            int modifications=0;
//            while (iterator().hasNext()) {
//                if(!c.contains(iterator().next())){
//                    iterator().remove();
//                    modifications++;
//                }
//            }
//            return modifications>0;
//        }
//
//        @Override
//        public void clear() {
//            ArrayBasedMap.this.clear();
//        }
//
//    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> setPair=new HashSet<Entry<K,V>>();
        if(!isEmpty()){
            values.stream().filter(list -> list != null).forEach(setPair::addAll);
        }
        return setPair;
    }

    private class Pair implements Map.Entry<K, V> {

        private final K key;

        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            final V oldValue = this.value;

            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            Map.Entry<K, V> pair = (Map.Entry<K, V>) o;

            if (key != null ? !key.equals(pair.getKey()) : pair.getKey() != null) return false;
            return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);

        }

        @Override
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }
    }
}
