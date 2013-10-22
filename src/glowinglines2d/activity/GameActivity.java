package glowinglines2d.activity;
//�������� ����� ����. ��������. � ���� ����������� ���� � ������.

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
//������ �������: ����� ���������� ��� �������� ���� � ��������� �����, �����������!
//------------------------------------------------------------------------------------
public class GameActivity extends BaseGameActivity
{
	//------------------------------------------------------------------
	//�������
	//------------------------------------------------------------------
	private BoundCamera camera; //������
	
	//------------------------------------------------------------------
	
	//------------------------------------------------------------------
	//����������
	//------------------------------------------------------------------
	protected static int CAMERA_WIDTH = 1280; //������ ������
	protected static int CAMERA_HEIGHT = 720; //������ ������, �� ������ � ��������� ������
	protected static int DEFAULT_WIDTH; //���������� ��� ����������� ������ ������ � ��������
	public static int DEFAULT_HEIGHT; //���������� ��� ����������� ������ ������ � ��������
	protected static double width; //���������� ������ ��� ��������� �� 1.777777777 
	protected static int RESULT_WIDTH; //���������� ��� ��������� �������� ������ ������
	
	
	//------------------------------------------------------------------

	
	//------------------------------------------------------------------
	//������ ������
	//------------------------------------------------------------------

	//����� ������
	@Override
	public EngineOptions onCreateEngineOptions() 
	{
		//��������� ���������� ������
		final DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		wm.getDefaultDisplay().getRotation();
		//--------------------------------------
		
		DEFAULT_WIDTH = displayMetrics.widthPixels; //����������� ������
		DEFAULT_HEIGHT = displayMetrics.heightPixels; //������
		
		width = DEFAULT_HEIGHT * 1.7777777; //�������������� � ������ ������
		RESULT_WIDTH = (int) width; //��������� ����� ����� � �������������� ������
		
		this.camera = new BoundCamera(0,0,CAMERA_WIDTH,CAMERA_HEIGHT); //��������� � ������������� ������
		
		EngineOptions engineOptions = new EngineOptions(true,ScreenOrientation.LANDSCAPE_FIXED, new FixedResolutionPolicy(RESULT_WIDTH,DEFAULT_HEIGHT), this.camera);
		//������, ����� ������, ���������� ������, ����� ���������� � ������
		
		engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true); //��������� �������� �����
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON); //���� ������� ��� ��� �������������, ��� �� ��������� � ����������� ������
		return engineOptions; //���������� �����
	}
	
	//������� ������� ���������� ��� �� ���������� ����� ��� ������� ������ "�����"
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{  
	    if (keyCode == KeyEvent.KEYCODE_BACK)
	    {
	        SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
	    }
	    return false; 
	}
	//������ ������ ������

	//�������, ������� ����� ��������� ��� ������� ������
	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws IOException 
	{
		ResourceManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager()); //�������� � �������� ������, ��������, ������ � ��� ����� �������� �������� � ��� ��� ����������� �������
		pOnCreateResourcesCallback.onCreateResourcesFinished();//������ ��� ����� �������� ���������
		
	}

	//���, ������� ����� ��������� ��� �������� �����
	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws IOException 
	
	{
		SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback); //����� ��������� �����
	}

	//������ ������, ���������� ����� ���� �������� ����� ����
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
	
	//����������� � �����
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		System.exit(0);	
	}
	
}