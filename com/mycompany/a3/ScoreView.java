package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

//ScoreView contains  code  to  output  the  current  game/ant state information.
//Implemented as an Observer of GameWorld
public class ScoreView extends Container implements Observer
{
	private GameWorld gw;
	
	//declare all labels here
	private Label clockLabel;
	private Label clockValue;
	private Label livesLabel;
	private Label livesValue;
	private Label lastFlagReachedLabel;
	private Label lastFlagReachedValue;
	private Label foodLevelLabel;
	private Label foodLevelValue;
	private Label healthLevelLabel;
	private Label healthLevelValue;
	
	private Label soundLabel;
	private Label soundValue;
	
	public ScoreView(Observable gwModel)
	{
		gw = (GameWorld) gwModel;
		gwModel.addObserver(this);
		
		this.setLayout(new FlowLayout(Component.CENTER));
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.GRAY));
		
		//set up all labels then add them to the scoreview
		this.setupLabels();
		this.add(clockLabel);
		this.add(clockValue);
		this.add(livesLabel);
		this.add(livesValue);

		this.add(lastFlagReachedLabel);
		this.add(lastFlagReachedValue);
		this.add(foodLevelLabel);
		this.add(foodLevelValue);
		this.add(healthLevelLabel);
		this.add(healthLevelValue);
		this.add(soundLabel);
		this.add(soundValue);
	}
	
	public void setupLabels()
	{
		//North container
		
		clockLabel = new Label("Time: ");
		clockValue = new Label("" + gw.getClock());
		clockLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		clockValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		livesLabel = new Label("Lives: ");
		livesValue = new Label("" + gw.getLivesLeft());
		livesLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		lastFlagReachedLabel = new Label("Last Flag Reached: ");
		lastFlagReachedValue = new Label("" + gw.getLastFlagReached());
		lastFlagReachedLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastFlagReachedValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		foodLevelLabel = new Label("Food Level: ");
		foodLevelValue = new Label("" + gw.getFoodLevel());
		foodLevelLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		foodLevelValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		healthLevelLabel = new Label("Health Level: ");
		healthLevelValue = new Label("" + gw.getHealthLevel());
		healthLevelLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		healthLevelValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		soundLabel = new Label("Sound: ");
		soundValue = new Label("" + gw.isSound());
		soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		

	}
	
	public void updateLabels()
	{
		//update text of game labels as clock ticks/game progresses
		clockValue.setText("" + gw.getClock());
		clockValue.getParent().revalidate();
		
		livesValue.setText("" + gw.getLivesLeft());
		livesValue.getParent().revalidate();
		
		lastFlagReachedValue.setText("" + gw.getLastFlagReached());
		lastFlagReachedValue.getParent().revalidate();
		
		foodLevelValue.setText("" + gw.getFoodLevel());
		foodLevelValue.getParent().revalidate();
		
		healthLevelValue.setText("" + gw.getHealthLevel());
		healthLevelValue.getParent().revalidate();
		
		soundValue.setText("" + gw.isSound());
		soundValue.getParent().revalidate();
	}
	
	@Override
	public void update(Observable observable, Object data)
	{
		this.updateLabels();
	}

}