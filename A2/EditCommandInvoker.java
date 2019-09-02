package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class EditCommandInvoker {
	private EditRedoCommand erc;
	private EditUndoCommand edc;
	private PaintPanel paintpanel;
	
	private ArrayList<EditCommand> commands = new ArrayList<EditCommand>();
	
	public EditCommandInvoker(PaintPanel paintpane) {
		this.paintpanel = paintpanel;
		this.erc = new EditRedoCommand(paintpanel);
		this.edc = new EditUndoCommand(paintpanel);
	}
	
	
	public void addEditCommand() {
		if(paintpanel.getEdit() == "Redo") {
			commands.add(erc);
			}else 
		if(paintpanel.getEdit() == "Undo") {
			commands.add(edc);
		}else {
			
		}
	}
	
	
	public void executeAllCommand(GraphicsContext g) {
		if(commands.size() > 0) {
		for(EditCommand command: commands) {
			command.modify(g);
		}
		}
	}
	
	
	
}
