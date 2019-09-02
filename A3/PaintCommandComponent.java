package ca.utoronto.utm.paint;

public interface PaintCommandComponent {
	public void accept(PaintCommandVisitor paintCommandVisitor);
}
