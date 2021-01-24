package prog3hazi;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;

public class GameCharacter extends ImageLabel implements Serializable {
	
	private Image drawimage;
	protected int hp;
	private int speedX;
	private int speedY;

	private long lastTime;
	private boolean jumping;
	protected boolean alive;
public GameCharacter() {
	
}
public GameCharacter(ScaledGifImage img) throws IOException {
		
		super(img);
		drawimage = img.getImage();
		speedX=0;
		speedY=0;

		alive = true;
	}


public void setLocation(int x, int y) {
	super.setLocation(x, y);
}

public double getFps() {
	double fps = (lastTime - (lastTime = System.nanoTime()));
	return fps;
}
public boolean jumping() {
	return jumping;
}
public void jumped() {
	jumping=true;
}
public Image getImage() {
	return drawimage;
}
public void hitground() {
	jumping=false;
}
public void setSpeedX(int x) {
	speedX=x;
}
public void setSpeedY(int y) {
	speedY=y;
}
public int getSpeedX() {
	return speedX;
}
public int getSpeedY() {
	return speedY;
}
public void gotHit() {
	hp-=1;
	if(hp==0) {
		this.setVisible(false);
		alive = false;
	}
}
public boolean isAlive() {
	return alive;
}
@Override
protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(drawimage,0,0,this);
}	
	
}
