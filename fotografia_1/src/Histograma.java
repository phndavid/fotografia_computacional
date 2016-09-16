import processing.core.PImage;
import processing.core.PApplet;

public class Histograma  extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	public void setup() {
		size(640, 360);	
	}
	public void draw(){

		// Load an image from the data directory
		// Load a different image by modifying the comments
		PImage img = loadImage("/home/phndavid/Documents/git/java/fotrografia_1/tarea/uno.jpg");
		img.resize(600,400);
		image(img, 0, 0);
		int[] hist = new int[256];
		int[] hist_r = new int[256];
		int[] hist_g = new int[256];
		int[] hist_b = new int[256];

		// Calculate the histogram
		for (int i = 0; i < img.width; i++) {
		  for (int j = 0; j < img.height; j++) {
		    int bright = (int) (brightness(get(i, j)));
		    int red = (int) red(img.get(i, j));
		    int green = (int) green(img.get(i, j));
		    int blue = (int) blue(img.get(i, j));		    
		    hist[bright]++;
		    hist_r[red]++;
		    hist_g[green]++;
		    hist_b[blue]++;		    
		  }
		}
		// Find the largest value in the histogram
		int histMax = max(hist);
		int histMax_r = max(hist_r);
		int histMax_g = max(hist_g);
		int histMax_b = max(hist_b);
		// Draw half of the histogram (skip every second value)
		for (int i = 0; i < img.width; i += 2) {
		  // Map i (from 0..img.width) to a location in the histogram (0..255)
		  int which = (int) (map(i, 0, img.width, 0, 255));
		  // Convert the histogram value to a location between 
		  // the bottom and the top of the picture
		  int y = (int) (map(hist[which], 0, histMax, img.height, 0));
		  int r = (int) (map(hist[which], 0, histMax_r, img.height, 0));
		  int g = (int) (map(hist[which], 0, histMax_g, img.height, 0));
		  int b = (int) (map(hist[which], 0, histMax_b, img.height, 0));
		  stroke(255);			
		  line(i, img.height, i, y);
		  stroke(255,0,0);
		  line(i, img.height, i, r);
		  stroke(0,255,0);
		  line(i, img.height, i, g);
		  stroke(0,0,255);
		  line(i, img.height, i, b);		  		 
		}
		
	}

}
