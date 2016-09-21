import cv2  
  
img = cv2.imread('horse.jpg')  
cv2.imshow('src', img)  

img1 = cv2.imread('lapla1.jpg')  
img2 = cv2.imread('level1_up.jpg')  
#cv2.imshow('srcup', img1+img2) 

img1 = img  
h,w = img1.shape[:2]
lower_reso_img1 = cv2.pyrDown(img1, dstsize=(w/2, h/2))  
print lower_reso_img1.shape  
#
cv2.imshow('level 1', lower_reso_img1)  
#cv2.imwrite('level1.jpg', lower_reso_img1)

img2 = lower_reso_img1
lower_reso_img2  = cv2.pyrDown(img2)
print lower_reso_img2.shape  
#cv2.imshow('level 2', lower_reso_img2)  
#cv2.imwrite('level2.jpg',lower_reso_img2)

img3 = lower_reso_img2
lower_reso_img3  = cv2.pyrDown(img3)
print lower_reso_img3.shape  
#cv2.imshow('level 3', lower_reso_img3)  
#cv2.imwrite('level3.jpg',lower_reso_img3)

kernel_size = 3
scale = 1
delta = 0
ddepth = cv2.CV_16S
imgl1 = cv2.GaussianBlur(img3,(3,3),0);
gray = cv2.cvtColor(img3,cv2.COLOR_BGR2GRAY)
cv2.imshow('GaussianBlur',gray)
cv2.imwrite('GaussianBlur3.jpg',gray)
gray_lap = cv2.Laplacian(gray,ddepth,ksize = kernel_size,scale = scale,delta = delta)
dst = cv2.convertScaleAbs(gray_lap)
  
cv2.imshow('laplacian',dst)
cv2.imwrite('laplacian3.jpg',dst)
#higher_reso = cv2.pyrUp(lower_reso_img1)  
#print higher_reso.shape  
#cv2.imshow('level1_up', higher_reso)  
#cv2.imwrite('level1_up.jpg',higher_reso)
#cv2.imshow('laplace_level1', img1 - higher_reso)   
#cv2.imwrite('lapla1.jpg',img1 - higher_reso)

#higher_reso_img2 = cv2.pyrUp(lower_reso_img2)  
#print higher_reso_img2.shape  
#cv2.imshow('level2_up', higher_reso_img2)  
#cv2.imwrite('level2_up.jpg',higher_reso_img2)
#cv2.imshow('laplace_level2', img2 - higher_reso_img2)   
#cv2.imwrite('lapla2.jpg',img2 - higher_reso_img2)


#higher_reso_img3 = cv2.pyrUp(lower_reso_img3)  
#print higher_reso_img3.shape  
#cv2.imshow('level3_up', higher_reso_img3)  
#cv2.imwrite('level3_up.jpg',higher_reso_img3)
#cv2.imshow('laplace_level3', img3 - higher_reso_img3)   
#cv2.imwrite('lapla3.jpg', img3 - higher_reso_img3)

cv2.waitKey(0)