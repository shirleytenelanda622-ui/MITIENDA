package com.example.mitienda;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ComboBox<String> comboRol;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContraseña;
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<String> opciones = FXCollections.observableArrayList(
                "- Seleccionar -" ,
                "Administrador",
                "Vendedor",
                "Cajero"
        );
        comboRol.setItems(opciones);
    }
    @FXML
    public void Menu() throws Exception{
        if(txtUsuario.getText().equals("admin") && txtContraseña.getText().equals("1234") && "Administrador".equals(comboRol.getValue())){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuLateral.fxml"));
            Parent parent = fxmlLoader.load();
            MenuLateralController controller = fxmlLoader.getController();
            controller.Menu(txtUsuario.getText(), comboRol.getValue());
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
        }
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
