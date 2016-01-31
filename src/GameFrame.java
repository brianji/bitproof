import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.Timer;


public class GameFrame extends JFrame{

	Timer timer1;
	Timer timer2;
	Clip clip;
	Clip clip2;
	String setState;
	String file;
	EnemyGenerator generator;

	private int state = 0;
	private FirstPanel first;
	private GamePanel aPanel;

	public GameFrame(String name){
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("twisted.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File("music.wav").getAbsoluteFile());
			clip2 = AudioSystem.getClip();
			clip2.open(audioInputStream);
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
		timer1 = new Timer(1800*1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clip.flush();
				clip.setFramePosition(0);
				clip.start();
			}	    	
		});

		timer2 = new Timer(119*1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					clip2.flush();
					clip2.setFramePosition(0);
					clip2.start();

				} catch(Exception ex) {
					System.out.println("Error with playing sound.");
					ex.printStackTrace();
				}

			}	    	
		});

		timer1.setInitialDelay(0);
		timer2.setInitialDelay(0);

		first = new FirstPanel(this);
		add(first);

		timer1.start();
		state = 0;
		setVisible(true);
	}

	public void setState(String str, String fileName){
		setState = new String(str);
		file = new String(fileName);
	}

	public void switchPanel(){
		if(state == 0){
			remove(first);
			clip.stop();
			timer1.stop();
			timer2.start();
			state = 1;

			aPanel = new GamePanel(getWidth(), getHeight(), this, setState);
			generator = new EnemyGenerator(getWidth(),getHeight(),file);
			aPanel.setFocusable(true);
			add(aPanel);
			setVisible(false);
			setVisible(true);
		}else{
			clip2.stop();
			timer2.stop();
			timer1.start();
			remove(aPanel);
			first = new FirstPanel(this);
			EnemyGenerator.list = new ArrayList<Enemy>();
			EnemyGenerator.aThread.interrupt();
			EnemyGenerator.aDate = null;
			add(first);
			setVisible(false);
			setVisible(true);
			state = 0;
		}
	}

}
