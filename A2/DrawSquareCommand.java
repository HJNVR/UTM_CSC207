package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class DrawSquareCommand implements DrawingCommand{
	private PaintPanel paintpanel;
	private PaintModel model;
	
	public DrawSquareCommand(PaintPanel paintpanel, PaintModel model) {
		this.paintpanel = paintpanel;
		this.model = model;
	}

	@Override
	public void draw(GraphicsContext g) {
		// draw real_time squares
		Square s_realtime = this.model.getS_realtime();
		g.setStroke(s_realtime.getColor());
		g.strokeRect(s_realtime.getVertex().getX(), s_realtime.getVertex().getY() , 
						s_realtime.getWidth(), s_realtime.getWidth());
				
		// draw squares
				
		ArrayList<Square> square = this.model.getSquare();
		for (Square s : square) {
			double x = s.getVertex().getX();
			double y = s.getVertex().getY();
			double width = s.getWidth();
					
			g.setStroke(s.getColor());
			g.strokeRect(x, y, width, width);
		}
				
		//draw solid square
		ArrayList<Square> squares_solid = this.model.getSquare_solid();
		for (Square s : squares_solid) {
			double x = s.getVertex().getX();
			double y = s.getVertex().getY();
			double width = s.getWidth();
					
			g.setFill(s.getColor());
			g.fillRect(x, y,width, width);
					
			}
				
		// draw outlined square
		ArrayList<Square> squares_outline = this.model.getSquare_outline();
		for (Square s : squares_outline) {
			double x = s.getVertex().getX();
			double y = s.getVertex().getY();
			double width = s.getWidth();
					
			g.setStroke(s.getColor());
			g.strokeRect(x, y, width, width);
			g.strokeRect(x-10, y-10, width + 20, width + 20 );
		}
		
	}
}