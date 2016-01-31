import java.awt.Graphics2D;
import java.awt.Shape;

public interface Enemy{

	void update(Graphics2D graphics);
	boolean isAlive();
	boolean intersects(double x, double y, double w, double h);
	
	/*int minX;
	int minY;
	int maxX;
	int maxY;
	String text;
	Timer aTimer;
	Date day;
	int type;
	int size;
	
	public Enemy(int mx, int my, int max, int may, String txt){
		minX = mx;
		minY = my;
		maxX = max;
		maxY = may;
		text = txt;
	}
	
	public int getMinX(){
		return minX;
	}
	
	public int getMinY(){
		return minY;
	}
	
	public int getMaxX(){
		return maxX;
	}
	
	public int getMaxY(){
		return maxY;
	}*/
}
