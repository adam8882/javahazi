package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by knagymate on 2017. 05. 04..
 */
public class DrawText extends JPanel {
    private int blockSize;
    private Object[][] matrix;
    int type;

    public DrawText(ArrayList<Object[][]> matrix) {
        this.blockSize = Dimensions.BLOCK_SIZE_2;
        this.matrix = mergeArray(matrix);
    }
    public Object[][] mergeArray(ArrayList<Object[][]> text) {
        /*System.out.println(text.get(0).length);
        System.out.println(text.get(0)[1].length);
        System.out.println(text.get(0)[5][4]);
        for (int i = 1; i < text.size(); i++) {
            Object[][] newt = new Object[text.get(i)[0].length-1][text.get(i).length-1];
            for (int j = 1; j < text.get(i).length; j++) {
                for (int k = 1; k < text.get(i)[0].length; k++) {
                    newt[k][j]=text.get(i)[j][k];
                }
            }
            text.set(i,newt);
        }*/
        for(int k=0;k<text.size();k++) {
            Object[][] m = text.get(k);
            Object[][] temp = new Object[m[0].length][m.length];
            for (int i = 0; i < m.length; i++)
                for (int j = 0; j < m[0].length; j++)
                    temp[j][i] = m[i][j];
            text.set(k,temp);
        }

        for (int i = 1; i < text.size(); i++) {
            Object[][] a1 = text.get(0);
            Object[][] a2 = text.get(i);
            Object[][] result = new Object[a1.length + a2.length][5];
            System.arraycopy(a1, 0, result, 0, a1.length);
            System.arraycopy(a2, 0, result, a1.length, a2.length);
            text.set(0, result);
        }
        return text.get(0);
    }

    public void paint(Graphics g) {
        /*for (int i = 0; i < getMatrix()[0].length; i++) {
            for (int j = 0; j < getMatrix().length; j++) {
                g.setColor(Colors.EMPTY_PIECE_BORDER);
                g.drawRect(i * blockSize, j * blockSize, blockSize, blockSize);
            }
        }*/
        for (int i = 0; i < getMatrix().length; i++) {
            for (int j = 0; j < getMatrix()[0].length; j++) {
                type = (int)getMatrix()[i][j];
                if (type != 0) {
                    Piece p = new Piece(getBlockSize() / 2 + getBlockSize() * i + (600-getMatrix().length*10)/2, getBlockSize() / 2 + getBlockSize() * j+30, type,Dimensions.BLOCK_SIZE_2);
                    drawPiece(p, g);
                }
            }
        }
    }

    public void drawPiece(Piece p, Graphics g) {
        ArrayList<int[]> coords = p.getPolyCoords();
        int[] xl = coords.get(0);
        int[] yl = coords.get(1);
        int[] xb = coords.get(2);
        int[] yb = coords.get(3);
        int[] xr = coords.get(4);
        int[] yr = coords.get(5);
        int[] xt = coords.get(6);
        int[] yt = coords.get(7);
        int[] xc = coords.get(8);
        int[] yc = coords.get(9);

        ArrayList<Color> colors = p.getColors();
        Color color_L = colors.get(0);
        Color color_B = colors.get(1);
        Color color_R = colors.get(2);
        Color color_T = colors.get(3);
        Color color_C = colors.get(4);

        g.setColor(color_L);
        g.fillPolygon(xl, yl, 4);
        g.setColor(color_B);
        g.fillPolygon(xb, yb, 4);
        g.setColor(color_R);
        g.fillPolygon(xr, yr, 4);
        g.setColor(color_T);
        g.fillPolygon(xt, yt, 4);
        g.setColor(color_C);
        g.fillPolygon(xc, yc, 4);
    }
    public Object[][] getMatrix() {
        return this.matrix;
    }
    public int getBlockSize() {
        return this.blockSize;
    }
}
