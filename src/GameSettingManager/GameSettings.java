package project_setting;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GameSettings extends JFrame {
    private JComboBox<String> fontComboBox;
    private JComboBox<String> fontSizeComboBox;
    private JComboBox<String> windowSizeComboBox;
    private JSlider volumeSlider;
    private JButton saveButton;
    private JButton colorButton;

    // File path
    private static final String SETTINGS_FILE = "settings.txt";

    public GameSettings() {
        setTitle("Game Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Disable window resizing

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setLayout(new GridLayout(7, 2, 10, 10));
        setContentPane(contentPanel);

        // Font ComboBox
        JLabel fontLabel = new JLabel("  Font:");
        fontComboBox = new JComboBox<>(new String[]{"Arial", "Times New Roman", "Consolas"});
        fontComboBox.setToolTipText("Select the game's font");
        fontComboBox.addActionListener(e -> {
            updateFont();
            saveSettings();
        });
        add(fontLabel);
        add(fontComboBox);

        // Font Size ComboBox
        JLabel fontSizeLabel = new JLabel("  Font Size:");
        fontSizeComboBox = new JComboBox<>(new String[]{"Small", "Medium", "Large"});
        fontSizeComboBox.setToolTipText("Select the size of the game's font");
        fontSizeComboBox.addActionListener(e -> {
            updateFont();
            saveSettings();
        });
        add(fontSizeLabel);
        add(fontSizeComboBox);

        // Window Size ComboBox
        JLabel windowSizeLabel = new JLabel("  Window Size:");
        windowSizeComboBox = new JComboBox<>(new String[]{"400x400", "600x600", "800x800"});
        windowSizeComboBox.setToolTipText("Select the game window size");
        windowSizeComboBox.addActionListener(e -> {
            updateWindowSize();
            saveSettings();
        });
        add(windowSizeLabel);
        add(windowSizeComboBox);

        // Color Button
        JLabel colorLabel = new JLabel("  Color:");
        colorButton = new JButton("Pick one!");
        colorButton.setToolTipText("Click to choose a color");
        colorButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(this, "Choose Color", Color.WHITE);
            if (selectedColor != null) {
                getContentPane().setBackground(selectedColor);
                saveSettings();
            }
        });
        add(colorLabel);
        add(colorButton);

        // Volume Slider
        JLabel volumeLabel = new JLabel("  Volume:");
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider.setToolTipText("Adjust the game's volume");
        volumeSlider.setMajorTickSpacing(50);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(false);
        volumeSlider.addChangeListener(e -> saveSettings());
        add(volumeLabel);
        add(volumeSlider);

        add(new JLabel());

        // Save Button
        saveButton = new JButton("Save");
        saveButton.setToolTipText("Click to save your settings");
        saveButton.addActionListener(e -> {
            saveSettings();
            dispose();
        });
        add(saveButton);

        loadSettings();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveSettings() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SETTINGS_FILE))) {
            String selectedFont = (String) fontComboBox.getSelectedItem();
            String selectedFontSize = (String) fontSizeComboBox.getSelectedItem();
            String selectedWindowSize = (String) windowSizeComboBox.getSelectedItem();
            Color bgColor = getContentPane().getBackground();
            String selectedColor = Integer.toString(bgColor.getRGB());
            int selectedVolume = volumeSlider.getValue();

            writer.write(selectedFont);
            writer.newLine();
            writer.write(selectedFontSize);
            writer.newLine();
            writer.write(selectedWindowSize);
            writer.newLine();
            writer.write(selectedColor);
            writer.newLine();
            writer.write(String.valueOf(selectedVolume));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving settings. Please try again.");
        }
    }

    private void updateFont() {
        String font = (String) fontComboBox.getSelectedItem();
        String fontSize = (String) fontSizeComboBox.getSelectedItem();
        Font newFont = new Font(font, Font.PLAIN,
                "Small".equals(fontSize) ? 12 :
                        "Medium".equals(fontSize) ? 18 : 24);
        updateComponentFont(this, newFont);
    }

    private void updateComponentFont(Component comp, Font font) {
        comp.setFont(font);
        if (comp instanceof Container) {
            for (Component child : ((Container) comp).getComponents()) {
                updateComponentFont(child, font);
            }
        }
    }

    private void updateWindowSize() {
        try {
            String windowSize = (String) windowSizeComboBox.getSelectedItem();
            String[] dimensions = windowSize.split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            setPreferredSize(new Dimension(width, height));
            pack();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error parsing window size. Please check your settings.");
        }
    }

    private void loadSettings() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SETTINGS_FILE))) {
            String font = reader.readLine();
            String fontSize = reader.readLine();
            String windowSizeString = reader.readLine();
            String colorRGB = reader.readLine();
            String volume = reader.readLine();

            if (font == null || fontSize == null || windowSizeString == null || colorRGB == null || volume == null) {
                throw new IOException("Settings file is corrupted.");
            }

            fontComboBox.setSelectedItem(font);
            fontSizeComboBox.setSelectedItem(fontSize);
            windowSizeComboBox.setSelectedItem(windowSizeString);

            Color bgColor = new Color(Integer.parseInt(colorRGB));
            getContentPane().setBackground(bgColor);
            int volumeValue = volume.isEmpty() ? 50 : Integer.parseInt(volume);

            volumeSlider.setValue(volumeValue);

            updateFont();
            updateWindowSize();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading settings. Using default settings.");
            loadDefaultSettings();
        }
    }

    public void loadDefaultSettings() {
        String defaultFont = "Consolas";
        String defaultFontSize = "Medium";
        String defaultWindowSize = "400x400";
        Color defaultColor = Color.WHITE;
        int defaultVolume = 50;

        fontComboBox.setSelectedItem(defaultFont);
        fontSizeComboBox.setSelectedItem(defaultFontSize);
        windowSizeComboBox.setSelectedItem(defaultWindowSize);
        getContentPane().setBackground(defaultColor);
        volumeSlider.setValue(defaultVolume);

        updateFont();
        updateWindowSize();
    }

    public String getSelectedFont() {
        Object selectedItem = fontComboBox.getSelectedItem();
        return (selectedItem != null) ? (String) selectedItem : "Consolas";
    }

    public int getFontSize() {
        Object selectedItem = fontSizeComboBox.getSelectedItem();
        return (selectedItem != null) ?
                ("Small".equals(selectedItem) ? 12 : "Medium".equals(selectedItem) ? 18 : 24)
                : 18;
    }

    public int getWindowSize() {
        Object selectedItem = windowSizeComboBox.getSelectedItem();
        if (selectedItem != null) {
            String windowSize = (String) selectedItem;
            String[] dimensions = windowSize.split("x");
            return Integer.parseInt(dimensions[0]);
        } else {
            return 400;
        }
    }

    public int getWindowColor() {
        Color bgColor = getContentPane().getBackground();
        return bgColor.getRGB();
    }

    public int getVolume() {
        if (volumeSlider.getValueIsAdjusting()) {
            return volumeSlider.getValue();
        } else {
            return 50;
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                new GameSettings();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error launching settings. Please try again.");
            }
        });
    }
}
