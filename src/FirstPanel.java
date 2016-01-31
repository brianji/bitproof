import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class FirstPanel extends JPanel {

	BufferedImage bg = null;
	BufferedImage logo = null;
	int mode = 0;
	Timer timer;
	GameFrame frame;
	ArrayList<Integer>rect;
	ArrayList<String> list;
	ArrayList<String> files;
	int dy = 0;
	int loading = 0;

	public FirstPanel(GameFrame f){
		frame = f;
		try {
			if(logo == null){
			logo = ImageIO.read(new File("images/bitproof.png"));
			bg = ImageIO.read(new File("images/nightsky.jpg"));
			}
		} catch (IOException e) {
		}
		rect = new ArrayList<Integer>();
		setFocusable(true);
		
		addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if(arg0.getX() > getWidth()/4 && arg0.getX() < getWidth() * 3/4){
					for(int i = 0; i < rect.size(); i++){
						if(loading == 1){
							break;
						}
						if(arg0.getY() >= rect.get(i) && arg0.getY() <= rect.get(i) + dy){
							int loading = 1;
							repaint();
							frame.setState(list.get(i), files.get(i));
							frame.switchPanel();
							break;
						}
					}
					
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
		addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(mode == 0 || mode == 1){
					mode = 2;
					repaint();
					timer.stop();
				}else if(mode == 2){
					frame.switchPanel();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}


		});
		timer = new Timer(500, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(mode == 0){
					mode = 1;
				}else if(mode == 1){
					mode = 0;
				}
				repaint();
			}
			
			
		});
	    timer.setInitialDelay(0);
	    timer.start(); 
		setVisible(true);
	}
	public void paint(Graphics g){
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHints(new RenderingHints(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
		graphics.drawImage(bg, 0,0,getWidth(),getHeight(), 0,0, bg.getWidth(), bg.getHeight(), null);
		if(mode == 0){
			graphics.drawImage(logo, getWidth()/2-400, getHeight()/4-100, getWidth()/2+400, getHeight()/4+200, 0,0, logo.getWidth(), logo.getHeight(), null);
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Impact", Font.BOLD, 45));
			
			Rectangle2D rect = graphics.getFontMetrics().getStringBounds("Press Any Key to Continue", graphics);
			graphics.drawString("Press Any Key to Continue",(int)(getWidth()/2-rect.getWidth()/2),(int)(getHeight()*3/4-rect.getHeight()/2));
			
			
			graphics.setFont(new Font("Impact", Font.BOLD, 20));
			graphics.setColor(Color.BLACK);
			Rectangle2D rectmusic = graphics.getFontMetrics().getStringBounds("Music: Battle Song Loop - Goukisan", graphics);
			Rectangle2D rectmusic2 = graphics.getFontMetrics().getStringBounds("Phoenix Wright - Twisted Samurai", graphics);
			int startx = getWidth()-Math.max((int) rectmusic.getWidth(), (int)rectmusic2.getWidth())-10;
			int starty = getHeight()- (int) rectmusic.getHeight() - (int)rectmusic2.getHeight()-10;
			
			graphics.drawString("Music: Battle Song Loop - Goukisan",startx,starty);
			graphics.drawString("Phoenix Wright - Twisted Samurai",startx,starty+(int)rectmusic.getHeight());
		}else if(mode == 1){
			graphics.drawImage(logo, getWidth()/2-400, getHeight()/4-100, getWidth()/2+400, getHeight()/4+200, 0,0, logo.getWidth(), logo.getHeight(), null);
			graphics.setFont(new Font("Impact", Font.BOLD, 20));
			graphics.setColor(Color.BLACK);
			Rectangle2D rectmusic = graphics.getFontMetrics().getStringBounds("Music: Battle Song Loop - Goukisan", graphics);
			Rectangle2D rectmusic2 = graphics.getFontMetrics().getStringBounds("Phoenix Wright - Twisted Samurai", graphics);
			int startx = getWidth()-Math.max((int) rectmusic.getWidth(), (int)rectmusic2.getWidth())-10;
			int starty = getHeight()- (int) rectmusic.getHeight() - (int)rectmusic2.getHeight()-10;
			
			graphics.drawString("Music: Battle Song Loop - Goukisan",startx,starty);
			graphics.drawString("Phoenix Wright - Twisted Samurai",startx,starty+(int)rectmusic.getHeight());
		}else{
			graphics.setFont(new Font("Impact", Font.ITALIC, 30));
			String quote = "\"Do not go gentle into that good night.\"";
			String by = "- Dylan Thomas";
			Rectangle2D rectQuote = graphics.getFontMetrics().getStringBounds(quote, graphics);
			Rectangle2D rectBy = graphics.getFontMetrics().getStringBounds(by, graphics);
			graphics.setColor(Color.WHITE);
			graphics.drawString(quote, (int) (getWidth()/2 - rectQuote.getWidth()/2), 65);
			graphics.drawString(by, (int) (getWidth()/2+40), 100);
			
			
			Scanner s = null;
			try {
				s = new Scanner(new File("dataset.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		    list = new ArrayList<String>(); 
		    files = new ArrayList<String>(); 
			while(s.hasNextLine()){
			    list.add(s.nextLine());
			    files.add(s.nextLine());
			}
			s.close();
			

			int y = getHeight()/5;
			dy = (int)(getHeight()/1.5/list.size());
			for(int i = 0; i < list.size(); i++){
				rect.add(y);
				y = y + dy + 10;
			}
			
			graphics.setColor(Color.WHITE);
			for (int i = 0; i < list.size(); i++) {
				graphics.setFont(new Font("Impact", Font.BOLD, 50));
				String str = list.get(i);
				Rectangle2D r1 = graphics.getFontMetrics().getStringBounds(str, graphics);
				graphics.setFont(new Font("Impact", Font.BOLD, 50));
				graphics.setColor(Color.GRAY);
				graphics.fillRoundRect((int) (getWidth()/4), rect.get(i), (int) getWidth()/2, dy, 15, 15);
				graphics.setColor(Color.WHITE);
				graphics.drawString(str, (int) (getWidth()/2 - r1.getWidth()/2 + 20), rect.get(i)+dy*11/16);		
			}
				
			
		}
	}

}
