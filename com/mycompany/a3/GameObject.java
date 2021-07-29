package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject implements ICollider
{
	//all GameObjects have a size, location, and color
    private int size = 0;
    private Point location;
    private int color;
    
    public GameObject(float x, float y)
    {
    	this.location = new Point();
    	this.location.setX(x);
    	this.location.setY(y);
    }
    //getters and setters
    //@return size
    public int getSize() 			{return this.size;} 
    public void setSize(int size)	{this.size = size;}
    //@return x
    public float getX() 			{return this.location.getX();}
    //@return y
    public float getY() 			{return this.location.getY();}
    
    //methods to set x and y location with respect to dimension of mapview
    //@param x
    public void setX(float newX, float mapHeight, float mapWidth)
    {
    	if (newX > 0 && newX < mapWidth - this.size)
    	{
    		this.location.setX(newX);
    	} else if (newX > mapWidth - this.size) {
    		this.location.setX(mapWidth - this.size);
    	} else if(newX < 0 ) {
    		this.location.setX(0);
    	}
    }
    //@param y
    public void setY(float newY, float mapHeight, float mapWidth)
    {
    	if (newY > 0 && newY < mapWidth - this.size)
    	{
    		this.location.setY(newY);
    	} else if (newY > mapWidth - this.size) {
    		this.location.setY(mapWidth - this.size);
    	} else if(newY < 0 ) {
    		this.location.setY(0);
    	}
    }
    //@param x, y
    public void setLocation(float newX, float newY)
    {
        this.location.setX(newX);
        this.location.setY(newY);
    }
    
    //@return color
    public int getColor() 			   {return this.color;}
    //@param newColor
    public void setColor(int newColor) {this.color = newColor;}
    

    //toString method used for all game objects
    //only location and color (not size) can be changed
    public String toString()
    {
        String s = " location= " + Math.round(getX()*10.0)/10.0 +","+Math.round(getY()*10.0)/10.0
                + " color=[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) + "]"  +
                " size="  + this.getSize();
        return s;
    }

}