package prog3hazi;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

public class GameTile extends ImageLabel implements Serializable{
	private ScaledGifImage img;
	private Rectangle hitbox;
	private Point[] edges;
	
	public GameTile(ScaledGifImage image) {
		super(image);
		img = image;
		hitbox = img.getBounds();
	}
	public GameTile(ScaledGifImage image, int x, int y) {
		super(image);
		img = image;
		img.setLocation(x,y);
		hitbox = img.getBounds();
		
	}
	public Rectangle getHitBox() {
		return hitbox;
	}
	public ScaledGifImage getImage() {
		return img;
	}
	public Point[] getEdges() {
		return edges;
	}
	public void setEdges(Point[] p) {
		edges = p;
	}

}
