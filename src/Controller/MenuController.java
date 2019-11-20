package Controller;



import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

import com.sun.javafx.scene.EnteredExitedHandler;

import Model.*; 
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


public class MenuController implements Initializable{

	Main main = Main.getInstance();

    @FXML
    private AnchorPane fenetre;
	@FXML
	private GridPane grid;
	
    @FXML
	void showSolo(ActionEvent event) throws IOException{
    	Main main = Main.getInstance();
    	
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../View/GameView.fxml"));
		main.setRoot(loader.load());

		Scene scene = new Scene(main.getRoot());
    	main.getWindow().setScene(scene);
    	main.getWindow().show();

	}
    

    
	
    @FXML
    void handleQuit(ActionEvent event)
    {
    	System.exit(0);
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{

		Main.getInstance().getWindow().setOnCloseRequest( event ->
		{
			Platform.exit();
			System.exit(0);
		});
	}
	

}
