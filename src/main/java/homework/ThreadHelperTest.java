package homework;

/**
 * Created by OTBA}|{HbIu` on 02.11.16.
 */
public class ThreadHelperTest {
    public static void main(String[] args) {
        try {
            for(int i=1;i<11;i++)
                System.out.println(ThreadHelper.fib(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
