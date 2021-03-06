package  mycollections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class myArrayCollection<T> implements Collection<T> {

    private T[] m = (T[])new Object[1];

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        for (int i = 0; i < size; i++) {
            if (m[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        final T[] newM = (T[])new Object[this.size()];
        System.arraycopy(m, 0, newM, 0, this.size());
        return newM;
    }


    @Override
    public <T1> T1[] toArray(final T1[] a) {
        // BEGIN (write your solution here)
        int lenA=a.length;
        T1[] newM;
        if(lenA<this.size()){
            return (T1[]) Arrays.copyOf(m,this.size(),a.getClass());
        }else {
            newM = a;
            if (lenA>size())
                newM[this.size()]=null;
        }
        System.arraycopy(m, 0, newM, 0, this.size());

        return newM;

        // END
    }

    @Override
    public boolean add(final T t) {
        if (m.length == size) {
            final T[] oldM = m;
            m = (T[]) new Object[this.size() * 2];
            System.arraycopy(oldM, 0, m, 0, oldM.length);
        }
        m[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size(); i++) {
            if(m[i].equals(o)){
                System.arraycopy(m,i+1,m,i,size-i);
                size--;
                return true;
            }
        }
        return false;
    }
//    public boolean remove(final Object o) {
//        for (int i = 0; i < size(); i++) {
//            if (m[i].equals(o)) {
//                this.remove(i);
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final Object item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
        m = (T[])new Object[1];
        size = 0;
    }

    private void remove(final int index) {
        if (index != this.size() - 1)
            System.arraycopy(m, index + 1, m, index, this.size() - index - 1);
        size--;
    }

    private class ElementsIterator implements Iterator<T> {

        private int index;

        private int last = -1;

        @Override
        public boolean hasNext() {
            return myArrayCollection.this.size() > index;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            last = index;
            return myArrayCollection.this.m[index++];
        }

        public void remove() {
            if (last == -1)
                throw new IllegalStateException();
            myArrayCollection.this.remove(last);
            index--;
            last = -1;
        }

    }


}
