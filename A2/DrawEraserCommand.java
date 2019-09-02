package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class DrawEraserCommand implements DrawingCommand{
	private PaintPanel paintpanel;
	private PaintModel model;
	
	public DrawEraserCommand(PaintPanel paintpanel, PaintModel model) {
		this.paintpanel = paintpanel;
		this.model = model;
	}

	@Override
	public void draw(GraphicsContext g) {
		
	//eraser
	ArrayList<Eraser> Clear = this.model.getclearspace();
	for (Eraser c : Clear) {
		double x = c.getVertex().getX();
		double y = c.getVertex().getY();
		
		g.clearRect(x, y, 9, 9);
		}

		
	}
}