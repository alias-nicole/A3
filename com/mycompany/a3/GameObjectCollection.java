package com.mycompany.a3;

import java.util.Vector;

public class GameObjectCollection implements ICollection
{
	private Vector<GameObject> gameObjCollection;
	
	public GameObjectCollection()
	{
		gameObjCollection = new Vector<GameObject>();
	}
	
	//methods inherited from ICollection
	public void add(GameObject newGameObject)
	{
		gameObjCollection.addElement(newGameObject);
	}
	
	public void remove(GameObject gameObject)
	{
		gameObjCollection.removeElement(gameObject);
	}
	
	public IIterator getIterator()
	{
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements IIterator
	{
		private int currentIndex;
		public GameObjectIterator()
		{
			currentIndex = -1;
		}
		//methods inherited from the interface IIterator
		public boolean hasNext()
		{
			if (gameObjCollection.size() <=0 || currentIndex == gameObjCollection.size() -1 )
			{
				return false;
			}
			
			return true;
		}
		
		public GameObject getNext()
		{
			currentIndex++;
			//return the next element in the collection
			return (gameObjCollection.elementAt(currentIndex));
			
		}
	}

}