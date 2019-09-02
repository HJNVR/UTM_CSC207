package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class color_picker extends GridPane implements EventHandler<ActionEvent> {
	private View view; // So we can talk to our parent or other components of the view
    private Image button_image;
    private Canvas canvas;
    private ColorPicker colorPicker;
	public color_picker(View view) {
      
		this.view = view;
		
		
		

		String[] buttonLabels = { "Red", "Green", "White", "Blue", "Orange","Purple","Black"};
		
		int row = 0;
		for (String label : buttonLabels) {
			if(label == "Red") {
				button_image = new Image("ca/utoronto/utm/paint/image/red.png");
				
			}
			if(label == "Green") {
				button_image = new Image("ca/utoronto/utm/paint/image/green.png");
			}
			if(label == "White") {
				button_image = new Image("ca/utoronto/utm/paint/image/square_used.png");
			}
			if(label == "Blue") {
				button_image = new Image("ca/utoronto/utm/paint/image/blue.png");
			}
			if(label == "Orange") {
				button_image = new Image("ca/utoronto/utm/paint/image/orange.png");
			}
			if(label == "Purple") {
				button_image = new Image("ca/utoronto/utm/paint/image/purple.png");
			}
			if(label == "Black") {
				button_image = new Image("ca/utoronto/utm/paint/image/black.png");
			}
			
			ImageView button_images=new ImageView(button_image);
			Button button = new Button(label, button_images);
			//button.set
			//button.setGraphic();
			button.setMinWidth(10);
			button.setMinHeight(10);	
			button_images.setFitHeight(15);
			button_images.setFitWidth(15);
			//button.setStyle("fx-background-color: #FF0000" );
			button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			button.setGraphicTextGap(1.0);
		    
			this.add(button,row , 2);
			row++;
			button.setOnAction(this);
		}
		
	}

	@Override
	public void handle(ActionEvent event) {
		String command = ((Button) event.getSource()).getText();
		this.view.getPaintPanel().setColor(command);
		
		System.out.println(command);
		
	}
}
