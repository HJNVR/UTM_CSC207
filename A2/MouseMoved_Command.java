package ca.utoronto.utm.paint;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseMoved_Command implements Mouse_Command, EventHandler<MouseEvent>{
	private PaintPanel paintpanel;
	public MouseMoved_Command(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
	}
	@Override
	public void handle(MouseEvent event) {
		this.execute(event);
	//execute the command	
	}
	@Override
	public void execute(MouseEvent e) {
		if (this.paintpanel.getCurrentShpae().getName() == "Squiggle") {
		} else if (this.paintpanel.getCurrentShpae().getName() == "Circle") {

		}else if (this.paintpanel.getCurrentShpae().getName() == "Rectangle") {

		} else if (this.paintpanel.getCurrentShpae().getName() == "Square"){

		}else if(this.paintpanel.getCurrentShpae().getName() == "Polyline") {
			
		}
		
	}	
	}