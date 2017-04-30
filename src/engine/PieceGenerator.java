package engine;

import java.util.Random;

/**
 * Created by Adam on 2017. 03. 06..
 */
public class PieceGenerator {
    public static final int TYPE_I = 1;
    public static final int TYPE_J = 2;
    public static final int TYPE_L = 3;
    public static final int TYPE_O = 4;
    public static final int TYPE_S = 5;
    public static final int TYPE_T = 6;
    public static final int TYPE_Z = 7;

    private int type;
    Random rand;

    public PieceGenerator(long seed) {
        rand = new Random(seed);
    }

    public Piece getNewPiece() {
        type = rand.nextInt(7) + 1;
        return createPiece(type);
    }

    private Piece createPiece(int color) {
        switch (type) {
            case TYPE_I:
                return new Ipiece(color);
            case TYPE_J:
                return new Jpiece(color);
            case TYPE_L:
                return new Lpiece(color);
            case TYPE_O:
                return new Opiece(color);
            case TYPE_S:
                return new Spiece(color);
            case TYPE_T:
                return new Tpiece(color);
            case TYPE_Z:
                return new Zpiece(color);
            default:
                return null;
        }
    }
}
