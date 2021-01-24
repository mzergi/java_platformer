package prog3hazi;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.IOException;

import org.junit.Test;

public class BulletTest {

	@Test
	public void testDestination() {
		try {
			Bullet b = new Bullet(new Point(20,20));
			assertEquals("nem megfelelo a cel", b.getDestination(),new Point(20,20));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testLocation() {
		try {
			Bullet b = new Bullet(new Point(20,20));
			b.setLocation(new Point(30,30));
			assertEquals("nem megfelelo a helyzet", b.getLocation(),new Point(30,30));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testShotByMe() {
		try {
			Bullet b = new Bullet(new Point(20,20));
			GameCharacter c = new GameCharacter();
			b.shotByMe(c);
			assertEquals("nem felel meg aki lotte", b.whoShot(),c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testShoot() {
		GameWindow window = new GameWindow();
		Level level;
		try {
			level = new Level(new ScaledGifImage("level1.gif",300,300),window);
			GameCharacter [] hostiles = level.getHostiles();
			GameCharacter c = level.getPlayer();
			hostiles[0].setLocation(100,100);
			c.setLocation(200,100);
			for(int i=0;i<10;i++) {
				level.shootBulletWithoutDelay(c.getLocation(), hostiles[0]);
			}
			Thread.sleep(3000); //bevárjuk a szálakat
			
			assertEquals("a karakter meghalt",false,c.alive);
			
			//az utolsó thread még valamiért az end game után is próbál futni ?? ??
			//játék közben nem teszi ezt
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
