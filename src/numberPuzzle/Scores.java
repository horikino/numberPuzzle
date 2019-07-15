package numberPuzzle;

class Scores {
	private long score = 0L;
	private long moveCount = 0L;

	public long getScore() {
		return this.score;
	}

	public void increaseScore(long score) {
		this.score += score;
	}

	public long getMoveCount() {
		return this.moveCount;
	}

	public void increaseMoveCount() {
		this.moveCount++;
	}
}