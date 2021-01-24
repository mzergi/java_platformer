package prog3hazi;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameWindow extends JFrame implements Serializable{
	

	private GameCharacter character;
	private Clip clip;
	private Level currentlevel;
	private long lastKeyPress;
	private int lastKey=0;
	
	final static int KEY_W=KeyEvent.VK_W;
	final static int KEY_A=KeyEvent.VK_A;
	final static int KEY_D=KeyEvent.VK_D;
	
	final static int TIME_LIMIT = 50;

	public GameWindow() {
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setTitle("Platformer hazi");
		this.setSize(1280,720);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("cursor.png");
		Cursor c = toolkit.createCustomCursor(image, new Point(this.getX(),this.getY()), "cursor");
		this.setCursor(c);
		
		createKeyListener();
	}

	public Clip getAudio() {
		return clip;
	}
	//karakter mozgatása
	public void keyHandler(KeyEvent ke) throws IOException {
	//TODO: egyszerre több billentyű lenyomása vagy egymás után gyorsan több lenyomás észrevehetően nagy késéssel reagál (windows/java beépitett késleltetés első lenyomásnál)
	//TODO: Ugrás után ha nyomva tartod az A-t vagy a D-t akkor mozogjon tovább
		
				if(ke.getKeyCode()==KeyEvent.VK_D&&!character.jumping()) {
					if(!currentlevel.checkHit(character.getLocation())) {
						character.jumped();
						FallingThread jthread = new FallingThread(character,this);
						jthread.start();
					}
					else {
							character.setSpeedX(10);
							character.setLocation(character.getX()+10, character.getY());
							character.repaint();
							lastKeyPress=System.currentTimeMillis();
							lastKey=ke.getKeyCode();
					}
			
				}
				if(ke.getKeyCode()==KeyEvent.VK_A&&!character.jumping()) {
					if(!currentlevel.checkHit(character.getLocation())) {
						character.jumped();
						FallingThread jthread = new FallingThread(character,this);
						jthread.start();
					}
					else if(character.getX()>0){
						character.setSpeedX(-10);
						character.setLocation(character.getX()-10, character.getY());
						character.repaint();
						lastKeyPress=System.currentTimeMillis();
						lastKey=ke.getKeyCode();
					}
			
				}
				if(ke.getKeyCode()==KEY_W&&!character.jumping()&&lastKey!=KEY_D&&lastKey!=KEY_A) {
					character.jumped();
					UpwardsJumpThread jthread = new UpwardsJumpThread(character,this);
					jthread.start();
					
					lastKey=KEY_W;
				}
				if (System.currentTimeMillis() - lastKeyPress < TIME_LIMIT && ((lastKey==KEY_D&&ke.getKeyCode()==KEY_W)) ){
					character.jumped();
					JumpingThread jthread = new JumpingThread(character,this);
					character.setSpeedX(10);
					jthread.start();
					
					lastKey=KEY_W;
				}
				if (System.currentTimeMillis() - lastKeyPress < TIME_LIMIT && ((lastKey==KEY_A&&ke.getKeyCode()==KEY_W)) ){
					character.jumped();
					JumpingThread jthread = new JumpingThread(character,this);
					character.setSpeedX(-10);
					jthread.start();
					
					lastKey=KEY_W;
				}
				
	}

	public void createKeyListener() {
		this.addKeyListener(new KeyListener () {
			boolean running = false;
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if(running==false)
						character.setIcon(new ScaledGifImage("character_run.gif",character.getWidth(),character.getHeight()).getImageIcon());
					running = true;
					keyHandler(e);
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				try {
					if(running==true) {
						lastKey=0;
						character.setIcon(new ScaledGifImage("character_idle.gif",character.getWidth(),character.getHeight()).getImageIcon());
						running = false;
						
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

	}

	public void setLastKey(int k) {
		lastKey=k;
	}
	public void addCharacter() throws IOException {
		ScaledGifImage charimg = new ScaledGifImage("character_idle.gif",120,120);
		character = new GameCharacter(charimg);
		character.setBounds(500, 500, charimg.getWidth(), charimg.getHeight());
		this.add(character);
	}
	public void addPlayerCharacter(GameCharacter c) {
		character = c;
	}
	public void startLevel(Menu menu) throws IOException {
		
		Level level = new Level(new ScaledGifImage("level1.gif",1280,720),this);
		
		this.addPlayerCharacter(level.getPlayer());
		this.add(level);
		currentlevel = level;
		
		
		this.remove(menu);

		this.repaint();
		this.setVisible(true);
		
	}
	public Level getLevel() {
		return currentlevel;
	}
	public void endGame() {
		try {
			ScaledGifImage gameover = new ScaledGifImage("game_over.gif",1280,300);
			gameover.setLocation(0, 250);
			this.add(gameover);
			this.add(new ScaledGifImage("mainmenu2.gif",1280,720));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.repaint();
		this.setVisible(true);
		this.remove(currentlevel);
	}
	
	public synchronized void playAudioOnLoop(File f) {
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
			clip=AudioSystem.getClip();
			clip.open(inputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			Thread.sleep(10000); //loop ameddig él a szál
		}
		catch (UnsupportedAudioFileException uae) {
			uae.printStackTrace();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		catch(LineUnavailableException lue) {
			lue.printStackTrace();
		}
		catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
