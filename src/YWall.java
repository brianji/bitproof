import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class YWall extends Rectangle2D.Double implements Enemy {

	private int width;
	private int length;
	private int x;
	private int y;
	private int maxX;
	private int maxY;
	private String temp;
	
	public YWall(int size, int x, int y, int maxX, int maxY, Color c, String str) {
		super(x - size / 2, y - size / 2, size, size);
		this.x = x;
		this.y = y;
		this.width = size;
		this.length = size;
		this.maxX = maxX;
		this.maxY = maxY;
		temp = new String(str);
	}
	
	@Override
	public void update(Graphics2D graphics) {
		graphics.setColor(new Color(0, 0, 255));
		graphics.fillRect(x - width / 2, y - length / 2, width, length);
		setFrame(x - width / 2, y - length / 2, width, length);
		length += 2;
		graphics.setFont(new Font("Impact", Font.BOLD, 12));
		Rectangle2D rect = graphics.getFontMetrics().getStringBounds(temp, graphics);
		graphics.setColor(Color.WHITE);
		graphics.drawString(temp, (int)(x-rect.getWidth()/2),y);
	}

	@Override
	public boolean isAlive() {
		return y - length / 2 >= -10 || y + length / 2 <= maxY+10;
	}
}
