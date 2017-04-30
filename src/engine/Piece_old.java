package engine;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Adam on 2017. 02. 26..
 */
public class Piece_old {
    private Vector<Coordinate> piece;
    private int type = 0;
    private int state = 0;
    private static final int INITX = 5;
    private static final int TYPE_I = 1;
    private static final int TYPE_J = 2;
    private static final int TYPE_L = 3;
    private static final int TYPE_O = 4;
    private static final int TYPE_S = 5;
    private static final int TYPE_T = 6;
    private static final int TYPE_Z = 7;

    public Piece_old() {
        piece = new Vector(4);
        Random rand = new Random();
        type = rand.nextInt(7) + 1;
        createPiece();
    }

    private void createPiece() {
        switch (type) {
            case TYPE_I:
                piece.add(0, new Coordinate(INITX-2, 0));
                piece.add(1, new Coordinate(INITX-1, 0));
                piece.add(2, new Coordinate(INITX, 0));
                piece.add(3, new Coordinate(INITX+1, 0));
                break;
            case TYPE_J:
                piece.add(0, new Coordinate(INITX-1, 0));
                piece.add(1, new Coordinate(INITX, 0));
                piece.add(2, new Coordinate(INITX+1, 0));
                piece.add(3, new Coordinate(INITX+1, 1));
                break;
            case TYPE_L:
                piece.add(0, new Coordinate(INITX-1, 1));
                piece.add(1, new Coordinate(INITX-1, 0));
                piece.add(2, new Coordinate(INITX, 0));
                piece.add(3, new Coordinate(INITX+1, 0));
                break;
            case TYPE_O:
                piece.add(0, new Coordinate(INITX-1, 0));
                piece.add(1, new Coordinate(INITX, 0));
                piece.add(2, new Coordinate(INITX-1, 1));
                piece.add(3, new Coordinate(INITX, 1));
                break;
            case TYPE_S:
                piece.add(0, new Coordinate(INITX, 0));
                piece.add(1, new Coordinate(INITX+1, 0));
                piece.add(2, new Coordinate(INITX-1, 1));
                piece.add(3, new Coordinate(INITX, 1));
                break;
            case TYPE_T:
                piece.add(0, new Coordinate(INITX-1, 0));
                piece.add(1, new Coordinate(INITX, 0));
                piece.add(2, new Coordinate(INITX+1, 0));
                piece.add(3, new Coordinate(INITX, 1));
                break;
            case TYPE_Z:
                piece.add(0, new Coordinate(INITX-1, 0));
                piece.add(1, new Coordinate(INITX, 0));
                piece.add(2, new Coordinate(INITX, 1));
                piece.add(3, new Coordinate(INITX+1, 1));
                break;
        }

    }

    public Coordinate getCoordinate(int n) {
        return piece.get(n);
    }

    public void shiftDown() {
        for (int i = 0; i < 4; i++)
            piece.get(i).setY(piece.get(i).getY() + 1);
    }

    public void shiftRight() {
        for (int i = 0; i < 4; i++)
            piece.get(i).setX(piece.get(i).getX() + 1);
    }

    public void shiftLeft() {
        for (int i = 0; i < 4; i++)
            piece.get(i).setX(piece.get(i).getX() - 1);
    }

    public void rotateRight() {
        if (state < 3)
            state++;
        else
            state = 0;
        switch (type) {
            case TYPE_I:
                break;
            case TYPE_J:
                break;
            case TYPE_L:
                break;
            case TYPE_O:    //do nothing
                break;
            case TYPE_S:
                break;
            case TYPE_T:
                break;
            case TYPE_Z:
                break;
        }
    }

    public void rotateLeft() {
        if (state == 0)
            state = 3;
        else
            state--;

        switch (type) {
            case TYPE_I:
                break;
            case TYPE_J:
                break;
            case TYPE_L:
                break;
            case TYPE_O:    //do nothing
                break;
            case TYPE_S:
                break;
            case TYPE_T:
                break;
            case TYPE_Z:
                break;
        }
    }

    public int getType() {
        return type;
    }
}
