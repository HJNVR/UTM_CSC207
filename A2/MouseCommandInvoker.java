package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *  this is invoker
 * 
 *
 */

public class MouseCommandInvoker implements EventHandler<MouseEvent>{
	private ArrayList<Mouse_Command> commands = new ArrayList<Mouse_Command>();
	private ArrayList<String> list = new ArrayList<String>();
	private MouseClicked_Command mcc;
	private MouseDragged_Command mdc;
	private MousePressed_Command mpc;
	private MouseMoved_Command mmc;
	private MouseEntered_Command mec;
	private MouseExited_Command metc;
	private MouseReleased_Command mrc;
	public MouseCommandInvoker(MouseClicked_Command mcc,MouseDragged_Command mdc,
			MousePressed_Command mpc, MouseMoved_Command mmc, MouseEntered_Command mec,
			MouseExited_Command metc,MouseReleased_Command mrc) {
	//	commands.add(null);
		this.mcc = mcc;
		this.mdc = mdc;
		this.mpc = mpc;
		this.mmc = mmc;
		this.mec = mec;
		this.metc = metc;
		this.mrc = mrc;
	}
	
	public void addCommand(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			this.commands.add(mdc);
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			this.commands.add(mpc);
		} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			this.commands.add(mmc);
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			this.commands.add(mcc);
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			this.commands.add(mrc);
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			this.commands.add(mec);
		} else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			this.commands.add(metc);
		}
		
	}
	public void executeCommand(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			this.mdc.execute(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			this.mpc.execute(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			this.mmc.execute(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			this.mcc.execute(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			this.mrc.execute(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			this.mec.execute(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			this.metc.execute(event);
		}
		
	}
	
	public ArrayList<String> getList(){
		return this.list;
	}
	
	@Override
	public void handle(MouseEvent event) {
		this.addCommand(event);
		this.executeCommand(event);
	}
}