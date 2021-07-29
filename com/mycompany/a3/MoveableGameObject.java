package com.mycompany.a3;

import java.util.Random;

import com.mycompany.a3.GameObject;

public abstract class MoveableGameObject extends GameObject
{
    private int speed;
    private int direction;
    private Random rand;
    
    public static final int MAX_SPEED = 10;
    public static final int DEGREE_LIMIT = 359;

    public MoveableGameObject(float x, float y)
    {
        super(x,y);
        //this.rand = new Random();
        //this.speed = rand.nextInt(MAX_SPEED);
        //this.direction = rand.nextInt(DEGREE_LIMIT);
    }
    
    //move method, inherited by all MoveableGameObjects
    public void move(int elapsedTime, float height, float width)
    {
    	
    	//pass in elapsed time and mv.getHeight(), mv.getWidth() for bounds
        float r = this.getSpeed();
        //North = 0 degrees
        double theta = Math.toRadians(90-this.getDirection());

        float dx = (float) (r * Math.cos(theta));
        float dy = (float) (r * Math.sin(theta));
        
        dx *= elapsedTime;
        dy *= elapsedTime;

        float newX = this.getX() + dx;
        float newY = this.getY() + dy;
        /*if (newX < 0 || newX >= width)
        {
        	this.setDirection(this.getDirection() + 180);
        }
        if (newY < 0 || newY >= height)
        {
        	this.setDirection(this.getDirection() - 180);
        }*/
        this.setX(newX, height, width);
        this.setY(newY, height, width);
        if (this.getX() <= 0 )
        {
        	this.setX(0, height, width);
        	this.setDirection(90);
        }
        else if (this.getX() >= 1800)
        {
        	this.setX(1800, height, width);
        	this.setDirection(270);
        	System.out.println("x test");
        }
        
        if (this.getY() <= 0 )
        {
        	this.setY(0, height, width);
        	this.setDirection(0);
        }
        else if (this.getY() >= 1600)
        {
        	this.setY(1600, height, width);
        	this.setDirection(180);
        	System.out.println("y test");
        }
      

    }

    //getters and setters
    //@return speed
    public int getSpeed()
    {
        return this.speed;
    }
    //@param newSpeed
    public void setSpeed(int newSpeed)
    {
        this.speed = newSpeed;
    }
    //@return direction
    public int getDirection()
    {
        return this.direction;
    }
    //@param newDirection
    public void setDirection(int newDirection)
    {
        this.direction = newDirection;
    }

    public String toString()
    {
        String s = super.toString();
        String ss = " direction= " + direction + " speed = " + speed;
        return s + ss;
    }
}