package numberPuzzle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

class Ranking {
	private List<PuzzleScores> rankingList = null;
	private PuzzleDao dao = null;

	public void displayRanking(int number, JTextArea text, int mode) {
		dao = new PuzzleDao();
		rankingList = new ArrayList<PuzzleScores>();
		try {
			rankingList = dao.getResults(number);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (mode == 0) {
			text.setText("ランキング");
		} else {
			text.append("\nランキング");
		}

		int count = 1;
		for (PuzzleScores result : rankingList) {
			text.append("\n" + count + "位：");
			text.append(result.getName());
			text.append(" スコア：" + result.getScore());
			text.append(" 移動回数：" + result.getMoveCount());
			count++;
		}
	}

	public void insertRanking(Scores score) {
		dao = new PuzzleDao();
		try {
			dao.insertResult("test99", score.getScore(), score.getMoveCount());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}