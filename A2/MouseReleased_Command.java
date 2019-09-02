package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.event.EventHandler;


/**
 * concrete command of command
 */
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MouseReleased_Command implements Mouse_Command, EventHandler<MouseEvent>{
	private PaintPanel paintpanel;
	public MouseReleased_Command(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
	}
	@Override
	public void handle(MouseEvent event) {
		this.execute(event);
		
	}
	@Override
	public void execute(MouseEvent e) {
		if (this.paintpanel.getCurrentShpae().getName() == "Squiggle") {
			ArrayList<Point> points = (this.paintpanel.getModel().getPoints());
			this.paintpanel.getModel().addSquiggle(points);
			ArrayList<Point> new_points =new ArrayList<Point>();
			//Point point = points.get(points.size()-1);
			//new_points.add(point);
			this.paintpanel.getModel().setPoints(new_points);
			this.paintpanel.getModel().setline_realtime(new_points);

	}else if (this.paintpanel.getCurrentShpae().getName() == "Circle") {
		if (this.paintpanel.getCircle() != null) {
			
			Point realcenter = this.paintpanel.getCircle().getrealCentre();
			double realcenter_x = realcenter.getX();
			double realcenter_y = realcenter.getY();
		//	Point centre = new Point(e.getX(),e.getY());
			double x  = Math.abs(realcenter_x - e.getX());
			double y  = Math.abs(realcenter_y - e.getY());
			double radius = Math.sqrt(Math.pow(x, 2)+ Math.pow(y, 2));
			if(e.getX()==realcenter_x) {
				radius = y;
			}
			if(e.getY()==realcenter_y) {
				radius = x;
			}
			Point centre = new Point(realcenter_x - radius,realcenter_y-radius);

			Circle circle = this.paintpanel.getCircle();
			circle.setRadius(radius);
			circle.setCentre(centre);
			this.paintpanel.getModel().addCircle(circle);
			if(this.paintpanel.getDrawStyle() == "Solid") {
				this.paintpanel.getModel().addCircle_solid(circle);
			}else if (this.paintpanel.getDrawStyle() == "Outline") {
				this.paintpanel.getModel().addCircle_outline(circle);
			}
			this.paintpanel.setCircle(null);
		}
		}else if (this.paintpanel.getCurrentShpae().getName() == "Square"){
			if (this.paintpanel.getSquare() != null){
				Point vertex = this.paintpanel.getSquare().getVertex();
				double width = Math.abs(vertex.getX() - e.getX());
				
				Point new_vertex= new Point(vertex.getX() ,vertex.getY());
				if(e.getX() <= vertex.getX() && e.getY() >= vertex.getY()) {
					new_vertex.setX(vertex.getX() - width);
				} else if(e.getX() <= vertex.getX() && e.getY() <= vertex.getY()){
					new_vertex.setX(vertex.getX() - width);
					new_vertex.setY(vertex.getY() - width);
				} else if(e.getX() >= vertex.getX() && e.getY() <= vertex.getY()) {
					new_vertex.setY(vertex.getY() - width);
				}
				
				Square square = this.paintpanel.getSquare();
				square.setWidth(width);
				square.setVertex(new_vertex);
				this.paintpanel.getModel().addSquare(square);
				if(this.paintpanel.getDrawStyle() == "Outline") {
					this.paintpanel.getModel().addSquare_outline(square);
				}else if (this.paintpanel.getDrawStyle() == "Solid") {
					this.paintpanel.getModel().addSquare_solid(square);
				}
				this.paintpanel.setSquare(null);
				
			}
		}
	else if (this.paintpanel.getCurrentShpae().getName() == "Rectangle") {
		if (this.paintpanel.getRectangle() != null) {
			Point vertex = this.paintpanel.getRectangle().getVertex();
			double width =  Math.abs(vertex.getX() -  e.getX());
			double height =  Math.abs(vertex.getY() -  e.getY());
			
			Point new_vertex= new Point(vertex.getX() ,vertex.getY());
			if(e.getX() <= vertex.getX() && e.getY() >= vertex.getY()) {
				new_vertex.setX(vertex.getX() - width);
			} else if(e.getX() <= vertex.getX() && e.getY() <= vertex.getY()){
				new_vertex.setX(vertex.getX() - width);
				new_vertex.setY(vertex.getY() - height);
			} else if(e.getX() >= vertex.getX() && e.getY() <= vertex.getY()) {
				new_vertex.setY(vertex.getY() - height);
			}
			Rectangle rectangle = this.paintpanel.getRectangle();
			rectangle.setVertex(new_vertex);
			rectangle.setWidth(width);
			rectangle.setHeight(height);
			this.paintpanel.getModel().addRectangle(rectangle);
			if(this.paintpanel.getDrawStyle() == "Outline") {
				this.paintpanel.getModel().addRectangle_outline(rectangle);
			}else if(this.paintpanel.getDrawStyle() == "Solid") {
				this.paintpanel.getModel().addRectangle_solid(rectangle);
			}
			this.paintpanel.setRectangle(null);
		}
		}else if(this.paintpanel.getCurrentShpae().getName() == "Polyline") {
			
		}
		}
}
	
	