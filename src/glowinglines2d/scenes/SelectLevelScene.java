package glowinglines2d.scenes;

import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;

import glowinglines2d.managers.SceneManager;
import glowinglines2d.managers.SceneManager.SceneType;
import glowinglines2d.objects.Grid;

public class SelectLevelScene extends BaseScene {

	private Grid grid;

	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		createBackground();
		createIcons(2);
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		SceneManager.getInstance().loadSecondMenuScene();
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
		this.setBackground(new Background(0,0,0));
	    grid = new Grid (0,0,0,768,1f,1280,768,1f,1f,1f,vbo);
		this.attachChild(grid);
	}
	
	private void createIcons(int levelCount)
	{
		Rectangle levelIcon = new Rectangle(224, 640, 95, 95, vbo);
		levelIcon.setColor(0f, 0f, 0.2f);
	
		this.attachChild(levelIcon);
	}
}
