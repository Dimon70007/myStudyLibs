package main.java.homework;

/**
 * Created by OTBA}|{HbIu` on 16.09.16.
 */
public class ImmutableNode implements INode<Integer> {

    private final int value;
    private final INode left;
    private final INode right;


    public ImmutableNode(int value, INode left, INode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public ImmutableNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    @Override
    public INode add(final Integer value) {
        if(this.value==value)
            return this;
        if (value < this.value) {
            if (left != null)
                return new ImmutableNode(this.value,left.add(value),right);
            return new ImmutableNode(this.value, new ImmutableNode(value), right);

        }
        if(right!=null)
            return new ImmutableNode(this.value,left,right.add(value));
        return new ImmutableNode(this.value, left, new ImmutableNode(value));
    }

    @Override
    public void forEach(Consumer<Integer> consumer) {
        if(left!=null) left.forEach(consumer);
        consumer.consume(value);
        if(right!=null) right.forEach(consumer);
    }
    @Override
    public int count() {
        int leftCount=  left!=null ? left.count(): 0;
        int rightCount=  right!=null ? right.count() : 0;
        return leftCount+1+rightCount;
    }
}
