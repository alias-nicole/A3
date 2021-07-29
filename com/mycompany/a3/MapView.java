package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;
//MapView contains code to output the map
//Implemented as an Observer of GameWorld
public class MapView extends Container implements Observer
{
	
	//model of MVC
	private GameWorld gw;
	
	MapView(Observable gwModel)
	{
		gw = (GameWorld) gwModel;
		gwModel.addObserver(this);
		//create red border around container
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.rgb(255, 0, 0)));
	}
	
	public int getMapWidth() 
	{
		
		return getComponentForm().getWidth() - (2 * getX());
	}
	
	public int getMapHeight() 
	{
		
		return getHeight();
	}
	public void update(Observable observable, Object data)
	{
		this.repaint();
		//gw.map();
		
	}
	
	public void paint(Graphics g)
	{
		//MapView must override paint()
		super.paint(g);
		Point pCmpRelPar = new Point(getX(), getY());
		gw.draw(g, pCmpRelPar);
		
		GameObjectCollection gameObjCollection = gw.getGameObjectCollection();
		IIterator selectors = gameObjCollection.getIterator();
		while (selectors.hasNext())
		{
			GameObject currObj = (GameObject)selectors.getNext();
			//Flags and FoodStations cannot be selected while the game is running
			if ((currObj instanceof Flag || currObj instanceof FoodStation)&&gw.getPaused()==false)
			{
				((ISelectable)currObj).setSelected(false);
			}
		}		
	}
	
	public void pointerPressed(int x, int y) 
	{ 
		if (gw.getPaused() == false ) 
		{
			return;
		}
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY(); 
		Point pPtrRelPrnt = new Point(x, y);
	       
		Point pCmpRelPrnt = new Point(getX(), getY());
		GameObjectCollection gameObjCollection = gw.getGameObjectCollection();
		
		IIterator selectors = gameObjCollection.getIterator();
		
		while(selectors.hasNext())
		{
			GameObject currObj = (GameObject)selectors.getNext();
			if (currObj instanceof Flag || currObj instanceof FoodStation)
			{
				
				
				//check if the object was selected and position button was pressed so we can move it
				if (((ISelectable)currObj).isSelected() && gw.getPositionable()) 
				{
					
					((GameObject)currObj).setX(x - getX() - currObj.getSize() / 2, getMapHeight(), getMapWidth());
					((GameObject)currObj).setY(y - getY() - currObj.getSize() / 2, getMapHeight(), getMapWidth());
					((ISelectable)currObj).setSelected(false);
					gw.setPositionable(false);
					
				} else if(((ISelectable)currObj).contains(pPtrRelPrnt, pCmpRelPrnt)) 
				{
					((ISelectable)currObj).setSelected(true);
					
				}
				else
				{
					((ISelectable)currObj).setSelected(false);
				}
			
			}
			update(gw,null);
			repaint(); 
			
		}
	}

}
