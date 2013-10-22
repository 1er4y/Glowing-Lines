package glowinglines2d.scenes;

import java.io.IOException;
import java.util.Random;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.LineStrip;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.SAXUtils;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.level.EntityLoader;
import org.andengine.util.level.constants.LevelConstants;
import org.andengine.util.level.simple.SimpleLevelEntityLoaderData;
import org.andengine.util.level.simple.SimpleLevelLoader;
import org.xml.sax.Attributes;

import glowinglines2d.scenes.GameScene;

import glowinglines2d.managers.SceneManager;
import glowinglines2d.managers.SceneManager.SceneType;
import glowinglines2d.objects.Packets;
import glowinglines2d.objects.Socket;

public class GameScene extends BaseScene implements IOnSceneTouchListener {

	//-----------------------------------------------------------------
	//ПЕРЕМЕННЫЕ
	//-----------------------------------------------------------------
	private static final String TAG_ENTITY = "entity"; 
	private static final String TAG_ENTITY_ATTRIBUTE_INDEX = "index";
	private static final String TAG_ENTITY_ATTRIBUTE_COLOR_RED = "red";
	private static final String TAG_ENTITY_ATTRIBUTE_COLOR_GREEN = "green";
	private static final String TAG_ENTITY_ATTRIBUTE_COLOR_BLUE = "blue";
	private static final String TAG_ENTITY_ATTRIBUTE_CORNERS = "corners";
	private static final String TAG_ENTITY_ATTRIBUTE_FIRSTCOORDY = "firstYCoor";
	private static final String TAG_ENTITY_ATTRIBUTE_FIRSTCOORDX = "firstXCoor";
	private static final String TAG_ENTITY_ATTRIBUTE_SECONDCOORDY = "secondYCoor";

	private int firstYCoordinate;
	private int firstXCoordinate;
	private int secondYCoordinate;
	private int XNext;
	private int indexOfLine;
	private int numbOfPoints;
	private int numbOfLines;
	private float cRed;
	private float cGreen;
	private float cBlue;
	int k=0;
	private float socketXcoor;
	private float socketYcoor;
	private static final LineStrip[] arrayOfLines = new LineStrip[20];
	private static final Socket[] arrayOfSockets = new Socket[20];
	private static final int [] points = new int[20];
	private static final int[] arrayOfLengthX = new int[20];
	private static final int[] arrayOfLengthY = new int[20];
	private static final Packets[] arrayOfSprites = new Packets[20];
	private static final Packets[] arrayOfSprites2 = new Packets[20];
	private static final Packets[] arrayOfSprites3 = new Packets[20];
	int sX = 640;
	int M=0;
    Socket socket;
    Socket socket1;
    Socket socket2;
    Socket socket3;
    Socket socket4;
    int K=0;
    
	
	//-----------------------------------------------------------------
	//ПЕРЕМЕННЫЕ УРОВНЕЙ
	//-----------------------------------------------------------------
	//эти все объекты нужны для загрузки уровня, поймете когда посмотрите функцию загрузки уровня

	protected int v = 0;
	private Text scoreText;
	private Text infoText;
	protected int selected_index;
	protected int selected_count=0;
	private Text gameOverText;
	protected int sock;
	float indexOfDamage;
	float systemHealth = 100f;
	private boolean gameOver = false;
	boolean firstAttach = false;
	//-----------------------------------------------------------------------------------------------
	//private Player player; //создаем игрока
	private Text youWin;
	
	//---------------------------------------------------------------------
	//ПЕРЕМЕННЫЕ БЭКГРАУНДА
	
