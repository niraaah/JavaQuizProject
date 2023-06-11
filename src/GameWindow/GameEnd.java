package GameWindow;

import ScoreManager.ScoreWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEnd extends JFrame{
    int score;
    public GameEnd(int score){
        this.score = score;
        setTitle("GAME OVER");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        addScore(c);//use North
        addSelection(c);//use Center

        setVisible(true);
        setSize(500,400);
    }

    void addScore(Container c){
        JPanel temp = new JPanel();
        temp.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel la = new JLabel();
        la.setText("Total Score : "+String.valueOf(score));
        la.setHorizontalAlignment(0);

        temp.add(la);

        c.add(temp,BorderLayout.CENTER);

    }

    void addSelection(Container c){
        JPanel temp = new JPanel();
        temp.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton bt1 = new JButton("RETRY");
        JButton bt2 = new JButton("SAVE SCORE");

        bt2.addActionListener(new sendRanking());
        bt1.addActionListener(new retryGame());

        temp.add(bt1);
        temp.add(bt2);

        c.add(temp,BorderLayout.SOUTH);
    }

    class sendRanking implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ScoreWriter();
            dispose();
        }
    }

    class retryGame implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            dispose();
        }
    }
}
