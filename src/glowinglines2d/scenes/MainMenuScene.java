//—÷≈Õ¿ Ã≈Õﬁ
package glowinglines2d.scenes;

import glowinglines2d.managers.SceneManager;
import glowinglines2d.managers.SceneManager.SceneType;
import glowinglines2d.objects.Grid;

import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import android.opengl.GLES20;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener
{
	//---------------------------------------------
	// œ≈–≈Ã≈ÕÕ€≈
	//---------------------------------------------
	
	private MenuScene menuChildScene;
	private final int MENU_PLAY = 0;
	private final int MENU_OPTIONS = 1;
	private final int MENU_QUIT = 4;
	private final int MENU_AUTHORS = 2;
	private final int MENU_ABOUTGAME = 3;
	Text systemDefenceText;
	//---------------------------------------------
	// Ã≈“Œƒ€
	//--------------------------------------------
	private Grid grid;

	@Override
	public void createScene()
	{	
		createBackground();
		createMenuChildScene();
	}

	@Override
	public void onBackKeyPressed()
	{
		System.exit(0);
	}

	@Override
	public SceneType getSceneType()
	{
		return SceneType.SCENE_MENU;
	}
	

	@Override
	public void disposeScene()
	{
		menuChildScene.detachSelf();
		menuChildScene.dispose();
		grid.detachSelf();
		grid.dispose();
		//this.dispose();
		//this.disposeScene();
		
	}
	
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY)
	{
		switch(pMenuItem.getID())
		{
			case MENU_PLAY:
				SceneManager.getInstance().loadSecondMenuScene();
				return true;
			case MENU_OPTIONS:
				return true;
			case MENU_AUTHORS:
				return true;
			case MENU_ABOUTGAME:
				return true;
			case MENU_QUIT:
				System.exit(0);
				return true;
			default:
				return false;
		}
	}
	
	//---------------------------------------------
	// ÀŒ√» ¿  À¿——¿
	//---------------------------------------------
	
	//ÒÓÁ‰‡ÌËÂ ÒˆÂÌ˚ ·˝Í„‡ÛÌ‰‡
	private void createBackground()
	{
		systemDefenceText = new Text (0,0,resourceManager.gameFont,"SYSTEM DEFENCE 2D",vbo);
		systemDefenceText.setAnchorCenter(0, 0);
		systemDefenceText.setPosition(192,600);
		systemDefenceText.setScale(1.3f);
		systemDefenceText.setColor(0.5f, 0.7f, 1f);
		this.attachChild(systemDefenceText);
		
		this.setBackground(new Background(0,0,0));
	    grid = new Grid (0,0,0,768,1f,1280,768,1f,1f,1f,vbo);
		this.attachChild(grid);
		
	}
	
	//ÙÛÌÍˆËˇ ÒÓÁ‰‡ÌËˇ ÔÓ‰ÒˆÂÌ˚ ˝ÎÂÏÂÌÚÓ‚ ÏÂÌ˛
	private void createMenuChildScene()
	{
		menuChildScene = new MenuScene(camera);
		menuChildScene.setPosition(0,0);
		
		final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new ColorMenuItemDecorator(new TextMenuItem(MENU_PLAY, resourceManager.gameFont, "»√–¿“‹", vbo), new Color(1f,0.71f,0.37f), new Color(1f,1f,1f)),1.2f,1f);
		final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new ColorMenuItemDecorator(new TextMenuItem(MENU_OPTIONS, resourceManager.gameFont, "Õ¿—“–Œ… »", vbo), new Color(1f,0.71f,0.37f), new Color(1f,1f,1f)),1.2f,1f);
		final IMenuItem quitMenuItem = new ScaleMenuItemDecorator(new ColorMenuItemDecorator(new TextMenuItem(MENU_QUIT, resourceManager.gameFont, "¬€’Œƒ", vbo), new Color(1f,0.71f,0.37f), new Color(1f,1f,1f)),1.2f,1f);
		final IMenuItem authorsMenuItem = new ScaleMenuItemDecorator(new ColorMenuItemDecorator(new TextMenuItem(MENU_AUTHORS,resourceManager.gameFont,"¿¬“Œ–€",vbo),new Color(1f,0.71f,0.37f),new Color (1f,1f,1f)),1.2f,1f);
		final IMenuItem aboutGameMenuItem = new ScaleMenuItemDecorator(new ColorMenuItemDecorator(new TextMenuItem(MENU_ABOUTGAME,resourceManager.gameFont,"Œ¡ »√–≈",vbo),new Color(1f,0.71f,0.37f),new Color (1f,1f,1f)),1.2f,1f);
		
		menuChildScene.addMenuItem(playMenuItem);
		menuChildScene.addMenuItem(optionsMenuItem);
		menuChildScene.addMenuItem(authorsMenuItem);
		menuChildScene.addMenuItem(aboutGameMenuItem);
		menuChildScene.addMenuItem(quitMenuItem);
		
		playMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		playMenuItem.registerEntityModifier(new AlphaModifier(5.5f, 0f, 1f));
		optionsMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		optionsMenuItem.registerEntityModifier(new AlphaModifier(5.5f, 0f, 1f));
		quitMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		quitMenuItem.registerEntityModifier(new AlphaModifier(5.5f, 0f, 1f));
		authorsMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		authorsMenuItem.registerEntityModifier(new AlphaModifier(5.5f, 0f, 1f));
		aboutGameMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		aboutGameMenuItem.registerEntityModifier(new AlphaModifier(5.5f, 0f, 1f));
		
		
		
		menuChildScene.buildAnimations();
		menuChildScene.setBackgroundEnabled(false);
		
		playMenuItem.setAnchorCenter(0, 0);
		optionsMenuItem.setAnchorCenter(0, 0);
		authorsMenuItem.setAnchorCenter(0, 0);
		aboutGameMenuItem.setAnchorCenter(0, 0);
		quitMenuItem.setAnchorCenter(0, 0);
		
		playMenuItem.setPosition(192, 460);
		optionsMenuItem.setPosition(192, 380);
		authorsMenuItem.setPosition(192,300);
		aboutGameMenuItem.setPosition(192,220);
		quitMenuItem.setPosition(192, 140);
		
		menuChildScene.setOnMenuItemClickListener(this);
		
		setChildScene(menuChildScene);
	}
}