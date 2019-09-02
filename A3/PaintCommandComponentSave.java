package ca.utoronto.utm.paint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import javafx.stage.FileChooser;

public class PaintCommandComponentSave implements PaintCommandVisitor {
	
	private View view;
	private File file;
	private PrintWriter writer1;
	public PaintCommandComponentSave(File file, View view) {
		this.file = file;
		this.view = view;
	}
	
	/**
	 * Save the given paintModel to the open file
	 * @param paintModel
	 */
	public void save(PaintModel paintModel) {
		
		if (this.file != null) {
			// This is where a real application would open the file.
			System.out.println("Saving: " + file.getName() + "." + "\n");
			// Add something like the following...
			try {
				writer1 = new PrintWriter(file);
				writer1.write("Paint Save File Version 1.0" + "\n");
				if (paintModel.getCommands().size() >0) {
					for(PaintCommand paintcommand: paintModel.getCommands()) {
						paintcommand.accept(this);
					}
				}
				writer1.write("End Paint Save File" + "\n");
				writer1.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Save command cancelled by user." + "\n");
		}
			
		}
	
	/**
	 * read from specific file and draw shapes
	 */
	public void open() {
		if (file != null) {
			System.out.println("Opening: " + file.getName() + "." + "\n");
			String path = file.getPath();
			BufferedReader bufferedReader = null; 
				try {
					bufferedReader = new BufferedReader(new FileReader(path));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			PaintModel paintModel = new PaintModel();
			PaintFileParser parser = new PaintFileParser();
			parser.parse(bufferedReader,  paintModel);
			// to see error message
			System.out.println(parser.getErrorMessage());
			
			this.view.setPaintModel(paintModel);
		} else {
			System.out.println("Open command cancelled by user." + "\n");
		}
	}
	
	
	@Override
	public void visit(PaintCommand paintCommand) {
		this.writer1.write(paintCommand.getName() + "\n");
		this.writer1.write(paintCommand.getColorDetail());
		this.writer1.write(paintCommand.getDetails());
		this.writer1.write("End " + paintCommand.getName() + "\n");
		
	}
	@Override
	public void visit(CircleCommand circleCommand) {
		this.writer1.write(circleCommand.getName() + "\n");
		this.writer1.write(circleCommand.getColorDetail());
		this.writer1.write(circleCommand.getDetails());
		this.writer1.write("End " + circleCommand.getName() + "\n");
		
	}
	@Override
	public void visit(RectangleCommand rectangleCommand) {
		this.writer1.write(rectangleCommand.getName() + "\n");
		this.writer1.write(rectangleCommand.getColorDetail());
		this.writer1.write(rectangleCommand.getDetails());
		this.writer1.write("End " + rectangleCommand.getName() + "\n");
		
	}
	@Override
	public void visit(SquiggleCommand squiggleCommand) {
		this.writer1.write(squiggleCommand.getName() + "\n");
		this.writer1.write(squiggleCommand.getColorDetail());
		this.writer1.write(squiggleCommand.getDetails());
		this.writer1.write("End " + squiggleCommand.getName() + "\n");
		
	}
	
	
}