	//-----------------------------------------------------------------
	//ОБЯЗАТЕЛЬНЫЕ ФУНКЦИИ
	//--------------------------------------------------------------------
	@Override
	public void createScene() { //тут всё довольно просто, при создании игровой сцены, грузим бэкграунд, грузим худ, грузим физику, грузим уровень, заранее создаем текст об окончании игры и устанавливаем слушатель
		createBackground(); //создаем бэкграунд
		//createHUD(); //грузим худ
		loadLevel(1);
		indexOfDamage = 0.03f;
		setSockets();
		randomizeArray();
		setDataSprites();
		createGameOverText();
		setOnSceneTouchListener(this);
		Sprite topBorder = new Sprite(640,688,resourceManager.topBorderTextureRegion,vbo);
		this.attachChild(topBorder);
		
		Sprite bottomBorder = new Sprite(640,32,resourceManager.topBorderTextureRegion,vbo);
		bottomBorder.setRotation(180);
		this.attachChild(bottomBorder);
		this.registerUpdateHandler(new IUpdateHandler() {
            @Override
            public void onUpdate(float pSecondsElapsed) {
            	
            	
            	if(gameOver==false)
            	{
            		int k = 0;
            	int i;
            	int b;
            	int l;
            	for(i=0;i<numbOfLines;i++)
            	{
            		if (arrayOfLines[i].getX(points[i]*2+1)==arrayOfSockets[i].getX())
        			{
            			k++;
            			if(k==numbOfLines)
            			{
            				displayYouWin();
            				gameOver = true;
            			}
        			}
            		//--------------------------------ПРОВЕРКА НА СОВПАДЕНИЕ С ЦВЕТОМ ЛИНИИ-----------------------------------
            		for(b=0;b<numbOfLines;b++)
            		{
            			if(arrayOfLines[i].getX(points[i]*2+1)==arrayOfSockets[b].getX())
            			{
            				sock=b;
            			}
            			
            		}
            		
            		if(arrayOfLines[i].getRed()==arrayOfSockets[sock].getRed()&&arrayOfLines[i].getGreen()==arrayOfSockets[sock].getGreen()&&arrayOfLines[i].getBlue()==arrayOfSockets[sock].getBlue()&&arrayOfSockets[sock].socket_state()==true)
            		{
            			arrayOfSockets[sock].setCurrentTileIndex(3);
            		}
            		else if(arrayOfLines[i].getRed()==arrayOfSockets[sock].getRed()&&arrayOfLines[i].getGreen()==arrayOfSockets[sock].getGreen()&&arrayOfLines[i].getBlue()==arrayOfSockets[sock].getBlue()&&arrayOfSockets[sock].socket_state()==false)
            			{
            				arrayOfSockets[sock].setCurrentTileIndex(2);
            			}
            		else if(arrayOfSockets[sock].socket_state()==true)
            		{
            			arrayOfSockets[sock].setCurrentTileIndex(1);
            		}
            		else if(arrayOfSockets[sock].socket_state()==false)
            		{
            			arrayOfSockets[sock].setCurrentTileIndex(0);
            		}
            		//------------------------------------------------------------------------------------------------------------
            		//------------------------------------------------------------------------------------------------------------
            		
            		
            		for (l=0;l<numbOfLines;l++)
                	{
                		if(arrayOfSprites[l].collidesWith(arrayOfSockets[i])&&arrayOfLines[i].getX(points[i]*2+1)!=arrayOfSockets[i].getX())
                		{
                			systemHealth=systemHealth-arrayOfSprites[l].getDamage();
                			infoText.setText("HP: "+(int)systemHealth);
                		}
                		if(arrayOfSprites2[l].collidesWith(arrayOfSockets[i])&&arrayOfLines[i].getX(points[i]*2+1)!=arrayOfSockets[i].getX())
                		{
                			systemHealth=systemHealth-arrayOfSprites2[l].getDamage();
                			infoText.setText("HP: "+(int)systemHealth);
                		}
                		
                		if(arrayOfSprites3[l].collidesWith(arrayOfSockets[i])&&arrayOfLines[i].getX(points[i]*2+1)!=arrayOfSockets[i].getX())
                		{
                			systemHealth=systemHealth-arrayOfSprites3[l].getDamage();
                			infoText.setText("HP: "+(int)systemHealth);
                		}
                		
                		if(systemHealth<0)
                		{
                			displayGameOverText();
                			gameOver=true;
                			infoText.setText("HP: 0");
                		}
                	}
            		
            		
            	}
            
            }
            	
            	
          }

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}});
    
