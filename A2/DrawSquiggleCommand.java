package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class DrawSquiggleCommand implements DrawingCommand{
	private PaintPanel paintpanel;
	private PaintModel model;
	
	public DrawSquiggleCommand(PaintPanel paintpanel, PaintModel model) {
		this.paintpanel = paintpanel;
		this.model = model;
	}

	@Override
	public void draw(GraphicsContext g) {
		// Draw real_time squiggle
		ArrayList<Point> line_points = this.model.getLine_times();
		for (int i = 0; i < line_points.size() - 1; i++) {
			Point p1 = line_points.get(i);
			Point p2 = line_points.get(i + 1);
						
			g.setStroke(p1.getColor());
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
				
		// Draw Lines - squiggle
		ArrayList<ArrayList<Point>> squiggles = this.model.getSquigles();
		for (ArrayList<Point> points: squiggles) {
			for (int i = 0; i < points.size() - 1; i++) {
				Point p1 = points.get(i);
				Point p2 = points.get(i + 1);
						
				g.setStroke(p1.getColor());
				g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			}
		}	
	}
		
	}
	