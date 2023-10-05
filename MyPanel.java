import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
 
class MyPanel extends JPanel {
        private BufferedImage image;
        int width = 400;
        int height = 400;
        double w=width;
        double h = height;
        int x;
        double y;
        int yInt;
        double aF= h/w;
        int helper = 1;
        int r=232;
        int gg=252;
        int b=5;
        int i = 0;
        int mswitch = 1;
        int ballSize=20;
        Random rand = new Random();
        // int squareHorizontally= rand.nextInt(width-ballSize);
        // int squarePerpendicularly= rand.nextInt(height-ballSize);
        int squareHorizontally=189;
        int squarePerpendicularly=189;
        int squareSize = 50;
        int temporaryXSquare ;
        int temporaryYSquare ;
        int xSquareTill;
        int ySquareTill;
        int xSquareLessBall;
        int ySquareLessBall;
        int stuckChanger=0;
        public MyPanel () {        	
         setPreferredSize(new Dimension(width, height));
        }
        public void dzialaj() throws IOException {
	        try {  
              while(mswitch>0){
                width= getWidth();
                height= getHeight();
                w=width;
                h = height;
                aF= h/w;
                yInt=(int)y;
                System.out.println("x kwadratu " + squareHorizontally + "y kwadratu " + squarePerpendicularly);
                //System.out.println("lokalizacja x " + x + "lokalizacja y " + y);
                x=i;
                boolean colision = checkColision(x, yInt,squareHorizontally,squarePerpendicularly);
                Thread.sleep(5);
                File imageFile = new File("enemy.png");
                image = ImageIO.read(imageFile);
                System.out.println(y);
                repaint();
                int previousSquareHorizontally=squareHorizontally;
                int previousSquarePerpendicularly=squarePerpendicularly;
                if(colision)
                {
                  changeColor();
                  System.out.println("ALARMO !!!!");
                  List combinationAfterColision =checkCombinationAfterColision(x, yInt,squareHorizontally,squarePerpendicularly);
                  moveAfterColision(x, yInt,combinationAfterColision);
                  colision=false;  
                  if(previousSquareHorizontally==squareHorizontally && previousSquarePerpendicularly==squarePerpendicularly)
                  {
                    stuckChanger++;
                  } 
                  if(stuckChanger==5)
                  {
                    squareHorizontally = rand.nextInt(width-squareSize);
                    squarePerpendicularly = rand.nextInt(height-squareSize);
                    stuckChanger=0;
                  }
                }
              {if(mswitch==1)
                {       
                  y=-(aF*x)+(height/2);  
                  System.out.println(y);    
                  i++;
                  if(i==width/2)
                  {
                    changeColor();
                    mswitch=2;
                    helper=0;
                  }
                }
                if(mswitch==2){
                  if(i==width/2+1)
                  {
                    helper=0;      
                  }                       
                  y= helper*aF;
                  i++;
                  helper++;
                  if(i==width-ballSize)
                  {
                    changeColor();
                    mswitch=3;
                    // squareHorizontally = rand.nextInt(width-ballSize);
                    // squarePerpendicularly = rand.nextInt(height-ballSize);
                  }
                }
                if(mswitch==3)
                {
                  System.out.println(y);
                  if(i==width-ballSize)
                  {
                    helper=0;  
                  }
                  helper++;
                  y=(helper*aF)+(height/2)-ballSize;
                  i--;
                  if(i==width/2-ballSize)
                  {
                    changeColor();
                    mswitch=4;
                  }
                }
                if(mswitch==4)
                {
                  if(i==width/2-ballSize)
                  {     
                    helper=0;
                  }                              
                  y=-(aF*helper)+height-ballSize;
                  i--;
                  helper++;
                  if(i==0)
                  {
                    changeColor();
                    mswitch=1;
                    squareHorizontally = rand.nextInt(width-squareSize);
                    squarePerpendicularly = rand.nextInt(height-squareSize);
                  }
                }
}
}
	        } catch (InterruptedException e) {
	        }
        }
        public boolean checkColision(int xBall, int yBall,int xSquare,int ySquare)
        {
                xSquareLessBall=xSquare-(ballSize);
                ySquareLessBall = ySquare-ballSize;
                System.out.println("pilka");
                System.out.println(xBall+" "+yBall);               
                xSquareTill= xSquare+squareSize;
                ySquareTill=ySquare+squareSize;
                boolean dependenceX = xBall>=xSquareLessBall && xBall<=xSquareTill;
                boolean dependenceY = yBall>=ySquareLessBall && yBall<=ySquareTill;    
                if(dependenceX && dependenceY)
                {
                        return true;
                }
                return false;
        }
        public List checkCombinationAfterColision(int xBall, int yBall,int xSquare,int ySquare)
        {
                List combinationList = new ArrayList();
                boolean a,b,c,d=true;
                int xSquareLessBall=xSquare-(ballSize);
                int ySquareLessBall = ySquare-ballSize;
                System.out.println("pilka");
                System.out.println(xBall+" "+yBall);
                System.out.println("kwadrat");
                System.out.println(xSquareLessBall + " " + xSquareTill + " " + ySquareLessBall + " " + ySquareTill);
                if(a=true)
                {
                  boolean decisionX=true;
                  boolean decisionY=true;
                  for (int i = 0; i < xBall; i++) {
                    if(i>xSquareLessBall && i<xSquareTill)
                    {
                      decisionX=false;
                    }
                  }
                  if(height/2 >ySquareLessBall)
                  {
                    // tutej edycja
                    for (int i = yBall; i > width/2; i--) {
                      if(ySquareLessBall<i && i<ySquareTill)
                      {
                        decisionY=false;
                      }
                      }
                    for (int j = width/2; j > yBall; j--) {
                      if(ySquareLessBall<j && j<ySquareTill)
                      {
                        decisionY=false;
                      }
                    }
                  
                  }
                  else
                  {
                    for (int i = width/2; i < yBall; i++) {
                      if(i>=ySquareLessBall || i==ySquareTill)
                      {
                        decisionY=false;
                      }   
                  }
                  }
                  if(decisionX ==false && decisionY == false)
                  {
                    a=false;
                  }
                  else
                  {
                    a=true;
                    combinationList.add(1);
                  }
                }
                if(b=true)
                {
                  boolean decisionX=true;
                  boolean decisionY=true;
                  if(width/2>xBall)
                  {       
                    int i = xBall;
                    while(i<width/2)
                    {
                      if(i==xSquareLessBall || i<xSquareTill)
                      {
                        decisionX=false;  
                      }
                      i++;
                    }
                  }
                  else
                  {
                    int i= xBall;
                    while (i>width/2) {
                      if(i>xSquareLessBall && i<xSquareTill)
                      {
                        decisionX=false;  
                      }
                      i--;
                    }
                  }
                  for (int i = 0; i < yBall; i++) {
                    if(i>ySquareLessBall && i<ySquareTill)
                    {
                      decisionY=false;
                    }   
                  }
                  if(decisionX ==false && decisionY == false)
                  {
                    b=false;
                  }
                  else
                  {
                    b=true;
                    combinationList.add(2);
                  }

                }
                if(c=true)
                {
                  boolean decisionX=true;
                  boolean decisionY=true;
                  for (int i = xBall; i < width; i++) {
                    if(i>xSquareLessBall && i+1<xSquareTill)
                    {
                      decisionX=false;  
                    }
                  }
                  if(yBall<height/2)
                  {
                    for (int i = yBall; i < width/2; i++) {
                      if(i>ySquareLessBall && i<ySquareTill)
                      {
                        decisionY=false;
                      }   
                    }
                  }
                  else
                  {
                    for (int i = height/2; i <= yBall; i++) {
                      if(i>ySquareLessBall && i<ySquareTill)
                      {
                        decisionY=false;
                      } 
                    }
                  }
                  if(decisionX ==false && decisionY == false)
                  {
                    c=false;
                  }
                  else
                  {
                    c=true;
                    combinationList.add(3);
                  }
                }
                if(d=true)
                { boolean decisionX=true;
                  boolean decisionY=true; 
                  if(xBall<width/2)
                  {      
                    for (int i = xBall; i < width/2; i++) {
                      if(i==xSquareLessBall || i<xSquareTill)
                      {
                        decisionX=false;  
                      }
                    } 
                  }
                  else
                  {
                    for (int i = width/2; i < xBall; i++) {
                      if(i>xSquareLessBall && i<xSquareTill)
                      {
                        decisionX=false;  
                      }
                    }
                  }
                    for (int i = yBall; i < height; i++) {
                      if(i==ySquareLessBall || i<ySquareTill)
                      {
                        decisionY=false;
                      }
                    }
                    if(decisionX ==false && decisionY == false)
                    {
                      d=false;
                    }
                    else
                    {
                      d=true;
                      combinationList.add(4);
                    }
                }
                return combinationList;
        }
        int changeSquareIfBallStuck = 0;
        public void moveAfterColision(int xBall, int yBall,List combinationAfterColision) throws InterruptedException
        {       
                int int_random = rand.nextInt(combinationAfterColision.size());
                int randomWay=(int) combinationAfterColision.get(int_random);
                for (int i = 0; i < combinationAfterColision.size(); i++) {
                  System.out.println(combinationAfterColision.get(i));
                  System.out.println(squareHorizontally);
                  System.out.println(squarePerpendicularly);
                }
                // ----- WAY 2---------
                if(randomWay==2)
                {
                  double ballPositionX = xBall;
                  int heightForComeback = yInt;
                  while(y!=0)
                  if(xBall>=width/2)
                  {     
                    // double widthForComeback = x-(width/2);
                    // double temporaryY=(heightForComeback/widthForComeback)*(x-width/2);
                    // System.out.println(temporaryY);
                    double dodatek =heightForComeback/((width/2)-ballPositionX);
                    y=y+dodatek;
                    x=xBall;
                    if(x==width/2 || y==0)
                    {
                      i=width/2+1;
                      y=0;
                      mswitch=2;
                    }
                    xBall--;
                    Thread.sleep(5);
                    repaint();
                  }
                else
                {
                  System.out.println(y);
                  double widthForComeback = width/2-x;
                  double temporaryY=(width/2-x);
                  y-= y/widthForComeback;
                  Thread.sleep(5);
                  repaint();
                  xBall++;
                  x=xBall;
                  if(x==width/2)
                  {
                    i=width/2+1;
                    y=0;
                    mswitch=2;
                  }     
                }
          }
          //way ----4-----
                if(randomWay==4)
                {
                  double ballPositionX = xBall;
                  //int heightForComeback = yInt;
                  while(y!=height-20)
                  {
                    double totalHeightForBall = height-yBall;
                    if(xBall>=width/2)
                    {
                      double totalWidthForBall = ballPositionX-(width/2);
                      xBall--;
                      x=xBall;
                      double mojdodatek =  (totalHeightForBall-ballSize)/totalWidthForBall;
                      y=y+mojdodatek;
                      Thread.sleep(5);
                      repaint();
                      if(x==width/2)
                      {
                        i=width/2-ballSize;
                        y=height-20;
                        mswitch=4;
                      }
                    }
                    else
                    {
                      double totalWidthForBall = (width/2)-ballPositionX;
                      xBall++;
                      x=xBall;
                      double mojdodatek = totalHeightForBall/totalWidthForBall;
                      y=y+mojdodatek;
                      System.out.println(y);
                      Thread.sleep(5);
                      repaint();
                      if(x==width/2-ballSize)
                      {
                        i=width/2-ballSize;
                        y=height-20;
                        mswitch=4;
                      }
                    }
                  }
                }
                //way 1
                int as=0;
                if(randomWay==1)
                {
                  double ballPositionX = xBall;
                  while(x!=0)
                  {
                    if(yBall<height/2)
                    {
                      int heightForComeback = (width/2)-yBall;
                      xBall--;
                      x=xBall;
                      double dodatek = heightForComeback/ballPositionX;
                      y=y+dodatek;
                      Thread.sleep(5);
                      repaint();
                      if(x==0)
                      {
                        y=width/2;
                        mswitch=1;
                        i=0;
                      }
                    }
                    else
                    {
                      int heightForComeback= yBall-(width/2);
                      double dodatek = heightForComeback/ballPositionX;
                      y=y-dodatek;
                      xBall--;
                      x=xBall;
                      Thread.sleep(5);
                      repaint();
                      if(x==0)
                      {
                        y=width/2;
                        mswitch=1;
                        i=0;
                      }
                    }
                  }
                }
                //way 3
                if(randomWay==3)
                {
                  double ballPositionX = xBall;
                  while(x!=width-ballSize)
                  {
                    if(yBall<height/2)
                    {
                      int heightForComeback = width/2-yBall-ballSize;
                      xBall++;
                      x=xBall;
                      double dodatek = heightForComeback/(width-ballPositionX);
                      y=y+dodatek;
                      Thread.sleep(5);
                      repaint();
                      if(x==width-ballSize)
                      {
                        System.out.println("aaa");
                        System.out.println(y);
                        y=width/2;
                        mswitch=3;
                        i=0;
                        i=width-ballSize;
                      }
                    }
                    else
                    {
                      //int heightForComeback=height-yBall;
                      double dodatek = (yBall-height/2+ballSize)/(width-ballPositionX-ballSize);
                      y=y-dodatek;
                      xBall++;
                      x=xBall;
                      Thread.sleep(5);
                      repaint();
                      if(x==width-ballSize)
                      {
                        y=width/2;
                        mswitch=3;
                        i=0;
                        i=width-ballSize;
                      }
                    }
                  }
                }

        }
        public void changeColor()
        {
                gg=rand.nextInt(255); 
                r=rand.nextInt(255); 
                b=rand.nextInt(255);
        }
 
        public void paintComponent (Graphics g) {
        	super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                g2.setColor(new Color(r, gg, b));
                g2.fill(new Ellipse2D.Double(x, y, ballSize, ballSize));
                g2.setColor(new Color(0, 0, 0));
                g2.drawRect(squareHorizontally, squarePerpendicularly, squareSize, squareSize);
                g2.drawImage(image, squareHorizontally, squarePerpendicularly, this);
        }
 
}