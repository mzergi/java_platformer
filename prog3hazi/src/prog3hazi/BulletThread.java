package prog3hazi;

public class BulletThread extends Thread{
	private Bullet b;
	private Level level;
	
	public BulletThread (Bullet b, Level level) {
		this.b=b;
		this.level = level;
	}
	public void run() {
		int startingX = b.getX();
		while(b.getX()>0 && b.getX()<1280 && b.getY()>0 && b.getY()<720) {
			for(int j=0;j<20;j++) {
				if(b.getDestination().getX()>startingX)
					b.setLocation(b.getX()+1,(b.getb()-b.getmx()*(b.getX()+1))/b.getmy());
				else
					b.setLocation(b.getX()-1,(b.getb()-b.getmx()*(b.getX()-1))/b.getmy());
			}
			
			b.repaint();
			
			if(level.checkBulletHit(b.getLocation(),b.whoShot())) break;
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		b.setVisible(false);
		b.setLocation(0,0);
	}
}
