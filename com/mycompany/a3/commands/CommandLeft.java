package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CommandLeft extends Command
{
	private GameWorld gw;
	
	public CommandLeft(GameWorld gw)
	{
		super("Left");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		gw.left();
	}

}