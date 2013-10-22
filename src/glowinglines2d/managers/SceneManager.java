//--------------------------------------------------------------------------
//МЕНЕДЖЕР СЦЕН, ЧЕРЕЗ НЕГО УПРАВЛЯЮТСЯ СЦЕНЫ
//--------------------------------------------------------------------------
package glowinglines2d.managers;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import glowinglines2d.scenes.BaseScene;
import glowinglines2d.scenes.GameScene;
import glowinglines2d.scenes.LoadingScene;
import glowinglines2d.scenes.MainMenuScene;
import glowinglines2d.scenes.SecondMenuScene;
import glowinglines2d.scenes.SelectLevelScene;
import glowinglines2d.scenes.SplashScene;

public class SceneManager 
{
	//-------------------------------------------------------
	//КАКИЕ СЦЕНЫ БУДУТ В ИГРЕ
	//-------------------------------------------------------
	private BaseScene splashScene; //интро
	private BaseScene menuScene; //меню
	private BaseScene loadingScene;//загрузка
	public BaseScene gameScene;//игра
	private BaseScene secondMenuScene;
	private BaseScene levelSelectScene;
	private boolean mainMenuSceneDisposed = false;
	//-------------------------------------------------------
	
	//-------------------------------------------------------
	//ПЕРЕМЕННЫЕ И ОБЪЕКТЫ
	//-------------------------------------------------------
	private static final SceneManager INSTANCE = new SceneManager();
	private SceneType currentSceneType = SceneType.SCENE_SPLASH; //ставим начальную сцену на интро
	private BaseScene currentScene; 
	private Engine engine = ResourceManager.getInstance().engine;
	
	//тип сцены
	public enum SceneType
	{
		SCENE_SPLASH,
		SCENE_MENU,
		SCENE_LOADING,
		SCENE_GAME,
		SCENE_SECONDMENUSCENE,
		SCENE_LEVELSELECT
	}
	
	//---------------------------------------------------------
	//ЛОГИКА КЛАССА
	//---------------------------------------------------------
	public void setScene (BaseScene scene)//функция получает в параметры название сцены которую нужно установить
	{
		engine.setScene(scene); //через движок ставит сцену
		currentScene = scene; //меняет текущую сцену на ту которую передали
		currentSceneType = scene.getSceneType(); //меняет ТИП текущей сцены на ТИП которой передали 
	}
	
	//меняет сцену, но только уже получив ТИП СЦЕНЫ, а не НАЗВАНИЕ как в функции выше
	public void setScene (SceneType sceneType)
	{
		switch (sceneType)
		{
		case SCENE_MENU:
			setScene(menuScene); 
			break;
		case SCENE_GAME:
			setScene(gameScene);
			break;
		case SCENE_LOADING:
			setScene(loadingScene);
			break;
		case SCENE_SPLASH:
			setScene(splashScene);
			break;
		case SCENE_SECONDMENUSCENE:
			setScene(secondMenuScene);
			break;
		case SCENE_LEVELSELECT:
			setScene(levelSelectScene);
			break;
		default:
			break;
		}
	}
	
	//---------------------------------------------------------------------------------
	
	//--------------------------------------------------------------------------------
	//УСТАНОВЩИКИ И ДОЗНАВАТЕЛИ :))))
	//---------------------------------------------------------------------------------
	public static SceneManager getInstance()
	{
		return INSTANCE;
	}
	
	public SceneType getCurrentSceneType()
	{
		return currentSceneType;
	}
	
	public BaseScene getCurrentScene()
	{
		return currentScene;
	}
	//---------------------------------------------------------------------------------
	
	//---------------------------------------------------------------------------------
	//ИНТРО
	//---------------------------------------------------------------------------------
	public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback)
	{
		ResourceManager.getInstance().loadSplashScreen();//процедура загрузки нач. экрана
		splashScene = new SplashScene(); //инициализируем
		currentScene = splashScene; //меняем текущую сцену
		pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
	}
	
	private void disposeSplashScene()
	{
		ResourceManager.getInstance().unloadSplashScreen(); //выгрузка ресурсов
		splashScene.disposeScene(); //уничтожение
		splashScene = null;
	}
	
	private void disposeMenuScene()
	{
		ResourceManager.getInstance().unloadMenuTextures(); //выгрузка ресурсов
		menuScene.disposeScene(); //уничтожение
	}
	
	private void disposeSecondMenuScene() {
		// TODO Auto-generated method stub
		
	}
	//-------------------------------------------------------------------------------------
	
	//-------------------------------------------------------------------------------------
	//СЦЕНА МЕНЮ
	//-------------------------------------------------------------------------------------
	public void createMenuScene()
	{
	    ResourceManager.getInstance().loadMenuResources(); //загрузка ресурсов, через обращение к ResourceManager
	    menuScene = new MainMenuScene(); 
	    loadingScene = new LoadingScene();
	    SceneManager.getInstance().setScene(menuScene);
	    disposeSplashScene(); 
	}
	
	public void loadSecondMenuScene()
	{
		ResourceManager.getInstance().loadsecondMenuSceneResources();
		secondMenuScene = new SecondMenuScene();
		SceneManager.getInstance().setScene(secondMenuScene);
		if (mainMenuSceneDisposed == false)
		{
			disposeMenuScene();
			mainMenuSceneDisposed = true;
		}
		
	}
	
	public void loadLevelSelectScene()
	{
		ResourceManager.getInstance().loadLevelSelectResources();
		levelSelectScene = new SelectLevelScene();
		SceneManager.getInstance().setScene(levelSelectScene);
		//disposeSecondMenuScene();
	}
	

	//----------------ИГРОВАЯ СЦЕНА
	public void loadGameScene(final Engine mEngine)
	{
		setScene(loadingScene); // на время загрузки игровой сцены, ставим загрузочный экран
		ResourceManager.getInstance().unloadMenuTextures();
		disposeMenuScene();
		mEngine.registerUpdateHandler(new TimerHandler(0.1f,new ITimerCallback()
		{
			public void onTimePassed (final TimerHandler pTimerHandler)
			{
				mEngine.unregisterUpdateHandler(pTimerHandler);
				ResourceManager.getInstance().loadGameResources();
				gameScene = new GameScene();
				setScene(gameScene);
			}
		}));
	}
	
	//загрузка сцены меню при переходе
	public void loadMenuScene(final Engine mEngine)
	{
		//setScene(loadingScene);
		//ResourceManager.getInstance().unloadGameTextures();
		//gameScene.disposeScene();
		mEngine.registerUpdateHandler(new TimerHandler(0.1f,new ITimerCallback()
		{
			public void onTimePassed (final TimerHandler pTimerHandler)
			{
				mEngine.unregisterUpdateHandler(pTimerHandler);
				ResourceManager.getInstance().loadMenuTextures();
				menuScene.createScene();
				setScene(menuScene);
				mainMenuSceneDisposed = false;
			}
		}));
	}
	
}
