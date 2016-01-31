import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Square extends Rectangle2D.Double implements Enemy {

	private int size;
	private int x;
	private int y;
	private int max;
	private int dir = 1;
	private Color col;
	private String temp;
	
	public Square(int x, int y, int max, Color c, String str) {
		super(x, y, 0, 0);
		this.x = x;
		this.y = y;
		this.max = max;
		col = c;
		temp = new String(str);
	}
	
	@Override
	public void update(Graphics2D graphics) {
		graphics.setColor(col);
		graphics.fillRect(x - size / 2, y - size / 2, size, size);
		setFrame(x - size / 2, y - size / 2, size, size);

		if (size >= max) {
			dir = -1;
		}

		size += dir;
		graphics.setFont(new Font("Impact", Font.BOLD, 12));
		Rectangle2D rect = graphics.getFontMetrics().getStringBounds(temp, graphics);
		graphics.setColor(Color.WHITE);
		graphics.drawString(temp, (int)(x-rect.getWidth()/2),y);
	}
	
	@Override
	public boolean isAlive() {
		return size >= 0;
	}
}
