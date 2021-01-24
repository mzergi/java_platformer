package prog3hazi;

import java.awt.Graphics;
import java.io.IOException;
import java.io.Serializable;

public class PlayerCharacter extends GameCharacter implements Serializable{
	private GameWindow window;
	
	public PlayerCharacter(ScaledGifImage img, GameWindow window) throws IOException {
		super(img);
		hp = 10;
		this.window = window;
	}
	public void gotHit() {
		hp-=1;
		if(hp==0) {
			this.setVisible(false);
			alive = false;
			try {
				Thread.sleep(3000);
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
	}
}
