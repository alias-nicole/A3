package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CommandBrake extends Command
{
	private GameWorld gw;
	
	public CommandBrake(GameWorld gw)
	{
		super("Brake");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		gw.brake();
	}

}