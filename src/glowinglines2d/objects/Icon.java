package glowinglines2d.objects;

import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.vbo.ILineVertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Icon extends Line  {

	public Icon(float pX1, float pY1, float pX2, float pY2, float pLineWidth,
			ILineVertexBufferObject pLineVertexBufferObject) {
		super(pX1, pY1, pX2, pY2, pLineWidth, pLineVertexBufferObject);
		// TODO Auto-generated constructor stub
	}

	/*public Icon(float pX1, float pY1, float pY2, float pLineWidth,
			VertexBufferObjectManager pVertexBufferObjectManager) 
	{
		super(pX1, pY1, pLineWidth, pLineWidth, pVertexBufferObjectManager);
	this.attachChild(new Line(pX1,pY1,pX1+95,pY1,pLineWidth,pVertexBufferObjectManager));
	this.attachChild(new Line(pX1+95,))
	}*/

}
