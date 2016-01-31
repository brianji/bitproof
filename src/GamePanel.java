import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	private Player main;
	private BufferedImage bg = null;
	private final Set<Integer> pressed = new TreeSet<Integer>();
	private GameFrame f;
	private int lives;
	private BufferedImage alive = null;
	private BufferedImage emblem = null;
	private BufferedImage dead = null;
	private int buttonBoundX;
	private int buttonBoundY;
	private String word;
	private EnemyGenerator generator;
	private int gameover;

	public GamePanel(int x, int y, GameFrame frame, String stat){
		f = frame;
		lives = 3;
		gameover = 0;
		main = new Player(x,y);
		setSize(x,y);
		setFocusable(true);
		addKey();
		if(bg == null){
			try {
				bg = ImageIO.read(new File("images/nightsky.jpg"));
			} catch (IOException e) {
			}

			try {
				if(alive == null){
					alive = ImageIO.read(new File("images/playeralive.png"));
					emblem = ImageIO.read(new File("images/emblem.png"));
					dead = ImageIO.read(new File("images/playerdead.png"));
				}
			} catch (IOException e) {
			}
		}
		addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if(arg0.getX() < getWidth() && arg0.getX() > buttonBoundX-5
						&& arg0.getY() < getHeight() && arg0.getY() > buttonBoundY-20){
					f.switchPanel();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		word = new String(stat);

		Thread aThread = new Thread(this);
		aThread.start();

		setVisible(true);

	}

	public void paint(Graphics g){
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHints(new RenderingHints(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, getWidth(),getHeight());
		graphics.drawImage(bg, 0,0,getWidth(),getHeight(), 0,0, bg.getWidth(), bg.getHeight(), null);
		main.paint(graphics);

		if(!EnemyGenerator.list.isEmpty()){
			for(int i = 0; i < EnemyGenerator.list.size(); i++){
				if(EnemyGenerator.list.get(i) == null){
					continue;
				}
				if(EnemyGenerator.list.get(i) != null&&EnemyGenerator.list.get(i).intersects(main.returnX()-15, main.returnY()-15, 30,30)){
					lives--;
					EnemyGenerator.list.remove(i);
					i--;
					if(lives == 0){
						EnemyGenerator.aThread.interrupt();
						EnemyGenerator.list = new ArrayList<Enemy>();
						EnemyGenerator.aDate = null;
						gameover = 1;
					}
				}else if(EnemyGenerator.list.get(i) != null){
					EnemyGenerator.list.get(i).update(graphics);
				}

				if(i >= 0 &&EnemyGenerator.list.get(i) != null&& !(EnemyGenerator.list.get(i).isAlive())){
					EnemyGenerator.list.remove(i);
					i--;
				}
			}


		}
		int startX = 10;

		for(int i = 0; i < 3; i++){
			if(lives > i){
				graphics.drawImage(alive, startX,getHeight()-38,startX+30,getHeight()-8, 0,0, alive.getWidth(), alive.getHeight(), null);
			}else{
				graphics.drawImage(dead, startX,getHeight()-38,startX+30,getHeight()-8, 0,0, dead.getWidth(), dead.getHeight(), null);
			}
			startX = startX + 40;
		}

		graphics.setFont(new Font("Impact", Font.BOLD, 20));
		graphics.setColor(Color.WHITE);
		Rectangle2D rectmusic = graphics.getFontMetrics().getStringBounds("Menu", graphics);
		graphics.fillRect(getWidth()-(int)rectmusic.getWidth()-10,getHeight()-(int)rectmusic.getHeight()-20, getWidth(), getHeight());
		buttonBoundX = getWidth()-(int)rectmusic.getWidth()-10;
		buttonBoundY = getHeight()-(int)rectmusic.getHeight();
		graphics.setColor(Color.BLACK);
		graphics.drawString("Menu",getWidth()-(int)rectmusic.getWidth()-5,getHeight()-(int)rectmusic.getHeight()+10);
		graphics.fillRect(0 ,getHeight()-(int)rectmusic.getHeight()-25, getWidth(), 5);
		graphics.setColor(Color.WHITE);
		Rectangle2D wordbound = graphics.getFontMetrics().getStringBounds(word, graphics);
		graphics.drawString(word,getWidth()/5 , getHeight()-(int)wordbound.getHeight()+10);

		if(EnemyGenerator.aDate != null && gameover == 0){
			Rectangle2D daterect = graphics.getFontMetrics().getStringBounds(EnemyGenerator.aDate.toString(), graphics);
			graphics.setColor(Color.WHITE);
			graphics.drawString(EnemyGenerator.aDate.toString(), (int)(getWidth()/2 - daterect.getWidth()/2),(int)(getHeight()-(int)rectmusic.getHeight()+5));
		}else if(gameover == 1){
			Rectangle2D daterect = graphics.getFontMetrics().getStringBounds("Game Over!", graphics);
			graphics.setColor(Color.WHITE);
			graphics.drawString("Game Over!", (int)(getWidth()/2 - daterect.getWidth()/2),getHeight()-(int)wordbound.getHeight()+10);
		}
	}


	public void addKey(){

		addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				int xchange = 0;
				int ychange = 0;

				if(!pressed.contains(arg0.getKeyCode())){
					pressed.add(arg0.getKeyCode());
				}
				int d = getHeight()/170;
				for(Integer i: pressed){
					if(i.intValue() == KeyEvent.VK_UP){
						ychange = ychange - getHeight()/96;
					}
					if(i.intValue() == KeyEvent.VK_DOWN){
						ychange = ychange + getHeight()/96;

					}
					if(i.intValue() == KeyEvent.VK_LEFT){
						xchange = xchange - getWidth()/170;
					}
					if(i.intValue() == KeyEvent.VK_RIGHT){
						xchange = xchange + getWidth()/170;
					}
				}
				main.moveX(xchange);
				main.moveY(ychange);
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				pressed.remove(Integer.valueOf(arg0.getKeyCode()));
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}


		});
	}

	@Override
	public void run() {
		while(true){
			// TODO Auto-generated method stub
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}

}
