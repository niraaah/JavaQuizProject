package ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoardManager extends JFrame implements ActionListener {
    private static final String FILE_PATH = "score.txt";
    private JTextField scoreTextField;
    private JButton deleteButton;
    private JTextArea scoreTextArea;

    public ScoreBoardManager() {
        setTitle("Score Board Manager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setPreferredSize(new Dimension(800, 800));
        setLayout(new FlowLayout());

        JLabel scoreLabel = new JLabel("삭제할 기록의 이름:점수를 입력하세요:");
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(24f));
        scoreTextField = new JTextField(10);
        scoreTextField.setFont(scoreTextField.getFont().deriveFont(24f));
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        deleteButton.setFont(deleteButton.getFont().deriveFont(24f));

        scoreTextArea = new JTextArea();
        scoreTextArea.setEditable(false);
        scoreTextArea.setFont(scoreTextArea.getFont().deriveFont(24f));
        JScrollPane scrollPane = new JScrollPane(scoreTextArea);
        scrollPane.setPreferredSize(new Dimension(600, 500));

        add(scoreLabel);
        add(scoreTextField);
        add(deleteButton);
        add(scrollPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        updateScoreBoard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            String input = scoreTextField.getText();

            if (input.contains(":")) {
                String[] parts = input.split(":");
                String name = parts[0].trim();
                String score = parts[1].trim();

                deleteScore(name, score);
                JOptionPane.showMessageDialog(this, "점수가 삭제되었습니다.");

                // Clear the input field
                scoreTextField.setText("");

                updateScoreBoard();
            } else {
                JOptionPane.showMessageDialog(this, "올바른 형식으로 입력하세요. (이름:점수)");
            }
        }
    }

    private void deleteScore(String name, String score) {
        List<String> scoreBoardData = loadScoreBoard();
        List<String> updatedScoreBoardData = new ArrayList<>();

        for (String scoreData : scoreBoardData) {
            if (!scoreData.equals(name + ":" + score)) {
                updatedScoreBoardData.add(scoreData);
            }
        }

        saveScoreBoard(updatedScoreBoardData);
    }

    private List<String> loadScoreBoard() {
        List<String> scoreBoardData = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    scoreBoardData.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return scoreBoardData;
    }

    private void saveScoreBoard(List<String> scoreBoardData) {
        File file = new File(FILE_PATH);
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (String scoreData : scoreBoardData) {
                writer.println(scoreData);
            }
        } catch (IOException e) {
            System.out.println("파일에 점수를 저장하는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private void updateScoreBoard() {
        List<String> scoreBoardData = loadScoreBoard();
        StringBuilder scoreBoardMessage = new StringBuilder();
        scoreBoardMessage.append("점수기록\n");

        for (int i = 0; i < scoreBoardData.size(); i++) {
            scoreBoardMessage.append(i + 1).append(". ").append(scoreBoardData.get(i)).append("\n");
        }

        scoreTextArea.setText(scoreBoardMessage.toString());
    }
}