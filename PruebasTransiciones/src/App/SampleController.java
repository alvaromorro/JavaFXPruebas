package App;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class SampleController implements Initializable {
	
	@FXML
	private Button boton;
	
	private Main mainApp;
	

	public void pulsar(ActionEvent event){
		//ir a la siguiente vista
		System.out.println("Botón pulsado");
		mainApp.ChangeScene("segundaVista");

	}
	
	public void SetMainApp(Main mainApp){

		this.mainApp = mainApp;
		System.out.println(mainApp);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		boton.setOnAction(this::pulsar);
	}
	
}
