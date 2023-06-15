package ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ScoreWriter extends JDialog {
    private JTextField nameTextField;
    private JButton saveButton;

    public ScoreWriter(int score) {
        super(new JFrame(),"Score Write!",true);
        super.setLocationRelativeTo(null);
        super.setSize(200,100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("기록을 저장하실 이름을 입력해주세요: ");
        nameTextField = new JTextField(10);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScoreToFile(score);
                dispose();
            }
        });

        add(nameLabel);
        add(nameTextField);
        add(saveButton);

        pack();
        setVisible(true);
    }

    public void saveScoreToFile(int score) {
        String name = nameTextField.getText();
         // score점수는 일단 0으로 설정하였습니다.

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("score.txt", true));
            writer.println(name + ":" + score);
            writer.close();
            JOptionPane.showMessageDialog(this, "입력이 파일에 저장되었습니다.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "파일에 입력을 저장하는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
