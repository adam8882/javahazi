package engine;

/**
 * Created by Adam on 2017. 03. 06..
 */
public class Tpiece extends Piece {
    public Tpiece(int color) {
        super();
        this.color = color;
        int[][] state_0 = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};

        int[][] state_1 = {
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}};

        int[][] state_2 = {
                {0, 0, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}};

        int[][] state_3 = {
                {0, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}};

        v.add(0, state_0);
        v.add(1, state_1);
        v.add(2, state_2);
        v.add(3, state_3);
        super.position.set(3, 0);
    }
}
