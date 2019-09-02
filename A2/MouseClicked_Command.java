package ca.utoronto.utm.paint;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
//Defines how the algorithm reacts when mouse is clicked. Most of the code of polyline is in this class
public class MouseClicked_Command implements Mouse_Command, EventHandler<MouseEvent>{
 private PaintPanel paintpanel;
 public MouseClicked_Command(PaintPanel paintpanel) {
  this.paintpanel = paintpanel;
 }
 
 public void handle(MouseEvent event) {
   this.execute(event);
 }
  
 //when the name of the shape matches the name of one of the names below, mouse event is executed
 @Override
 public void execute(MouseEvent e) {
  if (this.paintpanel.getCurrentShpae().getName() == "Squiggle") {
  }else if (this.paintpanel.getCurrentShpae().getName()  == "Circle" ) {
  }else if (this.paintpanel.getCurrentShpae().getName()  == "Rectangle" ) {
   }else if (this.paintpanel.getCurrentShpae().getName()  == "Square") {
   }else if(this.paintpanel.getCurrentShpae().getName() == "Polyline") {
		if(this.paintpanel.getPolyline() == null){
			Point p1 = new Point(e.getX(), e.getY());
			Point p2 = new Point(e.getX(), e.getY());
			Color color = this.getColor();
			Polyline polyline = new Polyline(p1,p2,color);
			this.paintpanel.setPolyline(polyline);
			this.paintpanel.getModel().addpolyline(polyline);
		}else {
			Color color = this.getColor();
			Polyline polyline = this.paintpanel.getPolyline();
			Point p1 = polyline.getEndPoint();
			Point p2 = new Point(e.getX(), e.getY());
			polyline.setStartPoint(p1);
			polyline.setEndPoint(p2);
			this.paintpanel.setPolyline(polyline);
			Polyline polyline_new = new Polyline(p1,p2,color);
			this.paintpanel.getModel().addpolyline(polyline_new);
			
			
		}
	}
  
  }
 // get the color of the shape we are drawing
	public Color getColor() {
		Color r = Color.BLACK;
		if (this.paintpanel.getColor() == "Red") {
			r = Color.RED;
		}else if(this.paintpanel.getColor() == "Green"){
			r = Color.GREEN;
		}else if(this.paintpanel.getColor() == "White") {
			r = Color.WHITE;
		}else if(this.paintpanel.getColor() == "Yellow") {
			r = Color.YELLOW;
		}else if(this.paintpanel.getColor() == "Blue") {
			r = Color.BLUE;
		}else if(this.paintpanel.getColor() == "Orange") {
			r = Color.ORANGE;
		}else if(this.paintpanel.getColor() == "Purple") {
			r = Color.PURPLE;
		}
		return r;
	}
	
 }