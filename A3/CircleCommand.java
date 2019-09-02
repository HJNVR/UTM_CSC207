package ca.utoronto.utm.paint;
import javafx.scene.canvas.GraphicsContext;

public class CircleCommand extends PaintCommand implements PaintCommandComponent {
	private Point centre;
	private int radius;
	
	public CircleCommand(Point centre, int radius){
		this.centre = centre;
		this.radius = radius;
	}
	public Point getCentre() { return centre; }
	public void setCentre(Point centre) { 
		this.centre = centre; 
		this.setChanged();
		this.notifyObservers();
	}
	public int getRadius() { return radius; }
	public void setRadius(int radius) { 
		this.radius = radius; 
		this.setChanged();
		this.notifyObservers();
	}
	public void execute(GraphicsContext g){
		int x = this.getCentre().x;
		int y = this.getCentre().y;
		int radius = this.getRadius();
		if(this.isFill()){
			g.setFill(this.getColor());
			g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
		} else {
			g.setStroke(this.getColor());
			g.strokeOval(x-radius, y-radius, 2*radius, 2*radius);
		}
	}
	@Override
	public String getName() {
		return "Circle";
	}
	@Override
	public String getDetails() {
		String s="";
		s += "\tcenter:"+"("+this.getCentre().x+","+this.getCentre().y+")"+"\n";
		s += "\tradius:" + this.getRadius() + "\n";
		return s;
	}
	@Override
	public void accept(PaintCommandVisitor paintCommandVisitor) {
		paintCommandVisitor.visit(this);
		
	}
}
