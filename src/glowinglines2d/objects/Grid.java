package glowinglines2d.objects;

import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.vbo.ILineVertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Grid extends Line{

	public Grid(float X1, float Y1, float X2, float Y2, float lineWidth, float pWidth, float pHeight, float pRed, float pGreen, float pBlue, VertexBufferObjectManager vbo) 
	{
		super(X1, Y1, X2, Y2, lineWidth,vbo);
		
		this.setColor(0.5f, 0.5f, 0.5f);
        int cont = 0;
	       
        while(cont < pWidth){
                cont += 32;
                this.attachChild(new Line(cont, 0, cont, pHeight,vbo));
                this.getLastChild().setColor(pRed, pGreen, pBlue);
                this.getLastChild().setAlpha(0.1f);
        }
       
        cont = 0;
        while (cont < pHeight){
                cont += 32;
                this.attachChild(new Line(0, cont, pWidth, cont,vbo));
                this.getLastChild().setColor(pRed, pGreen, pBlue);
                this.getLastChild().setAlpha(0.1f);
        }
		// TODO Auto-generated constructor stub
	}
}
