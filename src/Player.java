import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Player extends Rectangle2D.Double {

	private int x;
	private int y;
	private int xmax;
	private int ymax;
	private int offsetsmall = 8;
	private int offsetlarge = 15;
	
	
	public Player(int xs, int ys){
		xmax = xs-20;
		ymax = (int)(ys*0.95-30);
		x = xs / 2;
		y = (int)(ys * 0.95 / 2);
	}
	
	public void paint(Graphics2D graphics){
		graphics.setColor(new Color(244,67,54));
		graphics.fillRect(x-offsetlarge, y - offsetlarge, 30, 30);
	}
	
	public void setX(int xnew){
		x = xnew;
	}
	
	public void setY(int ynew){
		y = ynew;
	}
	
	public int returnX(){
		return x;
	}
	
	public int returnY(){
		return y;
	}

	
	public void moveX(int dx){
		x = x + dx;
		if(x > xmax){
			x = xmax;
		}
		if(x < 20){
			x = 20;
		}
	}
	
	public void moveY(int dy){
		y = y + dy;
		if(y > ymax){
			y = ymax;
		}
		if(y < 20){
			y = 20;
		}
	}
}

