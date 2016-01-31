import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class CamoProjectile extends Projectile {
	
	private int color;
	private String temp;

	public CamoProjectile(int size, int x, int y, int dx, int dy, int maxX, int maxY, Color c, String str) {
		super(size, x, y, dx, dy, maxX,maxY, Color.WHITE, str);
		temp = new String(str);
	}

	@Override
	public void paint(Graphics2D graphics) {
		if (color >= 400) {
			graphics.setColor(new Color(0, 255, 0));
		} else {
			graphics.setColor(new Color(3,58,114));
		}
		graphics.fillOval(x - size / 2, y - size / 2, size, size);
		color = (color + 1) % 800;
		graphics.setFont(new Font("Impact", Font.BOLD, 12));
		Rectangle2D rect = graphics.getFontMetrics().getStringBounds(temp, graphics);
		graphics.setColor(Color.WHITE);
		graphics.drawString(temp, (int)(x-rect.getWidth()/2),y);
	}
}
