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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

import org.omg.PortableInterceptor.SUCCESSFUL;


import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import Model.Engine;
import Model.Quixo;
import Model.Tictactoe;
import images.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.scene.EnteredExitedHandler;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.omg.PortableInterceptor.SUCCESSFUL;

import com.sun.prism.shader.Texture_Color_AlphaTest_Loader;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;

import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.WindowEvent;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;

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
	
    /*
    final TableColumn<Car, String> brandColumn = new TableColumn<>("Marque"); 
    final TableColumn<Car, Color> colorColumn = new TableColumn<>("Couleur"); 
    final TableColumn<Car, Integer> seatsColumn = new TableColumn<>("Si�ges"); 
    final TableColumn<Car, Integer> doorsColumn = new TableColumn<>("Portes"); 
    tableView.getColumns().addAll(brandColumn, colorColumn, seatsColumn, doorsColumn);*/
	
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
	private ImageView current;
	
	private ImageView[][] gridImg;


	Quixo game = new Quixo();
	Main main = Main.getInstance();
	
    @FXML
    void goBack(ActionEvent event) throws IOException
    {
		this.game.Reset();
		this.eraseImage();
		this.ableBoard();
		
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

		if(game.Current().toString().equals("X")) current.setImage(game.Current().getImage());
		else current.setImage(game.Current().getImage());
		
		this.Refresh();
		
		Main.getInstance().getWindow().setOnCloseRequest( event ->
		{
			Platform.exit();
			System.exit(0);
		}); 
	}

	@FXML
	public void clickGrid(javafx.scene.input.MouseEvent event) {
		Engine engine = new Engine();
	    Node clickedNode = event.getPickResult().getIntersectedNode();
	    if(clickedNode != grid ) {
	        
	    	if(this.game.getCurrent().toString().equals("O"))	return ;
	        
	    	Integer colIndex = GridPane.getColumnIndex(clickedNode);
	        Integer rowIndex = GridPane.getRowIndex(clickedNode);
	        if(colIndex == null)colIndex = 0;
	        if(rowIndex == null)rowIndex = 0;
	        
	        System.out.println("Clique " + rowIndex + " et " + colIndex );
	        System.out.println(game.getCase(rowIndex, colIndex).toString());
	        if(this.moveId.getId() == null)	this.moveId.setId(Integer.toString(rowIndex)+Integer.toString(colIndex));
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
	/*
	public void setPosPossible(int i, int j) {
		Engine g = new Engine();
		for (int j2 = 0; j2 < gridImg.length; j2++) {
			for (int k = 0; k < gridImg.length; k++) {
				if(g.verifyTictactoeSecond(i, j, j2, k, game)) gridImg[j2][k].setStyle("-fx-background-color: #fff7ad;");
				else  gridImg[j2][k].setStyle("-fx-background-color: #000000;");

			}
		}
	}*/
	

	void disableBoard() {
		this.a0.setMouseTransparent(true);
		this.a1.setMouseTransparent(true);
		this.a2.setMouseTransparent(true);
		this.a3.setMouseTransparent(true);
		this.a4.setMouseTransparent(true);
	
		this.b0.setMouseTransparent(true);
		this.b1.setMouseTransparent(true);
		this.b2.setMouseTransparent(true);
		this.b3.setMouseTransparent(true);
		this.b4.setMouseTransparent(true);
	
		this.c0.setMouseTransparent(true);
		this.c1.setMouseTransparent(true);
		this.c2.setMouseTransparent(true);
		this.c3.setMouseTransparent(true);
		this.c4.setMouseTransparent(true);
	
		this.d0.setMouseTransparent(true);
		this.d1.setMouseTransparent(true);
		this.d2.setMouseTransparent(true);
		this.d3.setMouseTransparent(true);
		this.d4.setMouseTransparent(true);
	
		this.e0.setMouseTransparent(true);
		this.e1.setMouseTransparent(true);
		this.e2.setMouseTransparent(true);
		this.e3.setMouseTransparent(true);
		this.e4.setMouseTransparent(true);
	}
	
	void ableBoard()
	{
		this.a0.setMouseTransparent(false);
		this.a1.setMouseTransparent(false);
		this.a2.setMouseTransparent(false);
		this.a3.setMouseTransparent(false);
		this.a4.setMouseTransparent(false);
	
		this.b0.setMouseTransparent(false);
		this.b1.setMouseTransparent(false);
		this.b2.setMouseTransparent(false);
		this.b3.setMouseTransparent(false);
		this.b4.setMouseTransparent(false);
	
		this.c0.setMouseTransparent(false);
		this.c1.setMouseTransparent(false);
		this.c2.setMouseTransparent(false);
		this.c3.setMouseTransparent(false);
		this.c4.setMouseTransparent(false);
	
		this.d0.setMouseTransparent(false);
		this.d1.setMouseTransparent(false);
		this.d2.setMouseTransparent(false);
		this.d3.setMouseTransparent(false);
		this.d4.setMouseTransparent(false);
	
		this.e0.setMouseTransparent(false);
		this.e1.setMouseTransparent(false);
		this.e2.setMouseTransparent(false);
		this.e3.setMouseTransparent(false);
		this.e4.setMouseTransparent(false);
	}
	
	void eraseImage()
	{
		this.a0.setImage(null);
		this.a1.setImage(null);
		this.a2.setImage(null);
		this.a3.setImage(null);
		this.a4.setImage(null);
		
		this.b0.setImage(null);
		this.b1.setImage(null);
		this.b2.setImage(null);
		this.b3.setImage(null);
		this.b4.setImage(null);
		
		this.c0.setImage(null);
		this.c1.setImage(null);
		this.c2.setImage(null);
		this.c3.setImage(null);
		this.c4.setImage(null);
		
		this.d0.setImage(null);
		this.d1.setImage(null);
		this.d2.setImage(null);
		this.d3.setImage(null);
		this.d4.setImage(null);
		
		this.e0.setImage(null);
		this.e1.setImage(null);
		this.e2.setImage(null);
		this.e3.setImage(null);
		this.e4.setImage(null);
	}
	
	void Refresh() {

		for (int i = 0; i < gridImg.length; i++) {
			for (int j = 0; j < gridImg.length; j++) {
				gridImg[i][j].setImage(this.game.getBoard()[i][j].getImage());
			}
		}
		
		this.current.setImage(this.game.getCurrent().getImage());
		
		this.moveId.setId(null);
	}
	
	/*
	
	@FXML
	void a0(ActionEvent event) {
		//a0.setDisable(true);
		if(this.game.isEmpty(0, 0)) {
		if(this.game.Current().toString().equals("X")) {
			a0.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/x.png", a0.getWidth(), a0.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		else {
			
			a0.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/o.png", a0.getWidth(), a0.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		this.game.addTac(this.game.Current(), 0, 0);
		if(this.game.winCondition() != null)
		{
			this.afficheTrait(this.game.WinningAnim());

			this.win() ;
		}
		else if(this.game.nulRound())
		{
			this.gameNull() ;
		}
		if(game.Current().toString().equals("X")) current.setImage(croix);
		else current.setImage(cercle);
		}
	}*/
	
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
			this.disableBoard();
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne)
			{
				this.game.Reset();
				this.eraseImage();
				this.ableBoard();
			}
			else if (result.get() == buttonTypeTwo) 
			{
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
			else if (result.get() == buttonTypeThree) 
			{
				Platform.exit();
				System.exit(0);
			}
			else 
			{
				
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
			this.game.Reset();
			this.eraseImage();
			this.ableBoard();
		}
		else if (result.get() == buttonTypeTwo) 
		{
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
		else if (result.get() == buttonTypeThree) 
		{
			Platform.exit();
			System.exit(0);
		}
		else 
		{
			// ... user chose CANCEL or closed the dialog
		}
	}
}
