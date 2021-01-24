package prog3hazi;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JLabel;

public class ImageLabel extends JLabel implements Serializable{
	
	private ScaledGifImage img;
	private Image drawimage;
	
	public ImageLabel() {
		super();
	}
	
	public ImageLabel(String s,int x, int y,int width, int height) throws IOException {
		
		super(new ScaledGifImage(s,width,height).getImageIcon());
		img = new ScaledGifImage(s,width,height);
		drawimage = img.getImage();
		this.setBounds(x,y,width,height);
		
	}
	
	public ImageLabel(ScaledGifImage img) {
		super(img.getImageIcon());
		this.img=img;
	}
	
	public ScaledGifImage getScaledGifImage() {
		return img;
	}
	public int getX() {
		return super.getX();
	}
	public int getY() {
		return super.getY();
	}
	public void setLocation(int x, int y) {
		super.setLocation(x, y);
	}
	
	public void setLocation(double x, double y) {
		super.setLocation((int)x, (int)y);
	}
	
	public void setGif(ScaledGifImage image) {
		img = image;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(drawimage,0,0,this);
	}
}
