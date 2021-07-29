package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.util.Random;

public class Spider extends MoveableGameObject implements IDrawable, ICollider
{
    Random rand = new Random();
    private static final int MAX_DEGREES = 360;
    
    //constructor for spider class, sets location, size, speed, direction, and color
    public Spider(float x, float y, int speed, int size, int direction)
    {
        super(x,y);
        this.setSpeed(speed);
        this.setSize(size);
        this.setDirection(direction);
        this.setColor(ColorUtil.BLACK);

    }
    
    //spider has a random heading at time of instantiation
    public void randomDirection()
    {
        this.setDirection(rand.nextInt(MAX_DEGREES-1));
    }

    public void setNewDirection()
    {
    	this.setDirection(this.getDirection()+(rand.nextInt((15-5)-1)+5));
    }

    public void setSize(int size) {}
    @Override
    public void setColor(int color) {}

    public String toString()
    {
        return "Spider:" + super.toString();
    }
    public void draw(Graphics g, Point pCmpRelPar) {
		g.setColor(this.getColor());
		
		int x[] = new int[3];
		int y[] = new int[3];
		
		x[0] = (int)(pCmpRelPar.getX() + this.getX() );
		x[1] = (int)(pCmpRelPar.getX() + this.getX() + 90);
		x[2] = (int)(pCmpRelPar.getX() + this.getX() + 90/2);
		
		y[0] = (int)(pCmpRelPar.getY() + this.getY() );
		y[1] = (int)(pCmpRelPar.getY() + this.getY());
		y[2] = (int)(pCmpRelPar.getY() + this.getY() + 90 );
		
		g.drawPolygon(x, y, 3);
		
	}

	
	public boolean collidesWith(ICollider otherObject) 
	{
		boolean result = false;
		int thisCenterX = (int) (this.getX() + (this.getSize()/2)); // find centers 
		int thisCenterY = (int) (this.getY() + (this.getSize()/2));
		int otherCenterX = (int) (((GameObject) otherObject).getX() + (((GameObject)otherObject).getSize()/2));
		int otherCenterY = (int) (((GameObject)otherObject).getY() + (((GameObject)otherObject).getSize()/2));
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		     // find square of sum of radii
		int thisRadius = this.getSize()/2;
		int otherRadius = ((GameObject)otherObject).getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius
		+ otherRadius*otherRadius); if (distBetweenCentersSqr <= radiiSqr) { result = true ; }
		  return result ;
	}


	public void handleCollision(ICollider otherObject) {
	
		
	}


}