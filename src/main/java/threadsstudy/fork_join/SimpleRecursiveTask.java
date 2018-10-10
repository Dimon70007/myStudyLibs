package threadsstudy.fork_join;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static threadsstudy.fork_join.Counter.THRESHOLD;

/**
 * Created by OTBA}|{HbIu` on 16.02.17.
 */
public class SimpleRecursiveTask extends RecursiveTask<Long> {

    private final Problem problem;
    private final int l;
    private final int r;

    public SimpleRecursiveTask(Problem problem, int l, int r) {
        this.problem = problem;
        this.l = l;
        this.r = r;
    }

    @Override
    protected Long compute() {

        if(r-l<=THRESHOLD)
            return problem.solve(l,r);

        int mid = (l+r) >>> 1;//сдвиг вправо на 1 символ
        // означает деление на основание системы счисления,
        // в данном случае - на 2
        ForkJoinTask<Long> t1= new SimpleRecursiveTask(problem,l,mid);
        ForkJoinTask<Long> t2= new SimpleRecursiveTask(problem,mid,r);
// ForkJoinTask
// --хранит результат
        //--хранит состояние(done,cancelled,exceptional)
        //--использоватть 1 и тот же инстанс опасно- он просто вернет уже посчитанный  прошлом результат

        //Для сброса состояния есть метод
//        t1.reinitialize();
        t1.fork(); //запустили в фоне выполнение задачи
        //fork() кладет задачу в очередь и возвращает нам ссылку на нее
        // в виде ForkJoinTask, при этом любой свободный поток может эту задачу
        // своровать и выполнить
        t2.fork(); //запустили в фоне выполнение задачи
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //Вместо fork() лучше использовать метод invokeAll()
//        ForkJoinTask.invokeAll(t1,t2);
        //или сразу 1 задачу запустить на выполнение а вторую форкнуть:
//        t2.fork();
//        long res=0;
//        res+=t1.invoke();
//        res+=t2.join();

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //КОРОЧЕ join() НУЖНО ВЫЗЫВАТЬ В ОБРАТНОМ ПОРЯДКЕ ОТНОСИТЕЛЬНО fork()
        //если мы вызвали
//        t1.fork();
//        t2.fork();
//        джойниться надо наоборот чтобы выиграть в производительности
//        t2.join();
//        t1.join();


        long res=0;
        res+=t2.join();//получили результат выполнения 2 потоком
        //join() блокируется, пока задача не закончится, но поток при
        // окончании выполнения не убивается, а ForkJoinPool может дать
        // ему что-нибудь еще повыполнять
        res+=t1.join();//получили результат выполнения 1 потоком
        // Какие варианты работы join() во время вызова(внутреннее устройство)
        //--если задача на верхушке очереди потока - он ее выполняет
        //--если задача где-то в очереди того-же потока - он пробегается  по ней,
        // находит задачу и исполняет (при asyncMode(true) будет всегда пробегать
        // всю очередь до конца)
        //--если задача отсутствует в очереди потока (ее кто-то стырил), тогда поток
        // идет забирать эту задачу у своровавшего потока, а если она уже выполняется -
        //то пытается этот поток разгрузить
        //--если выполнять нечего - поток блокируется (даже если есть внешние задачи)
        // ...но поскольку поток потерялся нужно скомпенсировать его отсутствие
        return res;
    }

    private static class Problem {

        public Long solve(int l, int r) {
            //// TODO: 16.02.17

            return null;
        }
    }
}
