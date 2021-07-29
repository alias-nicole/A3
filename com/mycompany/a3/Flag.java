package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flag extends FixedGameObject implements ISelectable, IDrawable, ICollider
{
    private int sequenceNumber = 1;
    private boolean isSelected;

    public Flag(float x, float y, int sequenceNumber)
    {
        super(x, y);
        this.setSize(100);
        this.sequenceNumber = sequenceNumber;
        this.setColor(ColorUtil.BLUE);
    }

	//@return sequenceNumber
    public int getSequenceNumber()
    {
        return sequenceNumber;
    }
    
    @Override
    public void setColor(int color) {}

    public String toString()
    {
        String objDesc = super.toString();
        String localDesc = " seqNum=" + sequenceNumber;
        return "Flag:" + objDesc + localDesc;
    }

	public void setSelected(boolean b) 
	{
		isSelected = b;
	}
	
	public boolean isSelected() 
	{
		return isSelected;
	}
	
	public boolean contains(Point pPtrRelPar, Point pCmpRelPar) 
	{
		float px = pPtrRelPar.getX(); // pointer location relative to
		float py = pPtrRelPar.getY(); // parent's origin
		int xLoc = (int) (pCmpRelPar.getX()+ this.getX());// shape location relative 
		int yLoc = (int) (pCmpRelPar.getY()+ this.getY());// to parent's origin
		if ( (px >= xLoc) && (px <= xLoc+this.getSize())&& (py >= yLoc) && (py <= yLoc+this.getSize()) )
		        return true; 
		else
		        	return false;
	}

	public void draw(Graphics g, Point pCmpRelPar) 
	{
		g.setColor(ColorUtil.BLUE);
		
		int x[] = new int[3];
		int y[] = new int[3];
		
		x[0] = (int)(pCmpRelPar.getX() + this.getX());
		x[1] = (int)(pCmpRelPar.getX() + this.getX() + 100);
		x[2] = (int)(pCmpRelPar.getX() + this.getX() + 100/2);
		
		y[0] = (int)(pCmpRelPar.getY() + this.getY());
		y[1] = (int)(pCmpRelPar.getY() + this.getY());
		y[2] = (int)(pCmpRelPar.getY() + this.getY() + 100);
		
		//a selected flag is drawn as an unfilled triangle
		if (isSelected())
		{
			g.drawPolygon(x,y,3);
			g.setColor(ColorUtil.BLUE);
		} else
		{
			g.fillPolygon(x,y,3);
			//g.setColor(ColorUtil.BLACK);
		}
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + getSequenceNumber(), (int)(pCmpRelPar.getX() + this.getX() + this.getSize()/2.5), 
				(int)(pCmpRelPar.getY() + this.getY() + this.getSize() / 2.8));
		
	}

	public boolean collidesWith(ICollider otherObject) {
		return false;
	}

	public void handleCollision(ICollider otherObject) {
		
	}
}