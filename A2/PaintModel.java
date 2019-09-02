package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;import javafx.scene.canvas.GraphicsContext;
//Create the model for the MVC pattern algorithm
public class PaintModel extends Observable {
	
	
	public PaintModel() {
		
	}
	
	// invoker
	private PaintPanel paintpanel;
	private DrawCircleCommand dcc;
	private DrawSquiggleCommand dsc;
	private DrawRectangleCommand drc;
	private DrawSquareCommand dsqc;
	private DrawEraserCommand dec;
	private DrawPolylineCommand dpc;
	//calls the commands to draw the shapes
	public PaintModel(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
		this.dcc = new DrawCircleCommand(paintpanel,paintpanel.getModel());
		this.dsc = new DrawSquiggleCommand(paintpanel,paintpanel.getModel());
		this.drc = new DrawRectangleCommand(paintpanel, paintpanel.getModel());
		this.dsqc = new DrawSquareCommand(paintpanel, paintpanel.getModel());
		this.dec = new DrawEraserCommand(paintpanel, paintpanel.getModel());
		this.dpc = new DrawPolylineCommand(paintpanel, paintpanel.getModel());
	}
	private ArrayList<DrawingCommand> commands = new ArrayList<DrawingCommand>();
	//Create different shapes according to the given shape
	public void addCommand() {
		if(this.paintpanel.getCurrentShpae().getName() == "Circle") {
			commands.add(dcc);
		}else if(this.paintpanel.getCurrentShpae().getName() == "Squiggle") {
			commands.add(dsc);
		}else if(this.paintpanel.getCurrentShpae().getName() == "Rectangle") {
			commands.add(drc);
		}else if(this.paintpanel.getCurrentShpae().getName() == "Square") {
			commands.add(dsqc);
		}else if(this.paintpanel.getCurrentShpae().getName() == "Eraser") {
			commands.add(dec);
		}else if(this.paintpanel.getCurrentShpae().getName() == "Polyline") {
			commands.add(dpc);
		}
	}
	
	public ArrayList<DrawingCommand> getCommands(){
		return commands;
	}
	//draw all the shapes with the given command
	public void drawCommandAll(GraphicsContext g) {
		for(DrawingCommand dc: commands) {
			dc.draw(g);
		}
	}
    private Circle c_realtime = new Circle();
    private Rectangle r_realtime = new Rectangle();
    private Square s_realtime = new Square();
  
    private ArrayList<Point>  line_realtime = new ArrayList<Point>();
    
	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<ArrayList<Point>> squiggles = new ArrayList<ArrayList<Point>>();
	
	private ArrayList<Polyline> polylines = new ArrayList<Polyline>();
	
	private ArrayList<Circle> circles = new ArrayList<Circle>();
	private ArrayList<Circle> circles_solid = new ArrayList<Circle>();
	private ArrayList<Circle> circles_outline = new ArrayList<Circle>();
	
	private ArrayList<Square> square = new ArrayList<Square>();
	private ArrayList<Square> square_outline = new ArrayList<Square>();
	private ArrayList<Square> squares_solid = new ArrayList<Square>();
	
	private ArrayList<Rectangle> rectangle = new ArrayList<Rectangle>();
	private ArrayList<Rectangle> rectangle_solid = new ArrayList<Rectangle>();
	private ArrayList<Rectangle> rectangle_outline = new ArrayList<Rectangle>();
	
	private ArrayList<Eraser> Clear = new ArrayList<Eraser>();
	
	// polylines
	public void addpolyline(Polyline p) {
		this.polylines.add(p);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Polyline> getPolylines() {
		return polylines;
	}
	
	public void addPoint(Point p) {
		this.points.add(p);
		this.setChanged();
		this.notifyObservers();
	}
	
	// squiggles
	
	
	public ArrayList<Point> getPoints() {
		return points;
	}
	
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addline_realtime(Point point) {
		this.line_realtime.add(point);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setline_realtime(ArrayList<Point> points) {
		this.line_realtime = points;
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Point> getLine_times() {
		return this.line_realtime;
	}
	
	public void addSquiggle(ArrayList<Point> points) {
		this.squiggles.add(points);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<ArrayList<Point>> getSquigles() {
		return squiggles;
	}

	// circle
	public void addCircle(Circle c) {
		this.circles.add(c);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Circle> getCircles() {
		return circles;
	}
	public void addCircle_solid(Circle c) {
		this.circles_solid.add(c);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Circle> getCircles_solid() {
		return circles_solid;
	}
	
	public void addCircle_outline(Circle c) {
		this.circles_outline.add(c);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Circle> getCircles_outline() {
		return circles_outline;
	}
	
	
	
	public void changeCircle_realtime(Circle c) {
		this.c_realtime = c;
		this.setChanged();
		this.notifyObservers();
	}
	
	public Circle getC_realtime() {
		return c_realtime;
	}

	// square
	public void addSquare(Square s){
		this.square.add(s);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Square> getSquare(){
		return square;
	}
	
	public void changeSquare_realtime(Square square) {
		this.s_realtime = square;
		this.setChanged();
		this.notifyObservers();
	}
	
	public Square getS_realtime() {
		return this.s_realtime;
	}
	
	public void addSquare_outline(Square square) {
		this.square_outline.add(square);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Square> getSquare_outline() {
		return square_outline;
	}
	
	
	public void addSquare_solid(Square square) {
		this.squares_solid.add(square);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Square> getSquare_solid() {
		return squares_solid;
	}
	 
	
	// rectagnle
	public void addRectangle(Rectangle r){
		this.rectangle.add(r);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Rectangle> getRectangle(){
		return rectangle;
	}
	
	public void addRectangle_outline(Rectangle r){
		this.rectangle_outline.add(r);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Rectangle> getRectangle_outline(){
		return rectangle_outline;
	}
	public void changeRectangle_realtime(Rectangle r) {
		this.r_realtime = r;
		this.setChanged();
		this.notifyObservers();
	}
	
	public Rectangle getR_realtime() {
		return r_realtime;
	}
	
	public void addRectangle_solid(Rectangle r){
		this.rectangle_solid.add(r);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Rectangle> getRectangle_solid(){
		return rectangle_solid;
	}
	
	public void add_clear(Eraser eraser){
		this.Clear.add(eraser);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Eraser> getclearspace(){
		return Clear;
	}
}
