package ca.utoronto.utm.paint;



import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MouseDragged_Command implements Mouse_Command, EventHandler<MouseEvent>{
	private PaintPanel paintpanel;
	
	public MouseDragged_Command(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
	}
	@Override
	public void handle(MouseEvent event) {
		this.execute(event);
		
	}
	
	@Override
	public void execute(MouseEvent e) {
		if (this.paintpanel.getCurrentShpae().getName() == "Squiggle") {
			Color color = this.getColor();
			Point point = new Point(e.getX(),e.getY(),color);
			this.paintpanel.getModel().addPoint(point);
			this.paintpanel.getModel().addline_realtime(point);
		} else if (this.paintpanel.getCurrentShpae().getName() == "Circle") {
			Point realcenter = this.paintpanel.getCircle().getrealCentre();
			double realcenter_x = realcenter.getX();
			double realcenter_y = realcenter.getY();
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
			
			
			Color color = this.getColor();
			int thickness = this.paintpanel.getCircle().getThickness();
			Circle circle = new Circle(centre, radius,color);
			circle.setThickness(thickness);
			circle.setRadius(radius);
			circle.setCentre(centre);
			circle.setrealCentre(realcenter);
			this.paintpanel.getModel().changeCircle_realtime(circle);

		} else if (this.paintpanel.getCurrentShpae().getName() == "Rectangle") {
			Color color = this.getColor();
			Point vertex = this.paintpanel.getRectangle().getVertex();
			double width = Math.abs(vertex.getX() - e.getX());
			double height = Math.abs(vertex.getY() - e.getY());
			// determine which direction user wants to drag
			Point new_vertex= new Point(vertex.getX() ,vertex.getY());
			if(e.getX() <= vertex.getX() && e.getY() >= vertex.getY()) {
				new_vertex.setX(vertex.getX() - width);
			} else if(e.getX() <= vertex.getX() && e.getY() <= vertex.getY()){
				new_vertex.setX(vertex.getX() - width);
				new_vertex.setY(vertex.getY() - height);
			} else if(e.getX() >= vertex.getX() && e.getY() <= vertex.getY()) {
				new_vertex.setY(vertex.getY() - height);
			}
			
			Rectangle r = new Rectangle(new_vertex, width,height,color);
			this.paintpanel.getModel().changeRectangle_realtime(r);

		} else if (this.paintpanel.getCurrentShpae().getName() == "Square") {
			Color color = this.getColor();
			
			Point vertex = this.paintpanel.getSquare().getVertex();
			double width = Math.abs(vertex.getX() - e.getX());
			
			// to determine which direction user wants to drag the square
			Point new_vertex= new Point(vertex.getX() ,vertex.getY());
			if(e.getX() <= vertex.getX() && e.getY() >= vertex.getY()) {
				new_vertex.setX(vertex.getX() - width);
			} else if(e.getX() <= vertex.getX() && e.getY() <= vertex.getY()){
				new_vertex.setX(vertex.getX() - width);
				new_vertex.setY(vertex.getY() - width);
			} else if(e.getX() >= vertex.getX() && e.getY() <= vertex.getY()) {
				new_vertex.setY(vertex.getY() - width);
			}
			
			Square square = new Square(new_vertex, width,color);
			this.paintpanel.getModel().changeSquare_realtime(square);
			
		} else if(this.paintpanel.getCurrentShpae().getName() == "Eraser"){
			Point vertex = new Point( e.getX(),  e.getY());
			Eraser eraser = new Eraser(vertex);
			this.paintpanel.seteraser(eraser);
			this.paintpanel.getModel().add_clear(eraser);
			
		}else if(this.paintpanel.getCurrentShpae().getName() == "Polyline") {
			
		}
  
		}
	
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
	
	public int getLineThickness() {
		if(this.paintpanel.getThickness() != null) {
			return Integer.parseInt(this.paintpanel.getThickness());
		}
		return 0;
	}
	}
