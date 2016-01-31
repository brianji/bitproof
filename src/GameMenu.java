//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.geom.Rectangle2D;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.JPanel;
//
//public class GameMenu extends JPanel {
//
//	private int lives;
//	private BufferedImage alive = null;
//	private BufferedImage emblem = null;
//	private BufferedImage dead = null;
//	private int buttonBoundX;
//	private int buttonBoundY;
//	private GameFrame f;
//
//	public GameMenu(int x, int y, GameFrame frame){
//		f = frame;
//
//		setSize(x,y/20);
//		lives = 3;
//		setBackground(Color.BLACK);
//
//		try {
//			if(alive == null){
//				alive = ImageIO.read(new File("images/playeralive.png"));
//				emblem = ImageIO.read(new File("images/emblem.png"));
//				dead = ImageIO.read(new File("images/playerdead.png"));
//			}
//		} catch (IOException e) {
//		}
//		addMouseListener(new MouseListener(){
//
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//
//				if(arg0.getX() < getWidth() && arg0.getX() > buttonBoundX-10
//						&& arg0.getY() < getHeight() && arg0.getY() > buttonBoundY-10){
//					f.switchPanel();
//				}
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//		});
//		setVisible(true);
//	}
//
//	public void paint(Graphics g){
//		Graphics2D graphics = (Graphics2D) g;
//		graphics.setRenderingHints(new RenderingHints(
//				RenderingHints.KEY_TEXT_ANTIALIASING,
//				RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
//		graphics.setColor(Color.BLACK);
//	
//	}
//
//	public void loseLife(){
//		lives--;
//	}
//
//
//}
