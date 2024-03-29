package ca.utoronto.utm.paint;
import java.util.Observable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class PaintCommand extends Observable implements PaintCommandComponent{
	private Color color;
	private boolean fill;
	PaintCommand(){
		// Pick a random color for this
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b= (int)(Math.random()*256);
		
		this.color = Color.rgb(r, g, b);
		
		this.fill = (1==(int)(Math.random()*2));
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isFill() {
		return fill;
	}
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	public String toString(){
		double r = this.color.getRed();
		double g = this.color.getGreen();
		double b = this.color.getBlue();
		
		String s = "";
		// first try * 255 and round
		s+="\tcolor:"+r+","+g+","+b+"\n";
		s+="\tfilled:"+this.fill+"\n";
		return s;
	}
	 
	// helper function 
	public abstract String getName();
	
	// helper function
	public abstract String getDetails();
	
	//helper function
	public String getColorDetail() {
		double r = this.color.getRed();
		double g = this.color.getGreen();
		double b = this.color.getBlue();
		
		String s = "";
		s+="\tcolor:"+(int)(r*255)+","+(int)(g*255)+","+(int)(b*255)+"\n";
		s+="\tfilled:"+this.fill+"\n";
		return s;
		
	}
	
	public void accept(PaintCommandVisitor paintCommandVisitor) {
		paintCommandVisitor.visit(this);
	}
	
	public abstract void execute(GraphicsContext g);
}
