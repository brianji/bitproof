import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.sound.sampled.Line;
import javax.swing.Timer;

public class EnemyGenerator implements Runnable {

	int maxEnemy = 12;
	Timer aTimer;
	String filename;
	static ArrayList<Enemy> list = new ArrayList<Enemy>();
	private int x;
	private int y;
	private BoundReader reader;
	private TypeSizeReader typeR;
	private BufferedReader br;
	private double latma;
	private double lonma;
	private double latmi;
	private double lonmi;
	private int invalid;
	private double scalelat;
	private double scalelong;
	public static Date aDate = null;
	public static Thread aThread;


	public EnemyGenerator(int mx, int my, String filename) {
		invalid = 0;
		reader = new BoundReader(filename+".data");
		typeR = new TypeSizeReader(filename);
		latma = reader.getMaxLat();
		lonma = reader.getMaxLong();
		latmi = reader.getMinLat();
		lonmi = reader.getMinLong();

		if(latma == Double.MAX_VALUE){
			invalid = 1;
		}else{
			scalelat = (latma-latmi)/mx;
			scalelong = (lonma-latmi)/my;
		}

		list = new ArrayList<Enemy>();
		this.filename = new String(filename);
		x = mx;
		y = my;

		aThread = new Thread(this);
		aThread.start();

	}

