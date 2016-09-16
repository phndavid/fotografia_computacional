package Alignment;


import processing.core.PApplet;
import processing.core.PImage;

public class Proyecto extends PApplet{
	public final static int WIDTH = 200;
	public final static int HEIGHT = 200;
	
	PImage img;
	PImage imgR;
	PImage imgG;
	PImage imgB;
	public void setup(){
		size(340, 360);	
		img = loadImage("/home/phndavid/Documents/git/java/proyecto_1/tarea/img2.jpg");
		img.resize(300,650);
		imgB =  img.get(0, 0, img.width ,(img.height/3));
		imgG =  img.get(0,(img.height/3), img.width ,(img.height/3));
		imgR =  img.get(0,(img.height/3)*2, img.width ,(img.height/3));
		
		//Alinear usando SDD
		/*
		int start = (int) System.currentTimeMillis();
		int[] coordR = coordinateSSD(imgG,imgR,"R");
		int[] coordB = coordinateSSD(imgG,imgB,"B");
		alignment(coordR[0],coordR[1],coordB[0],coordB[1]);
		int end = (int) System.currentTimeMillis();
		System.out.println("Time: "+(end-start));
		*/
		
		//Alinear usando NCC		
		int start = (int) System.currentTimeMillis();		
		int[] coordRn = coordinateNCC(imgG,imgR,"R");
		int[] coordBn = coordinateNCC(imgG,imgB,"B");
		alignment(coordRn[0],coordRn[1],coordBn[0],coordBn[1]);		
		int end = (int) System.currentTimeMillis();		
		System.out.println("Time: "+(end-start));
		
		
	}
	
	public void alignment(int x1, int y1, int x2, int y2){
		PImage ColorImg= createImage(img.width,(img.height/3), RGB);
		
		int r = 0,g,b = 0;

		for(int i =0; i< img.width; i++){
			for(int j =0; j< img.height; j++){
				g = (int) green(imgG.get(i,j)); // canal verde	
				r = (int) red(imgR.get(i-x1,j-y1)); // canal rojo
				b = (int) blue(imgB.get(i-x2,j-y2)); // canal azul				
				ColorImg.set(i, j, color(r,g,b));				
			}
		}
		ColorImg.save("alignment_2.jpg");
		image(ColorImg,10,50);		
	}
	public int[] coordinateSSD(PImage imgG, PImage imgB,String c){
		int[] coordinate = new int[2];
		PImage imgStatic = PImageStatic(imgB);
		PImage imgDinamyc = null;
		float lessSSD = 1000000000;
		int x=0,y=0;
		for(int i =0; i< imgG.width; i++){
			for(int j =0; j< imgG.height; j++){
				imgDinamyc  = PImageDinamyc(imgG, i, j);
				float SSD =  SSD(imgStatic, imgDinamyc,c);
				if(SSD < lessSSD){
					lessSSD = SSD;
					//System.out.println("LessSSD:"+SSD);
					x = i;
					y = j;
				}
			}
		}
		System.out.println("X:"+x+",Y:"+y+"C:"+c);
		coordinate[0] = x;
		coordinate[1] = y;
		return coordinate;
	}
	
	
	public int[] coordinateNCC(PImage imgG, PImage imgB,String c){
		int[] coordinate = new int[2];
		PImage imgStatic = PImageStatic(imgB);
		PImage imgDinamyc = null;
		float higherNCC = 0;
		int x=0,y=0;
		for(int i =0; i< imgG.width; i++){
			for(int j =0; j< imgG.height; j++){
				imgDinamyc  = PImageDinamyc(imgG, i, j);
				float NCC =  nccAlgorithm(imgStatic, imgDinamyc);
				if(NCC > higherNCC){
					higherNCC = NCC;
					System.out.println("higherNCC:"+NCC);
					x = i;
					y = j;
				}
			}
		}
		coordinate[0] = x;
		coordinate[1] = y;
		System.out.println("X:"+x+",Y:"+y+"C:"+c);
		return coordinate;
	}
	public float nccAlgorithm(PImage base, PImage movable) {
		float ncc = 0;
		int numpBase = base.width * base.height;
		// int numpMove = movable.width * movable.height; equal sizes
		float[] pixelsBase = new float[numpBase];
		float[] pixelsMova = new float[numpBase];
		float sumBase = 0;
		float sumMova = 0;
		int index = 0;
		for (int i = 0; i < base.width; i++) {
			for (int j = 0; j < base.height; j++) {
				float bcolor = base.get(i, j);
				float bbase = brightness((int) bcolor);
				float mcolor = movable.get(i, j);
				float bmova = brightness((int) mcolor);
				pixelsBase[index] = bbase;
				pixelsMova[index] = bmova;
				sumBase += bbase;
				sumMova += bmova;
				index++;
			}
		}
		float averageBase = sumBase / numpBase;
		float averageMove = sumMova / numpBase;
		float[] difsBase = new float[numpBase];
		float difsQBaseSum = 0;
		float[] difsMove = new float[numpBase];
		float difsQMoveSum = 0;
		for (int i = 0; i < pixelsBase.length; i++) {
			difsBase[i] = pixelsBase[i] - averageBase;
			difsQBaseSum += difsBase[i] * difsBase[i];

			difsMove[i] = pixelsMova[i] - averageMove;
			difsQMoveSum += difsMove[i] * difsMove[i];
		}

		float varBase = (float) Math.pow((double) difsQBaseSum, 0.5);
		float varMove = (float) Math.pow((double) difsQMoveSum, 0.5);

		for (int i = 0; i < difsMove.length; i++) {
			float f = difsBase[i] / varBase;
			float g = difsMove[i] / varMove;
			ncc += f * g;
		}

		return ncc;
	}
	/**
	 * Imagen A e Imagen B de igual tamaño
	 * @param imgA
	 * @param imgB
	 * @return
	 */
	public int SSD(PImage imgA, PImage imgB, String c){
		int ssd = 0;
		int ca =0;
		int cb = 0;
		for(int i =0; i< imgA.pixels.length; i++){
			if(c.equals("R")){
				ca = (int)(red(imgA.pixels[i]));
				cb = (int)(red(imgB.pixels[i]));
			}else if(c.equals("B")){
				ca = (int)(blue(imgA.pixels[i]));
				cb = (int)(blue(imgB.pixels[i]));			
			}
				int diff = ca-cb;				
				ssd += diff*diff;		
		}		
		return ssd;
	}

	public PImage PImageStatic(PImage img){
		PImage cropStatic= img.get(0,0,WIDTH,HEIGHT);
		return cropStatic;
	}
	
	public PImage PImageDinamyc(PImage img, int x, int y){
		  PImage cropDinamyc= img.get(x,y, WIDTH,HEIGHT);
		  return cropDinamyc;
	}
}
