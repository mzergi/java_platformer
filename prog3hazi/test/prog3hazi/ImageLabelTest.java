package prog3hazi;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.io.IOException;

import org.junit.Test;

public class ImageLabelTest {

	@Test
	public void testSetGif() {
		ImageLabel label = new ImageLabel();
		try {
			ScaledGifImage image = new ScaledGifImage("mainmenu.gif",200,200);
			label.setGif(image);
			assertSame("ugyanaz a kep", image, label.getScaledGifImage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testConstructors() {
		try {
			ScaledGifImage image = new ScaledGifImage("mainmenu.gif",200,200);
			ImageLabel label = new ImageLabel(image);
			assertSame("ugyanaz a kep", label.getScaledGifImage(),image);
			//ez a teszt sikeresen lefut
			
			image.setLocation(0,0);
			ImageLabel label2 = new ImageLabel("mainmenu.gif",0,0,200,200);
			assertSame("ugyanolyan kep",image.getImage(),label2.getScaledGifImage().getImage());
			
			//A teszt nem fut le sikeresen, mert a ScaledGifImage bound-ja 200-200, míg az ImageLabel saját bound-ja lesz 200-200, a benne levo képé nem.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testBounds() {
		try {
			ImageLabel label = new ImageLabel("mainmenu.gif",0,0,200,200);
			Rectangle bounds = new Rectangle(0,0,200,200);
			assertEquals("a megfelelo meretu", bounds, label.getBounds());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//a testbounds már sikeresen lefut, a Label megfelelo meretu lesz
	}

}
