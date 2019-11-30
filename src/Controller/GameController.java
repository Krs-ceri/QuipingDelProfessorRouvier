package Controller;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import Model.Engine;
import Model.Quixo;
import Model.Tictactoe;


/*
 * Pattern utiliser: 
 * 			-MVC
 * 			-Observer
 * 			-strategie
 * 			-composite
 */

public class GameController implements Initializable{
	
	@FXML
	private GridPane grid;
	
	@FXML
	private AnchorPane f;
	
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


	Quixo game = new Quixo();
	Main main = Main.getInstance();
	
    @FXML
    void goBack(ActionEvent event) throws IOException
    {
		//this.game = null;
		Refresh();
		//this.setBoard(false);
		
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
		
		this.moveId.setVisible(true);
		this.back.setCancelButton(true);
		if(game.Current().toString().equals("X")) current.setImage(game.Current().getImage());
		else {
			current.setImage(game.Current().getImage());
			
		}
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

	@FXML
	public void clickGrid(javafx.scene.input.MouseEvent event) {
		Engine engine = new Engine();
	    Node clickedNode = event.getPickResult().getIntersectedNode();
	    if(clickedNode != grid ) {
	        
	    	//if(this.game.getCurrent().toString().equals("O"))	return ;

	    	Integer colIndex = GridPane.getColumnIndex(clickedNode);
	        Integer rowIndex = GridPane.getRowIndex(clickedNode);
	        if(colIndex == null)colIndex = 0;
	        if(rowIndex == null)rowIndex = 0;
	        
	        System.out.println("Clique " + rowIndex + " et " + colIndex );
	        System.out.println(game.getCase(rowIndex, colIndex).toString());
	        if(this.moveId.getId() == null)	{
	        	
	        	this.moveId.setId(Integer.toString(rowIndex)+Integer.toString(colIndex));
	        	Sparkling(rowIndex, colIndex);
	        	Gray(rowIndex, colIndex);
	        }
	        else {
		        //moveId.setId(Integer.toString(rowIndex) + Integer.toString(colIndex));
	        	//this.setPosPossible(rowIndex, colIndex);
		        //game.addTac(colIndex, rowIndex);
	        	String mv = this.moveId.getId();
	        	int ri = Character.getNumericValue(mv.charAt(0));
	        	int ci = Character.getNumericValue(mv.charAt(1));
	        	System.out.println("Premier clique "+ ri + " " + ci);
	        	if(engine.rule( game.Current(), ri, ci, rowIndex, colIndex, game)) {
	        		game.ConcretePlay(ri, ci , rowIndex , colIndex );
		        	//game.Print();
	        		this.Refresh();
		        	if(this.game.winCondition() != null) {
		        		this.win();
		        	}
		        	else {
			        	this.game.switchPlayer();
			        	this.Refresh();
		        	}	        	
	        	}
	        	else {
	        		this.Refresh();
	        	} 
	        }
	    }
	}
	
	void Sparkling(int x, int y) {
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
				if(e.rule(this.game.getCurrent(), x, y, i, j, this.game)) gridImg[i][j].setEffect(lighting); 
			}
		}
	}
	
	void Gray(int x, int y) {
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
	
	void Refresh() {

		for (int i = 0; i < gridImg.length; i++) {
			for (int j = 0; j < gridImg.length; j++) {
				gridImg[i][j].setImage(this.game.getBoard()[i][j].getImage());
				gridImg[i][j].setEffect(null); 
			}
		}
		
		this.current.setImage(this.game.getCurrent().getImage());
		/*if(this.game.getCurrent().equals(Tictactoe.CIRCLE))	{
			this.game.getAi().execute(game);
			this.game.switchPlayer();
			Refresh();
		}*/
		btnUndo();
		this.moveId.setId(null);
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
		this.a0.setMouseTransparent(value);
		this.a1.setMouseTransparent(value);
		this.a2.setMouseTransparent(value);
		this.a3.setMouseTransparent(value);
		this.a4.setMouseTransparent(value);
	
		this.b0.setMouseTransparent(value);
		this.b1.setMouseTransparent(value);
		this.b2.setMouseTransparent(value);
		this.b3.setMouseTransparent(value);
		this.b4.setMouseTransparent(value);
	
		this.c0.setMouseTransparent(value);
		this.c1.setMouseTransparent(value);
		this.c2.setMouseTransparent(value);
		this.c3.setMouseTransparent(value);
		this.c4.setMouseTransparent(value);
	
		this.d0.setMouseTransparent(value);
		this.d1.setMouseTransparent(value);
		this.d2.setMouseTransparent(value);
		this.d3.setMouseTransparent(value);
		this.d4.setMouseTransparent(value);
	
		this.e0.setMouseTransparent(value);
		this.e1.setMouseTransparent(value);
		this.e2.setMouseTransparent(value);
		this.e3.setMouseTransparent(value);
		this.e4.setMouseTransparent(value);
	}

	@FXML
	void undo() {
		this.game.undoMove();
		Refresh();
	}
	
	@FXML
	void play() {
		Refresh();
	}
	
	public void win()
	{
			String name;
			if (this.game.winCondition() == Tictactoe.CIRCLE) 	name = this.game.getAi().getName();
			else name = this.game.getHuman().getName();

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Congratulations");
			alert.setHeaderText(name + "  Le joueur "+ this.game.winCondition().toString() +" a gagner ");
			alert.setContentText("Choose your option.");

			ButtonType buttonTypeOne = new ButtonType("Recommencer");
			ButtonType buttonTypeTwo = new ButtonType("Back");
			ButtonType buttonTypeThree = new ButtonType("Quit");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			this.setBoard(true);
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne) {
				this.game = new Quixo();
				Refresh();
				this.setBoard(false);
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
	
	public void gameNull() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Fin de la aprtie");
		alert.setHeaderText("Match null ! Personne n'a gagner");
		alert.setContentText("Choose your option.");

		ButtonType buttonTypeOne = new ButtonType("Recommencer");
		ButtonType buttonTypeTwo = new ButtonType("Back");
		ButtonType buttonTypeThree = new ButtonType("Quit");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne)
		{
			this.game = new Quixo();
			Refresh();
			this.setBoard(false);
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
			// ... user chose CANCEL or closed the dialog
		}
	}
}
