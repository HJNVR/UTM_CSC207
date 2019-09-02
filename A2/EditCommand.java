package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public interface EditCommand {
	public void modify(GraphicsContext g);
}
