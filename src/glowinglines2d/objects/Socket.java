package glowinglines2d.objects;

import glowinglines2d.managers.ResourceManager;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Socket extends AnimatedSprite{

	//int[] indexs = new int[20];
	//конструктор
	int K = 0;
	public Socket(float pX, float pY, VertexBufferObjectManager vbo,int N) {
		
		super(pX, pY, ResourceManager.getInstance().socketTextureRegion, vbo);
		
		K=N;
	}
    
	private boolean state = false;
	public void swap ()
	{
	
	}
	public int getIndex()
	{
		return K;
	}
	
	public void socket_selected()
	{
		state=true;
	}
	
	public void socket_unselected()
	{
		state=false;
	}
	
	public boolean socket_state()
	{
		return state;
	}
	
	

}
