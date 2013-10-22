package glowinglines2d.activity;
//ОСНОВНОЙ КЛАСС ИГРЫ. Активити. С него запускается игра и движок.

import glowinglines2d.managers.ResourceManager;
import glowinglines2d.managers.SceneManager;

import java.io.IOException;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FixedResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.WindowManager;



//----------------------------------------------------------------------
//ВАЖНАЯ ЗАМЕТКА: ИМЕНА ПЕРЕМЕННЫХ ИЛИ ОБЪЕКТОВ ПИШИ С мАЛЕНЬКОЙ буквы, обязательно!
//------------------------------------------------------------------------------------
public class GameActivity extends BaseGameActivity
{
	//------------------------------------------------------------------
	//ОБЪЕКТЫ
	//------------------------------------------------------------------
	private BoundCamera camera; //камера
	
	//------------------------------------------------------------------
	
	//------------------------------------------------------------------
	//ПЕРЕМЕННЫЕ
	//------------------------------------------------------------------
	protected static int CAMERA_WIDTH = 1280; //ширина камеры
	protected static int CAMERA_HEIGHT = 720; //высота камеры, не путать с размерами экрана
	protected static int DEFAULT_WIDTH; //переменная для определения ширины экрана в пикселях
	public static int DEFAULT_HEIGHT; //переменная для определения высоты экрана в пикселях
	protected static double width; //переменная ширины для умножения на 1.777777777 
	protected static int RESULT_WIDTH; //переменная для конечного значения ширины экрана
	
	
	//------------------------------------------------------------------

	
	//------------------------------------------------------------------
	//ЛОГИКА КЛАССА
	//------------------------------------------------------------------

	//Опции движка
	@Override
	public EngineOptions onCreateEngineOptions() 
	{
		//получение разрешения экрана
		final DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		wm.getDefaultDisplay().getRotation();
		//--------------------------------------
		
		DEFAULT_WIDTH = displayMetrics.widthPixels; //запоминание ширины
		DEFAULT_HEIGHT = displayMetrics.heightPixels; //высоты
		
		width = DEFAULT_HEIGHT * 1.7777777; //преобразование в нужную ширину
		RESULT_WIDTH = (int) width; //выделение целой части и результирующая ширина
		
		this.camera = new BoundCamera(0,0,CAMERA_WIDTH,CAMERA_HEIGHT); //установка и инициализация камеры
		
		EngineOptions engineOptions = new EngineOptions(true,ScreenOrientation.LANDSCAPE_FIXED, new FixedResolutionPolicy(RESULT_WIDTH,DEFAULT_HEIGHT), this.camera);
		//сверху, опции движка, ориентация экрана, опции разрешения и камера
		
		engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true); //установка звуковых опции
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON); //хрен значает что это устанавливает, что то связанное с блокировкой экрана
		return engineOptions; //возвращаем опции
	}
	
	//функция которая возвращает нас на предыдущую сцену при нажатии кнопки "назад"
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{  
	    if (keyCode == KeyEvent.KEYCODE_BACK)
	    {
	        SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
	    }
	    return false; 
	}
	//начало работы движка

	//ресурсы, которые нужно загрузить при запуске движка
	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws IOException 
	{
		ResourceManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager()); //передаем в Менеджер движок, активити, камеру и ВБО чтобы Менеджер загрузил в них все необходимые ресурсы
		pOnCreateResourcesCallback.onCreateResourcesFinished();//делаем это когда загрузка завершена
		
	}

	//код, который нужно выполнить при созданий сцены
	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws IOException 
	
	{
		SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback); //сразу запускаем интро
	}

	//ставим таймер, показываем интро пока грузится сцена меню
	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException 
	{
		mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
	    {
	        public void onTimePassed(final TimerHandler pTimerHandler) 
	        {
	            mEngine.unregisterUpdateHandler(pTimerHandler);
	            SceneManager.getInstance().createMenuScene();
	        }
	    }));
	    pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	
	//уничтожение и выход
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		System.exit(0);	
	}
	
}