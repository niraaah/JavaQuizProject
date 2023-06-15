package GameWindow;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameSettingLoader {
    private String font;
    private String fontSizeInformation;
    private String windowSizeInformation;
    private String colorInformation;
    private int fontSize;
    private int windowSize;
    private int buttonWide;
    private int buttonHeight;
    private int imageSize;
    private BufferedReader settingReader;

    public static void main(String[] args){
        new GameSettingLoader();
    }
    public GameSettingLoader() {
        callSettingFile();
        readFile();

        makeFontSize();
        makeWindowSize();
        makeButtonSize();

    }

    void callSettingFile(){
        try {
            settingReader = new BufferedReader(new FileReader("settings.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("callSettingFile in GameSettingLoader Error! fix there");
            throw new RuntimeException(e);
        }
    }
    void readFile(){
            try {
                font = settingReader.readLine();
                fontSizeInformation=settingReader.readLine();
                windowSizeInformation = settingReader.readLine();
                colorInformation = settingReader.readLine();
                settingReader.close();
            } catch (IOException e) {
                System.out.println("readFile in GameSettingLoader Error! fix there");
                throw new RuntimeException(e);
            }


    }
    void makeFontSize(){
        switch (fontSizeInformation){
            case "Large": fontSize=15;
            break;
            case "Medium": fontSize=12;
            break;
            case "Small": fontSize=9;
            break;
        }
    }
    void makeWindowSize(){
        String[] splitStr = windowSizeInformation.split("x");
        windowSize = Integer.parseInt(splitStr[0].trim());
        imageSize=windowSize-100;
    }
    void makeButtonSize(){
        switch (fontSizeInformation){
            case "Large" :
                buttonHeight = 17;
                buttonWide = 120;
                break;
            case "Medium":
                buttonHeight = 14;
                buttonWide = 120;
                break;
            case "Small":
                buttonHeight = 11;
                buttonWide = 120;
                break;
        }
    }

    public Color getColor() {
        int alpha=Integer.parseInt(colorInformation.substring(0,2),16);
        int red = Integer.parseInt(colorInformation.substring(2, 4), 16);
        int green = Integer.parseInt(colorInformation.substring(4, 6), 16);
        int blue = Integer.parseInt(colorInformation.substring(6, 8), 16);

        System.out.println("RED : "+ red+colorInformation.substring(0, 2));
        System.out.println("GREEN : "+ green+colorInformation.substring(2, 4));
        System.out.println("BLUE : "+ blue+colorInformation.substring(4, 6));
        System.out.println("ALPHA : "+ alpha+colorInformation.substring(6, 8));

        return new Color(red,green,blue,alpha);
    }


    public String getFont(){
        return font;
    }

    public int getWindowSize(){
        return windowSize;
    }

    public int getFontSize(){
        return fontSize;
    }

    public int getImageSize(){
        return imageSize;
    }

    public int getButtonHeight(){
        return buttonHeight;
    }

    public int getButtonWide(){
        return buttonWide;
    }
}
