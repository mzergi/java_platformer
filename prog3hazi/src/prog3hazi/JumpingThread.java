package prog3hazi;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class JumpingThread extends Thread{
	private GameCharacter character;
	private GameWindow window;
	private Level level;
	private int startingY;
	
	public JumpingThread(GameCharacter character,GameWindow window) {
		super();
		this.character=character;
		this.window = window;
		level = window.getLevel();
	}
	public void run() {
		character.jumping();
		character.setSpeedY(-36);
		startingY=character.getY();
		if(character.getX()>0&&character.getSpeedX()>0) {
			while (true) {
				character.setLocation(character.getX()+character.getSpeedX(), character.getY()+character.getSpeedY());
				if(level.checkHit(character.getLocation())) {
					if(character.getSpeedY()<0) {
						character.setSpeedY(0);
						while(character.getY()<startingY-character.getSpeedY()) {
							character.setLocation(character.getX(), character.getY()+character.getSpeedY());
							character.repaint();
							try {
								Thread.sleep(33);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							character.setSpeedY(character.getSpeedY()+4);
						}
						character.setLocation(character.getX(), startingY);
						character.repaint();
						try {
							Thread.sleep(33);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
				
					else {
						startingY=level.getHitTile(character.getLocation()).getY()-100;
						character.setLocation(character.getX(), startingY);
						character.repaint();
						try {
							Thread.sleep(33);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						character.setSpeedY(0);
					}
					break;
				}
			
				character.repaint();
				try {
					Thread.sleep(33);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				character.setSpeedY(character.getSpeedY()+4);

			}
		}
		else {
			FallingThread fthread = new FallingThread(character,window);
			fthread.start();
		}
			character.hitground();
			try {
				Robot robot = new Robot();
				if(character.getSpeedX()>0) {
					robot.keyPress(KeyEvent.VK_D);
					Thread.sleep(500);//eddig tart egy ugrás
					robot.keyRelease(KeyEvent.VK_D);
				}
				else {
					robot.keyPress(KeyEvent.VK_A);
					Thread.sleep(500);//eddig tart egy ugrás
					robot.keyRelease(KeyEvent.VK_A);
				}
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException r) {
				// TODO Auto-generated catch block
				r.printStackTrace();
			}
			window.setLastKey(KeyEvent.VK_W);
		}

}
