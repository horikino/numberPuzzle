package numberPuzzle;

import javax.swing.JTextArea;

class DisplayScore {

    public void display(int[][] intArray, Scores score, JTextArea text) {
        String str = "";

        str += "スコア：" + score.getScore() + "  移動回数：" + score.getMoveCount();
        text.setText(str);
    }
}