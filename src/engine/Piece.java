package engine;

import java.util.Vector;

/**
 * Created by Adam on 2017. 02. 26..
 */
public abstract class Piece {
    protected Vector<Coordinate> blocks;
    protected Coordinate position;
    protected Vector<int[][]> v;
    int state;
    int color;

    public Piece() {
        blocks = new Vector(4);
        position = new Coordinate(0, 0);
        v = new Vector(4);
        for (int i = 0; i < 4; i++) {
            blocks.add(i, new Coordinate(0, 0));
        }
    }

    public Coordinate getCoordinate(int n) {
        generateCoordinates();
        return blocks.get(n);
    }

    public void shiftDown(Integer fixmatrix[][]) {
        if (isShiftDownValid(fixmatrix))
            position.setY(position.getY() + 1);
        generateCoordinates();
    }

    public void shiftRight(Integer fixmatrix[][]) {
        if (isShiftRightValid(fixmatrix))
            position.setX(position.getX() + 1);
        generateCoordinates();
    }

    public void shiftLeft(Integer fixmatrix[][]) {
        if (isShiftLeftValid(fixmatrix))
            position.setX(position.getX() - 1);
        generateCoordinates();
    }

    public void rotateRight(Integer fixmatrix[][]) {
        if (isRotateRightValid(fixmatrix))
            if (state > 0) state--;
            else state = 3;
        generateCoordinates();
    }

    public void rotateLeft(Integer fixmatrix[][]) {
        if (isRotateLeftValid(fixmatrix))
            if (state < 3) state++;
            else state = 0;
        generateCoordinates();
    }


    private boolean isShiftDownValid(Integer fixmatrix[][]) {
        for (int i = 0; i < 4; i++) {
            if (blocks.get(i).getY() < (Field.HEIGHT - 1) &&
                    blocks.get(i).getY() >= 0 &&
                    blocks.get(i).getX() >= 0)
                if (fixmatrix[blocks.get(i).getX()][blocks.get(i).getY() + 1] == 0)
                    ;
                else
                    return false;
            else
                return false;
        }
        return true;
    }

    private boolean isShiftRightValid(Integer fixmatrix[][]) {
        for (int i = 0; i < 4; i++) {
            if (blocks.get(i).getX() < (Field.WIDTH - 1) &&
                    blocks.get(i).getY() >= 0 &&
                    blocks.get(i).getX() >= 0)
                if (fixmatrix[blocks.get(i).getX() + 1][blocks.get(i).getY()] == 0)
                    ;
                else
                    return false;
            else
                return false;
        }
        return true;
    }

    private boolean isShiftLeftValid(Integer fixmatrix[][]) {
        for (int i = 0; i < 4; i++) {
            if (blocks.get(i).getX() > 0 &&
                    blocks.get(i).getY() >= 0 &&
                    blocks.get(i).getX() >= 0)
                if (fixmatrix[blocks.get(i).getX() - 1][blocks.get(i).getY()] == 0)
                    ;
                else
                    return false;
            else
                return false;
        }
        return true;
    }

    private void generateCoordinates() {
        int n = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (v.get(state)[i][j] != 0) {
                    blocks.get(n).set(position.getX() + i, position.getY() + j);
                    if (n < 3) n++;
                }
    }

    private boolean isRotateRightValid(Integer fixmatrix[][]) {
        int n = 0, tempState;
        if (state < 3) tempState = state + 1;
        else tempState = 0;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (v.get(tempState)[i][j] != 0) {
                    if (((position.getX() + i) >= 0) && ((position.getX() + i) < Field.WIDTH) && ((position.getY()) + j >= 0) && ((position.getY() + j) < Field.HEIGHT))
                        if (fixmatrix[position.getX() + i][position.getY() + j] == 0) ;
                        else return false;
                    else return false;
                    if (n < 4) n++;
                }
        return true;
    }

    private boolean isRotateLeftValid(Integer fixmatrix[][]) {
        int n = 0, tempState;
        if (state > 0) tempState = state - 1;
        else tempState = 3;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (v.get(tempState)[i][j] != 0) {
                    if (((position.getX() + i) >= 0) && ((position.getX() + i) < Field.WIDTH) && ((position.getY()) + j >= 0) && ((position.getY() + j) < Field.HEIGHT))
                        if (fixmatrix[position.getX() + i][position.getY() + j] == 0) ;
                        else return false;
                    else return false;
                    if (n < 4) n++;
                }
        return true;
    }

    public int getColor() {
        return color;
    }
}
