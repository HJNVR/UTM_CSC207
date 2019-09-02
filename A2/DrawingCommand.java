package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public interface DrawingCommand{
	public void draw(GraphicsContext g);	
}
