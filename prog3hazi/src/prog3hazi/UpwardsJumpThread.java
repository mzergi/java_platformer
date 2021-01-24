package prog3hazi;

import java.awt.event.KeyEvent;

public class UpwardsJumpThread extends Thread{
	private GameCharacter character;
	private GameWindow window;
	private Level level;
	private int startingY;
	
	public UpwardsJumpThread(GameCharacter character,GameWindow window) {
		super();
		this.character=character;
		this.window = window;
		level = window.getLevel();
	}
	public void run() {

		character.jumping();
		character.setSpeedY(-36);
		startingY=character.getY();
		while (true) {
			character.setLocation(character.getX(), character.getY()+character.getSpeedY());
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
		character.hitground();
		window.setLastKey(KeyEvent.VK_W);
	}
}
