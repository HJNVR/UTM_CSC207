package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class DrawPolylineCommand implements DrawingCommand{
	private PaintPanel paintpanel;
	private PaintModel model;
	
	public DrawPolylineCommand(PaintPanel paintpanel, PaintModel model) {
		this.paintpanel = paintpanel;
		this.model = model;
	}

	@Override
	public void draw(GraphicsContext g) {
		ArrayList<Polyline> polylines = this.model.getPolylines();
		for(Polyline polyline: polylines) {
			Point p1 = polyline.getStartPoint();
			Point p2 = polyline.getEndPoint();
			
			g.setStroke(polyline.getColor());
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
	}
		
}
