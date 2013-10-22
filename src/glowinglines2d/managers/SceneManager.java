//--------------------------------------------------------------------------
//�������� ����, ����� ���� ����������� �����
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
	//����� ����� ����� � ����
	//-------------------------------------------------------
	private BaseScene splashScene; //�����
	private BaseScene menuScene; //����
	private BaseScene loadingScene;//��������
	public BaseScene gameScene;//����
	private BaseScene secondMenuScene;
	private BaseScene levelSelectScene;
	private boolean mainMenuSceneDisposed = false;
	//-------------------------------------------------------
	
	//-------------------------------------------------------
	//���������� � �������
	//-------------------------------------------------------
	private static final SceneManager INSTANCE = new SceneManager();
	private SceneType currentSceneType = SceneType.SCENE_SPLASH; //������ ��������� ����� �� �����
	private BaseScene currentScene; 
	private Engine engine = ResourceManager.getInstance().engine;
	
	//��� �����
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
	//������ ������
	//---------------------------------------------------------
	public void setScene (BaseScene scene)//������� �������� � ��������� �������� ����� ������� ����� ����������
	{
		engine.setScene(scene); //����� ������ ������ �����
		currentScene = scene; //������ ������� ����� �� �� ������� ��������
		currentSceneType = scene.getSceneType(); //������ ��� ������� ����� �� ��� ������� �������� 
	}
	
	//������ �����, �� ������ ��� ������� ��� �����, � �� �������� ��� � ������� ����
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
	//����������� � ����������� :))))
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
	//�����
	//---------------------------------------------------------------------------------
	public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback)
	{
		ResourceManager.getInstance().loadSplashScreen();//��������� �������� ���. ������
		splashScene = new SplashScene(); //��������������
		currentScene = splashScene; //������ ������� �����
		pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
	}
	
	private void disposeSplashScene()
	{
		ResourceManager.getInstance().unloadSplashScreen(); //�������� ��������
		splashScene.disposeScene(); //�����������
		splashScene = null;
	}
	
	private void disposeMenuScene()
	{
		ResourceManager.getInstance().unloadMenuTextures(); //�������� ��������
		menuScene.disposeScene(); //�����������
	}
	
	private void disposeSecondMenuScene() {
		// TODO Auto-generated method stub
		
	}
	//-------------------------------------------------------------------------------------
	
	//-------------------------------------------------------------------------------------
	//����� ����
	//-------------------------------------------------------------------------------------
	public void createMenuScene()
	{
	    ResourceManager.getInstance().loadMenuResources(); //�������� ��������, ����� ��������� � ResourceManager
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
	

	//----------------������� �����
	public void loadGameScene(final Engine mEngine)
	{
		setScene(loadingScene); // �� ����� �������� ������� �����, ������ ����������� �����
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
	
	//�������� ����� ���� ��� ��������
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