		//ОПИСАНИЕ ВСЕХ ЭТИХ ФУНКЦИИ СМОТРИТЕ НИЖЕ
	}


	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadMenuScene(engine);
	
	}

	@Override
	public SceneType getSceneType() {
		
		return  SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene()
	{
	    camera.setHUD(null);
	    camera.setCenter(640, 360);
	    camera.setChaseEntity(null);
	}
	
	//-------------------------------------------------------------------------------------
	//ВСЕ ОСТАЛЬНЫЕ ФУНКЦИИ, КОТОРЫЕ БЫЛИ УКАЗАНЫ В CREATESCENE.
	//-------------------------------------------------------------------------------------
	//создание бэкграунда
	private void createBackground()
	{
		this.setBackground(new Background(0f,0f,0f));
		Line grid = buildGrid(1280, 768, 0.451f, 0.732f, 1f);
		this.attachChild(grid);
		this.setAlpha(0.2f);
		
		scoreText = new Text (130,0,resourceManager.gameFont,"Очки: 0123456789",new TextOptions(HorizontalAlign.LEFT),vbo);
		 scoreText.setAnchorCenter(0, 0);
		 scoreText.setText("Очки: ");
		//this.attachChild(scoreText);
		 
		 infoText = new Text (130,665,resourceManager.gameFont,"Инфо: 0123456789",new TextOptions(HorizontalAlign.LEFT),vbo);
		 infoText.setAnchorCenter(0, 0);
		 infoText.setText("HP: ");
		 infoText.setColor(0f, 1f, 0f);
		 this.attachChild(infoText);
		 
	}
	
	private void createGameOverText()
	{
		gameOverText = new Text(0, 0, resourceManager.gameFont, "Game Over!", vbo);
		youWin = new Text(0,0,resourceManager.gameFont,"Well done!",vbo);
	}
	
	private void displayGameOverText()
	{
		camera.setChaseEntity(null);
		gameOverText.setPosition(camera.getCenterX(), camera.getCenterY());
		
		if (firstAttach == false)
		{
			firstAttach = true;
			attachChild(gameOverText);
		}
	}
	private void displayYouWin()
	{
		camera.setChaseEntity(null);
		youWin.setPosition(camera.getCenterX(), camera.getCenterY());
		
		if (firstAttach == false)
		{
			firstAttach = true;
			attachChild(youWin);
		}
	}
	//генерация задней сетки
	private Line buildGrid(int pWidth, int pHeight, float pRed, float pGreen, float pBlue){
        Line grid = new Line(0, 0, 0, pHeight, vbo);
        grid.setColor(0.5f, 0.5f, 0.5f);
        int cont = 0;
       
        while(cont < pWidth){
                cont += 32;
                grid.attachChild(new Line(cont, 0, cont, pHeight,vbo));
                grid.getLastChild().setColor(pRed, pGreen, pBlue);                     
                grid.getLastChild().setAlpha(0.15f);
        }
       
        cont = 0;
        while (cont < pHeight){
                cont += 32;
                grid.attachChild(new Line(0, cont, pWidth, cont,vbo));
                grid.getLastChild().setColor(pRed, pGreen, pBlue);
                grid.getLastChild().setAlpha(0.15f);
        }
       
        return grid;
}

	

	//создание HUD
	private void createHUD()
	{
		
	}
	
	//функция увеличения очков
	
	private void addToScore(int i)
	{
		
	}
	
	public LineStrip generateLine(int[] arrayX, int[]arrayY,int numbOfLine,int numbOfPoints, int indexOfLine,int XNext,int firstYCoordinate,int secondYCoordinate,
			int firstXCoordinate, float cRed, float cGreen, float cBlue) //в xml файле - "1" в левел 
	{	
		int newX;
		int newY;
		int startX = 0;
		int startY = 0;
		newY = firstYCoordinate;
		newX = firstXCoordinate;
		int i = 0;
		
		LineStrip oneLine = new LineStrip(firstXCoordinate,firstYCoordinate,numbOfPoints*2+2,vbo);
		oneLine.setColor(cRed,cGreen,cBlue);
		for (i=0;i<numbOfPoints;i++)
		{
			if(i==0)
			{
				oneLine.add(startX,startY);
				oneLine.add(startX, startY-64);
				newY=startY-64;
				newX=startX;
			}
			newX=startX+arrayX[i];
			oneLine.add(newX,newY);
			startY=newY+arrayY[i];
			startX=newX;
			oneLine.add(startX, startY);
			newY=startY;
		}
		socketXcoor = oneLine.getX(numbOfPoints*2);
		socketYcoor = oneLine.getY(numbOfPoints*2)+arrayY[numbOfPoints-1];
		generateSocket2(K,socketXcoor,socketYcoor,cRed,cGreen,cBlue);
		K++;
			return oneLine;
	}
	
	private void generateSocket2(final int i,float socketXcoor, float socketYcoor,float xRed,float xGreen, float xBlue)
	{
		arrayOfSockets[i] = new Socket(socketXcoor,socketYcoor,vbo,i)
		{
			
			public boolean onAreaTouched(TouchEvent touchEvent, float x, float y)
			{
				if(touchEvent.isActionDown()&&gameOver==false)
				{
				if (i==selected_index&&arrayOfSockets[i].socket_state()==false||selected_count==0)
				{
					arrayOfSockets[i].socket_selected();
					arrayOfSockets[i].setCurrentTileIndex(1);
					selected_count=1;
					selected_index = arrayOfSockets[i].getIndex();
				}
				else

					if(arrayOfSockets[i].socket_state()==true)
				
					{
						arrayOfSockets[i].setCurrentTileIndex(0);
						arrayOfSockets[i].socket_unselected();
						selected_index = -1;
						selected_count=0;
					}
					else 
						if(arrayOfSockets[i].socket_state()==false&&selected_count==0)
					{
						arrayOfSockets[i].socket_selected();
						arrayOfSockets[i].setCurrentTileIndex(1);
						selected_index = arrayOfSockets[i].getIndex();
					}
				
				}
				
						//scoreText.setText("Очки:"+selected_index);
				
				return true;
			};
		};
		this.attachChild(arrayOfSockets[i]);
		arrayOfSockets[i].setColor(xRed, xGreen, xBlue);
	}
	
	private void randomizeArray()
	{
		int i;
		int n;
		int[] TempArray = new int[numbOfLines];
		
		//заполнение
		for(i=0;i<numbOfLines;i++)
		{
			TempArray[i]=i;
		}
		
		//рандомизация
		Random rnd = new Random();
		for(i=numbOfLines-1;i>0;i--)
		{
			int j = rnd.nextInt(i);
			int tmp = TempArray[i];
			TempArray[i] = TempArray[j];
			TempArray[j] = tmp;
		}
		
		//вывод
		for (i=0;i<numbOfLines;i++)
		{	
			n=TempArray[i];
			arrayOfSockets[i].setPosition(arrayOfLines[n].getX(points[n]*2+1),arrayOfLines[n].getY(points[n]*2+1));	
		}
	}
	
	private void setSockets()
	{
		int i=0;
		
		for (i=0;i<numbOfLines;i++)
		{
			this.registerTouchArea(arrayOfSockets[i]);
		}
	}

	private void setDataSprites() 
	{
		int k;
		float s1,s2,s3;
		
		for(k=0;k<numbOfLines;k++)
		{
			s1=(float)(Math.random()*0.2+0.1);
			s2=(float)(Math.random()*0.3+0.2);
			s3=(float)(Math.random()*0.6+0.3);
			arrayOfSprites[k]= new Packets(arrayOfLines[k].getX(), arrayOfLines[k].getY(), vbo, k);
			arrayOfSprites[k].setScale(s1);
			arrayOfSprites2[k] = new Packets(arrayOfLines[k].getX(), arrayOfLines[k].getY(), vbo, k);
			arrayOfSprites2[k].setScale(s2);
			arrayOfSprites3[k] = new Packets(arrayOfLines[k].getX(), arrayOfLines[k].getY(), vbo, k); 
			arrayOfSprites3[k].setScale(s3);
			this.attachChild(arrayOfSprites2[k]);
			this.attachChild(arrayOfSprites[k]);
			this.attachChild(arrayOfSprites3[k]);
		}
	
		int i;
		for (i=0;i<numbOfLines;i++)
		{
			final Path path1 = new Path(points[i]*2+1);
			int l;
			for(l=0;l<points[i]*2+1;l++)
			{
				path1.to(arrayOfLines[i].getX(l+1),arrayOfLines[i].getY(l+1));
			}
			float n = (float) (Math.random()*0.9f+0.6f);
			float m = (float) (Math.random()*3.5f+2.5f);
			float b = (float) (Math.random()*6f+5f);
			arrayOfSprites[i].registerEntityModifier(new LoopEntityModifier(new PathModifier(n, path1)));
			arrayOfSprites[i].setSpeed(n);
			arrayOfSprites2[i].registerEntityModifier(new LoopEntityModifier(new PathModifier(m, path1)));
			arrayOfSprites2[i].setSpeed(m);
			arrayOfSprites3[i].registerEntityModifier(new LoopEntityModifier(new PathModifier(b,path1)));
			arrayOfSprites3[i].setSpeed(b);
			arrayOfSprites[i].setDamage((arrayOfSprites[i].getSpeed()*indexOfDamage));
			arrayOfSprites2[i].setDamage(indexOfDamage*arrayOfSprites2[i].getSpeed());
			arrayOfSprites3[i].setDamage(indexOfDamage*arrayOfSprites3[i].getSpeed());
		}
		
	}
	
	private void loadLevel(int levelID)
	{ 
		
		SimpleLevelLoader levelLoader = new SimpleLevelLoader (vbo);
		levelLoader.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(LevelConstants.TAG_LEVEL)
				{
					public IEntity onLoadEntity (final String pEntityName, final IEntity pParent, final Attributes pAttributes,final SimpleLevelEntityLoaderData pSimpleLevelEntityLoaderData) throws IOException
					{
						numbOfLines = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH); //получает из .xml файла ширину и высоту уровн
						//Lines.getValue(numbOfLines);
						return GameScene.this;
					}
				});
		
		levelLoader.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(TAG_ENTITY)
			{
			

			public IEntity onLoadEntity(final String pEntityName, final IEntity pParent, final Attributes pAttributes, final SimpleLevelEntityLoaderData pSimpleLevelEntityLoaderData) throws IOException
			{
				numbOfPoints = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_CORNERS);
				points[k]=numbOfPoints;
				indexOfLine = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_INDEX);
				
				cRed = SAXUtils.getFloatAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_COLOR_RED);
				cGreen = SAXUtils.getFloatAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_COLOR_GREEN);
				cBlue = SAXUtils.getFloatAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_COLOR_BLUE);
				
				int i=0;
				
				for (i=0;i<numbOfPoints;i++)
				{
					final String TAG_ENTITY_ATTRIBUTE_X = "X"+i;
					final String TAG_ENTITY_ATTRIBUTE_Y = "Y"+i;
					
					arrayOfLengthX[i] = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_X);
					arrayOfLengthY[i] = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_Y);
				}
				if (indexOfLine==0)
				{
					firstXCoordinate = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_FIRSTCOORDX);
					firstYCoordinate = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_FIRSTCOORDY);
					secondYCoordinate = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_SECONDCOORDY);
					firstXCoordinate-=64;
				}
				firstXCoordinate+=64;
		
				arrayOfLines[k]= generateLine(arrayOfLengthX,arrayOfLengthY, numbOfLines, numbOfPoints, indexOfLine,XNext, firstYCoordinate, secondYCoordinate,
						 firstXCoordinate,  cRed, cGreen, cBlue);
				k++;

				return arrayOfLines[k-1];	
				
			}
			
			
			});
		
		levelLoader.loadLevelFromAsset(activity.getAssets(), "gfx/"+levelID+".lvl");
	}	//подключение физики

	private float getLineYCoor()
	{
		int i;
		float y = 0;
		for(i=0;i<numbOfLines;i++)
		{
			y = arrayOfLines[i].getY(points[i]*2)+arrayOfLengthY[points[i]-1];
		}
		return y;
	}
	
	private int getLineIndex()
	{
		int i;
		int k;
		int x = 0;
		
		for(i=0;i<numbOfLines;i++)
		{	
				if(arrayOfLines[i].getX(points[i]*2+1)==arrayOfSockets[selected_index].getX()&&arrayOfSockets[selected_index].socket_state()==true)
				{
					x=i;
					
				}
		}
		
		return x;
	}
	
	private int getPrevSocketIndex(int indexOfLine)
	{
		int i;
		int k;
		int x = 0;
		
		for(k=0;k<numbOfLines;k++)
		{
				if(arrayOfLines[indexOfLine].getX(points[indexOfLine]*2+1)==arrayOfSockets[k].getX()&&arrayOfSockets[k].socket_state()==false)
				{
					x=k;
					//scoreText.setText("очки: "+x);
				}
		}
		
		return x;
	}
	
	private int getNextSocketIndex(int indexOfLine)
	{
		int i;
		int k;
		int x = 0;
		
		for(k=0;k<numbOfLines;k++)
		{
				if(arrayOfLines[indexOfLine].getX(points[indexOfLine]*2+1)==arrayOfSockets[k].getX()&&arrayOfSockets[k].socket_state()==false)
				{
					x=k;
					//scoreText.setText("очки: "+indexOfLine);
				}
		}		
		return x;
	}
	
	@Override
	
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
			
		if(selected_index!=-1&&gameOver==false)
		{
		 if (pSceneTouchEvent.getX()>arrayOfSockets[selected_index].getX())
			{
				if(arrayOfSockets[selected_index].socket_state()==true&&pSceneTouchEvent.isActionDown())
				{
					
					float oldX;
					float oldY;
					int indexOfLine = getLineIndex();
					if(indexOfLine!=numbOfLines-1)
					{
						oldX=arrayOfLines[indexOfLine].getX(points[indexOfLine]*2+1);
						oldY=getLineYCoor();
					
						float newX = arrayOfLines[indexOfLine+1].getX(points[indexOfLine+1]*2+1);
						float newY = getLineYCoor();
					
						arrayOfSockets[selected_index].setPosition(newX,newY);
						arrayOfSockets[getNextSocketIndex(indexOfLine+1)].setPosition(oldX,oldY);
					}
				}
				
			}
		 
		 //-----------------------------------------------------------------------------
		 if (pSceneTouchEvent.getX()<arrayOfSockets[selected_index].getX())
			{
			 if(pSceneTouchEvent.isActionDown())
				{
					float oldX;
					float oldY;
					int indexOfLine = getLineIndex();
					if(indexOfLine!=0)
						{
						oldX=arrayOfSockets[selected_index].getX();
						oldY=getLineYCoor();
					
						float newX = arrayOfLines[indexOfLine-1].getX(points[indexOfLine-1]*2+1);
						float newY = getLineYCoor();
					
						arrayOfSockets[selected_index].setPosition(newX,newY);
						arrayOfSockets[getPrevSocketIndex(indexOfLine-1)].setPosition(oldX,oldY);
						}
				}
			 
			}
		}
			
		//}
		
		return false;
		
		
	}
}
