package prog3hazi;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

public class Bullet extends ImageLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7354711278271645580L;
	
	private ScaledGifImage gifimg;
	private Image img;
	private double direction;
	private Point destination;
	private int b;
	private int mx;
	private int my;
	
	private GameCharacter shotBy;
	
	public Bullet() throws IOException {
		super(new ScaledGifImage("bullet.png",80,80));
		gifimg = new ScaledGifImage("bullet.png",80,80);
		img = gifimg.getImage();
		
		this.setVisible(false);
	}
	public Bullet(Point d) throws IOException {
		super(new ScaledGifImage("bullet.png",80,80));
		gifimg = new ScaledGifImage("bullet.png",80,80);
		img = gifimg.getImage();
		
		direction = Math.tan((d.getY()-gifimg.getY())/((d.getX()-gifimg.getX())));
		destination=d;
		this.add(gifimg);
		this.setLocation(20,20);
		
		Point v = new Point((int)(destination.getX()-this.getX()),(int)(destination.getY()-this.getY()));
		Point n = new Point((int)((-1)*v.getY()),(int)v.getX());
		mx = (int)n.getX();
		my = (int)n.getY();
		b  = (int)n.getX()*this.getX()+(int)n.getY()*this.getY();
	}
	public void setUpBullet() {
		
			direction = Math.tan((destination.getY()-this.getY())/((destination.getX()-this.getX())));
			Point v = new Point((int)(destination.getX()-this.getX()),(int)(destination.getY()-this.getY()));
			Point n = new Point((int)((-1)*v.getY()),(int)v.getX());
			mx = (int)n.getX();
			my = (int)n.getY();
			b  = (int)n.getX()*this.getX()+(int)n.getY()*this.getY();
	}
	public Image getImage() {
		return img;
	}
	public void setLocation(int x, int y) {
		
		super.setLocation(x,y);
		gifimg.setLocation(x, y);
		
	}
	public Point getDestination() {
		return destination;
	}

	public double getDirection() {
		return direction;
	}
	public int getmx (){
		return mx;
	}
	public int getmy() {
		return my;
	}
	public int getb() {
		return b;
	}
	public void setDestination(Point dest) {
		destination = dest;
	}
	public GameCharacter whoShot() {
		return shotBy;
	}
	public void shotByMe(GameCharacter c) {
		shotBy = c;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img,0,0,this);
	}
	
	
}
