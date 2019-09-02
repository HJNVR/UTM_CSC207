package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public class EditUndoCommand implements EditCommand{
	private PaintPanel paintpanel;
	
	public EditUndoCommand(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
	}

	@Override
	public void modify(GraphicsContext g) {
		g.clearRect(0, 0, paintpanel.getWidth(), paintpanel.getHeight());
		if(paintpanel.getModel_draw().getCommands().size()>0)
		paintpanel.getModel_draw().getCommands().remove(paintpanel.getModel_draw().getCommands().size()-1);	
	} 
}