package Controller;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import Model.*;
import Players.*;



/*
 * Pattern utiliser: 
 * 			-MVC
 * 			-Observer
 * 			-strategie
 * 			-composite
 * 			-factory
 */

public class GameController implements Initializable{
	
	@FXML
	private GridPane grid;
	
	@FXML
	private AnchorPane fenetre;
	
	@FXML
	private Text moveId;
	
	@FXML
	private ImageView a0;
	@FXML
	private ImageView a1;
	@FXML
	private ImageView a2;
	@FXML
	private ImageView a3;
	@FXML
	private ImageView a4;
	@FXML
	private ImageView b0;
	@FXML
	private ImageView b1;
	@FXML
	private ImageView b2;
	@FXML
	private ImageView b3;
	@FXML
	private ImageView b4;
	@FXML
	private ImageView c0;
	@FXML
	private ImageView c1;
	@FXML
	private ImageView c2;
	@FXML
	private ImageView c3;
	@FXML
	private ImageView c4;
	@FXML
	private ImageView d0;
	@FXML
	private ImageView d1;
	@FXML
	private ImageView d2;
	@FXML
	private ImageView d3;
	@FXML
	private ImageView d4;
	@FXML
	private ImageView e0;
	@FXML
	private ImageView e1;
	@FXML
	private ImageView e2;
	@FXML
	private ImageView e3;
	@FXML
	private ImageView e4;
	
	@FXML
	private Button back;
	
	@FXML
	private ImageView current;
	
	@FXML
	private Button undo;

	@FXML
	private Button play;
	
	private ImageView[][] gridImg;

	private Quixo game = null;
	Main main = Main.getInstance();
	
    @FXML
    void goBack(ActionEvent event) throws IOException
    {
		//Refresh();
		
    	Main main = Main.getInstance();
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../View/MenuView.fxml"));
    	main.setRoot(loader.load());

    	Scene scene = new Scene(main.getRoot());
    	main.getWindow().setScene(scene);
    	main.getWindow().show();
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		gridImg =  new ImageView[5][5];
		gridImg[0][0] = this.a0;
		gridImg[0][1] = this.a1;
		gridImg[0][2] = this.a2;
		gridImg[0][3] = this.a3;
		gridImg[0][4] = this.a4;
		
		gridImg[1][0] = this.b0;
		gridImg[1][1] = this.b1;
		gridImg[1][2] = this.b2;
		gridImg[1][3] = this.b3;
		gridImg[1][4] = this.b4;
		
		gridImg[2][0] = this.c0;
		gridImg[2][1] = this.c1;
		gridImg[2][2] = this.c2;
		gridImg[2][3] = this.c3;
		gridImg[2][4] = this.c4;
		
		gridImg[3][0] = this.d0;
		gridImg[3][1] = this.d1;
		gridImg[3][2] = this.d2;
		gridImg[3][3] = this.d3;
		gridImg[3][4] = this.d4;
		
		gridImg[4][0] = this.e0;
		gridImg[4][1] = this.e1;
		gridImg[4][2] = this.e2;
		gridImg[4][3] = this.e3;
		gridImg[4][4] = this.e4;
		
		this.game = new Quixo();
		this.moveId.setVisible(true);
		this.back.setCancelButton(true);
		
		this.Board();
		this.Refresh();
		
		Main.getInstance().getWindow().setOnCloseRequest( event ->
		{
			Platform.exit();
			System.exit(0);
		}); 
	}
	
	void btnUndo() {
		if(this.game.moveEmpty())	this.undo.setDisable(true);
		else  this.undo.setDisable(false);
	}
	
	void btnPlay() {
		if(!this.game.getCurrent().equals(Tictactoe.CIRCLE)) this.play.setDisable(true);
		else this.play.setDisable(false); 
	}

	@FXML
	public void clickGrid(javafx.scene.input.MouseEvent event) {
		if(this.game.Current().equals(Tictactoe.CIRCLE))	return;
		Engine engine = new Engine();
	    Node clickedNode = event.getPickResult().getIntersectedNode();
	    if(clickedNode != grid ) {
	    	Integer colIndex = GridPane.getColumnIndex(clickedNode);
	        Integer rowIndex = GridPane.getRowIndex(clickedNode);
	        if(colIndex == null)colIndex = 0;
	        if(rowIndex == null)rowIndex = 0;
	        //System.out.println("Clique " + rowIndex + " et " + colIndex );
	        //System.out.println(game.getCase(rowIndex, colIndex).toString());
	        if(this.moveId.getId() == null)	{
	        	this.moveId.setId(Integer.toString(rowIndex)+Integer.toString(colIndex));
	        	Sparkling(rowIndex, colIndex);
	        	Gray(rowIndex, colIndex);
	        }
	        else {
	        	String mv = this.moveId.getId();
	        	int ri = Character.getNumericValue(mv.charAt(0));
	        	int ci = Character.getNumericValue(mv.charAt(1));
	        	//System.out.println("Premier clique "+ ri + " " + ci);
	        	if(engine.rule( game.Current(), ri, ci, rowIndex, colIndex, game)) {
	        		game.ConcretePlay(ri, ci , rowIndex , colIndex );
	        		this.game.switchPlayer();
	        		this.Refresh();
	        	}
	        	this.RefreshWithoutAI();
	        }
	    }
	}
	
