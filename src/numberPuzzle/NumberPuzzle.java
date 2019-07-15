package numberPuzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;

class NumberPuzzle implements KeyListener, ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JTextArea text1, text2;
    private JLabel labels[][];
    private JButton button;
    private Container con;

    private DisplayScore displayScore;
    private DisplayNumbers displayNumbers;
    private MovePanels movePanels;
    private Scores score;

    private long aimValue;
    private int numberInCells[][];
    private boolean next = true, filled = false;
    private Ranking ranking;

    private int X_LENGTH = 4;
    private int Y_LENGTH = 4;

    public void init() {

        frame = new JFrame();
        frame.setTitle("数字パズル");
        frame.setSize(400, 400);

        panel = new JPanel();
        panel.setLayout(new GridLayout(X_LENGTH, Y_LENGTH));

        labels = new JLabel[X_LENGTH][Y_LENGTH];

        for (int i = 0; i < X_LENGTH; i++) {
            for (int j = 0; j < Y_LENGTH; j++) {
                labels[i][j] = new JLabel("");
                labels[i][j].setHorizontalAlignment(JLabel.CENTER);
                labels[i][j].setVerticalAlignment(JLabel.CENTER);
                labels[i][j].setFont(new Font("メイリオ", Font.BOLD, 16));
                labels[i][j].setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
                labels[i][j].setForeground(new Color(255, 255, 255));
                labels[i][j].setOpaque(true);
                panel.add(labels[i][j]);
            }
        }

        text1 = new JTextArea();
        text1.setFocusable(false);
        text1.setEditable(false);
        text1.setFont(new Font("メイリオ", Font.BOLD, 11));

        text2 = new JTextArea();
        text2.setFocusable(false);
        text2.setEditable(false);
        text2.setFont(new Font("メイリオ", Font.BOLD, 11));

        button = new JButton();
        button.setFont(new Font("メイリオ", Font.BOLD, 11));
        button.setText("ランキングを確認する");
        button.setFocusable(false);
        button.addActionListener(this);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        con = frame.getContentPane();

        con.add(text1, BorderLayout.NORTH);
        con.add(panel, BorderLayout.CENTER);
        con.add(button, BorderLayout.SOUTH);

        displayScore = new DisplayScore();
        displayNumbers = new DisplayNumbers(X_LENGTH, Y_LENGTH);
        movePanels = new MovePanels(X_LENGTH, Y_LENGTH);
        score = new Scores();
        aimValue = 100000;
        ranking = new Ranking();

        numberInCells = new int[X_LENGTH][Y_LENGTH];

        for (int i = 0; i < X_LENGTH; i++) {
            for (int j = 0; j < Y_LENGTH; j++) {
                numberInCells[i][j] = -1;
            }
        }

        movePanels.create(numberInCells);
        movePanels.create(numberInCells);

        displayScore.display(numberInCells, score, text1);
        displayNumbers.display(numberInCells, panel, labels);

        frame.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (next) {
            if (key == 37) {
                if (movePanels.moveLeft(numberInCells, score)) {
                    score.increaseMoveCount();
                    movePanels.create(numberInCells);
                }
            }
            if (key == 38) {
                if (movePanels.moveUp(numberInCells, score)) {
                    score.increaseMoveCount();
                    movePanels.create(numberInCells);
                }
            }
            if (key == 39) {
                if (movePanels.moveRight(numberInCells, score)) {
                    score.increaseMoveCount();
                    movePanels.create(numberInCells);
                }
            }
            if (key == 40) {
                if (movePanels.moveDown(numberInCells, score)) {
                    score.increaseMoveCount();
                    movePanels.create(numberInCells);
                }
            }
            displayScore.display(numberInCells, score, text1);
            displayNumbers.display(numberInCells, panel, labels);

            for (int i = 0; i < X_LENGTH; i++) {
                for (int j = 0; j < Y_LENGTH; j++) {
                    if (numberInCells[i][j] >= aimValue) {
                        text2.setText("ゲームクリア");
                        text2.append("\n今回の結果");
                        text2.append("\nスコア：" + score.getScore());
                        text2.append("\n移動回数：" + score.getMoveCount());
                        next = false;
                        ranking.insertRanking(score);
                        ranking.displayRanking(5, text2, 1);

                        JFrame frame2 = new JFrame();
                        frame2.setSize(200, 150);
                        frame2.setVisible(true);
                        frame2.add(text2, BorderLayout.CENTER);
                        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                }
            }

            filled = true;

            for (int i = 0; i < X_LENGTH; i++) {
                for (int j = 0; j < Y_LENGTH; j++) {
                    if (numberInCells[i][j] < 0) {
                        filled = false;
                        break;
                    }
                }
            }

            for (int i = 0; i < X_LENGTH - 1; i++) {
                for (int j = 0; j < Y_LENGTH; j++) {
                    if (!filled || numberInCells[i][j] == numberInCells[i + 1][j]) {
                        filled = false;
                        break;
                    }
                }
            }
            for (int j = 0; j < X_LENGTH - 1; j++) {
                for (int i = 0; i < Y_LENGTH; i++) {
                    if (!filled || numberInCells[i][j] == numberInCells[i][j + 1]) {
                        filled = false;
                        break;
                    }
                }
            }

            if (filled) {
                text2.setText("ゲームオーバー");
                text2.append("\n今回の結果");
                text2.append("\nスコア：" + score.getScore());
                text2.append("\n移動回数：" + score.getMoveCount());
                next = false;
                ranking.insertRanking(score);
                ranking.displayRanking(5, text2, 1);

                JFrame frame2 = new JFrame();
                frame2.setSize(250, 260);
                frame2.setVisible(true);
                frame2.add(text2, BorderLayout.CENTER);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ranking.displayRanking(10, text2, 0);

        JFrame frame2 = new JFrame();
        frame2.setSize(250, 260);
        frame2.setVisible(true);
        frame2.add(text2, BorderLayout.CENTER);

    }
}