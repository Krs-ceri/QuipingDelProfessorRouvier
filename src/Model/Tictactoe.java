package Model;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;

public enum Tictactoe {

	CIRCLE("O","images/o.png"),
	CROSS("X", "images/x.png"),
	EMPTY("gr", "images/r.png");
	private String pion = "";
	private final Image image;
	
	Tictactoe(String a, String filename) {
			this.pion = a;
	        Image tempImage = null;
	        try {
	             tempImage = new Image(filename);
	        } catch (Exception e) {
	             tempImage = null;
	        }
	        image = tempImage;
	   }
	public String toString() {
		return this.pion;
	}
	public Image getImage() {
		return this.image;
	}
}
