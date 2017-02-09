package homework.recursion_and_stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by OTBA}|{HbIu` on 22.12.16.
 */
public class StackTriangleApp {
    static int theNumber;
    static int theAnswer;
    static StackX theStack;
    static int codePart;
    static Params theseParams;

    public static void main(String[] args) throws IOException {
        System.out.print("Enter a number: ");
        theNumber=getInt();
        recTriangle();
        System.out.println("Triangle="+theAnswer);
    }

    private static void recTriangle() {
        theStack=new StackX(10000);
        codePart=1;
        while (true){
            if(step())//вызываем пока step() не вернет true
                break;
        }
    }

    private static boolean step() {
        switch (codePart){
            case 1://исходный вызов
                theseParams=new Params(theNumber,6);
                theStack.push(theseParams);
                codePart=2;
                break;
            case 2://вход в метод
                theseParams=theStack.peek();
                if (theseParams.n==1){//проверка
                    theAnswer=1;
                    codePart=5;//выход
                }else {//рекурсивный вызов
                    codePart=3;
                }
                break;
            case 3://вызов метода
                theStack.push(new Params(theseParams.n-1,4));
                codePart=2;//вход в метод
                break;
            case 4://вычисление
                theseParams=theStack.peek();
                theAnswer=theAnswer+theseParams.n;
                codePart=5;
                break;
            case 5://выход из метода
                theseParams=theStack.pop();
                codePart=theseParams.returnAddress;//4 или 6
                break;
            case 6://точка возврата
                return true;
        }
        return false;
    }

    private static int getInt() throws IOException {
        return Integer.parseInt(getString());
    }

    private static String getString() throws IOException {

        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);

        return br.readLine();
    }
}
