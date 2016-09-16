import processing.core.PApplet;
import processing.core.PImage;


public class Teoria extends PApplet {

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
		
		PImage img1 = loadImage("/home/phndavid/Documents/git/java/fotografia_2/tarea/imagen1.bmp");
		//processComplete(img1,0);
		int width = img1.width+20;
		//PImage img2 = loadImage("/home/phndavid/Documents/git/java/fotografia_2/tarea/imagen2.bmp");
		//processComplete(img2,width);
		//PImage img3 = loadImage("/home/phndavid/Documents/git/java/fotografia_2/tarea/imagen3.bmp");
		//processComplete(img3,width*2);
		PImage img4 = loadImage("/home/phndavid/Documents/git/java/fotografia_2/tarea/imagen4.bmp");
		processComplete(img4,0);
		PImage img5 = loadImage("/home/phndavid/Documents/git/java/fotografia_2/tarea/imagen5.bmp");
		processComplete(img5,width);
		PImage img6 = loadImage("/home/phndavid/Documents/git/java/fotografia_2/tarea/imagen6.bmp");
		processComplete(img6,width*2);
	}
	public void processComplete(PImage img, int width){
		image(img, 0+width, 0);
		int Rmax = 0;
		int Gmax = 0;
		int Bmax = 0;
		for (int i = 0; i < img.width; i++) {
			for (int j = 0; j < img.height; j++) {	
			 int red = (int) red(img.get(i, j));
			 int green = (int) green(img.get(i, j));
			 int blue = (int) blue(img.get(i, j));
			 if(Rmax < red) {Rmax = red;}
			 if(Gmax < green) {Gmax = green;}
			 if(Bmax < blue) {Bmax = blue;}	 
			}
		}
		
		whitePath(img,Rmax,Gmax,Bmax);
		image(img, 0+width,img.height+10);
		imgChromatic(img);
		image(img, 0+width,img.height*2+20);
		pixelToWhite(img);
		image(img, 0+width,img.height*3+40);
		
	}
	public void histograma(PImage img){
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

		  stroke(255,0,0);
		  line(i+img.width, img.height, i+img.width, r);
		  stroke(0,255,0);
		  line(i+img.width*2, img.height, i+img.width*2, g);
		  stroke(0,0,255);
		  line(i+img.width*3, img.height, i+img.width*3, b);		  		 
		}
	}
	public void pixelToWhite(PImage img){
		for (int i = 0; i < img.width; i++) {
			for (int j = 0; j < img.height; j++) {
				 int red = (int) red(img.get(i, j));
				 int green = (int) green(img.get(i, j));
				 int blue = (int) blue(img.get(i, j));
				 if((red >= 61) && (red <= 174) && (green >= 26) && (green <= 106) && (blue >= 0) && (blue <= 77))
						img.set(i, j, color(255,255,255));
				 else
						img.set(i, j, color(0,0,0));					
			}
		}
	}
	public void imgChromatic(PImage img){
		for (int i = 0; i < img.width; i++) {
			for (int j = 0; j < img.height; j++) {
				 float red = (int) red(img.get(i, j));
				 float green = (int) green(img.get(i, j));
				 float blue = (int) blue(img.get(i, j));
				 float sum= red+green+blue;
				 if(sum != 0){
					 float sum_r = (red/sum);
					 float sum_g = (green/sum);
					 float sum_b = (blue/sum);
					 int cr_r = (int) map(sum_r,0,1,0,255);
					 int cr_g = (int) map(sum_g,0,1,0,255);
					 int cr_b = (int) map(sum_b,0,1,0,255);				 
					 img.set(i, j, color(cr_r,cr_g,cr_b));
				 }
				 
			}
		}
	}
	public void whitePath(PImage img, int Rmax, int Gmax, int Bmax){

		for (int i = 0; i < img.width; i++) {
			for (int j = 0; j < img.height; j++) {	
			 int red = (int) red(img.get(i, j));
			 int green = (int) green(img.get(i, j));
			 int blue = (int) blue(img.get(i, j));
				 if(Rmax != 0 && Gmax!= 0 && Bmax != 0){
					 int sum_r = (int) (255*red/Rmax);
					 int sum_g = (int) (255*green/Gmax);
					 int sum_b = (int) (255*blue/Bmax);				 
					 img.set(i, j, color(sum_r,sum_g,sum_b));
				 }	 
			}
		}
	}
}
