package  homework;

/**
 * Created by OTBA}|{HbIu` on 16.09.16.
 */
public interface INode<T>{

    public INode add(final T value);

    public void forEach(final Consumer<T> consumer);

    public int count();

    public interface Consumer<R>{

        public void consume(final R value);
    }



}
