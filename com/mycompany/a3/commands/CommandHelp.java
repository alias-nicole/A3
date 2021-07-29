package com.mycompany.a3.commands;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.*;
import com.codename1.ui.table.TableLayout;

public class CommandHelp extends Command
{
	private GameWorld gw;
	
	public CommandHelp(GameWorld gw)
	{
		super("Help");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Dialog helpBox = new Dialog("help", new TableLayout(10,2));
		
		helpBox.add(new Label("Controllers/ Command"));
		helpBox.add(new Label("To accelerate, press a"));
		helpBox.add(new Label("To brake, press b"));
		helpBox.add(new Label("To make a left turn, press l"));
		helpBox.add(new Label("To make a right turn, press r"));
		helpBox.add(new Label("To collide with food station, press f"));
		helpBox.add(new Label("To collide with spider, press g"));
		helpBox.add(new Label("To tick the clock, press t"));
		helpBox.add(new Label("To exit, press x"));
		
		Command okCommand = new Command("ok");
		Command c = Dialog.show("", helpBox, okCommand);
		if (c==okCommand)
		{
			return;
		}
		
	}
}