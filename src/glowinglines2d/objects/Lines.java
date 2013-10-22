package glowinglines2d.objects;

import glowinglines2d.managers.ResourceManager;

import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.LineLoop;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public  class Lines extends Line{

	public Lines(int pX1, int pY1, int pX2, int pY2,
			VertexBufferObjectManager vbo) {
		super(pX1, pY1, pX2, pY2, ResourceManager.getInstance().vbo);
		// TODO Auto-generated constructor stub
	}
	
	static VertexBufferObjectManager vbo = ResourceManager.getInstance().vbo;
	public static int numbOfLines;

	static int socketXcoor;
	static int socketYcoor;
	public static void getValue(int N1)
	{
		numbOfLines = N1;
	}
	
	
	public static LineLoop generateLine(int[] arrayX, int[]arrayY,int numbOfLine,int numbOfPoints, int indexOfLine,int XNext,int firstYCoordinate,int secondYCoordinate,
			int firstXCoordinate, float cRed, float cGreen, float cBlue) //ג xml פאיכו - "1" ג כוגוכ 
	{
		
		int lastX;
		int lastY;
		int newX;
		int newY;
		
		newY = secondYCoordinate;
		newX = firstXCoordinate;
		
		int i = 0;
		LineLoop two;
		two = new LineLoop(firstXCoordinate, firstYCoordinate,5f,arrayX.length*2+1, vbo, DrawType.DYNAMIC);
		
		two.add(firstXCoordinate, secondYCoordinate);
		
		two.setColor(cRed,cGreen,cBlue);
		
		for (i=0;i<numbOfPoints;i++)
		{
			lastX=newX+arrayX[i];
			lastY=newY;
			two.add(lastX, lastY);
			
			lastX=newX;
			lastY=newY+arrayY[i];
			two.add(lastX, lastY);
			
		}
	
			return two;
	}
	
}
