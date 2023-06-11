package GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GAME extends JFrame {
    public static void main(String[] args){
        new GAME();
    }
    public GAME(){
        setTitle("GAME");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container c =getContentPane();

        setLayout(new FlowLayout());
        JButton btn = new JButton("START");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimerThread TT=new TimerThread();
                GameRun GR = new GameRun();
                GameThread GT = new GameThread();
                GT.setGR(GR);
                TT.setGameThread(GT);
                TT.start();
            }
        });

        c.add(btn);

        setVisible(true);
        setSize(1280,800);
    }

    void initSetting(){

    }

}
