package ca.utoronto.utm.paint;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

public class SquiggleCommand extends PaintCommand implements PaintCommandComponent {
	private ArrayList<Point> points=new ArrayList<Point>();
	
	public void add(Point p){ 
		this.points.add(p); 
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Point> getPoints(){ return this.points; }
	
	
	@Override
	public void execute(GraphicsContext g) {
		ArrayList<Point> points = this.getPoints();
		// test  
		// every time after drag will print all points that added to the points list
		//for(Point p:points) {
		//	System.out.println(p.x + "and y is" + p.y);
		//}
		g.setStroke(this.getColor());
		for(int i=0;i<points.size()-1;i++){
			Point p1 = points.get(i);
			Point p2 = points.get(i+1);
			g.strokeLine(p1.x, p1.y, p2.x, p2.y);
		}
		
		
	}
	@Override
	public String getName() {
		return "Squiggle";
	}
	@Override
	public String getDetails() {
		String s = "";
		s += "\tpoints" +"\n";
		if(this.getPoints().size() >= 0) {
		for(Point p:this.getPoints()) {
			s += "\t\tpoint:"+"("+p.x+","+p.y+")" + "\n";
		}
		s += "\tend points" + "\n";
		}
		return s;
	}
	@Override
	public void accept(PaintCommandVisitor paintCommandVisitor) {
		paintCommandVisitor.visit(this);
		
	}
}
