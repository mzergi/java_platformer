package prog3hazi;

import java.awt.Point;

public class HostileThread extends Thread{
	private GameCharacter hostile;
	private GameCharacter player;
	private Level level;
	private Point [] edges;
	
	public HostileThread(GameCharacter character,Point[] edges, Level level) {
		hostile=character;
		this.edges = edges;
		this.level = level;
		player = level.getPlayer();
	}
	public void run() {
		while(true) {
			
			while (hostile.getLocation().getX()>(int)edges[0].getX()) {
				
				hostile.setLocation(hostile.getX()-5, hostile.getY());
				hostile.repaint();
				if(Math.abs(player.getY()-hostile.getY())<50) {
					level.shootBullet(player.getLocation(),hostile);
				}
				try {
					Thread.sleep(33);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while (hostile.getLocation().getX()<(int)edges[1].getX()) {
				hostile.setLocation(hostile.getX()+5, hostile.getY());
				hostile.repaint();
				if(Math.abs(player.getY()-hostile.getY())<50) {
					level.shootBullet(player.getLocation(),hostile);
				}
				try {
					Thread.sleep(33);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
