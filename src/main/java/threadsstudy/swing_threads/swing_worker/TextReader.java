package threadsstudy.swing_threads.swing_worker;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/**
 * Created by OTBA}|{HbIu` on 23.11.16.
 */
public class TextReader extends SwingWorker<StringBuilder, ProgressData> {
    private final SwingWorkerFrame swingWorkerFrame;
    private File file;
    private StringBuilder text=new StringBuilder();

    public TextReader(File file,SwingWorkerFrame swingWorkerFrame) {
        this.swingWorkerFrame=swingWorkerFrame;
        this.file = file;
    }
//the folowing method executes in the worker thread; it does not touch Swing components
    @Override
    protected StringBuilder doInBackground() throws IOException, InterruptedException {
        int lineNumber=0;
        try(Scanner in = new Scanner(new FileInputStream(file),"UTF-8")){
            while (in.hasNextLine()){
                String line=in.nextLine();
                lineNumber++;
                text.append(line).append("%n");
                ProgressData data = new ProgressData();
                data.line=line;
                data.number=lineNumber;
                publish(data);
                Thread.sleep(1);//to test cancellatio; no need to do this in real programs
            }
        }
        return text;
    }

    @Override
    protected void process(List<ProgressData> data) {
        if (isCancelled()) return;
        StringBuilder sb=new StringBuilder();
        swingWorkerFrame.statusLine.setText(""+data.get(data.size()-1).number);
        for (ProgressData d:data)
            sb.append(d.line).append("%n");
        swingWorkerFrame.textArea.append(sb.toString());
    }

    @Override
    protected void done() {
        try {
            StringBuilder result=get();
            swingWorkerFrame.textArea.setText(result.toString());
            swingWorkerFrame.statusLine.setText("Done");

        } catch (InterruptedException e) {
//            e.printStackTrace();
        } catch (CancellationException e) {
            swingWorkerFrame.textArea.setText("");
            swingWorkerFrame.statusLine.setText("Cancelled");
        } catch (ExecutionException e) {
            swingWorkerFrame.statusLine.setText(""+e.getCause());
        }
        swingWorkerFrame.cancelItem.setEnabled(false);
        swingWorkerFrame.openItem.setEnabled(true);
    }
}
