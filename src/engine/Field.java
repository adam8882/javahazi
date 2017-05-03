package engine;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Adam on 2017. 02. 26..
 */

public class Field {
    private Integer matrix[][];
    private Integer fixmatrix[][];
    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    private Piece piece, nextpiece;
    private PieceGenerator generator;
    private boolean isPieceDown = true;
    private int score = 0;
    private int level = 0;
    private Timer timer;

    public Field(long seed) {
        matrix = new Integer[WIDTH][HEIGHT];
        for (int h = 0; h < HEIGHT; h++)
            for (int w = 0; w < WIDTH; w++)
                matrix[w][h] = 0;

        fixmatrix = new Integer[WIDTH][HEIGHT];
        for (int h = 0; h < HEIGHT; h++)
            for (int w = 0; w < WIDTH; w++)
                fixmatrix[w][h] = 0;

        generator = new PieceGenerator(seed);
        nextpiece = generator.getNewPiece();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkIsPieceDown();
                piece.shiftDown(fixmatrix);
                generateMatrix();
                checkIsPieceDown();
            }
        }, 0, 1000);
    }

    private void setDropTime(int t) {
        timer.cancel();
        timer.purge();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkIsPieceDown();
                piece.shiftDown(fixmatrix);
                generateMatrix();
                checkIsPieceDown();
            }
        }, 0, t);
    }

    private void checkIsPieceDown() {
        if (piece != null) {
            for (int j = 0; j < 4; j++) {
                if (piece.getCoordinate(j).getY() < (HEIGHT - 1) &&
                        piece.getCoordinate(j).getY() + 1 >= 0 &&
                        piece.getCoordinate(j).getX() >= 0)
                    if (fixmatrix[piece.getCoordinate(j).getX()][piece.getCoordinate(j).getY() + 1] == 0)
                        ;
                    else
                        isPieceDown = true;
                else
                    isPieceDown = true;
            }
        }
        if (isPieceDown) {
            if (piece != null)
                for (int i = 0; i < 4; i++) {
                    fixmatrix[piece.getCoordinate(i).getX()][piece.getCoordinate(i).getY()] = piece.getColor(); //TODO java.lang.ArrayIndexOutOfBoundsException: 12
                }
            findRow();
            piece = nextpiece;
            nextpiece = generator.getNewPiece();
            isPieceDown = false;
        }
    }
    private void generateMatrix() {
        if(isGameOver()) {
            for (int h = 0; h < HEIGHT; h++)
                for (int w = 0; w < WIDTH; w++)
                    matrix[w][h] = 9;
            return;
        }
        for (int h = 0; h < HEIGHT; h++)
            for (int w = 0; w < WIDTH; w++)
                matrix[w][h] = 0;
        for (int h = 0; h < HEIGHT; h++)
            for (int w = 0; w < WIDTH; w++)
                matrix[w][h] = new Integer(fixmatrix[w][h]);
        for (int i = 0; i < 4; i++) {

            matrix[piece.getCoordinate(i).getX()][piece.getCoordinate(i).getY()] = piece.getColor();
        }
    }

    public void drop() {
        while (!isPieceDown) {
            if (piece != null) {
                for (int j = 0; j < 4; j++) {
                    if (piece.getCoordinate(j).getY() < (HEIGHT - 1) &&
                            piece.getCoordinate(j).getY() + 1 >= 0 &&
                            piece.getCoordinate(j).getX() >= 0)
                        if (fixmatrix[piece.getCoordinate(j).getX()][piece.getCoordinate(j).getY() + 1] == 0) //TODO java.lang.ArrayIndexOutOfBoundsException: 12 for every call of drop()
                            ;
                        else
                            isPieceDown = true;
                    else
                        isPieceDown = true;
                }
            }
            if (!isPieceDown)
                piece.shiftDown(fixmatrix);
        }
        if (piece != null)
            for (int i = 0; i < 4; i++) {
                fixmatrix[piece.getCoordinate(i).getX()][piece.getCoordinate(i).getY()] = piece.getColor(); //TODO java.lang.ArrayIndexOutOfBoundsException: -3
            }
        findRow();
        generateMatrix();
        piece = nextpiece;
        nextpiece = generator.getNewPiece();
        isPieceDown = false;
    }

    public void shiftLeft() {
        if (piece != null && !isPieceDown)
            piece.shiftLeft(fixmatrix);
    }

    public void shiftRight() {
        if (piece != null && !isPieceDown)
            piece.shiftRight(fixmatrix);
    }

    public void rotateLeft() {
        if (piece != null && !isPieceDown)
            piece.rotateLeft(fixmatrix);
    }

    public void rotateRight() {
        if (piece != null && !isPieceDown)
            piece.rotateRight(fixmatrix);
    }

    public Integer[][] getMatrix() {
        if (piece != null && !isPieceDown) generateMatrix();
        Integer [][] matrix2 = new Integer[WIDTH][HEIGHT];
        for (int h = 0; h < HEIGHT; h++)
            for (int w = 0; w < WIDTH; w++)
                matrix2[w][h] = matrix[w][h];
        return matrix2;
    }

    private void findRow() {
        boolean found = true;
        int lines = 0;
        for (int h = 0; h < HEIGHT; h++) {
            found = true;
            for (int w = 0; w < WIDTH; w++)
                if (fixmatrix[w][h] == 0) {
                    found = false;
                    break;
                }
            if (found) {
                clearRow(h);
                lines++;
            }
        }
        switch (lines){
            case 1: score += 40  *(level+1); break;
            case 2: score += 100 *(level+1); break;
            case 3: score += 300 *(level+1); break;
            case 4: score += 1200*(level+1); break;
        }
        while (score > (500 + level*level*1000 - 1)) {
            level += 1;
            int droptime = 1000-level*100;
            if(droptime > 100)
                setDropTime(droptime);
            else
                setDropTime(100);
        }
    }

    private void clearRow(int row) {
        for (; row > 0; row--)
            for (int i = 0; i < WIDTH; i++) {
                fixmatrix[i][row]=fixmatrix[i][row-1];
            }
    }

    public boolean isGameOver(){
        for (int i = 0; i < WIDTH; i++)
            if(fixmatrix[i][1] != 0)
                return true;
        return false;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getNextPiece() {
        return nextpiece.getColor();
    }
}