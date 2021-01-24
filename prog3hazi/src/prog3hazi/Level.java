package prog3hazi;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Level extends ImagePanel implements Serializable{
	
	private GameWindow window;
	
	private ScaledGifImage background;
	private ScaledGifImage platform;
	private ScaledGifImage basetexture;
	private GameTile[] tiles;
	private GameTile[] base;

	private GameCharacter [] characters;
	private HostileThread [] hthreads;
	private List <Bullet> bulletlist;
	private Bullet[] bullets;
	private int freebullet;
	
	public int deadCount;
	
	private long lastPlayerShoot;
	private long lastHostileShoot;

	
	public Level (ScaledGifImage bg,GameWindow window) throws IOException {
		super();
		this.setPreferredSize(new Dimension(1280,720));
		this.setLayout(null);
		
		this.window = window;
		deadCount = 0;
		background = bg;
		platform = new ScaledGifImage("platform.gif",80,20);
		basetexture = new ScaledGifImage("basetexture.jpg",80,20);
		tiles = new GameTile [18];
		bullets = new Bullet [20];
		characters = new GameCharacter [4];
		for (int i = 0;i<20;i++) {
			try {
				bullets[i] = new Bullet();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.add(bullets[i]);
			bullets[i].setBounds(0, 0,20,20);
			//bullets[i].setVisible(true);
			
		}
		freebullet = 0;
		
		ScaledGifImage charimg = new ScaledGifImage("character_idle.gif",120,120);
		characters[0] = new PlayerCharacter(charimg,window);
		characters[0].setBounds(500, 500, charimg.getWidth(), charimg.getHeight());
		this.add(characters[0]);
		
		
		hthreads = new HostileThread [3];
		
		base = new GameTile[60];
		for(int i=0;i<3;i++) {
			for(int j=0;j<20;j++) {
				base[i*20+j] = new GameTile(basetexture);
				this.add(base[i*20+j]);
				base[i*20+j].setBounds(j*80,(i*20+610),80,20);
			}
		}
		
		for (int i=0;i<3;i++) {
			for (int j=0;j<6;j++) {
				tiles[i*6+j] = new GameTile(platform);
				this.add(tiles[i*6+j]);
				tiles[i*6+j].setBounds(600+i*350-j*80, 470-i*120,80,20);
				Point right = new Point((600+(i*350)),(470-(i*120)));
				Point left = new Point((600+(i*350)-400),(470-(i*120)));
				Point[] theseEdges = new Point [2];
				theseEdges[0] = left;
				theseEdges[1] = right;
				tiles[i*6+j].setEdges(theseEdges); //első a bal szél, második a jobb szél
			}
			characters[i+1] = new HostileCharacter(new ScaledGifImage("hostile.gif",120,120),this);
			this.add(characters[i+1]);
			characters[i+1].setBounds(600+i*350-160,380-i*120,120,120);
			
			hthreads[i] = new HostileThread(characters[i+1],tiles[i*6].getEdges(),this);
			
			hthreads[i].start();
		}
		
		ImageLabel background_label = new ImageLabel(background);
		this.add(background_label);
		background_label.setBounds(background.getWidth(),background.getHeight(),background.getWidth(),background.getHeight());
		mouseInit();
		
		lastPlayerShoot = lastHostileShoot = System.currentTimeMillis();
		
	}
	
	public GameCharacter getPlayer() {
		return characters[0];
	}
	public GameCharacter[] getHostiles() {
		GameCharacter [] hostiles = new GameCharacter[3];
		for (int i=0;i<3;i++) {
			hostiles[i] = characters[i+1];
		}
		return hostiles;
	}

	public void mouseInit() {
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				shootBullet(arg0);
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
	}
	public void shootBullet(MouseEvent me) {
		if(characters[0].isAlive()) {
			if(System.currentTimeMillis()-lastPlayerShoot>100) {
				bullets[freebullet].shotByMe(characters[0]);
				bullets[freebullet].setVisible(true);
				bullets[freebullet].setLocation(characters[0].getLocation().getX()+40,characters[0].getLocation().getY()+40);
				bullets[freebullet].setDestination(me.getPoint());
				bullets[freebullet].setUpBullet();
		
				BulletThread bthread = new BulletThread(bullets[freebullet],this);
				bthread.start();
				searchNewFreeBullet();
			
				lastPlayerShoot = System.currentTimeMillis();
			}
		}
	}
	public void shootBullet(Point p, GameCharacter hostile) {
		if(hostile.isAlive()) {
			if(System.currentTimeMillis()-lastHostileShoot>500) {
				bullets[freebullet].shotByMe(hostile);
				bullets[freebullet].setVisible(true);
				bullets[freebullet].setLocation(hostile.getLocation());
				bullets[freebullet].setDestination(p);
				bullets[freebullet].setUpBullet();
		
				BulletThread bthread = new BulletThread(bullets[freebullet],this);
				bthread.start();
				searchNewFreeBullet();
			
				lastHostileShoot = System.currentTimeMillis();
			}
		}
	}
	//teszteléshez
	public void shootBulletWithoutDelay(Point p, GameCharacter hostile) {
		if(hostile.isAlive()) {
			
				bullets[freebullet].shotByMe(hostile);
				bullets[freebullet].setVisible(true);
				bullets[freebullet].setLocation(hostile.getLocation());
				bullets[freebullet].setDestination(p);
				bullets[freebullet].setUpBullet();
		
				BulletThread bthread = new BulletThread(bullets[freebullet],this);
				bthread.start();
				searchNewFreeBullet();
		}
	}
	public boolean checkHit(Point p) {
		boolean hit = false;
		Rectangle r;
		Rectangle c = new Rectangle(p, new Dimension(120,120));
		for(int i=0;i<tiles.length;i++) {
			if(i == 5 || i==11 || i==17) 
			r = new Rectangle(tiles[i].getX()+40,tiles[i].getY(),tiles[i].getWidth(),tiles[i].getHeight());
			else
			r = new Rectangle(tiles[i].getX()-40,tiles[i].getY(),tiles[i].getWidth(),tiles[i].getHeight());
			if(r.intersects(c)) {
				hit = true;
				return hit;
			}
		}
		for(int i=0;i<base.length;i++) {
			r = new Rectangle(base[i].getX(),base[i].getY(),base[i].getWidth(),base[i].getHeight());
			if(r.intersects(c)) {
				hit = true;
				return hit;
			}
		}
		return hit;
	}
	public boolean checkBulletHit(Point p, GameCharacter character) {
		boolean hit = false;
		Rectangle r;
		Rectangle c = new Rectangle(p, new Dimension(30,30));
		for(int i=0;i<tiles.length;i++) {
			if(i == 5 || i==11 || i==17) 
			r = new Rectangle(tiles[i].getX()+40,tiles[i].getY(),tiles[i].getWidth(),tiles[i].getHeight());
			else
			r = new Rectangle(tiles[i].getX()-40,tiles[i].getY(),tiles[i].getWidth(),tiles[i].getHeight());
			if(r.intersects(c)) {
				hit = true;
				return hit;
			}
		}
		for(int i=0;i<base.length;i++) {
			r = new Rectangle(base[i].getX(),base[i].getY(),base[i].getWidth(),base[i].getHeight());
			if(r.intersects(c)) {
				hit = true;
				return hit;
			}
		}
		for(int i=0;i<characters.length;i++) {
			if(characters[i] == character) {
				;
			}
			else {
				r = new Rectangle(characters[i].getX(),characters[i].getY(),characters[i].getWidth(),characters[i].getHeight());
				if(r.intersects(c)) {
					hit = true;
					characters[i].gotHit();
					return hit;
				}
			}
		}
		return hit;
	}
	public GameTile getHitTile(Point p) {
		Rectangle r;
		Rectangle c = new Rectangle(p, new Dimension(120,120));
		for(int i=0;i<tiles.length;i++) {
			r = new Rectangle(tiles[i].getX(),tiles[i].getY(),tiles[i].getWidth(),tiles[i].getHeight());
			if(r.intersects(c)) {
				return tiles[i];
			}
		}
		for(int i=0;i<base.length;i++) {
			r = new Rectangle(base[i].getX(),base[i].getY(),base[i].getWidth(),base[i].getHeight());
			if(r.intersects(c)) {
				return base[i];
			}
		}
		return null;
	}
	public void searchNewFreeBullet() {
		for(int i=0;i<20;i++) {
			if(bullets[i].getX()==0&&bullets[i].getY()==0) {
				freebullet = i;
				break;
			}
		}
	}
	public void deadCounter() {
		deadCount++;
		if(deadCount == characters.length-1) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			window.endGame();
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i=0;i<18;i++) {
			g.drawImage(tiles[i].getImage().getImage(),0,0,this); //első get image ScaledGifImage-t ad, amiből kinyerjük a másodikkal a szimpla Image-et.
		}
		for (int i=0;i<20;i++) {
			g.drawImage(bullets[i].getImage(),0,0,this);
		}
		
		for(int i=0;i<60;i++) {
			g.drawImage(base[i].getImage().getImage(),0,0,this);
		}
		g.drawImage(background.getImage(),0,0,this);
	}
	
}
