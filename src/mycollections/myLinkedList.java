package mycollections;

import java.util.*;

public class myLinkedList<T> implements List<T> {

    private Item<T> first = null;

    private Item<T> last = null;

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
        // BEGIN (write your solution here)
        return indexOf(o)>-1;
        // END
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        final T[] newArr= (T[]) new Object[this.size()];
        Item<T> tmp=first;
            for (int i = 0; tmp!=null; i++) {
                newArr[i] = tmp.element;
                tmp=tmp.getNext();
            }
        return newArr;
        // END
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        if(a.length<size()){
//a = (T1[])java.lang.reflect.Array.newInstance(
//            a.getClass().getComponentType(), size);
            return (T1[]) Arrays.copyOf(toArray(), size(), a.getClass());

        }
        System.arraycopy( this.toArray(),0,a,0,size());
        if(a.length>size())
            a[size()]=null;
        return a;
        // END
    }

    @Override
    public boolean add(final T t) {
        // BEGIN (write your solution here)

        if (this.first==null) {
            this.first = new Item<T>(t, null, null);
            this.last=this.first;
            size=1;
        }else {
            Item<T> newItem = new Item<T>(t,this.last,null);
            newItem.getPrev().next=newItem;
            this.last=newItem;
            size++;
        }
        return true;

        // END
    }

    @Override
    public boolean remove(final Object o) {
        // BEGIN (write your solution here)
        Item<T> current=first;
        while(current!=null){
            if(o==null? current.element==null
                    : o.equals(current.element)) {
                unlink(current);
                return true;
            }
            current=current.getNext();
        }
        return false;
        // END
    }

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
        // BEGIN (write your solution here)
        this.first=null;
        this.last=null;
        this.size=0;
        // END
    }

    @Override
    public T remove(final int index) {
        // BEGIN (write your solution here)
        if (index<0 || index>=size()){
            throw new IndexOutOfBoundsException();
        }
        T current;
        Item<T> tmp=first;
        for(int i = 0; tmp!=null; i++) {
            if (i == index) {
                current = tmp.element;
                unlink(tmp);
                return  current;
            }
            tmp=tmp.getNext();
        }
        return null;
        // END
    }

    // BEGIN (write your solution here)
    public myLinkedList(final Collection<? extends T> c){
        if(c==null){
            throw new NullPointerException();
        }
        addAll(c);

    }
    public myLinkedList(){
    }

    private void unlink(Item<T> item){

        if(item.getPrev()!=null){//если предыдущий объект ==null значит это first
            item.getPrev().next=item.getNext();//для остальных просто перезаписываем ссылки между
            // предыдущим и следующим объектами

        }else{
            first=item.getNext();//присваиваем first ссылку на следующий объект
        }
        if(item.getNext()!=null){// если следующий объект ==null значит это last
            item.getNext().prev=item.getPrev();//для остальных просто перезаписываем ссылки между
            // предыдущим и следующим объектами
        }else{
            last=item.getPrev();//присваиваем  last ссылку на предыдущий объект
        }
        item=null;
        size--;
    }
    // END
    @Override
    public List<T> subList(final int start, final int end) {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object target) {
        // BEGIN (write your solution here)
        Item<T> tmp=first;
        for(int i=0;tmp!=null;i++){
            T current=tmp.element;
            if (target==null ? current==null : target.equals(current)){
                return i;
            }
            tmp=tmp.getNext();
        }
        return -1;
        // END
    }

    @Override
    public void add(final int index, final T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final int index, final Collection elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(final int index, final T element) {
        // BEGIN (write your solution here)
        if(index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        Item<T> tmp=first;
        T result;
        for(int i = 0;tmp!=null;i++){
            if(i==index){
                result=tmp.element;
                tmp.element=element;
                return result;
            }
            tmp=tmp.getNext();

        }
        return  null;
        // END
    }

    @Override
    public T get(final int index) {
        // BEGIN (write your solution here)
        return  listIterator(index).next();
        // END
    }

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> current;

        private Item<T> last;

        private int index;

        ElementsIterator() {
            this(0);
        }

         ElementsIterator(final int index) {
            // BEGIN (write your solution here)
            if  (index < 0 || index > size()){
                throw new IndexOutOfBoundsException();
            }
            //some optimization
            if (index<(myLinkedList.this.size()/2)){
                this.current= myLinkedList.this.first;
                for(int i = 0;i<index;i++){

                    this.current=this.current.getNext();

                }
            } else {
                this.current= myLinkedList.this.last;
                for(int i = size()-1;i>index;i--){
                    this.current=this.current.getPrev();
                }
            }
            this.last =null;
            this.index=index;
            // END
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            // BEGIN (write your solution here)
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            last =current;
            current=current.getNext();
            index++;
            return last.element;
            // END
        }

        @Override
        public void add(final T element) {
            myLinkedList.this.add(element);
        }

        @Override
        public void set(final T element) {
            // BEGIN (write your solution here)
            if (last==null){
                throw new IllegalStateException();
            }
            last.element=element;
            // END
        }

        @Override
        public int previousIndex(){
            // BEGIN (write your solution here)
            return index-1;
            // END
        }

        @Override
        public int nextIndex() {
            // BEGIN (write your solution here)

            return index;
            // END
        }

        @Override
        public boolean hasPrevious() {
            // BEGIN (write your solution here)
            return current!= myLinkedList.this.first && index>0;
            // END
        }

        @Override
        public T previous() {
            // BEGIN (write your solution here)
            if (!hasPrevious()){
                throw new NoSuchElementException();
            }

            if(current==null) {
                current= myLinkedList.this.last;//т.к. у нас проверка на то что
                //current!=first то если current==null будет означать конец списка
                //и в принципе нет разницы присвоить ему last или myLinkedList.this.lastn
                //так как в этом случае они ссылаются на 1 объект
                this.last=current;//эта операция нужна для вызова remove и set
            }else {
                this.last=current.getPrev();
                current = current.getPrev();
            }
            index--;
            return this.last.element;
            // END
        }

        @Override
        public void remove() {
            // BEGIN (write your solution here)
            if (last==null){
                throw new IllegalStateException();
            }

            myLinkedList.this.unlink(last);
            index--;
            last=null;
            // END
        }

    }

    private static class Item<T> {

        private T element;

        private Item<T> next;

        private Item<T> prev;

        public Item(final T element, final Item<T> prev, final Item<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }



        public T getElement() {
            return element;
        }

        public Item<T> getNext() {
            return next;
        }

        public Item<T> getPrev() {
            return prev;
        }

    }

}
