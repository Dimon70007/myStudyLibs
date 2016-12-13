package threadsstudy.swing_threads;

import javax.swing.*;

/**
 * Created by OTBA}|{HbIu` on 23.11.16.
 */
public class SwingThreadFrame extends JFrame {
    public SwingThreadFrame() {
        final JComboBox<Integer> comboBox=new JComboBox<>();
        comboBox.insertItemAt(Integer.MAX_VALUE,0);
        comboBox.setPrototypeDisplayValue(comboBox.getItemAt(0));
        comboBox.setSelectedIndex(0);

        JPanel panel=new JPanel();

        JButton goodButton=new JButton("Good");
        goodButton.addActionListener(event ->
                new Thread(new GoodWorkerRunnable(comboBox)).start());
        panel.add(goodButton);
        JButton badButton=new JButton("Bad");
        badButton.addActionListener(event ->
                new Thread(new BadWorkerRunnable(comboBox)).start());
        panel.add(badButton);

        panel.add(comboBox);
        add(panel);
        pack();
    }
}
