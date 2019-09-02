package ca.utoronto.utm.paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class colour_picker extends Application {
	Label color;
	Color c;
	static Color string;
	//private view;
	
	public colour_picker() {
		this.color = color;
		this.c = c;
		//this.view = view;
	}
	
	public void start(Stage s) 
    { 
        // set title for the stage 
        s.setTitle("Color Picker"); 
  
        
        // create a label 
        color = new Label("color picker"); 
        
        // create a color picker 
        ColorPicker cp = new ColorPicker(Color.BLACK); 
 
      
        //r.getChildren().add(cp); 
        cp.getStyleClass().add("split-button");
        cp.getCustomColors();
        cp.getAccessibleText();
        cp.getValue();
        string = cp.getValue();
        
        // create a scene 
        Scene sc = new Scene(cp, 160,40 ); 
  
        // set the scene 
        s.setScene(sc); 
        
        s.show(); 
    } 
	  
	//public void getColor() {
		//this.c = cp.getCustomColors();
	//}
	  
    public static void main(String args[]) 
    {   System.out.println(string);
        // launch the application 
        launch(args); 
    } 

	

}
