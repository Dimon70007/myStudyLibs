package homework;

/**
 * Created by OTBA}|{HbIu` on 09.11.16.
 */
public class ThreadHelperTeach {

    public static void main(String[] args) throws Exception {
        System.out.println(fac(25));
    }

    public static int fib(final int numberToCalculate) throws Exception {
        final ThreadHelperTeach.FibCalculator calculator = new ThreadHelperTeach.FibCalculator(numberToCalculate);
        calculator.start();
        calculator.join();
        return calculator.getResult();

    }

    public static long fac(final int numberFactorial)throws Exception {

        final ThreadHelperTeach.FactorialCalc calculator = new ThreadHelperTeach.FactorialCalc(numberFactorial);
        calculator.start();
        calculator.join();
        return calculator.getResult();
    }


    private static class FibCalculator extends Thread {
        private final int currentNum;

        private int result;

        public FibCalculator(final int numberToCalculate) {
            this.currentNum = numberToCalculate;
        }

        @Override
        public void run() {
            if (currentNum == 1 || currentNum == 2) {
                result = 1;
                return;
            }
            final FibCalculator left = new FibCalculator(currentNum - 1);
            final FibCalculator right = new FibCalculator(currentNum - 2);
            left.start();
            right.start();
            try {
                left.join();
                right.join();
            } catch (final InterruptedException e) {
            }
            result = left.getResult() + right.getResult();
        }

        public int getResult() {
            return result;
        }
        // END
    }

    public static class FactorialCalc extends Thread{
        private final int current;
        private long result=1;

        public FactorialCalc(int current) {
            this.current = current;
        }

        @Override
        public void run() {
            if (current<=1){
                result=1;
                return;
            }
            FactorialCalc fc1=new FactorialCalc(current-1);
//            FactorialCalc fc2=new FactorialCalc(current-2);
            System.out.println("starting "+fc1.getName());
            fc1.start();
//            System.out.println("starting "+fc2.getName());
//            fc2.start();
            try {

                fc1.join();
                System.out.println("joining "+fc1.getName()+"current="+current);
//                fc2.join();
//                System.out.println("joining "+fc2.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result=current*fc1.getResult();//*fc2.getResult();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        public long getResult() {
            return result;
        }
    }
}
