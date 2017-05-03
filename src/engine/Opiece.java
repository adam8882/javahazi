package engine;

/**
 * Created by Adam on 2017. 03. 06..
 */
public class Opiece extends Piece {
    public Opiece(int color) {
        super();
        this.color = color;
        int[][] state_0 = {
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};

        v.add(0, state_0);
        v.add(1, state_0);
        v.add(2, state_0);
        v.add(3, state_0);
        super.position.set(3, 0);
    }
}
