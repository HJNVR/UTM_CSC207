package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 *Create the view for the algorithm which has a MVC pattern
 */
public class View implements EventHandler<ActionEvent> {

	private PaintModel model;

	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private color_picker colourpicker;

	private ArrayList<String> Colors = new ArrayList<String>();
	private ArrayList<String> Styles = new ArrayList<String>();
	private ArrayList<String> Thicknesses = new ArrayList<String>();
	private ArrayList<Integer> NumOfThickness = new ArrayList<Integer>();
	private ArrayList<String> Edits = new ArrayList<String>();
	//Constructor of the View
	public View(PaintModel model, Stage stage) {

		this.model = model;
		initUI(stage);
		
		// set up line thickness from 0-9
		for(int i=0;i < 10; i++) {
			this.NumOfThickness.add(i);
		}
	}

	private void initUI(Stage stage) {

		this.paintPanel = new PaintPanel(this.model, this);
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		this.colourpicker = new color_picker(this);
		BorderPane root = new BorderPane();
		root.setTop(createMenuBarTop());
		root.setCenter(this.paintPanel);
		root.setLeft(this.shapeChooserPanel);
		root.setBottom(this.colourpicker);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Paint");
		stage.show();
	}


	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}

	private MenuBar createMenuBarTop() {

		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem menuItem;

		// A menu for File

		menu = new Menu("File");

		menuItem = new MenuItem("New");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Open");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Save");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Exit");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		// Another menu for Edit

		menu = new Menu("Edit");

		menuItem = new MenuItem("Cut");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Copy");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Paste");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Undo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Redo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);
		
		//another menu for selecting color
		menu = new Menu("Color");
		
		// one menu has menu items and can add items
		menuItem = new MenuItem("Red");
		menuItem.setStyle("-fx-text-fill:Red");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
				
		// now add green
		menuItem = new MenuItem("Green"); 
		menuItem.setStyle("-fx-text-fill:Green");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
						
		// now add white
		menuItem = new MenuItem("White"); 
		menuItem.setStyle("-fx-text-fill:White");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		// now add yellow
		menuItem = new MenuItem("Yellow"); 
		menuItem.setStyle("-fx-text-fill:Yellow");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		// now add blue
		menuItem = new MenuItem("Blue"); 
		menuItem.setStyle("-fx-text-fill:Blue");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		// now add orange
		menuItem = new MenuItem("Orange"); 
		menuItem.setStyle("-fx-text-fill:Orange");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		//now add purple
		menuItem = new MenuItem("Purple"); 
		menuItem.setStyle("-fx-text-fill:Purple");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		//now add black
		menuItem = new MenuItem("Black"); 
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		menuBar.getMenus().add(menu);
		
		//another menu for choosing style
		menu = new Menu("Style");
		
		// add solid style
		menuItem = new MenuItem("Solid");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		//add outline style
		menuItem = new MenuItem("Outline");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
				
		menuBar.getMenus().add(menu);
		
		// another menu for choosing 
		menu = new Menu("Line Thickness");
				
		for(int i = 0; i < 10; i++) {
			menuItem = new MenuItem(Integer.toString(i));
			menuItem.setOnAction(this);
			menu.getItems().add(menuItem);
		}
				
			menuBar.getMenus().add(menu);
			
		return menuBar;
	}
	
	// check the menuItem we choose is in the list
	public boolean IfInList(ArrayList<Integer> list, String text) {
		for(int e: list) {
			int num = Integer.parseInt(text);
			if (num == e) {
				return true;
			}
		}
		return false;
	}
	
	public void makeSetting(String text) {
		if (text == "Green" || text =="Red" || text == "White" || text == "Yellow"
				|| text == "Blue" || text == "Orange" || text == "Purple"|| text == "Black") {
			Colors.add(text);
		}else if (text == "Solid" || text == "Outline") {
			Styles.add(text);
		}else if(text == "Redo") {
			Edits.add(text);	
		}else if(IfInList(NumOfThickness, text)) {
			Thicknesses.add(text);
		}
	}	
	
	@Override
	public void handle(ActionEvent event) {
		String text = ((MenuItem)event.getSource()).getText();
		this.makeSetting(text);
		if(Colors.size() > 0) {
			this.getPaintPanel().setColor(Colors.get(Colors.size()-1));
		}
		if(Styles.size() > 0) {
			this.getPaintPanel().setDrawStyle(Styles.get(Styles.size()-1));
		}
		if(Thicknesses.size() >0) {
			this.getPaintPanel().setThickness(Thicknesses.get(Thicknesses.size()-1));
		}
		if(Edits.size() >0) {
			this.getPaintPanel().setEdit(Edits.get(Edits.size()-1));
		}
		
		System.out.println(text);
	}
}
