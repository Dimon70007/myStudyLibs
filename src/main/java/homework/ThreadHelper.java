package homework;


/**
 * Created by OTBA}|{HbIu` on 02.11.16.
 */
public class ThreadHelper {

    public static int fib(final int numberToCalculate) throws Exception {
        final FibCalculator calculator = new FibCalculator(numberToCalculate);
        calculator.start();
        calculator.join();
        return calculator.getResult();

    }

    private static class FibCalculator extends Thread {

        // BEGIN (write your solution here)
        private int result;
        private int numberToCalc;
        private int prevFib;
        private int prevPrevFib;

        public FibCalculator(final int numberToCalculate) {
            ThreadGroup parallelGroup;
            this.numberToCalc =numberToCalculate;
            result=0;
        }

        @Override
        public void run() {
            if (numberToCalc <4){
                final SimpleFibCalculator calculator = new SimpleFibCalculator(numberToCalc);
                calculator.start();
                try {
                    calculator.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result=calculator.getResult();
            }else{
                final SimpleFibCalculator prev = new SimpleFibCalculator(numberToCalc-1);
                final SimpleFibCalculator prevPrev = new SimpleFibCalculator(numberToCalc-2);
                prev.start();
                prevPrev.start();
                try {
                    prev.join();
                    prevPrev.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result=prev.getResult()+prevPrev.getResult();
            }
        }

        public int getResult() {
            return result;
        }

        private class SimpleFibCalculator extends Thread{
            private int fib;
            private int numberToCalculate;
            SimpleFibCalculator(final int numberToCalculate) {
                this.numberToCalculate=numberToCalculate;
                this.fib=0;
            }

            @Override
            public void run() {
                this.fib=calculateNextFib(0,1,1);
            }

            public int getResult() {
                return this.fib;
            }

            private int calculateNextFib(final int prevNumber, final int prevPrevNumber, final int nubersCount) {
                int currentNumber = prevNumber + prevPrevNumber;
                if (nubersCount >= numberToCalculate)
                    return currentNumber;
                return calculateNextFib(currentNumber, prevNumber, nubersCount + 1);

            }
        }
        // END
    }

}