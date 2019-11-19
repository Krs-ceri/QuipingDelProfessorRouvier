package Controller;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import Model.Quixo;
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



public class GameController implements Initializable{
	
	@FXML
	private GridPane grid;
	@FXML
	private TableView<?> table;
    /*
    final TableColumn<Car, String> brandColumn = new TableColumn<>("Marque"); 
    final TableColumn<Car, Color> colorColumn = new TableColumn<>("Couleur"); 
    final TableColumn<Car, Integer> seatsColumn = new TableColumn<>("Sièges"); 
    final TableColumn<Car, Integer> doorsColumn = new TableColumn<>("Portes"); 
    tableView.getColumns().addAll(brandColumn, colorColumn, seatsColumn, doorsColumn);*/
	
	@FXML
	private AnchorPane f;
	
	@FXML
	private Button a0;
	
	@FXML
	private Button a1;
	@FXML
	private Button a2;
	@FXML
	private Button b0;
	@FXML
	private Button b1;
	@FXML
	private Button b2;
	@FXML
	private Button c0;
	@FXML
	private Button c1;
	@FXML
	private Button c2;
	@FXML
	private ImageView current;
	


	Image cercle = new Image("images/o.png") ;
	Image croix = new Image("images/x.png") ;
	Quixo game = Quixo.getInstance();
	Main main = Main.getInstance();
	
    @FXML
    void goBack(ActionEvent event) throws IOException
    {
    	Main main = Main.getInstance();
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../View/APIView.fxml"));
    	main.setRoot(loader.load());

    	Scene scene = new Scene(main.getRoot());
    	main.getWindow().setScene(scene);
    	main.getWindow().show();
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(game.Current().toString().equals("X")) current.setImage(croix);
		else current.setImage(cercle);
		Main.getInstance().getWindow().setOnCloseRequest( event ->
		{
			Platform.exit();
			System.exit(0);
		}); 
	}
	
	
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
	}
	
	@FXML
	void a1(ActionEvent event) {
			//a1.setDisable(true);
		if(this.game.isEmpty(0, 1)) {
			if(this.game.Current().toString().equals("X")) {
				a1.setBackground(
					new Background(
						new BackgroundImage(
							new Image("images/x.png", a1.getWidth(), a1.getHeight(), false, true)
								,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
								, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
			}
			else {
				a1.setBackground(
					new Background(
						new BackgroundImage(
							new Image("images/o.png", a1.getWidth(), a1.getHeight(), false, true)
								,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
								, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
			}
			this.game.addTac(this.game.Current(), 0, 1);
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
	}
	@FXML
	void a2(ActionEvent event) {
		//a2.setDisable(true);
		if(this.game.isEmpty(0, 2)) {
		if(this.game.Current().toString().equals("X")) {
			a2.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/x.png", a2.getWidth(), a2.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		else {
			a2.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/o.png", a1.getWidth(), a1.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		this.game.addTac(this.game.Current(), 0, 2);
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
	}
	@FXML
	void b0(ActionEvent event) {
		//b0.setDisable(true);
		
		if(this.game.isEmpty(1, 0)) {
			if(this.game.Current().toString().equals("X")) {
				b0.setBackground(
					new Background(
						new BackgroundImage(
							new Image("images/x.png", b0.getWidth(), b0.getHeight(), false, true)
								,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
								, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
			}
			else {
				b0.setBackground(
					new Background(
						new BackgroundImage(
								new Image("images/o.png", b0.getWidth(), b0.getHeight(), false, true)
								,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
								, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
			}
			this.game.addTac(this.game.Current(), 1, 0);
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
		//------------------------------------------------------------------------------------------------------at use
		//a0.getStyleClass().add("image");
	}
	
	@FXML
	void b1(ActionEvent event) {
		//b1.setDisable(true);
		if(this.game.isEmpty(1, 1)) {
		if(this.game.Current().toString().equals("X")) {
			b1.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/x.png", b1.getWidth(), b1.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		else {
			b1.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/o.png", b1.getWidth(), b1.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		this.game.addTac(this.game.Current(), 1, 1);
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
	}
	
	@FXML
	void b2(ActionEvent event) {
		//b2.setDisable(true);
		if(this.game.isEmpty(1, 2)) {
		if(this.game.Current().toString().equals("X")) {
			b2.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/x.png", b2.getWidth(), b2.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		else {
			b2.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/o.png", b2.getWidth(), b2.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		this.game.addTac(this.game.Current(), 1, 2);
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
	}
	@FXML
	void c0(ActionEvent event) {
		//c0.setDisable(true);
		if(this.game.isEmpty(2, 0)) {
		if(this.game.Current().toString().equals("X")) {
			c0.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/x.png", c0.getWidth(), c0.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		else {
			c0.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/o.png", c0.getWidth(), c0.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		this.game.addTac(this.game.Current(), 2, 0);
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
	}
	
	@FXML
	void c1(ActionEvent event) {
		//c1.setDisable(true);
		if(this.game.isEmpty(2, 1)) {
		if(this.game.Current().toString().equals("X")) {
			c1.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/x.png", c1.getWidth(), c1.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		else {
			c1.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/o.png", c1.getWidth(), c1.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		this.game.addTac(this.game.Current(), 2, 1);
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
	}
	
	@FXML
	void c2(ActionEvent event) {
		//c2.setDisable(true);
		if(this.game.isEmpty(2, 2)) {
		if(this.game.Current().toString().equals("X")) {
			c2.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/x.png", c2.getWidth(), c2.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		else {
			c2.setBackground(
				new Background(
					new BackgroundImage(
						new Image("images/o.png", c2.getWidth(), c2.getHeight(), false, true)
							,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT
							, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		}
		this.game.addTac(this.game.Current(), 2, 2);
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
	}
	
	public void gameNull()
	{
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
			this.eraseImage() ;
		}
		else if (result.get() == buttonTypeTwo) 
		{
			// ... user chose "Two"
		} 
		else if (result.get() == buttonTypeThree) 
		{
			// ... user chose "Three"
		}
		else 
		{
			// ... user chose CANCEL or closed the dialog
		}
	}
	void disableBoard()
	{
		this.a0.setMouseTransparent(true);
		this.a1.setMouseTransparent(true);
		this.a2.setMouseTransparent(true);
		this.b0.setMouseTransparent(true);
		this.b1.setMouseTransparent(true);
		this.b2.setMouseTransparent(true);
		this.c0.setMouseTransparent(true);
		this.c1.setMouseTransparent(true);
		this.c2.setMouseTransparent(true);
	}
	
	void ableBoard()
	{
		this.a0.setMouseTransparent(false);
		this.a1.setMouseTransparent(false);
		this.a2.setMouseTransparent(false);
		this.b0.setMouseTransparent(false);
		this.b1.setMouseTransparent(false);
		this.b2.setMouseTransparent(false);
		this.c0.setMouseTransparent(false);
		this.c1.setMouseTransparent(false);
		this.c2.setMouseTransparent(false);
	}
	
	void eraseImage()
	{
		this.a0.setBackground(null);
		this.a1.setBackground(null);
		this.a2.setBackground(null);
		this.b0.setBackground(null);
		this.b1.setBackground(null);
		this.b2.setBackground(null);
		this.c0.setBackground(null);
		this.c1.setBackground(null);
		this.c2.setBackground(null);



		
	}
	//
	public void win()
	{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Congratulations");
			alert.setHeaderText("Le joueur "+ this.game.winCondition().toString() +" a gagner ");
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
				loader.setLocation(getClass().getResource("../View/APIView.fxml"));
		    	
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
}
