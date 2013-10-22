//-----------------------------------------------------------------------------
//���������, �������� �����, ������ �����, ��� �� ���� ������� �����, ��������
//-----------------------------------------------------------------------------
package glowinglines2d.scenes;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import glowinglines2d.activity.GameActivity;
import glowinglines2d.managers.ResourceManager;
import glowinglines2d.managers.SceneManager.SceneType;

public abstract class BaseScene extends Scene 
{
	//-----------------------------------------------------------
	//�������, ��� ������ � ��������� �����
	//-----------------------------------------------------------
	protected Engine engine;
	protected GameActivity activity;
	protected BoundCamera camera;
	protected ResourceManager resourceManager;
	protected VertexBufferObjectManager vbo;
	//-----------------------------------------------------------
	
	//-----------------------------------------------------------
	//�����������
	//-----------------------------------------------------------
	public BaseScene ()
	{
		//�������� ������ ������ �������� ����� ���������
		this.resourceManager = ResourceManager.getInstance();
		this.engine = resourceManager.engine;
		this.activity = resourceManager.activity;
		this.camera = resourceManager.camera;
		this.vbo = resourceManager.vbo;
		
		createScene(); //������� �����
	}
	
	//------------------------------------------------------------
	
	//------------------------------------------------------------
	//����������� � ������� ������� ��� ����� ��������� �����
	//------------------------------------------------------------
	public abstract void createScene();
	public abstract void onBackKeyPressed();
	public abstract SceneType getSceneType();
	public abstract void disposeScene();
	//------------------------------------------------------------
	
	
}
