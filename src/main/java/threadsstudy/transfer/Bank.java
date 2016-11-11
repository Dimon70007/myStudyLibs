package threadsstudy.transfer;

import java.util.Arrays;

/**
 * Created by OTBA}|{HbIu` on 10.11.16.
 */
public class Bank {

    private final double[] accounts;
//    private Lock bankLock=new ReentrantLock();
//    private Condition sufficientFunds;

    public Bank(int naccounts, double initialBalance) {

        accounts = new double[naccounts];
        Arrays.fill(accounts,initialBalance);
//        for (double acc:accounts){
//            acc=initialBalance;
//        }
//        bankLock=new ReentrantLock();
//        sufficientFunds=bankLock.newCondition();
    }

    /**
     * переводит деньги с 1 счета на другой
     * @param  from Счет, с которого переводятся деньги
     * @param  to  счет, на который переводятся деньги
     * @param  amount Сумма перевода
     */
//    public void transfer(int from,int to,double amount) throws InterruptedException {
//        bankLock.lock();
//        try {
//            while (accounts[from] < amount)
//                sufficientFunds.await();
//            System.out.print(Thread.currentThread());
//            accounts[from] -= amount;
//            System.out.printf("%10.2f from %d to %d", amount, from, to);
//            accounts[to] += amount;
//            System.out.printf(" Total balance: %10.2f%n", getTotalBalance());
//            sufficientFunds.signalAll();
//        }finally {
//            bankLock.unlock();
//        }
//    }

    public synchronized void transfer(int from,int to,double amount) throws InterruptedException {

        while (accounts[from] < amount)
            wait();
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf("%10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total balance: %10.2f%n", getTotalBalance());
        notifyAll();

    }

    /**
     * Получает сумму остатков на всех счетах
     * @return возвращает общий баланс
     */
    public synchronized double getTotalBalance() {
//        bankLock.lock();
//        try {
        double sum = 0;
        for (double acc : accounts)
            sum += acc;
        return sum;
//        }finally {
//            bankLock.unlock();
//        }

    }

    /**
     * Получает количество счетов в банке
     *@return Возвращает количество счетов
     */
    public int size(){
        return accounts.length;
    }
}
