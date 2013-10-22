//КЛАСС ОТВЕЧАЮЩИЙ ЗА ЗАГРУЗКУ И ВЫГРУЗКУ РЕСУРСОВ ИГРЫ
package glowinglines2d.managers;
//Менеджер ресурсов, отсюда загружаются все текстуры, звуки, регионы. 
import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import android.graphics.Color;

import glowinglines2d.activity.GameActivity;

public class ResourceManager 

{
	//------------------------------------------------------------------
	//ОБЪЕКТЫ
	//------------------------------------------------------------------
	private static final ResourceManager INSTANCE = new ResourceManager();
	
	public Engine engine;
	public GameActivity activity;
	public BoundCamera camera;
	public VertexBufferObjectManager vbo;
	//-----------------------------------------------------------------
	
	//-----------------------------------------------------------------
	//ПЕРЕМЕННЫЕ
	//-----------------------------------------------------------------
	//-----------------------ТЕКСТУРЫ ИНТРО ЭКРАНА-------------------------------
	BitmapTextureAtlas splashTexture;
	public ITextureRegion splashRegion;
	//--------------------------------------------------------------------------
	
	//-----------------------ПЕРЕМЕННЫЕ СЦЕНЫ МЕНЮ-------------------------------
	private BitmapTextureAtlas socketAtlas;
	public ITiledTextureRegion socketTextureRegion;
	
	private BitmapTextureAtlas topBorderAtlas;
	public ITextureRegion topBorderTextureRegion;
	//---------------------------------------------------------------------------
	
	//---------------------------ТЕКСТУРЫ ЗАГРУЗОЧНОГО ЭКРАНА---------------------
	//private BitmapTextureAtlas loadingTexture;
	//public ITextureRegion loadingRegion; //загрузка
	//----------------------------------------------------------------------------
	
	//---------------------------ТЕКСТУРЫ ИГРОВОЙ СЦЕНЫ--------------------------
	public ITexture menuFontTexture; 
	public ITexture gameFontTexture;
	//---------------------------ОСНОВНОЙ ШРИФТ-----------------------------------
	public Font menuFont;
	public Font gameFont;
	//----------------------------------------------------------------------------

	public ITiledTextureRegion packetsRegion;
	private BitmapTextureAtlas packetsAtlas;
	
	
	//-----------------------------------------------------------------
	//ЛОГИКА КЛАССА
	//-----------------------------------------------------------------
	
	//-------------------------------
	//ЗАГРУЗКА РЕСУРСОВ МЕНЮ

	public void loadMenuResources()
	{
		loadMenuGraphics();
		loadMenuSounds();
		loadMenuFont();
		
	}

	private void loadMenuFont()
	{
		FontFactory.setAssetBasePath("gfx/font/");
		gameFontTexture = new BitmapTextureAtlas(activity.getTextureManager(),512,512,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		
		gameFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), gameFontTexture, activity.getAssets(), "pixcyr.ttf", 45, true, Color.WHITE, 2, Color.BLACK);
		gameFont.load();
	}

	//загрузка графики
	private void loadMenuGraphics()
	{
		
	}
	
	
	//загрузка звуков
	private void loadMenuSounds()
	{
	
	}
	//---------------------------------
	
	
	//---------------------------------
	//ЗАГРУЗКА ИГРОВЫХ РЕСУРСОВ
	public void loadGameResources()
	{
		loadGameGraphics();
		loadGameSounds();
		loadGameFonts();
	}
	
	//загрузка графики
	private void loadGameGraphics()
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        socketAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.DEFAULT);
        socketTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(socketAtlas, activity, "tiled_socket.png",0,0,4,1);
		socketAtlas.load();
		
		topBorderAtlas = new BitmapTextureAtlas(activity.getTextureManager(),1280,128,TextureOptions.DEFAULT);
		topBorderTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(topBorderAtlas, activity, "top.png",0,0);
		topBorderAtlas.load();
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        packetsAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 64, 64, TextureOptions.DEFAULT);
        packetsRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(packetsAtlas, activity, "fiolet.png",0,0,1,1);
        packetsAtlas.load();
	}
	
	//загрузка звуков
	private void loadGameSounds()
	{
		
	}
	
	//загрузка шрифтов
	private void loadGameFonts()
	{	
		
	}
	
	//-----------------------------------

	//-----------------------------------
	//ЗАГРУЗКА И ВЫГРУЗКА СТАРТОВОГО ЭКРАНА ИГРЫ
	public void loadSplashScreen()
	{	
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.splashTexture = new BitmapTextureAtlas(activity.getTextureManager(),256,256,TextureOptions.BILINEAR);
		this.splashRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTexture, activity,"logo.png",0,0);
		splashTexture.load();
		
	}
	
	public void unloadSplashScreen()
	{
		splashTexture.unload();
		splashRegion = null;
	}
	
	public void unloadMenuTextures()
	{
		
	}
	
	public void loadMenuTextures()
	{
		
	}
	
	public void unloadGameTextures()
	{
		packetsAtlas.unload();
		socketAtlas.unload();
		topBorderAtlas.unload();
	}
	
	//-------------------------------------
	
	//------------------------------------------
	//помоему, это что то типа подготовки менеджера ресурсов
	//----------------------------------------------------
	public static void prepareManager (Engine engine, GameActivity activity,BoundCamera camera,VertexBufferObjectManager vbo)
	{
		getInstance().engine = engine; //скорее всего, мы этими кодами проверяем статус движка, актвити,камеры и ВБО,
		getInstance().activity = activity;
		getInstance().camera = camera;
		getInstance().vbo = vbo;
	}
	//не знаю пока, надо будет разобраться в дальнейшем и не забыть написать комментарии по этому поводу
	//UPD 1. Возможно с помощью этих действий мы принимаем положения и параметры каждого этого элемента, например камеры, чтобы не инициализировать её снова или получать её координаты, статус и прочую инфу сюда, чтобы дальше работать с этой инфой
	public static ResourceManager getInstance ()
	{
		return INSTANCE;
	}

	public void loadsecondMenuSceneResources() {
		// TODO Auto-generated method stub
		
	}

	public void loadLevelSelectResources() {
		// TODO Auto-generated method stub
		
	}
}
