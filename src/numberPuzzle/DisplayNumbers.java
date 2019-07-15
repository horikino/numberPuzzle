package numberPuzzle;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

class DisplayNumbers {

    private int X_LENGTH = 4;
    private int Y_LENGTH = 4;

    public DisplayNumbers() {

    }

    public DisplayNumbers(int x, int y) {
        this.X_LENGTH = x;
        this.Y_LENGTH = y;
    }

    public void display(int[][] intArray, JPanel panel, JLabel[][] labels) {

        for (int i = 0; i < X_LENGTH; i++) {
            for (int j = 0; j < Y_LENGTH; j++) {
                if (intArray[i][j] < 0) {
                    labels[i][j].setText("");
                } else {
                    labels[i][j].setText(String.valueOf(intArray[i][j]));
                }
                setColor(intArray[i][j], labels[i][j]);
            }
        }
    }

    private void setColor(int number, JLabel label) {
        int temp = 0, count = 0;

        if (number < 0) {
            label.setBackground(new Color(195, 195, 195));
        } else {
            temp = number;
            count = 0;
            while (temp != 1) {
                temp = temp / 2;
                count++;
            }
            if (count < 5) {
                label.setBackground(new Color(230, 220 - (count * 40), 120 - (count * 30)));
            } else if (count < 9) {
                label.setBackground(new Color(255, 216 - (count * 25), 213 - (count * 25)));
            } else {
                label.setBackground(new Color(255, 255 - ((count - 8) * 25), 0 + (count * 5)));
            }
        }
    }
}