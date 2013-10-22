package glowinglines2d.objects;

import glowinglines2d.managers.ResourceManager;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Packets extends AnimatedSprite {

	int K = 0;
	private float speed = 0.1f;
	private float damage = 0.1f;
	
	public Packets(float pX, float pY, VertexBufferObjectManager vbo,int N) {
		
		super(pX, pY, ResourceManager.getInstance().packetsRegion, vbo);
		
		K=N;
	}
	
	public void setSpeed(float speed1)
	{
		speed=speed1;
	}
	
	public float getSpeed()
	{
		return speed;
	}
	
	public void setDamage(float damage1)
	{
		damage=damage1;
	}
	
	public float getDamage()
	{
		return damage;
	}
}
