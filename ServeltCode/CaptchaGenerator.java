package com.CaptchaProject.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

@WebServlet("/CaptchaGenerator")
public class CaptchaGenerator extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 7705192180138203617L;
	public static final int WIDTH = 220; 
	 public static final int HEIGHT = 60; 
	    public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        this.doPost(request, response);
	    }
	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
//	    	 String createTypeFlag = request.getParameter("createTypeFlag");
	    	 BufferedImage bi = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
	    	 Graphics g = bi.getGraphics();
	    	  Graphics2D g2dImage = (Graphics2D) bi.getGraphics();
	    	 setBackGround(g);
	         setBorder(g);
	        // drawRandomLine(g);
	         Random randChars = new Random();
	         for ( int i = 0; i < 15; i++ )
	         {
	           g2dImage.setColor(new Color(randChars.nextInt(255),randChars.nextInt(255),randChars.nextInt(255)));
	           int iRadius = (int) (Math.random() * HEIGHT / 5.5);
	           int iX = (int) (Math.random() * WIDTH - iRadius);
	           int iY = (int) (Math.random() * HEIGHT - iRadius);
	           g2dImage.fillRoundRect(iX, iY, iRadius * 2, iRadius * 2,100,100);
	         }
	         Random rand = new Random();
	         for(int k=0;k<10;k++)
	         {
	        	 for(int j =0;j<20;j++) {
	 		for(int i = 0;i < 50;i++) {
				int x = rand.nextInt(bi.getWidth());
				int y = rand.nextInt(bi.getHeight());
				int rgb = GetRandomColor().getRGB();
				bi.setRGB(x, y, rgb);
			}
	         }
	    }
	         String random = drawRandomNum((Graphics2D) g);
	        drawRandomLine(g);
	          
	         drawThickLine(g,0,new Random().nextInt(HEIGHT)+1,WIDTH,new Random().nextInt(HEIGHT)+1,4,Color.BLACK);

	         Kernel kernel = new Kernel(3, 3, new float[] { 1f / 9f, 1f / 9f, 1f / 9f,
	        	        1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f });
	         BufferedImageOp op = new ConvolveOp(kernel);
	         
	         int random_int = (int)Math.floor(Math.random()*(4-1+1)+2);
	         for(int i = 0; i<= random_int; i ++) {
	        	 bi = op.filter(bi, null);
	         }

	         request.getSession().setAttribute("checkcode", random);
	         response.setContentType("image/jpeg");
	         response.setDateHeader("expries", -1);
	         response.setHeader("Cache-Control", "no-cache");
	         response.setHeader("Pragma", "no-cache");
	         ImageIO.write(bi, "jpg", response.getOutputStream());
	    }
	    private void setBackGround(Graphics g) {
	    	 g.setColor(Color.WHITE);
	    	 g.fillRect(0, 0, WIDTH, HEIGHT);
	    }
	    
	    private void setBorder(Graphics g) {
	    	 g.setColor(Color.BLACK);
	    	 g.drawRect(1, 1, WIDTH - 4, HEIGHT - 4);
	    	
	    }
		public Color GetRandomColor() {
			Random random = new Random();
	        int r = random.nextInt(256);
	        int g = random.nextInt(256);
	        int b = random.nextInt(256);
	        return new Color(r, g, b);
	 
		}
		
	
		
	    private void drawRandomLine(Graphics g) {
	    	g.setColor(Color.GREEN);
	    	for (int i = 0; i < 5; i++) {
	            int x1 = new Random().nextInt(WIDTH);
	            int y1 = new Random().nextInt(HEIGHT);
	            int x2 = new Random().nextInt(WIDTH);
	            int y2 = new Random().nextInt(HEIGHT);
	            g.drawLine(x1, y1, x2, y2);
	        }
	    	g.setColor(Color.RED);
	    	for (int i = 0; i < 5; i++) {
	            int x1 = new Random().nextInt(WIDTH);
	            int y1 = new Random().nextInt(HEIGHT);
	            int x2 = new Random().nextInt(WIDTH);
	            int y2 = new Random().nextInt(HEIGHT);
	            g.drawLine(x1, y1, x2, y2);
	        }
	    	g.setColor(Color.RED);
	    	for (int i = 0; i < 5; i++) {
	            int x1 = new Random().nextInt(WIDTH);
	            int y1 = new Random().nextInt(HEIGHT);
	            int x2 = new Random().nextInt(WIDTH);
	            int y2 = new Random().nextInt(HEIGHT);
	            g.drawLine(x1, y1, x2, y2);
	        }
	    }

	   

	    private String drawRandomNum(Graphics2D g){
	    	g.setColor(Color.RED);
	    	 g.setFont(new Font("Fraktur", Font.BOLD, 50));
	    	 
	    	 String baseNumLetter = "023456789ABDEFGHJKLMNPQRSTUVWXYZacdefgjkmnqrswxyz@#$&";
	    	
	        	 return createRandomChar(g, baseNumLetter);
	         }
	    private void drawThickLine(Graphics g, int x1, int y1, int x2, int y2, 
	    		   int thickness, Color c) { 

	    		  // The thick line is in fact a filled polygon 
	    		  g.setColor(c); 
	    		  int dX = x2 - x1; 
	    		  int dY = y2 - y1; 
	    		  // line length 
	    		  double lineLength = Math.sqrt(dX * dX + dY * dY); 

	    		  double scale = (double) (thickness) / (2 * lineLength); 

	    		  // The x and y increments from an endpoint needed to create a 
	    		  // rectangle... 
	    		  double ddx = -scale * (double) dY; 
	    		  double ddy = scale * (double) dX; 
	    		  ddx += (ddx > 0) ? 0.5 : -0.5; 
	    		  ddy += (ddy > 0) ? 0.5 : -0.5; 
	    		  int dx = (int) ddx; 
	    		  int dy = (int) ddy; 

	    		  // Now we can compute the corner points... 
	    		  int xPoints[] = new int[4]; 
	    		  int yPoints[] = new int[4]; 

	    		  xPoints[0] = x1 + dx; 
	    		  yPoints[0] = y1 + dy; 
	    		  xPoints[1] = x1 - dx; 
	    		  yPoints[1] = y1 - dy; 
	    		  xPoints[2] = x2 - dx; 
	    		  yPoints[2] = y2 - dy; 
	    		  xPoints[3] = x2 + dx; 
	    		  yPoints[3] = y2 + dy; 

	    		  g.fillPolygon(xPoints, yPoints, 4); 
	    		} 
	         
	    private String createRandomChar(Graphics2D g,String baseChar) {
	        StringBuffer sb = new StringBuffer();
	        int x = 5;
	        String ch ="";
	        for (int i = 0; i < 6; i++) {
	        	int degree = new Random().nextInt() % 30;
	            ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
	            sb.append(ch);
	            g.rotate(degree * Math.PI / 180, x, 20);
	            g.drawString(ch, x, 42);
	            g.rotate(-degree * Math.PI / 180, x, 20);
	            x += 32;
	        }
	        return sb.toString();
	    }
	
	        }
