package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import engine.Field;

public class DrawArea extends JPanel{ 
	private Field gameField;
	private int playGroundWidth;
	private int playGroundHeight;
	private int blockSize;

	//Network
	private Integer[][] matrix;
	int type;
	private FrameGUI gui;
	//Network end
	
	public DrawArea(Field gF) {
		super();
		this.gameField = gF;
		
		this.playGroundWidth = gF.WIDTH;
		this.playGroundHeight = gF.HEIGHT; 
		this.blockSize = Dimensions.BLOCK_SIZE;
	}

	//Network
	public DrawArea(FrameGUI gui) {
		super();
		gameField = null;
		this.gui = gui;

		playGroundWidth = Field.WIDTH;
		playGroundHeight = Field.HEIGHT;
		blockSize = Dimensions.BLOCK_SIZE;
	}
	//Network end
	
	public void paint(Graphics g) {
        for (int i = 0; i < playGroundWidth; i++) {
            for (int j = 0; j < playGroundHeight; j++) {
                g.setColor(Colors.EMPTY_PIECE_BORDER);
                g.drawRect (i*blockSize,j*blockSize, blockSize, blockSize);
            }
        }
		if(gameField != null)
			matrix = gameField.getMatrix();
		else
			matrix = gui.getMatrix();
        for (int i = 0; i < playGroundWidth; i++) {
            for (int j = 0; j < playGroundHeight; j++) {
				type = matrix[i][j];
                if (type != 0)
                {
                	Piece p = new Piece(Dimensions.BLOCK_SIZE/2+Dimensions.BLOCK_SIZE*i,Dimensions.BLOCK_SIZE/2+Dimensions.BLOCK_SIZE*j,type);
                	drawPiece(p,g);
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
	    g.fillPolygon(xl,yl,4);
	    g.setColor(color_B);
	    g.fillPolygon(xb,yb,4);
	    g.setColor(color_R);
	    g.fillPolygon(xr,yr,4);
	    g.setColor(color_T);
	    g.fillPolygon(xt,yt,4);
	    g.setColor(color_C);
	    g.fillPolygon(xc,yc,4);
	}
}