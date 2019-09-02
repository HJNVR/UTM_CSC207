package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Parse a file in Version 1.0 PaintSaveFile format. An instance of this class
 * understands the paint save file format, storing information about
 * its effort to parse a file. After a successful parse, an instance
 * will have an ArrayList of PaintCommand suitable for rendering.
 * If there is an error in the parse, the instance stores information
 * about the error. For more on the format of Version 1.0 of the paint 
 * save file format, see the associated documentation.
 * 
 * @author 
 *
 */
public class PaintFileParser {
	private int lineNumber = 0; // the current line being parsed
	private String errorMessage =""; // error encountered during parse
	private PaintModel paintModel; 
	
	
	/**
	 * Below are Patterns used in parsing 
	 */
	private Pattern pEmptyLine = Pattern.compile("^\\s*$");
	// for every r,g,b has to be in range of [0-255]
	
	private Pattern pColor = Pattern.compile("^color:([0-9]|[0-9][0-9]|[0-1][0-9][0-9]|[2][0-4][0-9]|[2][5][0-5]),([0-9]|[0-9][0-9]|[0-1][0-9][0-9]|[2][0-4][0-9]|[2][5][0-5]),"
			+ "([0-9]|[0-9][0-9]|[0-1][0-9][0-9]|[2][0-4][0-9]|[2][5][0-5])");
	private Pattern pWrongColor =  Pattern.compile("(.*)[2][5][6-9],(.*)|(.*)[2][5][6-9](.*)");
	private Pattern pFilled = Pattern.compile("^filled:(true|false)");
	private Pattern pNegativeValue = Pattern.compile("(.*)-(.*)");
	;
	private Pattern pFileStart=Pattern.compile("^PaintSaveFileVersion1.0$");
	private Pattern pFileEnd=Pattern.compile("^EndPaintSaveFile$");

	private Pattern pCircleStart=Pattern.compile("^Circle$");
	private Pattern pCircleEnd=Pattern.compile("^EndCircle$");
	private Pattern pCircleCenter = Pattern.compile("^center:[(]\\d*,\\d*[)]");
	private Pattern pCircleRadius = Pattern.compile("^radius:\\d*");
	// ADD MORE!!
	private Pattern pRectangleStart = Pattern.compile("^Rectangle$");
	private Pattern pRectangleEnd = Pattern.compile("^EndRectangle$");
	private Pattern pRectanglep1 = Pattern.compile("^p1:[(]\\d*,\\d*[)]");
	private Pattern pRectanglep2 = Pattern.compile("^p2:[(]\\d*,\\d*[)]");
	
	private Pattern pSquiggleStart = Pattern.compile("^Squiggle$");
	private Pattern pPointStart = Pattern.compile("^points$");
	private Pattern pPointEnd = Pattern.compile("^endpoints$");
	private Pattern pPointCoordinate = Pattern.compile("^point:[(]\\d*,\\d*[)]");
	private Pattern pSquiggleEnd = Pattern.compile("^EndSquiggle$");
	
	/**
	 * below are some string that will be use for checking index
	 */
	private String lb = "("; // left bracket
	private String comma = ","; // comma
	private String rb = ")"; // right bracket
	private String colon = ":"; // colon
	
	private int getIndexoflb(String l) {
		return l.indexOf(lb);
	}
	
	private int getIndexofcomma(String l) {
		return l.indexOf(comma);
	}
	private int getIndexofrb(String l) {
		return l.indexOf(rb);
	}
	private int getIndexofcolon(String l) {
		return l.indexOf(colon);
	}
	
	/**
	 * below are some functions that will get the details of each shape
	 * @param l the current line of string that get from the loading file
	 * @return
	 */
	private Point convertToPoint(String l) {
		String s = l.substring(this.getIndexoflb(l)+1, this.getIndexofcomma(l));
		int x = Integer.parseInt(s);	
		
		s = l.substring(this.getIndexofcomma(l)+1, this.getIndexofrb(l));
		int y = Integer.parseInt(s);	
		
		Point p = new Point(x,y);
		return p;
	}
	
	private int convertToRadius(String l) {
		String s = l.substring(this.getIndexofcolon(l)+1, l.length());
		int value = Integer.parseInt(s);	
		return value;
	}
	
	private Color converToColor(String l) {
		String s = l.substring(this.getIndexofcolon(l)+1, this.getIndexofcomma(l));
		int r = Integer.parseInt(s);
		
		String subl = l.substring(this.getIndexofcomma(l)+1,l.length());
		s = subl.substring(0,this.getIndexofcomma(subl));
		int g = Integer.parseInt(s);
		
		subl = subl.substring(this.getIndexofcomma(subl)+1, subl.length());
		s = subl.substring(0,subl.length());
		int b = Integer.parseInt(s);
		
		return Color.rgb(r, g, b);
	}
	
