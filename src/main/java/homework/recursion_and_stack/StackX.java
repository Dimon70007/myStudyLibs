package homework.recursion_and_stack;

/**
 * Created by OTBA}|{HbIu` on 22.12.16.
 */
public class StackX {
    private int maxSize;//размер массива StackX
    private Params[] stackArray;
    private int top;//Вершина стека

    public StackX(int s) {
        this.maxSize = s;
        this.stackArray=new Params[maxSize];
        this.top=-1;//массив пока не содержит элементов
    }

    public void push(Params p){//кладем элемент на вершину стэка
            stackArray[++top]=p;
    }

    public Params pop(){//забираем элемент с вершины стэка
        return stackArray[top--];
    }

    public Params peek(){//читаем элемент с вершины стэка
        return stackArray[top];
    }
}
