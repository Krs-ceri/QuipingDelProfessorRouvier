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
	private GridPane[][] grid;
	
    /*
    final TableColumn<Car, String> brandColumn = new TableColumn<>("Marque"); 
    final TableColumn<Car, Color> colorColumn = new TableColumn<>("Couleur"); 
    final TableColumn<Car, Integer> seatsColumn = new TableColumn<>("Sièges"); 
    final TableColumn<Car, Integer> doorsColumn = new TableColumn<>("Portes"); 
    tableView.getColumns().addAll(brandColumn, colorColumn, seatsColumn, doorsColumn);*/
	
	@FXML
	private AnchorPane f;
	
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
	


	Image cercle = new Image("images/o.png") ;
	Image croix = new Image("images/x.png") ;
	Quixo game = new Quixo();
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
	
	public void clickGrid(javafx.scene.input.MouseEvent event) {
	    Node clickedNode = event.getPickResult().getIntersectedNode();

	        // click on descendant node
	        Integer colIndex = GridPane.getColumnIndex(clickedNode);
	        Integer rowIndex = GridPane.getRowIndex(clickedNode);
	        System.out.println("Mouse clicked cell: " + colIndex + " And: " + rowIndex);

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
		this.a0.setImage(this.game.getBoard()[0][0].getImage());
		this.a1.setImage(this.game.getBoard()[1][0].getImage());
		this.a2.setImage(this.game.getBoard()[2][0].getImage());
		this.a3.setImage(this.game.getBoard()[3][0].getImage());
		this.a4.setImage(this.game.getBoard()[4][0].getImage());
		
		this.b0.setImage(this.game.getBoard()[0][1].getImage());
		this.b1.setImage(this.game.getBoard()[1][1].getImage());
		this.b2.setImage(this.game.getBoard()[2][1].getImage());
		this.b3.setImage(this.game.getBoard()[3][1].getImage());
		this.b4.setImage(this.game.getBoard()[4][1].getImage());
		
		this.c0.setImage(this.game.getBoard()[0][2].getImage());
		this.c1.setImage(this.game.getBoard()[1][2].getImage());
		this.c2.setImage(this.game.getBoard()[2][2].getImage());
		this.c3.setImage(this.game.getBoard()[3][2].getImage());
		this.c4.setImage(this.game.getBoard()[4][2].getImage());
		
		this.d0.setImage(this.game.getBoard()[0][3].getImage());
		this.d1.setImage(this.game.getBoard()[1][3].getImage());
		this.d2.setImage(this.game.getBoard()[2][3].getImage());
		this.d3.setImage(this.game.getBoard()[3][3].getImage());
		this.d4.setImage(this.game.getBoard()[4][3].getImage());
		
		this.e0.setImage(this.game.getBoard()[0][4].getImage());
		this.e1.setImage(this.game.getBoard()[1][4].getImage());
		this.e2.setImage(this.game.getBoard()[2][4].getImage());
		this.e3.setImage(this.game.getBoard()[3][4].getImage());
		this.e4.setImage(this.game.getBoard()[4][4].getImage());
	}
	
	
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
