package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class FoodStation extends FixedGameObject implements ISelectable, IDrawable, ICollider
{
    private int capacity;
    private boolean isSelected;
    
    //constructor for FoodStation, sets location, size, capacity, and color
    public FoodStation(float x, float y, int size)
    {
        super(x,y);
        this.setSize(size);
        this.setCapacity(size);
        this.setColor(ColorUtil.GREEN);
    }

	//@param capacity
    public void setCapacity(int c)
    {
        this.capacity = c;
    }
    //@return capacity
    public int getCapacity()
    {
        return this.capacity;
    }

    //toString method to return the food station coordinates & capacity
    public String toString()
    {
        String objDesc = super.toString();
        String localDesc = "Capacity = " + capacity;
        return "FoodStation: " + objDesc + localDesc;
    }

	public void draw(Graphics g, Point pCmpRelPar)
	{
		g.setColor(this.getColor());
		
		//a selected FoodStation is drawn as an unfilled square
		if (isSelected()) 
		{

			g.drawRect((int)(pCmpRelPar.getX()+this.getX()),
            		  (int)(pCmpRelPar.getY()+this.getY()),
            		  this.getSize(),this.getSize());
	
			g.setColor(this.getColor());
			
		} 
		else 
		{
			g.fillRect((int)(pCmpRelPar.getX()+this.getX()),
          		  (int)(pCmpRelPar.getY()+this.getY()),
          		  getSize(), getSize());
			g.setColor(ColorUtil.BLACK);
			
		}
		g.setColor(ColorUtil.BLACK);	
		g.drawString("" + getCapacity(), (int)(pCmpRelPar.getX() + this.getX() ),  
				(int)(pCmpRelPar.getY() + this.getY()  ));
		
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

	public boolean collidesWith(ICollider otherObject)
	{
		return false;
	}

	public void handleCollision(ICollider otherObject) 
	{
		
	}
}