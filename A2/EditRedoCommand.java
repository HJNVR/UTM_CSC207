package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public class EditRedoCommand implements EditCommand{
	private PaintPanel paintpanel;
	
	public EditRedoCommand(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
	}

	@Override
	public void modify(GraphicsContext g) {
		g.clearRect(0, 0, paintpanel.getWidth(), paintpanel.getHeight());
	}

}
