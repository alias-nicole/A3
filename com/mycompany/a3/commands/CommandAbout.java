package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.table.TableLayout;
import com.mycompany.a3.GameWorld;

public class CommandAbout extends Command
{
	private GameWorld gw;
	
	public CommandAbout(GameWorld gw)
	{
		super("About");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Dialog aboutBox = new Dialog("About", new TableLayout(4,1));
		Command okCommand = new Command("ok");
		
		aboutBox.add(new Label("Nicole Monroy"));
		aboutBox.add(new Label("CSC 133"));
		aboutBox.add(new Label("ThePath Game"));
		
		Command c = Dialog.show("",  aboutBox,  okCommand);
		if (c==okCommand)
		{
			return;
		}
	}
}
