package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent; 
import java.lang.String;

/**
 * @author Kevin Tran
 * CSC 133
 * Professor Muyan-Özçelik
 * Assignment 2
 * 
 * The Game class instantiates a GameWorld variable and starts the game
 */

public class Game extends Form implements Runnable{
	private UITimer timer = new UITimer(this);
	
	private GameWorld gw = new GameWorld();
	private MapView mv = MapView.getMapView(gw);;
	private ScoreView sv;
	
	//UI Components
	private Toolbar myToolbar = new Toolbar();
	private Container southContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
	private Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
	private Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
	
	//Commands
	private AccCommand myAccCommand = new AccCommand(gw);
	private BrakeCommand myBrakeCommand = new BrakeCommand(gw);
	private LTurnCommand myLTurnCommand = new LTurnCommand(gw);
	private RTurnCommand myRTurnCommand = new RTurnCommand(gw);
	private ExitCommand myExitCommand = new ExitCommand();
	private StratCommand myStratCommand = new StratCommand(gw);
	private SoundCommand mySoundCommand = new SoundCommand(gw);
	private AboutCommand myAboutCommand = new AboutCommand();
	private HelpCommand myHelpCommand = new HelpCommand();
	private PlayPauseCommand myPlayPauseCommand = new PlayPauseCommand(this);
	private PositionCommand myPositionCommand = new PositionCommand(gw);
	private PointerListener myPointerListener = new PointerListener(mv);
	
	/*Buttons
	 * 0 = Accelerate
	 * 1 = Brake
	 * 2 = LTurn
	 * 3 = RTurn
	 * 4 = Strategy
	 * 5 = Play/Pause
	 * 6 = Position
	*/
	private Button[] myButton = {new Button(myAccCommand),new Button(myBrakeCommand),new Button(myLTurnCommand),new Button(myRTurnCommand),new Button(myStratCommand),new Button(myPlayPauseCommand),new Button(myPositionCommand)};
	private CheckBox soundBox = new CheckBox("Sound");
	
	//Constructor for Game
	public Game() {
		sv = new ScoreView(gw);
		
		//Setting size and appearance of buttons
		for(int i = 0; i < myButton.length; i++) {
			myButton[i].getUnselectedStyle().setBgTransparency(255); //Set transparency to opaque
			myButton[i].getUnselectedStyle().setBgColor(ColorUtil.BLUE); //Set background color
			myButton[i].getUnselectedStyle().setFgColor(ColorUtil.WHITE); //Set text color
			myButton[i].getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK)); //Create a line border around the buttons
			myButton[i].getAllStyles().setPadding(Component.TOP, 5); //Set top padding
			myButton[i].getAllStyles().setPadding(Component.BOTTOM, 5); //Set bottom padding
		}
		
		//Add Key Listeners
		addKeyListener('a', myAccCommand);
		addKeyListener('b', myBrakeCommand);
		addKeyListener('l', myLTurnCommand);
		addKeyListener('r', myRTurnCommand);
		
		//Sets layout of form to BorderLayout
		this.setLayout(new BorderLayout()); 
		
		//Add commands to tool bar
		this.setToolbar(myToolbar);
		myToolbar.addCommandToSideMenu(myAccCommand);
		soundBox.setCommand(mySoundCommand);
		soundBox.getAllStyles().setBgTransparency(255); 
		soundBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		myToolbar.addComponentToSideMenu(soundBox);
		myToolbar.addCommandToSideMenu(myAboutCommand);
		myToolbar.addCommandToSideMenu(myExitCommand);
		myToolbar.addCommandToRightBar(myHelpCommand);
		
		//Add buttons to west container
		westContainer.getUnselectedStyle().setPadding(Component.TOP, 100);
		westContainer.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK)); 
		westContainer.add(myButton[0]); //Add Accelerate Button
		westContainer.add(myButton[2]); //Add LTurn Button
		westContainer.add(myButton[4]); //Add Strategy Button
		
		//Add buttons to east container
		eastContainer.getUnselectedStyle().setPadding(Component.TOP, 100);
		eastContainer.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK)); 
		eastContainer.add(myButton[1]); //Add Brake Button
		eastContainer.add(myButton[3]); //Add RTurn Button
		
		//Add buttons to south container
		southContainer.getUnselectedStyle().setPadding(Component.LEFT, 900);
		southContainer.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK)); 
		southContainer.add(myButton[6]); //Add Position Button
		myButton[6].setEnabled(false);
		southContainer.add(myButton[5]); //Add Play/Pause Button
		
		//Add containers to form
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.SOUTH, southContainer);
		this.add(BorderLayout.EAST, eastContainer);
		this.add(BorderLayout.WEST, westContainer);
		mv.addPointerReleasedListener(myPointerListener);
		this.add(BorderLayout.CENTER, mv);
		this.setTitle("Sili-Challenge Game");
		this.show();
		
		gw.init();
		
		timer.schedule(20, true, this);
	}

	@Override
	public void run() {
		gw.tick(20);
	}
	
	public void pause() {
		timer.cancel();
		gw.pauseSound();
		removeKeyListener('a', myAccCommand);
		removeKeyListener('b', myBrakeCommand);
		removeKeyListener('l', myLTurnCommand);
		removeKeyListener('r', myRTurnCommand);
		for(int i = 0; i < 5; i++) {
			myButton[i].setEnabled(false);
		}
		soundBox.setEnabled(false);
		myAccCommand.setEnabled(false);
		myButton[6].setEnabled(true);
		myButton[5].setText("Play");
	}
	
	public void play() {
		gw.unselectAll();
		timer.schedule(20, true, this);
		gw.playSound();
		addKeyListener('a', myAccCommand);
		addKeyListener('b', myBrakeCommand);
		addKeyListener('l', myLTurnCommand);
		addKeyListener('r', myRTurnCommand);
		for(int i = 0; i < 5; i++) {
			myButton[i].setEnabled(true);
		}
		soundBox.setEnabled(true);
		myAccCommand.setEnabled(true);
		myButton[6].setEnabled(false);
		myButton[5].setText("Pause");
	}
}
