package homework;

import java.util.*;

public class ArrayBasedMap<K, V> implements Map<K, V> {

    private List<Pair> values = new ArrayList<Pair>();

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return values.size();
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
        checkNull(key);
        Iterator<Pair> iterator=values.iterator();
        Pair pair;
        while(iterator.hasNext()){
            pair=iterator.next();
                if (pair.getKey().equals(key))
                    return true;
        }
        return false;
        // END
    }

    @Override
    public boolean containsValue(Object value) {
        // BEGIN (write your solution here)
        Iterator<Pair> iterator=values.iterator();
        Pair pair;
        while(iterator.hasNext()){
            pair=iterator.next();
                if ( pair.getValue()==null? value==null: pair.getValue().equals(value)) {
                    return true;
                }
        }
        return false;
        // END
    }

    @Override
    public V get(Object key) {
        // BEGIN (write your solution here)
        checkNull(key);
        Iterator<Pair> iterator=values.iterator();
        Pair pair;
        while(iterator.hasNext()){
            pair=iterator.next();
            if(pair.getKey().equals(key))
                    return pair.getValue();
        }
        return null;
        // END
    }

    @Override
    public V put(K key, V value) {
        // BEGIN (write your solution here)
        checkNull(key);
        Pair newPair=new Pair(key,value);
        Iterator<Pair> iterator=values.listIterator();
        Pair pair;
        while(iterator.hasNext()){
            pair=iterator.next();
            if(pair.getKey().equals(key)) {
                return pair.setValue(value);
            }//if did not return then key does not exist in map
        }
        values.add(newPair);
        return null;
        // END
    }
//    //my method
//    private int newIndex(final Object key){
//        //если у нас уже есть элемент с одинаковым ключом
//        // - мы вызываем другой алгоритм хэширования уже от ключа
//        // то есть для доступа к объекту у которого 4 раза выпал одинаковый ключ
//        // надо 4 раза вызвать другой метод
//        //при чем надо ключ выбирать из элементов за пределами массива
//        //пока сделаю реализацию без хэшей:))
//        try {
//            return Integer.valueOf(key.toString());
//        }catch (ClassCastException e){
//            throw new IllegalArgumentException();
//        }
//    }
//    //my method
//    private int hashOfKey(final K key){
//        if (key==null)
//            return 0;
//
//        return key.toString().hashCode();
//
//    }
    //my method
    private void checkNull(Object item){
        if(item==null)
            throw new NullPointerException();
    }

    @Override
    public V remove(Object key) {
        // BEGIN (write your solution here)
        checkNull(key);
        Pair tmpPair;
        Iterator<Pair> iterator=values.iterator();
        Pair pair;
        while(iterator.hasNext()){
            pair=iterator.next();
            if(pair.getKey().equals(key)) {
                tmpPair=new Pair(null,pair.getValue());
                iterator.remove();
                return tmpPair.getValue();
            }//if did not return then key does not exist in map
        }
        return null;


        // END
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : (Set<Map.Entry<K, V>>)(Set)m.entrySet())
            put(e.getKey(), e.getValue());
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        if(isEmpty()){
            throw new UnsupportedOperationException();
        }
        values.clear();
        // END
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<K>();
        for (Pair p : values) keys.add(p.getKey());
        return keys;
    }

    @Override
    public Collection<V> values() {
        // BEGIN (write your solution here)
        Collection<V> vals=new ArrayList<>();
        if (isEmpty())
            return vals;
        for(Pair pair:values)
            vals.add(pair.getValue());
        return vals;
//        return new Valls();
        // END
    }

//    private class Valls<V> extends AbstractCollection<V>{
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
//                @Override
//                public void forEachRemaining(Consumer<? super V> action) {
//                    Objects.requireNonNull(action);
//                    while (hasNext())
//                        action.accept(next());
//                }
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
//        @Override
//        public void forEach(Consumer<? super V> action) {
//            iterator().forEachRemaining(action);
//        }
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
        return (Set<Entry<K, V>>)(Set)new HashSet<>(values);
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
