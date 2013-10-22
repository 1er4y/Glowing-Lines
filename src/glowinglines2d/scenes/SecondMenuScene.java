package glowinglines2d.scenes;

import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

import android.opengl.GLES20;

import glowinglines2d.managers.SceneManager;
import glowinglines2d.managers.SceneManager.SceneType;
import glowinglines2d.objects.Grid;

public class SecondMenuScene extends BaseScene implements IOnMenuItemClickListener {

	private MenuScene menuChildScene;
	private int SECONDMENU_SELECTLEVEL = 0;
	private int SECONDMENU_SHOP = 1;
	//private int MENU_BA;
	private Text systemDefenceText;

	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		createBackground();
		createMenuChildScene();
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		SceneManager.getInstance().loadMenuScene(engine);
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}
	
	private void createBackground()
	{
		systemDefenceText = new Text (0,0,resourceManager.gameFont,"SYSTEM DEFENCE 2D",vbo);
		systemDefenceText.setAnchorCenter(0, 0);
		systemDefenceText.setPosition(192,600);
		systemDefenceText.setScale(1.3f);
		systemDefenceText.setColor(0.5f, 0.7f, 1f);
		this.attachChild(systemDefenceText);
		
		this.setBackground(new Background(0,0,0));
		Grid grid = new Grid (0,0,0,768,1f,1280,768,1f,1f,1f,vbo);
		this.attachChild(grid);
	}
	
	
	private void createMenuChildScene()
	{
		menuChildScene = new MenuScene(camera);
		menuChildScene.setPosition(0,0);
		
		final IMenuItem selectLevelSecondMenuItem = new ScaleMenuItemDecorator(new ColorMenuItemDecorator(new TextMenuItem(SECONDMENU_SELECTLEVEL, resourceManager.gameFont, "Выбор уровня", vbo), new Color(1f,0.71f,0.37f), new Color(1f,1f,1f)),1.2f,1f);
		final IMenuItem shopSecondMenuItem = new ScaleMenuItemDecorator(new ColorMenuItemDecorator(new TextMenuItem(SECONDMENU_SHOP, resourceManager.gameFont, "Магазин", vbo), new Color(1f,0.71f,0.37f), new Color(1f,1f,1f)),1.2f,1f);
		//final IMenuItem quitMenuItem = new ScaleMenuItemDecorator(new ColorMenuItemDecorator(new TextMenuItem(MENU_QUIT, resourceManager.gameFont, "Quit", vbo), new Color(1f,0.71f,0.37f), new Color(0f,1f,0f)),1.2f,1f);
		
		menuChildScene.addMenuItem(selectLevelSecondMenuItem);
		menuChildScene.addMenuItem(shopSecondMenuItem);
		//menuChildScene.addMenuItem(quitMenuItem);
		selectLevelSecondMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		selectLevelSecondMenuItem.registerEntityModifier(new AlphaModifier(5.5f, 0f, 1f));
		shopSecondMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		shopSecondMenuItem.registerEntityModifier(new AlphaModifier(5.5f, 0f, 1f));
		//quitMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		//quitMenuItem.registerEntityModifier(new AlphaModifier(5.5f, 0f, 1f));
		
		
		menuChildScene.buildAnimations();
		menuChildScene.setBackgroundEnabled(false);
		
		selectLevelSecondMenuItem.setAnchorCenter(0, 0);
		shopSecondMenuItem.setAnchorCenter(0, 0);
		selectLevelSecondMenuItem.setPosition(192, 460);
		shopSecondMenuItem.setPosition(192, 380);
		//quitMenuItem.setPosition(640, 260);
		
		menuChildScene.setOnMenuItemClickListener(this);
		
		setChildScene(menuChildScene);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		// TODO Auto-generated method stub
		
		switch(pMenuItem.getID())
		{
			case 0:
				SceneManager.getInstance().loadLevelSelectScene();
				return true;
			case 1:
				return true;
			default:
				return false;
		}
	}
	

}
