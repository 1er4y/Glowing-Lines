//СЦЕНА ЗАГРУЗКИ
package glowinglines2d.scenes;

import org.andengine.entity.sprite.Sprite;

//--------------------------------------------------------------------------
//ИНТРО
//--------------------------------------------------------------------------

public class SplashScene extends BaseScene
{
	//---------------------------------------------
	//ОБЪЕКТ
	//--------------------------------------------
	private Sprite splash;
	//----------------------------------------------
	
	//-------------------------------------------------
	//ЛОГИКА КЛАССА
	//-------------------------------------------------
	
	@Override
	public void createScene() //создание сцены 
	{
		splash = new Sprite(0,0, resourceManager.splashRegion,vbo); //создание спрайта
		//расстановка размеров, позиции и аттач
		splash.setScale(1f);
	
		splash.setPosition(640,360);
		
		attachChild(splash);
	}

	@Override
	public void onBackKeyPressed()
	{
		
	}

	@Override
	public glowinglines2d.managers.SceneManager.SceneType getSceneType()
	{
		//установка типа сцены 
		return glowinglines2d.managers.SceneManager.SceneType.SCENE_SPLASH;
	}

	@Override
	public void disposeScene()
	{	
		//уничтожение интро
		splash.detachSelf();
	    splash.dispose();
	    this.detachSelf();
	    this.dispose();
	}

}
