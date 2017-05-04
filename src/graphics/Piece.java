package graphics;

import java.awt.Color;
import java.util.ArrayList;

public class Piece {
	private Color color_L = new Color(1,1,1);
	private Color color_B;
	private Color color_R;
	private Color color_T;
	private Color color_C;
	private ArrayList<Color> colors;
	
	private ArrayList<int[]> polyCoords;
	
	private int offsetX;
	private int offsetY;
	private int blockSize;
	private int blockType;
	
	public Piece(int oX, int oY, int bT, int bS) {
		this.offsetX=oX;
		this.offsetY=oY;
		this.blockSize=bS;
		this.blockType=bT;
		this.polyCoords = new ArrayList();
		this.colors = new ArrayList();
		setColors();
		setPolyCoords();
	}
	
	public void setColors () {
		switch (blockType) {
        case 1:  this.color_L = Colors.I_COLOR_L;
		    	 this.color_B = Colors.I_COLOR_B;
		    	 this.color_R = Colors.I_COLOR_R;
		    	 this.color_T = Colors.I_COLOR_T;
		    	 this.color_C = Colors.I_COLOR_C;
                 break;
        case 2:  this.color_L = Colors.J_COLOR_L;
        		 this.color_B = Colors.J_COLOR_B;
        		 this.color_R = Colors.J_COLOR_R;
        		 this.color_T = Colors.J_COLOR_T;
        		 this.color_C = Colors.J_COLOR_C;
                 break;
        case 3:  this.color_L = Colors.L_COLOR_L;
        		 this.color_B = Colors.L_COLOR_B;
        		 this.color_R = Colors.L_COLOR_R;
        		 this.color_T = Colors.L_COLOR_T;
        		 this.color_C = Colors.L_COLOR_C;
                 break;
        case 4:  this.color_L = Colors.O_COLOR_L;
        		 this.color_B = Colors.O_COLOR_B;
        		 this.color_R = Colors.O_COLOR_R;
        		 this.color_T = Colors.O_COLOR_T;
        		 this.color_C = Colors.O_COLOR_C;
                 break;
        case 5:  this.color_L = Colors.S_COLOR_L;
		         this.color_B = Colors.S_COLOR_B;
		         this.color_R = Colors.S_COLOR_R;
		         this.color_T = Colors.S_COLOR_T;
		         this.color_C = Colors.S_COLOR_C;
                 break;
        case 6:  this.color_L = Colors.T_COLOR_L;
        		 this.color_B = Colors.T_COLOR_B;
        		 this.color_R = Colors.T_COLOR_R;
        		 this.color_T = Colors.T_COLOR_T;
        		 this.color_C = Colors.T_COLOR_C;
                 break;
        default:  this.color_L = Colors.Z_COLOR_L;
        		 this.color_B = Colors.Z_COLOR_B;
        		 this.color_R = Colors.Z_COLOR_R;
        		 this.color_T = Colors.Z_COLOR_T;
        		 this.color_C = Colors.Z_COLOR_C;
                 break;
		}
		colors.add(this.color_L);
		colors.add(this.color_B);
		colors.add(this.color_R);
		colors.add(this.color_T);
		colors.add(this.color_C);
	}
	public ArrayList<Color> getColors () {
		return colors;
	}
	public void setPolyCoords () {
		//Bal oldali polygon koordin�t�k
		int xl[] = {-this.blockSize/2+offsetX,-this.blockSize/4+offsetX,-this.blockSize/4+offsetX,-this.blockSize/2+offsetX};
		int yl[] = {this.blockSize/2+offsetY,this.blockSize/4+offsetY,-this.blockSize/4+offsetY,-this.blockSize/2+offsetY};

		//Lenti polygon koordin�t�k
		int xb[] = {-this.blockSize/2+offsetX,-this.blockSize/4+offsetX,this.blockSize/4+offsetX,this.blockSize/2+offsetX};
		int yb[] = {this.blockSize/2+offsetY,this.blockSize/4+offsetY,this.blockSize/4+offsetY,this.blockSize/2+offsetY};

		//Jobb oldali polygon koordin�t�k
		int xr[] = {this.blockSize/2+offsetX,this.blockSize/4+offsetX,this.blockSize/4+offsetX,this.blockSize/2+offsetX};
		int yr[] = {this.blockSize/2+offsetY,this.blockSize/4+offsetY,-this.blockSize/4+offsetY,-this.blockSize/2+offsetY};

		//Fenti polygon koordin�t�k
		int xt[] = {this.blockSize/2+offsetX,this.blockSize/4+offsetX,-this.blockSize/4+offsetX,-this.blockSize/2+offsetX};
		int yt[] = {-this.blockSize/2+offsetY,-this.blockSize/4+offsetY,-this.blockSize/4+offsetY,-this.blockSize/2+offsetY};

		//K�z�ps� polygon koordin�t�k
		int xc[] = {this.blockSize/4+offsetX,this.blockSize/4+offsetX,-this.blockSize/4+offsetX,-this.blockSize/4+offsetX};
		int yc[] = {this.blockSize/4+offsetY,-this.blockSize/4+offsetY,-this.blockSize/4+offsetY,this.blockSize/4+offsetY};
	    
	    this.polyCoords.add(xl);
	    this.polyCoords.add(yl);
	    this.polyCoords.add(xb);
	    this.polyCoords.add(yb);
	    this.polyCoords.add(xr);
	    this.polyCoords.add(yr);
	    this.polyCoords.add(xt);
	    this.polyCoords.add(yt);
	    this.polyCoords.add(xc);
	    this.polyCoords.add(yc);
	}
	public ArrayList<int[]> getPolyCoords () {
		return polyCoords;
	}
}
