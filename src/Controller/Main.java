package Controller;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


import javafx.fxml.FXMLLoader;
import java.io.IOException;



public class Main extends Application {
	
	
	private Stage window;
	private AnchorPane root;
	
	private static volatile Main instance = null;
	
	public Main(){
	    super();
	    synchronized(Main.class){
	        if(instance != null) throw new UnsupportedOperationException(
	                getClass()+" is singleton but constructor called more than once");
	        instance = this;
	    }
	}
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		window.setTitle("Quixo CERI");	
		Image tempImage = null;
		try {
            tempImage = new Image("images/menu.png");
       } catch (Exception e) {
            tempImage = null;
       }
		window.getIcons().add(tempImage);
		try
		{
			// Permet de charger le menuPrincipal.fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../View/MenuView.fxml"));
			root = (AnchorPane) loader.load();

			// Affiche la scne du menu
			Scene scene = new Scene(root);
			window.setScene(scene);
			window.setResizable(false);
			window.show();
	
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.err.println("Impossible d'afficher le menu");
		}
	}
	
	public final static Main getInstance()
	{
		if(Main.instance == null)
		{
			synchronized(Main.class)
			{
				if(Main.instance == null)
				{
					Main.instance = new Main();
				}
			}
		}
		return Main.instance;
	}
	

	public Stage getWindow() {
		return window;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}

	public AnchorPane getRoot() {
		return root;
	}

	public void setRoot(AnchorPane root) {
		this.root = root;
	}

	public static void main(String[] args) {
        launch(args);
    }

}
