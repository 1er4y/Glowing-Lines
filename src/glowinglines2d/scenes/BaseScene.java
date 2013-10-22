//-----------------------------------------------------------------------------
//ÁÓÊÂÀËÜÍÎ, ÎÑÍÎÂÍÀß ÑÖÅÍÀ, ÑÊÎĞÅÅ ÂÑÅÃÎ, ×ÒÎ ÒÎ ÒÈÏÀ ØÀÁËÎÍÀ ÑÖÅÍÛ, ÁÎËÂÀÍÊÀ
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
	//ÎÁÚÅÊÒÛ, ÊÀÊ ÂÑÅÃÄÀ Ñ ÌÀËÅÍÜÊÎÉ ÁÓÊÂÛ
	//-----------------------------------------------------------
	protected Engine engine;
	protected GameActivity activity;
	protected BoundCamera camera;
	protected ResourceManager resourceManager;
	protected VertexBufferObjectManager vbo;
	//-----------------------------------------------------------
	
	//-----------------------------------------------------------
	//ÊÎÍÑÒĞÓÊÒÎĞ
	//-----------------------------------------------------------
	public BaseScene ()
	{
		//ïîëó÷àåì ñòàòóñ íóæíûõ îáúåêòîâ ÷åğåç ìåíåäæåğà
		this.resourceManager = ResourceManager.getInstance();
		this.engine = resourceManager.engine;
		this.activity = resourceManager.activity;
		this.camera = resourceManager.camera;
		this.vbo = resourceManager.vbo;
		
		createScene(); //ñîçäàåì ñöåíó
	}
	
	//------------------------------------------------------------
	
	//------------------------------------------------------------
	//ÍÅÎÁÕÎÄÈÌÛÅ È ÁÀÇÎÂÛÅ ÔÓÍÊÖÈÈ ÄËß ËŞÁÎÉ ÀÁÑÎËŞÒÍÎ ÑÖÅÍÛ
	//------------------------------------------------------------
	public abstract void createScene();
	public abstract void onBackKeyPressed();
	public abstract SceneType getSceneType();
	public abstract void disposeScene();
	//------------------------------------------------------------
	
	
}
