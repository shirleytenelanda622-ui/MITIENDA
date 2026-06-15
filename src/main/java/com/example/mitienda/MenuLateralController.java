package com.example.mitienda;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MenuLateralController implements Initializable{
    @FXML
    private ComboBox<String> comboRol;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtStock;
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblRol;
    @FXML
    private Label lblRol1;
    @FXML
    private Label lblUser;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private ComboBox<String> comboCategoria;
    @FXML
    private ComboBox<String> comboEstado;
    @FXML
    private TableColumn<Producto, Void> columnaAcciones;
    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> coCodigo;

    @FXML
    private TableColumn<Producto, String> coNombre;

    @FXML
    private TableColumn<Producto, String> coCategoria;

    @FXML
    private TableColumn<Producto, Double> coPrecio;

    @FXML
    private TableColumn<Producto, Integer> coStock;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableColumn<Producto, String> coEstado;


    @FXML
    public void Menu(String usuarios, String rol){
        lblUsuario.setText(usuarios);
        lblRol.setText(rol);
        lblRol1.setText(rol);
        lblUser.setText(usuarios);
    }
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Calzado",
                "Ropa",
                "Accesorios"
        );
        comboCategoria.setItems(opciones);
        ObservableList<String> opcion = FXCollections.observableArrayList(
                "Activo",
                "Inactivo"
        );
        comboEstado.setItems(opcion);
        columnaAcciones.setCellFactory(param -> new TableCell<>() {

            private final Button btnEditar = new Button();
            private final Button btnEliminar1 = new Button();
            private final HBox contenedorBotones = new HBox(8, btnEditar, btnEliminar1);

            {
                try {
                    javafx.scene.image.Image imgEditar = new javafx.scene.image.Image(getClass().getResourceAsStream("actualizar.png"));
                    javafx.scene.image.ImageView vistaEditar = new javafx.scene.image.ImageView(imgEditar);
                    vistaEditar.setFitHeight(16);
                    vistaEditar.setFitWidth(16);
                    btnEditar.setGraphic(vistaEditar);

                    javafx.scene.image.Image imgEliminar = new javafx.scene.image.Image(getClass().getResourceAsStream("eliminar.png"));
                    javafx.scene.image.ImageView vistaEliminar = new javafx.scene.image.ImageView(imgEliminar);
                    vistaEliminar.setFitHeight(16);
                    vistaEliminar.setFitWidth(16);
                    btnEliminar1.setGraphic(vistaEliminar);

                } catch (Exception e) {
                    System.out.println("Error al cargar las imágenes de los botones: " + e.getMessage());
                }


                contenedorBotones.setAlignment(javafx.geometry.Pos.CENTER);
                btnEditar.setStyle("-fx-cursor: hand;");
                btnEliminar1.setStyle("-fx-cursor: hand;");

                btnEditar.setOnAction(event -> {
                    Producto p = getTableView().getItems().get(getIndex());

                    if (p != null) {
                        producto = p;

                        txtCodigo.setText(p.getCodigo());
                        txtNombre.setText(p.getNombre());
                        txtPrecio.setText(String.valueOf(p.getPrecio()));
                        txtStock.setText(String.valueOf(p.getStock()));

                        comboCategoria.setValue(p.getCategoria());
                        comboEstado.setValue(p.getEstado());

                        btnGuardar.setDisable(true);
                        btnAtualizar.setDisable(false);
                        btnEliminar.setDisable(false);
                    }
                });

                btnEliminar1.setOnAction(event -> {
                    Producto p = getTableView().getItems().get(getIndex());
                    if (p != null) {
                        listaProductos.remove(p);
                        nuevo();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    int totalElementos = getTableView().getItems().size();
                    int indiceActual = getIndex();
                    if (indiceActual >= 0 && indiceActual < totalElementos) {
                        btnEditar.setStyle("-fx-background-color: #33739E;" + " -fx-cursor: hand;");
                        btnEliminar1.setStyle("-fx-background-color: #E3301C; " + "-fx-cursor: hand;");
                        setGraphic(contenedorBotones);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });

        coEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        coEstado.setCellFactory(new javafx.util.Callback<TableColumn<Producto, String>, TableCell<Producto, String>>() {
            @Override
            public TableCell<Producto, String> call(TableColumn<Producto, String> param) {
                return new TableCell<Producto, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                            setStyle("");
                        } else {
                            setText(item);
                            setAlignment(javafx.geometry.Pos.CENTER);

                            if ("Activo".equals(item)) {
                                setStyle("-fx-background-color: #7BF1A8; " +
                                        "-fx-text-fill: #07411D; " +
                                        "-fx-font-weight: bold; " +
                                        "-fx-background-radius: 5;");
                            } else {
                                setStyle("-fx-background-color: #FF8A99; " +
                                        "-fx-text-fill: #750010; " +
                                        "-fx-font-weight: bold; " +
                                        "-fx-background-radius: 5;");
                            }
                        }
                    }
                };
            }
        });

        coCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        coNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        coCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        coPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        coStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tablaProductos.setItems(listaProductos);

        tablaProductos.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, anterior, seleccionado) -> {
                    if(seleccionado != null){
                        producto = seleccionado;

                        txtCodigo.setText(seleccionado.getCodigo());
                        txtNombre.setText(seleccionado.getNombre());
                        txtPrecio.setText(String.valueOf(seleccionado.getPrecio()));
                        txtStock.setText(String.valueOf(seleccionado.getStock()));

                        comboCategoria.setValue(seleccionado.getCategoria());
                        comboEstado.setValue(seleccionado.getEstado());

                        btnAtualizar.setDisable(false);
                        btnEliminar.setDisable(false);
                    }
                });
    }

    @FXML
    private Producto producto = null;
    @FXML
    private ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    @FXML
    public void nuevo(){
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtStock.clear();
        comboCategoria.getSelectionModel().clearSelection();
        comboEstado.getSelectionModel().clearSelection();
        producto = null;
        btnGuardar.setDisable(false);
        btnAtualizar.setDisable(true);
        btnEliminar.setDisable(true);
        txtCodigo.requestFocus();
    }
    @FXML
    public void guardar(){

        Producto producto1 = new Producto(
                txtCodigo.getText(),
                txtNombre.getText(),
                comboCategoria.getValue(),
                comboEstado.getValue(),
                Double.parseDouble(txtPrecio.getText()),
                Integer.parseInt(txtStock.getText())
        );

        listaProductos.add(producto1);

    }
    @FXML
    public void actualizar(){
        int indice = listaProductos.indexOf(producto);
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String categoria = comboCategoria.getSelectionModel().getSelectedItem();
        double precio = Double.parseDouble(txtPrecio.getText());
        int stock = Integer.parseInt(txtStock.getText());
        String estado = comboEstado.getSelectionModel().getSelectedItem();
        Producto productoNuevo = new Producto(codigo, nombre, categoria, estado, precio, stock);

        listaProductos.set(indice, productoNuevo);
        tablaProductos.refresh();
    }

    @FXML
    public void eliminar(){
        listaProductos.remove(producto);
        nuevo();
    }
    @FXML
    public void limpiar(){
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtStock.clear();
        comboCategoria.getSelectionModel().clearSelection();
        comboEstado.getSelectionModel().clearSelection();
    }
    @FXML
    public void buscar(){
        String Buscar = txtBuscar.getText().toLowerCase();
        ObservableList<Producto> resultado = FXCollections.observableArrayList();
        for(Producto p: listaProductos){
            if(p.getNombre().toLowerCase().contains(Buscar) || p.getCodigo().toLowerCase().contains(Buscar)){
                resultado.add(p);
            }
        }
        tablaProductos.setItems(resultado);
    }
}
