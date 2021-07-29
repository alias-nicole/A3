package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CommandPosition extends Command
{
	private GameWorld gw;
	
	public CommandPosition(GameWorld gw)
	{
		super("Position");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		gw.setPositionable(true);
	}

}
