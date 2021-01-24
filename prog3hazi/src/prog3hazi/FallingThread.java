package prog3hazi;

public class FallingThread extends Thread{
	private GameCharacter character;

	private Level level;

	
	public FallingThread(GameCharacter character,GameWindow window) {
		super();
		this.character=character;

		level = window.getLevel();
	}
	public void run() {
		character.jumped();
		character.setSpeedY(10);
		while(!level.checkHit(character.getLocation())) {
			character.setLocation(character.getX()+character.getSpeedX(), character.getY()+character.getSpeedY());
			character.repaint();
			if(character.getSpeedX()>4)
			character.setSpeedX(character.getSpeedX()-1);
			character.setSpeedY(character.getSpeedY()+5);
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		character.setSpeedY(0);
		character.setSpeedX(0);
		character.setLocation(character.getX(), level.getHitTile(character.getLocation()).getY()-100); 
		character.repaint();
		
		character.hitground();
	}
}
