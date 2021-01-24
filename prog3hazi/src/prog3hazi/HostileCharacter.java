package prog3hazi;

import java.awt.Graphics;
import java.io.IOException;
import java.io.Serializable;

public class HostileCharacter extends GameCharacter implements Serializable{
	private Level level;
	public HostileCharacter(ScaledGifImage img, Level level) throws IOException {
		super(img);
		hp = 4;
		this.level = level;
	}
	public void gotHit() {
		hp-=1;
		if(hp==0) {
			this.setVisible(false);
			alive = false;
			level.deadCounter();
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
