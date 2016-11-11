package threadsstudy.transfer;

/**
 * Created by OTBA}|{HbIu` on 10.11.16.
 *
 * Тута деньги переводятся с указанного счета на рандомный
 */
public class TransferRunnable implements Runnable{

    private Bank bank;
    private int fromAccount;
    private double maxAmount;
    private int DELAY=10;
    /**
     * Конструирует объектисполняемого потока
     * @param b банк,где деньги переводятся между счетами
     * @param  from счет, с которого переводятся деньги
     * @param max макс сумма перевода в каждой транзакции
     */
    public TransferRunnable(Bank b, int from, double max) {
        bank=b;
        fromAccount=from;
        maxAmount=max;
    }

    @Override
    public void run() {
        try {
            while (true){
                int toAccount=(int) (bank.size()*Math.random());
                double amount=maxAmount*Math.random();
                bank.transfer(fromAccount,toAccount,amount);
                Thread.sleep((int)(DELAY*Math.random()));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
