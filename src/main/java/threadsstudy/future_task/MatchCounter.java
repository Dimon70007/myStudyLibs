package threadsstudy.future_task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by OTBA}|{HbIu` on 19.11.16.
 */
public class MatchCounter implements Callable<Integer> {
    private final File directory;
    private final String keyword;
    private  final ExecutorService pool;
    private int count;

    public MatchCounter(File directory, String keyword, ExecutorService pool) {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;
    }


    @Override
    public Integer call()  {

        int count=0;
        try {
            File[] files=directory.listFiles();
            List<Future<Integer>> results=new ArrayList<>();

            for (File file: files != null ? files : new File[0]){
                if (file.isDirectory()){
                    MatchCounter counter=new MatchCounter(file,keyword, pool);
                    FutureTask<Integer> task=new FutureTask<Integer>(counter);
                    results.add(task);
//                    Thread t = new Thread(task);
//                    t.start();

                }else{
                    if (search(file)) count++;
                }
            }

            for (Future<Integer> result:results){
                try {
                    count+=result.get();
                }catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

        }catch (InterruptedException e){

        }
        return count;
    }

    private boolean search(File file) {
        try(Scanner in=new Scanner(file,"UTF-8")){
            boolean found =false;
            while (!found && in.hasNextLine()){
                String line=in.nextLine();
                if (line.contains(keyword)) found=true;
            }
            return found;
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            return false;
        }
//        Executors.newCachedThreadPool();
    }
}
