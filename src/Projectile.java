import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Projectile extends Ellipse2D.Double implements Enemy {
	
	protected int size;
	protected int x;
	protected int y;
	private int dx;
	private int dy;
	private int maxX;
	private int maxY;
	private Color col;
	private String temp;
	
	public Projectile(int size, int x, int y, int dx, int dy, int maxX, int maxY, Color c, String str) {
		super(x - size / 2, y - size / 2, size, size);
		this.size = size;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.maxX = maxX;
		this.maxY = maxY;
		col = c;
		temp = new String(str);
	}
	
	@Override
	public void update(Graphics2D graphics) {
		paint(graphics);
		setFrame(x - size / 2, y - size / 2, size, size);
		x += dx;
		y += dy;
	}
	
	public void paint(Graphics2D graphics) {
		graphics.setColor(col);
		graphics.fillOval(x - size / 2, y - size / 2, size, size);
		graphics.setFont(new Font("Impact", Font.BOLD, 12));
		Rectangle2D rect = graphics.getFontMetrics().getStringBounds(temp, graphics);
		graphics.setColor(Color.WHITE);
		graphics.drawString(temp, (int)(x-rect.getWidth()/2),y);
	}

	@Override
	public boolean isAlive() {
		return x + size * 3 / 2 >= 0
			&& y + size *3 / 2 >= 0 
			&& x - size *3/ 2 <= maxX
			&& y - size *3/ 2 <= maxY;
	}
}
