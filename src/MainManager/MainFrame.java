package MainManager;

import GameWindow.GAME;
import ScoreManager.ScoreBoardManager;
import GameSettingManager.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private JButton startButton;
    private JButton scoreBoardButton;
    private JButton exitButton;
    private JButton OptionButton;

    public MainFrame() {
        JFrame frame = new JFrame("그림 퀴즈");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        ImageIcon gifIcon = new ImageIcon("home.jpg");
        Image image = gifIcon.getImage();
        Image resizedImage = image.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel gifLabel = new JLabel(resizedIcon);
        gifLabel.setBounds(0,0,600,400);

        JLabel TextLabel = new JLabel("Java Quiz");
        Font font = new Font("Arial", Font.BOLD, 36);
        TextLabel.setFont(font);
        TextLabel.setBounds(215,-35,200,200);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        panel.add(TextLabel, BorderLayout.CENTER);
        panel.add(gifLabel, BorderLayout.CENTER);

        BGMManager BGM = new BGMManager("music/sqidgameBGM.wav");
        Thread BGMThread = new Thread(BGM);
        BGMThread.start();

        GAME game = new GAME("게임시작");
        startButton = game.getGameButton();
        startButton.setBounds(200, 125, 200, 30);
        panel.add(startButton);

        // 순위표 보기 버튼 초기화
        scoreBoardButton = new JButton("순위표 보기");
        scoreBoardButton.setBounds(200, 175, 200, 30);
        scoreBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //순위표 함수
                ScoreBoardManager bd = new ScoreBoardManager();
            }
        });
        panel.add(scoreBoardButton);

        OptionButton = new JButton("옵션");
        OptionButton.setBounds(200, 225, 200, 30);
        OptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameSettings();
            }
        });
        panel.add(OptionButton);

        // 종료 버튼 초기화
        exitButton = new JButton("종료");
        exitButton.setBounds(200, 275, 200, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String args[]){
        new MainFrame();
    }
}
