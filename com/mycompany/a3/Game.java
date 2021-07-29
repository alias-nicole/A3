package com.mycompany.a3;

import static com.codename1.ui.CN.*;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.commands.*;
/**
 * Following the Model-View-Controller (MVC) architecture
 * Game is the "controller"
 * Sound credits:
 * "chomp.wav" by InspectorJ (www.jshaw.co.uk) of Freesound.org
 * "mushroom background music" by Sunsai of Freesound.org
 */
public class Game extends Form implements Runnable
{
    private Form current;
    private GameWorld gw;
    private ScoreView sv;
    private MapView mv;
    private boolean check = false;
    UITimer timer;
    
    private Command accelerateCommand;
    private Command brakeCommand;
    private Command leftCommand;
    private Command rightCommand;
    private Command exitCommand;
    private Command pauseCommand;
    private Command positionCommand;
    private Command soundCommand;
    private Command helpCommand;
    private Command aboutCommand;
    
    private Button accelerateButton;
    private Button accelerateMenuButton;
    private Button brakeButton;
    private Button leftButton;
    private Button rightButton;
    private Button positionButton;
    private Button exitButton;
    private Button pauseButton;
    
    private BGSound bgSound;
    

    public Game()
    {
        gw = new GameWorld();
        //create a ScoreView and MapView within the Game World
        sv = new ScoreView(gw);
        mv = new MapView(gw);

        Toolbar toolbar = new Toolbar();
        this.setToolbar(toolbar);
        toolbar.setTitle("ThePath Game");

        //create west container
        Container westContainer = new Container();
        westContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
        westContainer.setLayout(new BoxLayout(2));
        //start adding components at a location 50 pixels below the upper border of container
        westContainer.getAllStyles().setPadding(Component.TOP, 50);
        
        //create east container
        Container eastContainer = new Container();
        eastContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
        eastContainer.setLayout(new BoxLayout(2));
        //start adding components at a location 50 pixels below the upper border of container
        eastContainer.getAllStyles().setPadding(Component.TOP, 50);
        
        
        //create south container
        Container southContainer = new Container();
        southContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
        southContainer.setLayout(new BoxLayout(2));
        //need buttons to be placed horizontally and stretched vertically
        southContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        //start adding components at a location 600 pixels to the right of the left border of container
        southContainer.getAllStyles().setPadding(Component.LEFT, 800);
         
        //commands with key bindings
        accelerateCommand = new CommandAccelerate(gw);
        brakeCommand = new CommandBrake(gw);
        leftCommand = new CommandLeft(gw);
        rightCommand = new CommandRight(gw);
        exitCommand = new CommandExit(gw);
        
        //commands with buttons only
        pauseCommand = new CommandPause(gw);
        positionCommand = new CommandPosition(gw);
        
        //commands only on side menu
        soundCommand = new CommandSound(gw);
        aboutCommand = new CommandAbout(gw);
        helpCommand = new CommandHelp(gw);
        
        //add key listeners here, upon key press command is activated
        addKeyListener('a',accelerateCommand);
        addKeyListener('b', brakeCommand);
        addKeyListener('l', leftCommand);
        addKeyListener('r',rightCommand);
        addKeyListener('e',exitCommand);
        
        //buttons on west container
        accelerateButton = new Button("Accelerate");
		accelerateButton.setCommand(accelerateCommand);
		westContainer.addComponent(accelerateButton);
		accelerateButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		accelerateButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		accelerateButton.getAllStyles().setBgTransparency(255);
		accelerateButton.getAllStyles().setMarginBottom(10); 
		
		accelerateMenuButton = new Button("Accelerate");
		
		leftButton = new Button("Left"); 
		leftButton.setCommand(leftCommand);
		westContainer.addComponent(leftButton);	
		leftButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		leftButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		leftButton.getAllStyles().setBgTransparency(255);	
		leftButton.getAllStyles().setMarginBottom(10);
		
		//buttons on east container
		brakeButton = new Button("Brake");
		brakeButton.setCommand(brakeCommand);
		eastContainer.addComponent(brakeButton);
		brakeButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		brakeButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		brakeButton.getAllStyles().setBgTransparency(255);	
		brakeButton.getAllStyles().setMarginBottom(10);
		
		
		rightButton = new Button("Right");
		rightButton.setCommand(rightCommand);
		eastContainer.addComponent(rightButton);
		rightButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		rightButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		rightButton.getAllStyles().setBgTransparency(255);	
		rightButton.getAllStyles().setMarginBottom(10);
		
		//buttons on south container
		positionButton = new Button("Position");
		southContainer.add(positionButton);
		positionButton.setCommand(positionCommand);
		positionButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		positionButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		positionButton.getAllStyles().setBgTransparency(255);	
		positionButton.getAllStyles().setMarginRight(5);
		
		pauseButton = new Button("Pause");
		southContainer.addComponent(pauseButton);
		pauseButton.setCommand(pauseCommand);
		pauseButton.getAllStyles().setFgColor(ColorUtil.WHITE);
		pauseButton.getAllStyles().setBgColor(ColorUtil.BLUE);
		pauseButton.getAllStyles().setBgTransparency(255);	
		pauseButton.getAllStyles().setMarginRight(5);
        
		//toolbar.addComponentToSideMenu(accelerateMenuButton);
		//accelerateMenuButton.setCommand(accelerateCommand);
		
		
		
		toolbar.addCommandToSideMenu(accelerateCommand);
		CheckBox soundCheck = new CheckBox();
		soundCheck.getAllStyles().setBgTransparency(255);
		//soundCheck.getAllStyles().setBgColor(ColorUtil.BLUE);
		toolbar.addComponentToSideMenu(soundCheck);
		//add the sound command to checkbox
		soundCheck.setCommand(soundCommand);
		toolbar.addCommandToSideMenu(aboutCommand);
		toolbar.addCommandToSideMenu(exitCommand);
		toolbar.addCommandToRightBar(helpCommand);
        
		//add border layouts and containers to the form
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.WEST, westContainer);
		this.add(BorderLayout.EAST, eastContainer);
		this.add(BorderLayout.SOUTH, southContainer);
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);
		
	
		
		timer = new UITimer(this);
		timer.schedule(1000, true, this);
        
        gw.init();
        this.show();
        bgSound = new BGSound("mushroom-background-music.wav");

        //set dimensions of game world, based off of MapView
      	gw.setMapHeight(mv.getComponentForm().getHeight());
      	gw.setMapWidth(mv.getComponentForm().getWidth());

      	revalidate();
    }
    
    
    public void run()
    {
    	if (!gw.getPaused())
    	{
    		gw.clockTick();
    	}
    	togglePause(gw.getPaused());
    	//check to see if game isn't paused
    	if (!gw.getPaused())
    	{
    		playSounds();
    		pauseButton.setText("Pause");
    	}
    	if (gw.getPaused())
    	{
    		pauseButton.setText("Play");
    		bgSound.pause();
    	}
    }
    
    public void togglePause(boolean isPaused)
    {
    	accelerateButton.setEnabled(!isPaused);
    	//accelerateButton.setDisabledStyle(accelerateButton.getDisabledStyle());
    	leftButton.setEnabled(!isPaused);
    	brakeButton.setEnabled(!isPaused);
    	rightButton.setEnabled(!isPaused);
    	//position button is only enabled when game is paused
    	positionButton.setEnabled(isPaused);
    	
    	accelerateCommand.setEnabled(!isPaused);
    	accelerateMenuButton.setEnabled(!isPaused);
    	leftCommand.setEnabled(!isPaused);
    	brakeCommand.setEnabled(!isPaused);
    	rightCommand.setEnabled(!isPaused);
    	//position command is only enabled when game is paused
    	positionCommand.setEnabled(isPaused);
    	
    	//if the game is paused, do not accept keyboard input
    	if (isPaused)
    	{
    		removeKeyListener('a',accelerateCommand);
    		removeKeyListener('b',brakeCommand);
    		removeKeyListener('l',leftCommand);
    		removeKeyListener('r',rightCommand);
    		removeKeyListener('x',exitCommand);
    	}
    	else
    	{
    		addKeyListener('a',accelerateCommand);
    		addKeyListener('b',brakeCommand);
    		addKeyListener('l',leftCommand);
    		addKeyListener('r',rightCommand);
    		addKeyListener('x',exitCommand);
    	}
    	
    	
    }
    
    public void playSounds()
    {
    	//check to see if sound is "on"
    	if (gw.getSoundCheck())
    	{
    		bgSound.play();
    		System.out.println("sound test 1");
    		if (gw.isSpiderCollision())
    		{
    			System.out.println("sound test spider");
    			gw.setIsSpiderCollision(false);
    			Sound spiderSound = new Sound("squeak.wav", "audio/mp3");
    			if(!gw.getPaused())
    			{
    				spiderSound.play();
    			}
    		}
    		
    		if (gw.isFoodStationCollision())
    		{
    			System.out.println("sound test food");
    			gw.setIsFoodStationCollision(false);
    			Sound foodSound = new Sound("chomp.wav", "audio/wav");
    			if(!gw.getPaused())
    			{
    				foodSound.play();
    			}
    		}
    		
    		if (gw.isFlagCollision())
    		{
    			gw.setIsFlagCollision(false);
    			System.out.println("sound test flag");
    			Sound flagSound = new Sound("success.wav", "audio/wav");
    			if(!gw.getPaused())
    			{
    				flagSound.play();
    			}
    		}
    		

    	}
    }


}