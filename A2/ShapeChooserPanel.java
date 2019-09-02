package ca.utoronto.utm.paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/**Creates the buttons on the drawing board
 */
public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {
	// this should be the factory, get rid of the list
	private View view; // So we can talk to our parent or other components of the view
    private Image button_image;
	//Constructor of the ShapeChooserPanel
	public ShapeChooserPanel(View view) {

		this.view = view;

		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline","Eraser" };//define what shapes we can choose
		
		int row = 0;
		for (String label : buttonLabels) {    //Create the buttons of these shapes with an image
			if(label == "Circle") {
				button_image = new Image("ca/utoronto/utm/paint/image/circle.png");
			}
			if(label == "Rectangle") {
				button_image = new Image("ca/utoronto/utm/paint/image/rec_used.png");
			}
			if(label == "Square") {
				button_image = new Image("ca/utoronto/utm/paint/image/square_used.png");
			}
			if(label == "Squiggle") {
				button_image = new Image("ca/utoronto/utm/paint/image/squiggle.png");
			}
			if(label == "Polyline") {
				button_image = new Image("ca/utoronto/utm/paint/image/polyline.png");
			}
			if(label == "Eraser") {
				button_image = new Image("ca/utoronto/utm/paint/image/eraser.jpeg");
			}
			
			ImageView button_images=new ImageView(button_image);
			Button button = new Button(label, button_images);
			button.setMinWidth(40);
			button.setMinHeight(10);	
			button_images.setFitHeight(30);
			button_images.setFitWidth(30);
			if(label == "Rectangle") {
				button_images.setFitHeight(40);
			}
			button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			button.setGraphicTextGap(1.0);
		    
			this.add(button, 1, row);
			row++;
			button.setOnAction(this);
		}
		
	}

	@Override
	//Deal with the action of the mouse
	public void handle(ActionEvent event) {
		String command = ((Button) event.getSource()).getText();
		Factory factory = new Factory();
		Shape shape = factory.createShape(command);
		this.view.getPaintPanel().setShape(shape);
		System.out.println(command);
		
	}
}
