import processing.core.PApplet;
import processing.core.PImage;


public class SpaceColor extends PApplet{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PImage img1;
	PImage img2;
	PImage img3;

	public void setup() {
	  
	  img1= loadImage("/home/phndavid/Documents/git/java/fotografia_3/tarea/uno.jpg");
	  img2= loadImage("/home/phndavid/Documents/git/java/fotografia_3/tarea/dos.jpg");
	  img3= loadImage("/home/phndavid/Documents/git/java/fotografia_3/tarea/tres.jpg");  
	}

	/**
	 * 
	 * @param img
	 */
	public void histogramRGB(PImage img) {

	  int[] histR = new int[256];
	  int[] histG = new int[256];
	  int[] histB = new int[256];


	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (red(img.get(i, j)));
	      int g = (int) (green(img.get(i, j)));
	      int b = (int) (blue(img.get(i, j)));
	      histR[r]++; 
	      histG[g]++; 
	      histB[b]++;
	    }
	  }

	  int histMaxR = max(histR);
	  int histMaxG = max(histG);
	  int histMaxB = max(histB);

	  for (int i = 0; i < img.width; i += 1) {
	  int which = (int) (map(i, 0, img.width, 0, 255));	  
	  	  int yr = (int) (map(histR[which], 0, histMaxR, img.height, 0));
		  stroke(255, 0, 0);
		  line(i+img.width, img.height, i+img.width, yr);
		           
		  
		  int yv = (int) (map(histG[which], 0, histMaxG, img.height, 0));
		  stroke(0, 255, 0);
		  line(i+img.width*2, img.height, i+img.width*2, yv);
		  
		  int yb = (int) (map(histB[which], 0, histMaxB, img.height, 0));
		  stroke(0, 0, 255);
		  line(i+img.width*3,img.height, i+img.width*3, yb);
	  }
	}
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramHue(PImage img) {

	  int[] histH = new int[256];

	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (hue(img.get(i, j)));
	      histH[r]++;
	    }
	  }

	  return histH;
	}
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramSat(PImage img) {

	  int[] histS = new int[256];

	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (saturation(img.get(i, j)));
	      histS[r]++;
	    }
	  }

	  return histS;
	}
	
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramValue(PImage img) {

	  int[] histV = new int[256];

	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (brightness(img.get(i, j)));
	      histV[r]++;
	    }
	  }

	  return histV;
	}
	/**
	 * 
	 * @param img
	 */
	public void histogramHSV(PImage img)
	{
	  int[] histH = histogramHue(img);
	  int[] histS = histogramSat(img);
	  int[] histV = histogramValue(img);

	  int histMaxH = max(histH);
	  int histMaxS = max(histS);
	  int histMaxV = max(histV);

	  for (int i = 0; i < img.width; i += 1) {
	    int which = (int) (map(i, 0, img.width, 0, 255));
	    int yr = (int) (map(histH[which], 0, histMaxH, img.height, 0));
	    stroke(255, 100, 0);
	    line(i+img.width, img.height, i+img.width, yr);
	    
	    int yv = (int) (map(histS[which], 0, histMaxS, img.height, 0));
	    stroke(0, 255, 100);
	    line(i+img.width*2, img.height, i+img.width*2, yv);
	    
	    int yb = (int) (map(histV[which], 0, histMaxV, img.height, 0));
	    stroke(100, 0, 255);
	    line(i+img.width*3, img.height, i+img.width*3, yb);
	  }
	}
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramUpperY(PImage img) {

	  int[] histY = new int[1000];

	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (red(img.get(i, j)));
	      int g = (int) (green(img.get(i, j)));
	      int b = (int) (blue(img.get(i, j)));
	      int pos = (int) (map((float) (0.177*r + 0.813*g + 0.011*b), 0, 255, 0, 999));
	      histY[pos]++;
	    }
	  }  
	  return histY;
	}
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramX(PImage img) {

	  int[] histx = new int[1000];

	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (red(img.get(i, j)));
	      int g = (int) (green(img.get(i, j)));
	      int b = (int) (blue(img.get(i, j)));

	      float X = map((float) (0.490*r + 0.310*g + 0.2*b), 0, 255, 0, 100);
	      float Y = map((float) (0.177*r + 0.813*g + 0.011*b), 0, 255, 0, 100);  
	      float Z = map((float) (0*r + 0.010*b + 0.990*b), 0, 255, 0, 100);

	      if (X==0.0 && Y==0.0 && Z==0.0)
	      {
	        histx[0]++;
	      } else
	      {
	        int pos = (int) (map((X/(X+Y+Z)), 0, 1, 0, 1000));
	        histx[pos]++ ;
	      }
	    }
	  }  
	  return histx;
	}
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramY(PImage img) {

	  int[] histy = new int[1000];

	 for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (red(img.get(i, j)));
	      int g = (int) (green(img.get(i, j)));
	      int b = (int) (blue(img.get(i, j)));

	      float X = map((float) (0.490*r + 0.310*g + 0.2*b), 0, 255, 0, 100);
	      float Y = map((float) (0.177*r + 0.813*g + 0.011*b), 0, 255, 0, 100);  
	      float Z = map((float) (0*r + 0.010*b + 0.990*b), 0, 255, 0, 100);

	      if (X==0.0 && Y==0.0 && Z==0.0)
	      {
	        histy[0]++;
	      } else
	      {
	        int pos = (int) (map((Y/(X+Y+Z)), 0, 1, 0, 1000));
	        histy[pos]++ ;
	      }
	    }
	  }  
	  return histy;
	}
	/**
	 * 
	 * @param img
	 */
	public void histogramYxy(PImage img) {

	  int[] histY = histogramUpperY(img);
	  int[] histx = histogramX(img);
	  int[] histy = histogramY(img);

	  int histMaxY = max(histY);
	  int histMaxx = max(histx);
	  int histMaxy = max(histy);

	  for (int i = 0; i < img.width; i += 1) {
	  int which = (int) (map(i, 0, img.width, 0, 1000));
	  int yr = (int) (map(histx[which], 0, histMaxx, img.height, 0));
	  stroke(255, 0, 150);
	  line(i+img.width*2, img.height, i+img.width*2,  yr);
	    
	  int yv = (int) (map(histy[which], 0, histMaxy, img.height, 0));
	  stroke(0, 255, 150);
	  line(i+img.width*3, img.height, i+img.width*3, yv);
	  
	  int whichDos = (int) (map(i, 0, img.width, 0, 255));
	  int yrDos = (int) (map(histY[whichDos], 0, histMaxY, img.height, 0));
	  stroke(255, 100, 150);
	  line(i+img.width, img.height, i+img.width, yrDos);
	  }

	}
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramL(PImage img) {

	  int[] histL = new int[100];

	  double epsilon = 0.008856;
	  double K = 903.3;

	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (red(img.get(i, j)));
	      int g = (int) (green(img.get(i, j)));
	      int b = (int) (blue(img.get(i, j)));

	      float X = map((float) (0.490*r + 0.310*g + 0.2*b), 0, 255, 0, 100);
	      float Y = map((float) (0.177*r + 0.813*g + 0.011*b), 0, 255, 0, 100);  
	      float Z = map((float) (0*r + 0.010*b + 0.990*b), 0, 255, 0, 100);

	      double x = X/(95.047);
	      double y = Y/100.0;
	      double z = Z/(108.883);

	      double fx = 0;
	      double fy = 0;
	      double fz = 0;

	      if (x>epsilon) {
	        fx = Math.pow(x, (1.0/3.0));
	      } else {
	        fx = ((K*x)+16)/(116.0);
	      }

	      if (y>epsilon) {
	        fy= Math.pow(y, (1.0/3.0));
	      } else {
	        fy= ((K*y)+16)/(116.0);
	      }

	      if (z>epsilon) {
	        fz= Math.pow(z, (1.0/3.0));
	      } else {
	        fz= ((K*z)+16)/(116.0);
	      }


	      double L1 = ((116*fy)-16);


	      float L = (float)(L1);


	      int pos = (int) (map(L, 0, 100, 0, 99)); 
	      histL[pos]++ ;
	    }
	  }  
	  return histL;
	}
	
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramA(PImage img) {

	  int[] hista = new int[256];

	  double epsilon = 0.008856;
	  double K = 903.3;

	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (red(img.get(i, j)));
	      int g = (int) (green(img.get(i, j)));
	      int b = (int) (blue(img.get(i, j)));

	      float X = map((float) (0.490*r + 0.310*g + 0.2*b), 0, 255, 0, 100);
	      float Y = map((float) (0.177*r + 0.813*g + 0.011*b), 0, 255, 0, 100);  
	      float Z = map((float) (0*r + 0.010*b + 0.990*b), 0, 255, 0, 100);

	      double x = X/(95.047);
	      double y = Y/100.0;
	      double z = Z/(108.883);

	      double fx = 0;
	      double fy = 0;
	      double fz = 0;

	      if (x>epsilon) {
	        fx= Math.pow(x, (1.0/3.0));
	      } else {
	        fx= ((K*x)+16)/(116.0);
	      }

	      if (y>epsilon) {
	        fy= Math.pow(y, (1.0/3.0));
	      } else {
	        fy= ((K*y)+16)/(116.0);
	      }

	      if (z>epsilon) {
	        fz= Math.pow(z, (1.0/3.0));
	      } else {
	        fz= ((K*z)+16)/(116.0);
	      }

	      int a1 = (int)(500*(fx-fy));

	      float a = (float)(a1);
	      int pos = (int) (map(a, -128, 128, 0, 255));
	      if (pos >= 255) {
	        hista[255]++;
	      } else if (pos<= 0) {
	        hista[0]++;
	      } else {      
	        hista[pos]++ ;
	      }
	    }
	  }  
	  return hista;
	}
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramB(PImage img) {

	  int[] histb = new int[256];

	  double epsilon = 0.008856;
	  double K = 903.3;

	  
	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (red(img.get(i, j)));
	      int g = (int) (green(img.get(i, j)));
	      int b = (int) (blue(img.get(i, j)));

	      float X = map((float) (0.490*r + 0.310*g + 0.2*b), 0, 255, 0, 100);
	      float Y = map((float) (0.177*r + 0.813*g + 0.011*b), 0, 255, 0, 100);  
	      float Z = map((float) (0*r + 0.010*b + 0.990*b), 0, 255, 0, 100);

	      double x = X/(95.047);
	      double y = Y/100.0;
	      double z = Z/(108.883);

	      double fx = 0;
	      double fy = 0;
	      double fz = 0;

	      if (x>epsilon) {
	        fx = Math.pow(x, (1.0/3.0));
	      } else {
	        fx= ((K*x)+16)/(116.0);
	      }

	      if (y>epsilon) {
	        fy= Math.pow(y, (1.0/3.0));
	      } else {
	        fy= ((K*y)+16)/(116.0);
	      }

	      if (z>epsilon) {
	        fz= Math.pow(z, (1.0/3.0));
	      } else {
	        fz= ((K*z)+16)/(116.0);
	      }

	      int be1 = (int)(200*(fy-fz));   

	      float be = (float)(be1);
	      
	      int pos = (int)(map(be, -128, 128, 0, 255));
	      if (pos >= 255) {
	        histb[255]++;
	      } else if (pos<= 0) {
	        histb[0]++;
	      } else {      
	        histb[pos]++ ;
	      }
	      
	    }
	  }  
	  return histb;
	}
	/**
	 * 
	 * @param img
	 */
	public void histogramLab(PImage img) {
	  int[] histL = histogramL(img);
	  int[] hista = histogramA(img);
	  int[] histb = histogramB(img);

	  int histMaxL = max(histL);
	  int histMaxA = max(hista);
	  int histMaxB = max(histb);

	  for (int i = 0; i < img.width; i += 1) {
	    int which = (int)(map(i, 0, img.width, 0, 255));
	    int yr = (int)(map(hista[which], 0, histMaxA, img.height, 0));
	    stroke(150, 100, 150);
	    line(i+img.width*2, img.height, i+img.width*2, yr);
	    
	    int yv = (int)(map(histb[which], 0, histMaxB, img.height, 0));
	    stroke(100, 255, 200);
	    line(i+img.width*3, img.height, i+img.width*3, yv);
	  }

	  for (int i = 0; i < img.width; i += 1) {
	    int which = (int)(map(i, 0, img.width, 0, 100));
	    int yr = (int)(map(histL[which], 0, histMaxL, img.height, 0));
	    stroke(90, 0, 150);
	    line(i+img.width, img.height, i+img.width, yr);
	  }
	}
	
	/**
	 * 
	 * @param img
	 * @return
	 */
	public PImage diagramChromatic(PImage img) {

	  PImage cartesiano = loadImage("/home/phndavid/Documents/git/java/fotografia_3/tarea/diagram.jpg");
	  cartesiano.resize((int) (cartesiano.width*0.70), (int) (cartesiano.width*0.70));

	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (red(img.get(i, j)));
	      int g = (int) (green(img.get(i, j)));
	      int b = (int) (blue(img.get(i, j)));

	      float X = (float) (0.490*r + 0.310*g + 0.2*b);
	      float Y = (float) (0.0177*r + 0.813*g + 0.011*b);  
	      float Z = (float) (0*r + 0.010*b + 0.990*b);

	      if (X==0 && Y==0 && Z==0)
	      {
	        cartesiano.set(0, cartesiano.height, color(255, 255, 255));
	      } else
	      {
	        int posx = (int) (map((X/(X+Y+Z)), 0, 1, 0, cartesiano.width));
	        int posy = (int) (map((Y/(X+Y+Z)), 0, 1, 0, cartesiano.height));
	        cartesiano.set(posx, cartesiano.height-posy, color(255, 255, 255));
	      }
	    }
	  }  

	  return cartesiano;
	}

	public void draw() {
		img3.resize(200, 200);
		//hRGB(img3);
	    image(img3, 0, 0);
	    //image(img1, 0, img1.height+5);
	    //hHSV(img3);
	    
	    //image(img2, 0, (img2.height*2)+10);
	    //hYxy(img3);
	    
	    //image(img2, 0, (img2.height*3)+15);
	    //hLab(img3);
		image(diagramChromatic(img3), 230, 50);
	    
	    //String m= "Diagrama de cromaticidad";
	    //fill(50);
	    //text(m, 780, 580); 
	}	
}
