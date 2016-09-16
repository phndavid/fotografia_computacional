import processing.core.PApplet;
import processing.core.PImage;
public class HistogramMatching extends PApplet {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PImage img1;
	 PImage img2;

	public void setup() {	  
	  
	  img2 = loadImage("/home/phndavid/Documents/git/java/fotografia_4/tarea/paisaje_1.jpg");
	  img1 = loadImage("/home/phndavid/Documents/git/java/fotografia_4/tarea/paisaje_2.jpg");
	 
	  img1.resize(200,200);
	  img2.resize(200, 200); 
	  
	}
	/**
	 * 
	 * @param hist
	 * @return
	 */
	public int[] histogramA(int[] hist) {

	  int[] hAc = new int[hist.length];

	  for (int i =0; i<hist.length; i++) {   
	    if (i==0) {
	      hAc[i]= hist[i];
	    } else {
	      hAc[i]= hist[i]+hAc[i-1];
	    }
	  }
	  return hAc;
	}
	
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramR(PImage img) {

	  int[] histR = new int[256];
	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (red(img.get(i, j)));
	      histR[r]++;
	    }
	  }

	  return histR;
	}
		
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramG(PImage img) {

	  int[] histG = new int[256];
	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (green(img.get(i, j)));
	      histG[r]++;
	    }
	  }

	  return histG;
	}
	
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramB(PImage img) {

	  int[] histB = new int[256];
	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (blue(img.get(i, j)));
	      histB[r]++;
	    }
	  }

	  return histB;
	}
		
	/**
	 * 
	 * @param img
	 * @return
	 */
	public int[] histogramY(PImage img) {

	  int[] histY = new int[256];
	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (red(img.get(i, j)));
	      int g = (int) (green(img.get(i, j)));
	      int b = (int) (blue(img.get(i, j)));
	      int pos = (int) (map((float) (0.177*r + 0.813*g + 0.011*b), 0, 255, 0, 255));	      
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

	  int[] histx = new int[256];

	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (red(img.get(i, j)));
	      int g = (int) (green(img.get(i, j)));
	      int b = (int) (blue(img.get(i, j)));

	      float X = map((float) (0.490*r + 0.310*g + 0.2*b), 0, 255, 0, 1);
	      float Y = map((float) (0.177*r + 0.813*g + 0.011*b), 0, 255, 0, 1);  
	      float Z = map((float) (0*r + 0.010*b + 0.990*b), 0, 255, 0, 1);

	      if (X==0.0 && Y==0.0 && Z==0.0)
	      {
	        histx[0]++;
	      } else
	      {
	        int pos = (int) (map((X/(X+Y+Z)), 0, 1, 0, 255));
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
	public int[] histogramy(PImage img) {

	  int[] histy = new int[255];
	  for (int i = 0; i < img.width; i++) {
	    for (int j = 0; j < img.height; j++) {
	      int r = (int) (red(img.get(i, j)));
	      int g = (int) (green(img.get(i, j)));
	      int b = (int) (blue(img.get(i, j)));

	      float X = map((float) (0.490*r + 0.310*g + 0.2*b), 0, 255, 0, 1);
	      float Y = map((float) (0.177*r + 0.813*g + 0.011*b), 0, 255, 0, 1);  
	      float Z = map((float) (0*r + 0.010*b + 0.990*b), 0, 255, 0, 1);

	      if (X==0.0 && Y==0.0 && Z==0.0)
	      {
	        histy[0]++;
	      } else
	      {
	        int pos = (int)(map((Y/(X+Y+Z)), 0, 1, 0, 255));
	        histy[pos]++ ;
	      }
	    }
	  }  
	  return histy;
	}
		
	/**
	 * 
	 * @param img1
	 * @param img2
	 * @return
	 */
	public PImage histogramMRGB(PImage img1, PImage img2) {
	  
	  int[] hR1 = histogramA(histogramR(img1));
	  int[] hG1 = histogramA(histogramG(img1));
	  int[] hB1 = histogramA(histogramB(img1));

	  int[] hR2 = histogramA(histogramR(img2));
	  int[] hG2 = histogramA(histogramG(img2));
	  int[] hB2 = histogramA(histogramB(img2));

	  PImage match = createImage(img1.width, img1.height, RGB);  

	  for (int i =0; i< img1.width; i++) {
	    for (int j =0; j< img1.height; j++) {
	      int r = (int)(red(img1.get(i, j)));
	      int g = (int)(green(img1.get(i, j)));
	      int b = (int)(blue(img1.get(i, j)));

	      int r2= buscar(hR1, hR2, r);
	      int g2= buscar(hG1, hG2, g);
	      int b2= buscar(hB1, hB2, b);

	      match.set(i, j, color(r2, g2, b2));
	    }
	  }

	  return match;
	}
	
	/**
	 * 
	 * @param img1
	 * @param img2
	 * @return
	 */
	public PImage histogramMYxy(PImage img1, PImage img2) {
	  int[] hY1 = histogramA(histogramY(img1));
	  int[] hX1 = histogramA(histogramX(img1));
	  int[] hy1 = histogramA(histogramy(img1));

	  int[] hY2 = histogramA(histogramY(img2));
	  int[] hX2 = histogramA(histogramX(img2));
	  int[] hy2 = histogramA(histogramy(img2));

	  PImage match = createImage(img1.width, img1.height, RGB);  

	  for (int i =0; i< img1.width; i++) {
	    for (int j =0; j< img1.height; j++) {
	      int r = (int)(red(img1.get(i, j)));
	      int g = (int)(green(img1.get(i, j)));
	      int b = (int)(blue(img1.get(i, j)));

	      float Xa = map((float) (0.490*r + 0.310*g + 0.2*b), 0, 255, 0, 1);
	      float Ya = map((float) (0.177*r + 0.813*g + 0.011*b), 0, 255, 0, 1);  
	      float Za = map((float) (0*r + 0.010*b + 0.990*b), 0, 255, 0, 1);
	      
	      int Y1;
	      int x1;
	      int y1;

	      if (Xa==0.0 && Ya==0.0 && Za==0.0) {
	        
	        Y1 = 0;
	        x1 = 0;
	        y1 = 0;
	        
	      } else
	      {
	        Y1 = (int)(map(Ya, 0, 1, 0, 255));
	        x1 = (int) (map((Xa/(Xa+Ya+Za)), 0, 1, 0, 255));
	        y1= (int) (map((Ya/(Xa+Ya+Za)), 0, 1, 0, 255));
	        
	      }

	      
	      float Y2= map((float)buscar(hY1, hY2, Y1),0,255,0,1);
	      float x2= map((float)buscar(hX1, hX2, x1),0,255,0,1);
	      float y2= map((float)buscar(hy1, hy2, y1),0,255,0,1);
	      
	      float Xb ;
	      float Yb ;  
	      float Zb ;
	      
	      
	      if(y2==0){
	        Xb=0;
	        Yb=0;
	        Zb=0;
	        
	      }
	      
	      else{
	        Xb= (int) (map((x2*Y2)/(y2),0,1,0,255));
	        Yb= (int)(map(Y2,0,1,0,255)); 
	        Zb= (int) (map(((1-x2-y2)*Y2)/y2,0,1,0,255));

	      }
	      
	      int r2 = (int)(2.3644022257276083*Xb + (-0.8958027717882945)*Yb + (-0.4677036511675257)*Zb);
	      int g2 = (int)((-0.5148295168923018*Xb) + (1.4252342558035476*Yb) + (0.08817002683295085*Zb));
	      int b2 = (int)((0.005200298150427291*Xb) + (-0.014396305614177247*Yb) + (1.009210403769364*Zb));


	      match.set(i, j, color(r2, g2, b2));
	    }
	  }

	  return match;
	}
	
	/**
	 * 
	 * @param img1
	 * @param img2
	 * @return
	 */
	public PImage histogramMxy(PImage img1, PImage img2) {
	  
	  int[] hx1 = histogramA(histogramX(img1));
	  int[] hy1 = histogramA(histogramy(img1));

	  int[] hx2 = histogramA(histogramX(img2));
	  int[] hy2 = histogramA(histogramy(img2));

	  PImage match = createImage(img1.width, img1.height, RGB);  

	  for (int i =0; i< img1.width; i++) {
	    for (int j =0; j< img1.height; j++) {
	      int r = (int) (red(img1.get(i, j)));
	      int g = (int) (green(img1.get(i, j)));
	      int b = (int) (blue(img1.get(i, j)));

	      float Xa = map((float) (0.490*r + 0.310*g + 0.2*b), 0, 255, 0, 1);
	      float Ya = map((float) (0.177*r + 0.813*g + 0.011*b), 0, 255, 0, 1);  
	      float Za = map((float) (0*r + 0.010*b + 0.990*b), 0, 255, 0, 1);
	      
	      int Y1;
	      int x1;
	      int y1;

	      if (Xa==0.0 && Ya==0.0 && Za==0.0) {
	        
	        Y1 = 0;
	        x1 = 0;
	        y1 = 0;
	        
	      } else
	      {
	        Y1 = (int)(map(Ya, 0, 1, 0, 255));
	        x1 = (int) (map((Xa/(Xa+Ya+Za)), 0, 1, 0, 255));
	        y1= (int) (map((Ya/(Xa+Ya+Za)), 0, 1, 0, 255));
	        
	      }

	      
	      float Y2= map(Y1,0,255,0,1);
	      float x2= map((float)buscar(hx1, hx2, x1),0,255,0,1);
	      float y2= map((float)buscar(hy1, hy2, y1),0,255,0,1);
	      
	      float Xb ;
	      float Yb ;  
	      float Zb ;
	      
	      
	      if(y2==0){
	        Xb=0;
	        Yb=0;
	        Zb=0;
	        
	      }
	      
	      else{
	        Xb= (int)(map((x2*Y2)/(y2),0,1,0,255));
	        Yb= (int)(map(Y2,0,1,0,255)); 
	        Zb= (int) (map(((1-x2-y2)*Y2)/y2,0,1,0,255));

	      }
	      
	      int r2 = (int)(2.3644022257276083*Xb + (-0.8958027717882945)*Yb + (-0.4677036511675257)*Zb);
	      int g2 = (int)((-0.5148295168923018*Xb) + (1.4252342558035476*Yb) + (0.08817002683295085*Zb));
	      int b2 = (int)((0.005200298150427291*Xb) + (-0.014396305614177247*Yb) + (1.009210403769364*Zb));


	      match.set(i, j, color(r2, g2, b2));
	    }
	  }

	  return match;
	}
	
	/**
	 * 
	 * @param img1
	 * @param img2
	 * @return
	 */
	public PImage histogramMY(PImage img1, PImage img2) {
	  int[] hY1 = histogramA(histogramY(img1));

	  int[] hY2 = histogramA(histogramY(img2));

	  PImage match = createImage(img1.width, img1.height, RGB);  

	  for (int i =0; i< img1.width; i++) {
	    for (int j =0; j< img1.height; j++) {
	      int r = (int) (red(img1.get(i, j)));
	      int g = (int) (green(img1.get(i, j)));
	      int b = (int) (blue(img1.get(i, j)));

	      float Xa = map((float) (0.490*r + 0.310*g + 0.2*b), 0, 255, 0, 1);
	      float Ya = map((float) (0.177*r + 0.813*g + 0.011*b), 0, 255, 0, 1);  
	      float Za = map((float) (0*r + 0.010*b + 0.990*b), 0, 255, 0, 1);
	      
	      int Y1;
	      int x1;
	      int y1;

	      if (Xa==0.0 && Ya==0.0 && Za==0.0) {
	        
	        Y1 = 0;
	        x1 = 0;
	        y1 = 0;
	        
	      } else
	      {
	        Y1 = (int)(map(Ya, 0, 1, 0, 255));
	        x1 = (int)(map((Xa/(Xa+Ya+Za)), 0, 1, 0, 255));
	        y1= (int) (map((Ya/(Xa+Ya+Za)), 0, 1, 0, 255));
	        
	      }

	      
	      float Y2= map((float)buscar(hY1, hY2, Y1),0,255,0,1);
	      float x2= map(x1,0,255,0,1);
	      float y2= map(y1,0,255,0,1);
	      
	      float Xb ;
	      float Yb ;  
	      float Zb ;
	      
	      
	      if(y2==0){
	        Xb=0;
	        Yb=0;
	        Zb=0;
	        
	      }
	      
	      else{
	        Xb= (int) (map((x2*Y2)/(y2),0,1,0,255));
	        Yb= (int) (map(Y2,0,1,0,255)); 
	        Zb= (int) (map(((1-x2-y2)*Y2)/y2,0,1,0,255));

	      }
	      
	      int r2 = (int)(2.3644022257276083*Xb + (-0.8958027717882945)*Yb + (-0.4677036511675257)*Zb);
	      int g2 = (int)((-0.5148295168923018*Xb) + (1.4252342558035476*Yb) + (0.08817002683295085*Zb));
	      int b2 = (int)((0.005200298150427291*Xb) + (-0.014396305614177247*Yb) + (1.009210403769364*Zb));


	      match.set(i, j, color(r2, g2, b2));
	    }
	  }

	  return match;
	}


	public int buscar(int[] hist, int[] histIdeal, int posini) {
	  int pos=0;
	  boolean salida=false;
	  for (int i=0; i< hist.length && !salida; i++) {
	    if (histIdeal[i]>hist[posini]) {

	      pos= i;
	      salida=true;
	    }
	  }        
	  return pos;
	}

	public void draw() {

	  image(img1, 0, 0);
	  image(img2, 0, 210);
	  
	  //image(histogramMRGB(img1, img2),  x+10, 100);
	  
	  //image(histogramMYxy(img1, img2), x+10, 100);
	  
	  //image(histogramMxy(img1, img2), x+10, 100);
	  
	  image(histogramMY(img1, img2), 210, 100);	 
	}
}
