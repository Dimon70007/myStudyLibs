package threadsstudy.swing_threads.swing_worker;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by OTBA}|{HbIu` on 23.11.16.
 */
public class SwingWorkerFrame extends JFrame {

    private JFileChooser chooser;
    JTextArea textArea;
    JLabel statusLine;
    JMenuItem openItem;
    JMenuItem cancelItem;
    private SwingWorker<StringBuilder,ProgressData> textReader;
    public static final int TEXT_ROWS=20;
    public static final int TEXT_COLUMNS=60;

    public SwingWorkerFrame() {

        this.chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        this.textArea = new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
        add(new JScrollPane(textArea));

        this.statusLine = new JLabel(" ");
        add(statusLine, BorderLayout.SOUTH);

        JMenuBar menuBar=new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu=new JMenu("File");
        menuBar.add(menu);

        this.openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(event ->{
            //show file chooser dialog
            int result=chooser.showOpenDialog(null);

            //if file selected, set it as icon of the label
            if (result==JFileChooser.APPROVE_OPTION){
                textArea.setText("");
                openItem.setEnabled(false);
                textReader=new TextReader(chooser.getSelectedFile(),this);
                textReader.execute();
                cancelItem.setEnabled(true);
            }
        });

        this.cancelItem = new JMenuItem("Cancel");
        menu.add(cancelItem);
        cancelItem.setEnabled(false);
        cancelItem.addActionListener(event ->textReader.cancel(true));
        pack();
    }
}
