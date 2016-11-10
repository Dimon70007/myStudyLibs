package  overridenmethods;

/**
 * Created by OTBA}|{HbIu` on 06.09.16.
 */
public class EqualsTests {


    public static void main(String[] args) {

        EqualsTests myTest= new EqualsTests();
        myTest.equalsE0();
        myTest.newLine();
        myTest.equalsE1();
        myTest.newLine();
        myTest.equalsE2();
        myTest.newLine();
        myTest.equalsE3();


    }

    private void equalsE0(){

        final Integer a=2;
        final Integer b=2;
        System.out.println(a==b);
        System.out.println(a.equals(b));
        System.out.println(a);

        Object i=10111111124L;
        Long y= 10111111124L;
        System.out.println(y.longValue()==((Long)i).longValue());
    }

    private void equalsE1(){

        final Integer a=256;
        final Integer b=256;
        System.out.println(a==b);
        System.out.println(a.equals(b));
        System.out.println(a.intValue()==b.intValue());
    }

    private void equalsE2(){

        final Integer a=2;
        final Integer b=new Integer(2);
        System.out.println(a==b);
        System.out.println(a.equals(b));
    }


    private void equalsE3(){

        String a="Mendeleev";
        String b="Mendeleev";

        System.out.println(a==b);

        for(int i=0;i<10000;i++){
            if(a!=b){
                System.out.println(a + " " +b+" "+ i);
                break;
            }
            a=a+"a";
            b+="a";

        }
        System.out.println(a.equals(b));
    }

/*    private void equalsE4(){

        final Integer a=2;
        final Integer b=2;
        System.out.println(a==b);
        System.out.println(a.equals(b));
    }
*/






    private void newLine(){

        System.out.println("");
    }
}
