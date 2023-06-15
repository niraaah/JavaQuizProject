package GameWindow;

import ScoreManager.ScoreWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEnd extends JDialog{
    int score;
    public GameEnd(int score){
        super(new Frame(),"GameOver",true);
        super.setSize(500,400);
        super.setLocationRelativeTo(null);
        this.score = score;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        addImage(c);
        addScore(c);//use North
        addSelection(c);//use Center

        setVisible(true);

    }

    void addImage(Container c){
        ImageIcon gifIcon = new ImageIcon("src/GameWindow/endGameBackGround.gif");
        JLabel gifLabel = new JLabel(gifIcon);
        gifLabel.setBackground(Color.BLACK);
        gifLabel.setOpaque(true);
        c.add(gifLabel,BorderLayout.CENTER);
    }
    void addScore(Container c){
        JPanel temp = new JPanel();
        temp.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel la = new JLabel();
        la.setText("Total Score : "+String.valueOf(score));
        la.setHorizontalAlignment(0);
        la.setForeground(Color.WHITE);
        la.setFont(new Font("Gothic",Font.PLAIN,26));

        temp.setOpaque(true);
        temp.setBackground(Color.black);
        temp.add(la);

        c.add(temp,BorderLayout.NORTH);

    }
    void addSelection(Container c){
        JPanel temp = new JPanel();
        temp.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton bt1 = new JButton("Go To Main");
        JButton bt2 = new JButton("SAVE SCORE");

        bt2.addActionListener(new sendRanking());
        bt2.setFont(new Font("Gothic",Font.PLAIN,26));
        bt1.addActionListener(new goTomain());
        bt1.setFont(new Font("Gothic",Font.PLAIN,26));

        temp.add(bt1);
        temp.add(bt2);
        temp.setOpaque(true);
        temp.setBackground(Color.black);

        c.add(temp,BorderLayout.SOUTH);
    }

    class sendRanking implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ScoreWriter(score);
            dispose();
        }
    }
    class goTomain implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            dispose();
        }
    }
}
