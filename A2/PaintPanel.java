package ca.utoronto.utm.paint;

import javafx.event.EventHandler;


import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 *  solid  =  fill method
 * outline = double shape
 *
 */
class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private int i = 0;
	private PaintModel model;// slight departure from MVC, because of the way painting works
	private PaintModel model_draw;
	private View view; // So we can talk to our parent or other components of the view
	
	private MouseClicked_Command mcc;
	private MouseDragged_Command mdc;
	private MousePressed_Command mpc;
	private MouseMoved_Command mmc;
	private MouseEntered_Command mec;
	private MouseExited_Command metc;
	private MouseReleased_Command mrc;
	public MouseCommandInvoker mci;
	public EditCommandInvoker medci;
	 // modifies how we interpret input (could be better?)
	//private String mode;
	private Shape shape = new Eraser();
	private String style;
	private String thickness;
	private String edit = "start";
	
	private String colorpicker;
	private String color; // the color we are choosing
	private Circle circle; // the circle we are building
	private Rectangle rectangle;
	private Square square; // the square we are building
	private Polyline polyline;

	private Canvas canvas;
	private Eraser eraser;

	public PaintPanel(PaintModel model, View view) {

		this.canvas = new Canvas(500, 500);
		this.getChildren().add(this.canvas);
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: white"); // was blue

		this.addEventHandler(MouseEvent.ANY, this); 
		this.model = model;
		this.model.addObserver(this);
		//this.shape = null;
		medci = new EditCommandInvoker(this);
		model_draw = new PaintModel(this);
		this.view = view;
		
		
		
		this.mcc = new MouseClicked_Command(this);
		this.mdc = new  MouseDragged_Command(this);
		this.mpc = new  MousePressed_Command(this);
		this.mmc = new  MouseMoved_Command(this);
		this.mec = new  MouseEntered_Command(this);
		this.metc = new  MouseExited_Command(this);
		this.mrc = new  MouseReleased_Command(this);
		this.mci = new MouseCommandInvoker(mcc,mdc,mpc,mmc,mec,metc,mrc);
	}

	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();

		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		// start drawing
		model_draw.addCommand();
		model_draw.drawCommandAll(g);
		
		//medci.addEditCommand();
		//medci.executeAllCommand(g);
	}
		

	@Override
	
	public void update(Observable o, Object arg) {

		// Not exactly how MVC works, but similar.
		this.repaint();
	}

	/**
	 * Controller aspect of this
	 */
	
	public void setEdit(String text) {
		this.edit = text;
	}
	
	public String getEdit() {
		return this.edit;
	}
	
	
	public void setThickness(String thickness) {
		this.thickness = thickness;
	}
	
	public String getThickness() {
		return this.thickness;
	}
	
	public void setDrawStyle(String text) {
		this.style = text;
	}
	public String getDrawStyle() {
		return this.style;
		
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public Shape getCurrentShpae() {
		return this.shape;
	}
	
	public PaintModel getModel() {
		return this.model;
	}
	public PaintModel getModel_draw() {
		return this.model_draw;
	}
	
	//polyline
	public void setPolyline(Polyline polyline) {
		this.polyline = polyline;
	}

	
	public Polyline getPolyline() {
		return this.polyline;
	}
	
	public Circle getCircle() {
		return this.circle;
	}
	public Square getSquare() {
		return this.square;
	}
	
	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	
	public Rectangle getRectangle() {
		return this.rectangle;
	}
	
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	public void setSquare(Square square) {
		this.square = square;

	}
	
	public void seteraser(Eraser e) {
		this.eraser = e;
     
	}
	public Eraser geteraser() {
		return this.eraser;

	}
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String text) {
		this.color = text;
	}

	@Override
	public void handle(MouseEvent event) {
		this.mci.executeCommand(event);
	}
}