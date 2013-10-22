//����� ��������
package glowinglines2d.scenes;

import org.andengine.entity.sprite.Sprite;

//--------------------------------------------------------------------------
//�����
//--------------------------------------------------------------------------

public class SplashScene extends BaseScene
{
	//---------------------------------------------
	//������
	//--------------------------------------------
	private Sprite splash;
	//----------------------------------------------
	
	//-------------------------------------------------
	//������ ������
	//-------------------------------------------------
	
	@Override
	public void createScene() //�������� ����� 
	{
		splash = new Sprite(0,0, resourceManager.splashRegion,vbo); //�������� �������
		//����������� ��������, ������� � �����
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
		//��������� ���� ����� 
		return glowinglines2d.managers.SceneManager.SceneType.SCENE_SPLASH;
	}

	@Override
	public void disposeScene()
	{	
		//����������� �����
		splash.detachSelf();
	    splash.dispose();
	    this.detachSelf();
	    this.dispose();
	}

}
