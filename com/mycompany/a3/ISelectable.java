package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public interface ISelectable 
{
	public void setSelected(boolean b);
	
	public boolean isSelected();
	
	//a way to determine if a pointer is in an object
	//cmp is the component position relative to the parent origin
	public boolean contains(Point pPtrRelPar, Point pCmpRelPar);
	
	public void draw(Graphics g, Point pCmpRelPar);

}
