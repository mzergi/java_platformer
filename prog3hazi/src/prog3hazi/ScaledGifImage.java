package prog3hazi;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class ScaledGifImage extends JComponent implements Serializable{
	private Image img;
	private ImageIcon icon;
	private JLabel label;
	private BufferedImage bimage;
	
	public ScaledGifImage(String s, int width, int height) throws IOException {
		
		bimage = ImageIO.read(new File (s));
		
		InputStream in= new FileInputStream(new File(s));
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte [16384];
		while((nRead = in.read(data, 0, data.length))!= -1) {
			buffer.write(data, 0, nRead);
		}
		img=Toolkit.getDefaultToolkit().createImage(buffer.toByteArray()).getScaledInstance(width, height, Image.SCALE_DEFAULT);
		
		icon = new ImageIcon(img);
		label = new JLabel(icon);
	}
	
	public JLabel getLabel() {
		return label;
	}
	
	public Image getImage() {
		return img;
	}
	public ImageIcon getImageIcon() {
		return icon;
	}
	
	public int getWidth() {
		return icon.getIconWidth();
	}
	
	public int getHeight() {
		return icon.getIconHeight();
	}
	
	public BufferedImage getBufferedImage() {
		return bimage;
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img != null) {
			g.drawImage(img, 0, 0, this);
		}
	}
	
}
