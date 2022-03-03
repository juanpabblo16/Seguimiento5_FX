package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Usuario;

public class SampleController<ResourceBundle> {
	
	//TABLAS

	@FXML
    private TableView<Usuario> TablaIngesosGastos;
	
	@FXML
    private TableColumn<Usuario, String> ColumnaGastos;

    @FXML
    private TableColumn<Usuario, String> ColumnaIngresos;
    
    @FXML
    private TableColumn<Usuario, Date> ColumnaFecha;
    
    //ATRIBUTOS
    ObservableList<Usuario> usuarios; 
    
    private StringProperty fechaIngreso;
    
    private int posicionUsuarioEntabla;
    
    
    //TEXT FIELDS
    @FXML
    private TextField ValorText;
    
    @FXML
    private TextField GastoText;

    @FXML
    private TextField SeleccionadoText;
    
    //FECHA
    @FXML
    private DatePicker AnadirFecha;

    //BOTONES
    
    @FXML
    private Button añadirFecha;
    
    @FXML
    private Button añadirBT;
    
    /*@FXML
    private Button agregarGastoBT
 	*/

    @FXML
    private Button eliminarBT;
    
	
    public void initialize() {
    	
    	ColumnaGastos.setCellValueFactory(new PropertyValueFactory<>("ingreso"));
    	ColumnaIngresos.setCellValueFactory(new PropertyValueFactory<>("gasto"));

		
		ArrayList<Usuario> usuarioModel = new ArrayList<>();
		usuarioModel.add(new Usuario());
		usuarioModel.add(new Usuario());
		
		ObservableList<Usuario> users = FXCollections.observableArrayList(usuarioModel);
		
		TablaIngesosGastos.setItems(users);
		}
	

    //METODOS
   
    @FXML
    void agregarValores(ActionEvent event) {
    	
    	eliminarBT.setDisable(true);
    	añadirBT.setDisable(false);
    	añadirFecha.setDisable(false);
    	
    	Usuario usuario = new Usuario();
    	Usuario.ingreso.set(ValorText.getText());
    	Usuario.gasto.set(GastoText.getText());
    	//usuarios.fecha.set(AnadirFecha.getText());
    	
    	usuarios.add(usuario);
    	
    }

    /*@FXML
    void agregarIngreso(ActionEvent event) {

    }
    */

    @FXML
    void eliminarValor(ActionEvent event) {
    	
    	eliminarBT.setDisable(false);
    	añadirBT.setDisable(true);
    	añadirFecha.setDisable(true);
    	
    	usuarios.remove(posicionUsuarioEntabla);

    }
    
    @FXML
    void agregarFecha(ActionEvent event) {
    	
    	eliminarBT.setDisable(true);
    	añadirBT.setDisable(true);
    	añadirFecha.setDisable(false);
    	
    	fechaIngreso(null);
    	
    }
    
    //metodo escuchador a cambios en la tabla
    private final ListChangeListener<Usuario> selectorTablaUsuario = new ListChangeListener<Usuario>() {
		@Override
		public void onChanged(Change<? extends Usuario> c) {		
			
			seleccionTablaUsuario();
			
		}
    };
   
    //metodo seleccionador y retornador de la seleccion en tabla 
	public Usuario getTablaUsuarioSeleccionado() {
		
		if (TablaIngesosGastos != null) {
			
			List<Usuario> tabla = TablaIngesosGastos.getSelectionModel().getSelectedItems();
			
			if (tabla.size()==1) {
				final Usuario tablaSeleccionada = tabla.get(0);
				
				return tablaSeleccionada;
			}
			
		}
		
		return null;
	}
    
    private void seleccionTablaUsuario() {
    	
    	final Usuario usuario = getTablaUsuarioSeleccionado();
    	posicionUsuarioEntabla = usuarios.indexOf(usuario);
    	
    	if (usuario != null) {
    		
    		ValorText.setText(usuario.getIngreso());
    		GastoText.setText(usuario.getGasto());
    		
    		eliminarBT.setDisable(false);
    		añadirBT.setDisable(true);
    		
    	}
    	
    }
    
    private void fechaIngreso (String fechaIngreso) {	
    	
    	this.fechaIngreso = new SimpleStringProperty (fechaIngreso);
    	
    }
    
    public StringProperty FechaVenceProperty() {
        return fechaIngreso;
    }
    
    public static void mostrarFechaLicenciasVence(Connection connection, ObservableList<Usuario> infofechasvence) {
        try {
            java.sql.Statement instruccion = connection.createStatement();
            ResultSet resultado = instruccion.executeQuery("SELECT nombre, licenciaexpira FROM conductores");
            while (resultado.next()) {
                infofechasvence.add(
                        new Usuario(
                        )
                );
            }
        } catch (SQLException e){

        }
        
    }
    
    
    	
    	/*@Override
        	
    	private void initialize(URL url, ResourceBundle rb) {
    		
    		this.inicializarTabla();
    		
    		eliminarBT.setDisable(true);
    		
    		
    		// Seleccionar tuplas en tabla 
    		
    		final ObservableList<Usuario>TablaIngresosGastosSel = TablaIngesosGastos.getSelectionModel().getSelectedItems();
    		
    	}
    	
    	
    	
    }
    */

	@SuppressWarnings("unused")
	
	private void inicializarTabla() {
		
		ColumnaGastos.setCellValueFactory(new PropertyValueFactory<Usuario, String>("valor"));
		ColumnaIngresos.setCellValueFactory(new PropertyValueFactory<Usuario, String>("ingreso"));
		ColumnaFecha.setCellValueFactory(new PropertyValueFactory<Usuario, Date>("fecha"));
		
		usuarios = FXCollections.observableArrayList();
		TablaIngesosGastos.setItems(usuarios);
		
	}

	   //GET AND SET
    

		public ListChangeListener<Usuario> getSelectorTablaUsuario() {
			return selectorTablaUsuario;
		}

		public StringProperty getFechaIngreso() {
			return fechaIngreso;
		}

		public void setFechaIngreso(StringProperty fechaIngreso) {
			this.fechaIngreso = fechaIngreso;
		}

		@Override
		public String toString() {
			
			return super.toString();
		}
	
}