	void AiPlay() {
    	Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
				if(game.getCurrent().equals(Tictactoe.CIRCLE))	{
					game.getAi().execute(game);
					game.switchPlayer();
				}
				return null;
			}
		};
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				// TODO Auto-generated method stub
				RefreshWithoutAI();
			}
		});
		new Thread(task).start();
	}	
	/*
	void AivsAi() {
    	Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
				if(game.getCurrentPlayer() instanceof PlayerAi) {
					(PlayerAi) game.getCurrentPlayer().execute(game);	
				}
				
					game.getAi().execute(game);
					game.switchPlayer();
				
				return null;
			}
		};
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				// TODO Auto-generated method stub
				Refresh();
				AivsAi()
			}
		});
		new Thread(task).start();
	}	
*/
	void Refresh() {
		this.current.setImage(this.game.getCurrent().getImage());
		for (int i = 0; i < gridImg.length; i++) {
			for (int j = 0; j < gridImg.length; j++) {
				gridImg[i][j].setImage(this.game.getBoard()[i][j].getImage());
				gridImg[i][j].setEffect(null); 
			}
		}
		
		btnUndo();
		btnPlay();
		this.moveId.setId(null);
		
		if(game.winCondition() != null) {
    		win();
    	}
		
		if(this.game.getCurrent().equals(Tictactoe.CROSS)) {
			setBoard(false);	
    		
		}
		else {
			setBoard(true);
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					AiPlay();
				}
			});
		}
	}
	
	void RefreshWithoutAI() {
		this.current.setImage(this.game.getCurrent().getImage());
		for (int i = 0; i < gridImg.length; i++) {
			for (int j = 0; j < gridImg.length; j++) {
				gridImg[i][j].setImage(this.game.getBoard()[i][j].getImage());
				gridImg[i][j].setEffect(null); 
			}
		}
		
		btnUndo();
		btnPlay();
		this.moveId.setId(null);
		
		if(game.winCondition() != null) {
    		win();
    	}
		
		if(this.game.getCurrent().equals(Tictactoe.CROSS)) {
			setBoard(false);	
		}
		else {
			setBoard(true);
		}
	}
	
	void Board() {
		for (int i = 0; i < gridImg.length; i++) {
			for (int j = 0; j < gridImg.length; j++) {
				if(i == 0 || i == 4 || j == 0 || j == 4) {}
				else gridImg[i][j].setMouseTransparent(true);
			}
		}
	}
	
	void setBoard(boolean value) {
		for (int i = 0; i < gridImg.length; i++) {
			for (int j = 0; j < gridImg.length; j++) {
				if(i == 0 || i == 4 || j == 0 || j == 4) gridImg[i][j].setMouseTransparent(value);
			}
		}
	}
	void Sparkling(int x, int y) {
		if(this.game.getCurrent().equals(Tictactoe.CIRCLE))	return ;
		Light.Spot L = new Light.Spot();
		L.setColor(Color.KHAKI);
	    L.setX(70); 
	    L.setY(70); 
	    L.setZ(70); 
	    Lighting lighting = new Lighting(); 
	    lighting.setLight(L);  
	    Engine e = new Engine();
		for (int i = 0; i < gridImg.length; i++) {
			for (int j = 0; j < gridImg.length; j++) {
				if(i == 0 || i == 4 || j == 0 || j == 4) if(e.rule(this.game.getCurrent(), x, y, i, j, this.game)) gridImg[i][j].setEffect(lighting); 
			}
		}
	}
	
	void Gray(int x, int y) {
		if(this.game.getCurrent().toString().equals("O"))	return ;
		Light.Spot L = new Light.Spot();

	    L.setX(70); 
	    L.setY(55); 
	    L.setZ(45); 
	    Lighting lighting = new Lighting(); 
	    lighting.setLight(L); 
		
		L.setColor(Color.GRAY);
		lighting.setLight(L);  
		gridImg[x][y].setEffect(lighting); 
	}
	
	@FXML
	void undo() {
		this.game.undoMove();
		RefreshWithoutAI();
	}
	
	@FXML
	void play() {
		AiPlay();
	}
	
	public void win()
	{

			Player name = null;
			if (this.game.winCondition() == Tictactoe.CIRCLE) 	name = this.game.getAi();
			else name = this.game.getHuman();

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Congratulations");
			alert.setHeaderText("The player "+ name.getName() + " with ["+ name.getSigne().toString() +"] won the game !");
			alert.setContentText("Choose your option.");

			ButtonType buttonTypeOne = new ButtonType("Try again");
			ButtonType buttonTypeTwo = new ButtonType("Back");
			ButtonType buttonTypeThree = new ButtonType("Quit");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			
			this.setBoard(true);
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne) {
				this.game = new Quixo();
				RefreshWithoutAI();
			}
			else if (result.get() == buttonTypeTwo) {
				try {
		    	Main main = Main.getInstance();
		    	FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../View/MenuView.fxml"));
		    	
				main.setRoot(loader.load());
				
		    	Scene scene = new Scene(main.getRoot());
		    	main.getWindow().setScene(scene);
		    	main.getWindow().show();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			} 
			else if (result.get() == buttonTypeThree) {
				Platform.exit();
				System.exit(0);
			}
			else {
				
			}
	}
}
