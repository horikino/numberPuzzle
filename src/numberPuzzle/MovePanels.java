package numberPuzzle;

import java.util.Random;

class MovePanels {
    private int X_LENGTH = 4;
    private int Y_LENGTH = 4;
    boolean isModifiable[][] = new boolean[4][4];

    public MovePanels() {
        initStatus();
    }

    public MovePanels(int x, int y) {
        this.X_LENGTH = x;
        this.Y_LENGTH = y;
        this.isModifiable = new boolean[x][y];
        initStatus();
    }

    private void initStatus() {
        for (int i = 0; i < X_LENGTH; i++) {
            for (int j = 0; j < Y_LENGTH; j++) {
                isModifiable[i][j] = true;
            }
        }
    }

    public void create(int[][] intArray) {
        int dimention1;
        int dimention2;
        while (true) {
            Random ran = new Random();
            dimention1 = ran.nextInt(X_LENGTH);
            dimention2 = ran.nextInt(Y_LENGTH);
            if (intArray[dimention1][dimention2] < 0) {
                intArray[dimention1][dimention2] = 2 * (ran.nextInt(2) + 1);
                break;
            }
        }
    }

    public boolean moveRight(int[][] intArray, Scores score) {
        boolean moved = false;
        for (int i = 0; i < X_LENGTH; i++) {
            for (int j = Y_LENGTH - 1; j > -1; j--) {
                if (intArray[i][j] > 0) {
                    for (int k = Y_LENGTH - 1; k > j; k--) {
                        if (this.isModifiable[i][k] && isSame(intArray[i][j], intArray[i][k])) {
                            boolean isVacant = true;
                            for (int l = j + 1; l < k; l++) {
                                if (intArray[i][l] > 0) {
                                    isVacant = false;
                                }
                            }
                            if (isVacant) {
                                intArray[i][k] *= 2;
                                intArray[i][j] = -1;
                                score.increaseScore(intArray[i][k]);
                                this.isModifiable[i][k] = false;
                                moved = true;
                            }
                        }
                        if (isMovable(intArray[i][j], intArray[i][k])) {
                            intArray[i][k] = intArray[i][j];
                            intArray[i][j] = -1;
                            moved = true;
                        }
                    }

                }
            }
        }
        initStatus();
        return moved;
    }

    public boolean moveLeft(int[][] intArray, Scores score) {
        boolean moved = false;
        for (int i = 0; i < X_LENGTH; i++) {
            for (int j = 0; j < Y_LENGTH; j++) {
                if (intArray[i][j] > 0) {
                    for (int k = 0; k < j; k++) {
                        if (this.isModifiable[i][k] && isSame(intArray[i][j], intArray[i][k])) {
                            boolean isVacant = true;
                            for (int l = j - 1; l > k; l--) {
                                if (intArray[i][l] > 0) {
                                    isVacant = false;
                                }
                            }
                            if (isVacant) {
                                intArray[i][k] *= 2;
                                intArray[i][j] = -1;
                                score.increaseScore(intArray[i][k]);
                                this.isModifiable[i][k] = false;
                                moved = true;
                            }
                        }
                        if (isMovable(intArray[i][j], intArray[i][k])) {
                            intArray[i][k] = intArray[i][j];
                            intArray[i][j] = -1;
                            moved = true;
                        }
                    }

                }
            }
        }
        initStatus();
        return moved;
    }

    public boolean moveUp(int[][] intArray, Scores score) {
        boolean moved = false;

        for (int j = 0; j < Y_LENGTH; j++) {
            for (int i = 0; i < X_LENGTH; i++) {
                if (intArray[i][j] > 0) {
                    for (int k = 0; k < i; k++) {
                        if (this.isModifiable[k][j] && isSame(intArray[i][j], intArray[k][j])) {
                            boolean isVacant = true;
                            for (int l = i - 1; l > k; l--) {
                                if (intArray[l][j] > 0) {
                                    isVacant = false;
                                }
                            }
                            if (isVacant) {
                                intArray[k][j] *= 2;
                                intArray[i][j] = -1;
                                score.increaseScore(intArray[k][j]);
                                this.isModifiable[k][j] = false;
                                moved = true;
                            }
                        }
                        if (isMovable(intArray[i][j], intArray[k][j])) {
                            intArray[k][j] = intArray[i][j];
                            intArray[i][j] = -1;
                            moved = true;
                        }
                    }

                }
            }
        }
        initStatus();
        return moved;
    }

    public boolean moveDown(int[][] intArray, Scores score) {
        boolean moved = false;

        for (int j = 0; j < Y_LENGTH; j++) {
            for (int i = X_LENGTH - 1; i > -1; i--) {
                if (intArray[i][j] > 0) {
                    for (int k = X_LENGTH - 1; k > i; k--) {
                        if (this.isModifiable[k][j] && isSame(intArray[i][j], intArray[k][j])) {
                            boolean isVacant = true;
                            for (int l = i + 1; l < k; l++) {
                                if (intArray[l][j] > 0) {
                                    isVacant = false;
                                }
                            }
                            if (isVacant) {
                                intArray[k][j] *= 2;
                                intArray[i][j] = -1;
                                score.increaseScore(intArray[k][j]);
                                this.isModifiable[k][j] = false;
                                moved = true;
                            }
                        }
                        if (isMovable(intArray[i][j], intArray[k][j])) {
                            intArray[k][j] = intArray[i][j];
                            intArray[i][j] = -1;
                            moved = true;
                        }
                    }

                }
            }
        }
        initStatus();
        return moved;
    }

    private boolean isMovable(int fromNum, int toNum) {
        if (fromNum > 0 && toNum < 0) {
            return true;
        }
        return false;
    }

    private boolean isSame(int fromNum, int toNum) {
        if (fromNum > 0 && (fromNum == toNum)) {
            return true;
        }
        return false;
    }
}