	private boolean convertToBoolean(String l) {
		String s = l.substring(this.getIndexofcolon(l)+1, l.length());
		boolean b =  Boolean.parseBoolean(s);
		return b;
	}
	/**
	 * Store an appropriate error message in this, including 
	 * lineNumber where the error occurred.
	 * @param mesg
	 */
	private void error(String mesg){
		this.errorMessage = "Error in line "+lineNumber+" "+mesg;
	}
	
	/**
	 * 
	 * @return the error message resulting from an unsuccessful parse
	 */
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	/**
	 * Parse the inputStream as a Paint Save File Format file.
	 * The result of the parse is stored as an ArrayList of Paint command.
	 * If the parse was not successful, this.errorMessage is appropriately
	 * set, with a useful error message.
	 * 
	 * @param inputStream the open file to parse
	 * @param paintModel the paint model to add the commands to
	 * @return whether the complete file was successfully parsed
	 */
	public boolean parse(BufferedReader inputStream, PaintModel paintModel) {
		this.paintModel = paintModel;
		this.errorMessage="";
		
		// During the parse, we will be building one of the 
		// following commands. As we parse the file, we modify 
		// the appropriate command.
		
		CircleCommand circleCommand = null; 
		RectangleCommand rectangleCommand = null;
		SquiggleCommand squiggleCommand = null;
	
		try {	
			int state=0; Matcher m; String l;
			
			this.lineNumber=0;
			while ((l = inputStream.readLine()) != null){
				
				m = pEmptyLine.matcher(l);
				if(m.matches()) {
				}
				else {
				this.lineNumber++;
				
				System.out.println(lineNumber+" "+l+" "+state);

				// skip white spaces
				l = l.replaceAll("\\s","");
				
				switch(state){
				case 0:		
					m=pFileStart.matcher(l);
					if(m.matches()){
						state=1;
						break;
					}
					error("Expected Start of Paint Save File");
					return false;
					
				case 1: // Looking for the start of a new object or end of the save file
					m=pCircleStart.matcher(l);
					if(m.matches()){
						circleCommand = new CircleCommand(new Point(0,0),0);
						state=2; 
						break; //after break no code will execution
						}
					
					m = pRectangleStart.matcher(l);
					if(m.matches()) { 
						rectangleCommand = new RectangleCommand(new Point(0,0), new Point(0,0));
						state = 3;
						break;
					}
					
					m = pSquiggleStart.matcher(l);
					if(m.matches()) {
						squiggleCommand = new SquiggleCommand();
						state = 4;
						break;
					}
					
					m = pFileEnd.matcher(l);
					if(m.matches()) {
						state = 5;
						break;
					}
					
					error("Expected Name of a New Shape or the End of Paint Save File");
					return false;
					
				case 2:
					// q2: the color of Circle
					m = pNegativeValue.matcher(l);
					if(m.matches()) {
						error("Positive Value Only for the Circle color");
						return false;
					}
					
					m = pWrongColor.matcher(l);
					if(m.matches()) {
						error("Color value in Range[0-255] Only for the Circle color");
						return false;
					}
					
					m = pColor.matcher(l);
					if(m.matches()) {
						Color color = this.converToColor(l);
						circleCommand.setColor(color);
						state = 6;
						break;
					}
					error("Expected Color([0-255])  of Circle");
					return false;
				case 3:
					// q3 the color of the Rectangle
					m = pNegativeValue.matcher(l);
					if(m.matches()) {
						error("Positive Value Only for the Rectangle color");
						return false;
					}
					
					m = pWrongColor.matcher(l);
					if(m.matches()) {
						error("Color value in Range[0-255] Only for the Rectangle color");
						return false;
					}
					
					m = pColor.matcher(l);
					if(m.matches()) {
						Color color = this.converToColor(l);
						rectangleCommand.setColor(color);
						state = 10;
						break;
					}
					error("Expected Color([0-255]) of Rectangle");
					return false;
				
				case 4:
					// q4: the color of Squiggle
					m = pNegativeValue.matcher(l);
					if(m.matches()) {
						error("Positive Value Only for the Squiggle color");
						return false;
					}
					
					m = pWrongColor.matcher(l);
					if(m.matches()) {
						error("Color value in Range[0-255] Only for the Squiggle color");
						return false;
					}
					
					m = pColor.matcher(l);
					if(m.matches()) {
						Color color = this.converToColor(l);
						squiggleCommand.setColor(color);
						state = 14;
						break;
					}
					error("Expected Color([0-255]) of Squiggle");
					break;
				case 5:
					if(l != null) {
						error("Wrong end words");
						return false;
					}
					break;
				case 6:
					// q6: the filled of Circle
					m = pFilled.matcher(l);
					if(m.matches()) {
						boolean fill = this.convertToBoolean(l);
						circleCommand.setFill(fill);
						state = 7;
						break;
					}
					error("Expected Filled Content of Circle");
					return false;
				case 7:
					// q7: the center of Circle
					m = pNegativeValue.matcher(l);
					if(m.matches()) {
						error("Positive Coordinates Only for the Circle Center");
						return false;
					}
					
					m = pCircleCenter.matcher(l);
					if(m.matches()) {
						Point center= this.convertToPoint(l);
						//System.out.println(center.x +":" + center.y);
						
						circleCommand.setCentre(center);
						state = 8;
						break;
					}
					error("Expected Center of Circle");
					return false;
				case 8: 
					//q8: the radius of Circle
					m = pNegativeValue.matcher(l);
					if(m.matches()) {
						error("Positive Value Only for the Circle Radius");
						return false;
					}
					
					m = pCircleRadius.matcher(l);
					if(m.matches()) {
						int radius = this.convertToRadius(l);
						System.out.println(radius);
						
						circleCommand.setRadius(radius);
						
						state = 9;
						break;
					}
					error("Expected Radius of Circle");
					return false;
				case 9:
					// q9: the end word for Circle
					m = pCircleEnd.matcher(l);
					if(m.matches()) {
					//	System.out.println("Circle end word");
						this.paintModel.addCommand(circleCommand);
						state = 1;
						break;
					}
					error("Expected End of Circle");
					return false;
				// circle loop done above
					
				case 10:
					// q3: the filled content of Rectangle
					m = pFilled.matcher(l);
					if(m.matches()) {
						boolean fill = this.convertToBoolean(l);
						rectangleCommand.setFill(fill);
						state = 11;
						break;
					}
					error("Expected filled content  of Rectangle");
					return false;
				case 11:
					//q11: the p1 content of Rectangle
					m = pNegativeValue.matcher(l);
					if(m.matches()) {
						error("Positive Coordinates Only for the Rectangle Points");
						return false;
					}
					
					m = pRectanglep1.matcher(l);
					if(m.matches()) {
						Point p1 = this.convertToPoint(l);
						rectangleCommand.setP1(p1);
						state = 12;
						break;
					}
					error("Expected p1 of Rectangle");
					return false;
				case 12:
					//q12: the p2 content of Rectangle
					m = pNegativeValue.matcher(l);
					if(m.matches()) {
						error("Positive Coordinates Only for the Rectangle Points");
						return false;
					}
					
					m = pRectanglep2.matcher(l);
					if(m.matches()) {
						Point p2 = this.convertToPoint(l);
						rectangleCommand.setP2(p2);
						state = 13;
						break;
					}
					error("Expected p2 of Rectangle");
					return false;
				case 13: 
					//q13: the end word for rectangle
					m = pRectangleEnd.matcher(l);
					if(m.matches()) {
						this.paintModel.addCommand(rectangleCommand);
						state = 1;
						break;
					}
					error("Expected End of Rectangle");
					return false;
				// Rectangle loop done above
					
				case 14:
					//q14: the filled content of Squiggle
					m = pFilled.matcher(l);
					if(m.matches()) {
						boolean fill = this.convertToBoolean(l);
						squiggleCommand.setFill(fill);
						state = 15;
						break;
					}
					error("Expected Filled Content of Squiggle");
					return false;
				case 15: 
					//q15: the points start of Squiggle
					m = pPointStart.matcher(l);
					if(m.matches()) {
						state = 16;
						break;
					}
					error("Expected Start of Squiggle");
					return false;
				case 16: 
					//q16: points coordinate of Squiggle
					m = pNegativeValue.matcher(l);
					if(m.matches()) {
						error("Positive Coordinates Only for the Squiggle Points");
						return false;
					}
					
					m = pPointCoordinate.matcher(l);
					if(m.matches()) {
						Point point = this.convertToPoint(l);
						squiggleCommand.add(point);
						state = 16;
						break;
					}
					m = pPointEnd.matcher(l);
					if(m.matches()) {
						state = 17;
						break;
					}
					error("Expected Points Coordinate or End of points");
					return false;
				case 17:
					//q17: Squiggle end words
					m = pSquiggleEnd.matcher(l);
					if(m.matches()) {
						this.paintModel.addCommand(squiggleCommand);
						state = 1;
						break;
					}
					error("Expected End of Squiggle");
					return false;
					
					
			}
				}
			}if(state != 5) {
				//
				lineNumber++; 
				error("Expected End of File");
				return false;
			}
		}  catch (Exception e){
			
		}
		return true;
	}
}
