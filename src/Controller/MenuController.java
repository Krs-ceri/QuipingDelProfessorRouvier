package Controller;


import Controller.Main;
import Model.Tictactoe;
import Players.Factory;
import Players.PlayerHumain;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;



public class MenuController implements Initializable{

	Main main = Main.getInstance();

    @FXML
    private AnchorPane fenetre;
    @FXML
    private Button solo;
    @FXML
    private Button quit;
    @FXML
    private ComboBox<String> playerID;
    @FXML
    private Label lbl;
    @FXML
    private TextField user;
    private String userx;
    private Factory ftp = new Factory();
	
    @FXML
	void showSolo(ActionEvent event) throws IOException{
    	Main main = Main.getInstance();
    	userx = user.getText().toString();
		if(userx.isEmpty())	userx = "userX";
    	main.setHuman(new PlayerHumain(userx, Tictactoe.CROSS));
    	main.setAi(ftp.getPlayer(playerID.getValue()));
    	
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
		
		this.quit.setCancelButton(true);
		this.solo.setDefaultButton(true);
    	playerID.setItems(main.getData()); 
    	playerID.getSelectionModel().selectFirst();
    	
		Main.getInstance().getWindow().setOnCloseRequest( event ->
		{
			Platform.exit();
			System.exit(0);
		});
	}





	

}
