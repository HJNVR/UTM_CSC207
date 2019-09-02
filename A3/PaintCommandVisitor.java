package ca.utoronto.utm.paint;
/**
 * Visitot pattern
 * @author Starkjing
 *
 */
public interface PaintCommandVisitor {
	public void visit(PaintCommand paintCommand);
	public void visit(CircleCommand circleCommand);
	public void visit(RectangleCommand rectangleCommand);
	public void visit(SquiggleCommand squiggleCommand);
}