	@Override
	public void run() {
		try {
			br = new BufferedReader(new FileReader("files/"+filename+".data"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){

			try {
				Thread.sleep(500);
			
			} catch (InterruptedException e1) {
				break;
			}
			if (list.size() < maxEnemy) {
				try {
					String line;
					line = br.readLine();
					if(line == null){
						break;
					}
					String[] data = line.split(",");
					if(data[0].indexOf("-") != -1){
						aDate = new Date(data[0]);
					}
					int k;
					if(typeR.getTypes().containsKey(data[3])){
						k = typeR.getTypes().get(data[3]);
					}else{
						k = (int) (Math.random() * 6) + 1;
					}

					//k = 8 - k;
					if(k < 1){
						k = 1;
					}
					int s;
					if(typeR.getSizes().containsKey(data[4])){
						s = typeR.getSizes().get(data[4]);
					}else{
						if(k == 1){
							s=(int) (Math.random()* y / 2);
						}else if (k ==2){
							s = (int) (Math.random() * y / 4);
						}else if (k == 3){
							s= (int) (Math.random() * y / 4);
						}else if(k==4){
							s=(int) (Math.random()* y / 2);
						}else if(k==5){
							s = (int) (Math.random() * x / 3);
						}else{
							s = (int)(Math.random()*y/3);
						}
					}
					if(invalid == 1){
						if (k == 1) {
							list.add(new Grower((int) (Math.random() * x),
									(int) (Math.random() * y), s%(y/2), new Color((int) (Math.random() * 255),
											(int) (Math.random() * 255),
											(int) (Math.random() * 255)),data[3]));
							continue;
						} else if (k == 2) {
							int temp = (int) (Math.random() * 4) + 1;
							int dx = (int) (Math.random() * 5);
							int dy = (int) (Math.random() * 5);
							int startX;
							int startY;
							s = s % (y/4);
							if (temp == 1) {
								startX = -1 * s;
								startY = (int) (Math.random() * y);
							} else if (temp == 2) {
								startX = s;
								startY = (int) (Math.random() * y);
								dx = dx * -1;
							} else if (temp == 3) {
								startX = (int) (Math.random() * x);
								startY = -1 * s;
							} else {
								startX = (int) (Math.random() * x);
								startY = s;
								dy = dy * -1;
							}

							list.add(new Projectile(s, startX, startY, dx, dy,
									x, y, new Color((int) (Math.random() * 255),
											(int) (Math.random() * 255),
											(int) (Math.random() * 255)),data[3]));
							continue;

						} else if (k == 3) {
							int temp = (int) (Math.random() * 4) + 1;
							s= (int) (Math.random() * y / 4);
							int dx = (int) (Math.random() * 5);
							int dy = (int) (Math.random() * 5);
							int startX;
							int startY;
							s = s % (y/4);

							if (temp == 1) {
								startX = -1 * s;
								startY = (int) (Math.random() * y);
							} else if (temp == 2) {
								startX = s;
								startY = (int) (Math.random() * y);
								dx = dx * -1;
							} else if (temp == 3) {
								startX = (int) (Math.random() * x);
								startY = -1 * s;
							} else {
								startX = (int) (Math.random() * x);
								startY = s;
								dy = dy * -1;
							}

							list.add(new CamoProjectile(s, startX, startY, dx,
									dy, x, y, new Color(
											(int) (Math.random() * 255),
											(int) (Math.random() * 255),
											(int) (Math.random() * 255)),data[3]));
							continue;

						} else if (k == 4) {
							list.add(new Square((int) (Math.random() * x),
									(int) (Math.random() * y),s%(y/2), new Color((int) (Math
											.random() * 255),
											(int) (Math.random() * 255),
											(int) (Math.random() * 255)), data[3]));
							continue;

						} else if (k == 5) {
							list.add(new XWall(s%(x/5),
									(int) (Math.random() * x),
									(int) (Math.random() * y), x, y, new Color(
											(int) (Math.random() * 255),
											(int) (Math.random() * 255),
											(int) (Math.random() * 255)), data[3]));
							continue;

						} else {
							list.add(new YWall(s%(y/5),
									(int) (Math.random() * x),
									(int) (Math.random() * y), x, y, new Color(
											(int) (Math.random() * 255),
											(int) (Math.random() * 255),
											(int) (Math.random() * 255)), data[3]));
							continue;

						}
					}else{
						double templat = (Double.parseDouble(data[1])-latmi)*scalelat;
						double templong = (Double.parseDouble(data[2])-lonmi)*scalelong;
						if (k == 1) {
							list.add(new Grower((int)templat,(int)templong, s%(y/2), new Color((int) (Math.random() * 255),
									(int) (Math.random() * 255),
									(int) (Math.random() * 255)),data[3]));
							continue;

						} else if (k == 2) {
							int temp = (int) (Math.random() * 4) + 1;
							int dx = (int) (Math.random() * 5);
							int dy = (int) (Math.random() * 5);
							int startX = (int) templat;
							int startY = (int)templong;
							s = s % (y/4);
							if (temp == 1) {
								startX = -1 * s;
							} else if (temp == 2) {
								startX = s;
								dx = dx * -1;
							} else if (temp == 3) {
								startY = -1 * s;
							} else {
								startY = s;
								dy = dy * -1;
							}

							list.add(new Projectile(s, startX, startY, dx, dy,
									x, y, new Color((int) (Math.random() * 255),
											(int) (Math.random() * 255),
											(int) (Math.random() * 255)),data[3]));
							continue;

						} else if (k == 3) {
							int temp = (int) (Math.random() * 4) + 1;
							s= (int) (Math.random() * y / 4);
							int dx = (int) (Math.random() * 5);
							int dy = (int) (Math.random() * 5);
							int startX = (int)templat;
							int startY = (int)templong;
							s = s % (y/4);

							if (temp == 1) {
								startX = -1 * s;
							} else if (temp == 2) {
								startX = s;
								dx = dx * -1;
							} else if (temp == 3) {
								startY = -1 * s;
							} else {

								startY = s;
								dy = dy * -1;
							}

							list.add(new CamoProjectile(s, (int)startX, (int)startY, dx,
									dy, x, y, new Color(
											(int) (Math.random() * 255),
											(int) (Math.random() * 255),
											(int) (Math.random() * 255)),data[3]));
							continue;

						} else if (k == 4) {
							list.add(new Square((int)templat, (int)templong,s%(y/2), new Color((int) (Math
									.random() * 255),
									(int) (Math.random() * 255),
									(int) (Math.random() * 255)), data[3]));
							continue;

						} else if (k == 5) {
							list.add(new XWall(s%(x/5),
									(int) templat,
									(int) templong, x, y, new Color(
											(int) (Math.random() * 255),
											(int) (Math.random() * 255),
											(int) (Math.random() * 255)), data[3]));
							continue;

						} else {
							list.add(new YWall(s%(y/5),
									(int)templat,
									(int)templong, x, y, new Color(
											(int) (Math.random() * 255),
											(int) (Math.random() * 255),
											(int) (Math.random() * 255)), data[3]));
							continue;


						}
					}
			}catch(Exception e){	
				System.out.println(e);
			}
		}
	}
}
}