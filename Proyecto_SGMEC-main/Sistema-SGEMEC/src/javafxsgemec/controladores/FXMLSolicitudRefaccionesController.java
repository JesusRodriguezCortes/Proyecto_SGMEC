/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgemec.controladores;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxsgemec.dao.ProveedorDAO;
import javafxsgemec.dao.RefaccionDAO;
import javafxsgemec.pojo.Proveedor;
import javafxsgemec.pojo.Refaccion;
import javafxsgemec.pojo.RefaccionComprada;
import javafxsgemec.util.ShowMessage;

/**
 * FXML Controller class
 *
 * @author je_zu
 */
public class FXMLSolicitudRefaccionesController implements Initializable {

    @FXML
    private Label lbCantidad;
    @FXML
    private ComboBox<Proveedor> cbProveedores;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbCorreoElectronico;
    @FXML
    private Label lbTelefono;
    @FXML
    private ComboBox<Refaccion> cbRefacciones;
    @FXML
    private Label lbNombreRefaccion;
    @FXML
    private Label lbTipoRefaccion;
    @FXML
    private Label lbPrecio;
    @FXML
    private Label lbPzasDisponibles;
    @FXML
    private TableView<RefaccionComprada> tbRefaccionesCompradas;
    @FXML
    private TableColumn tcNombreRefaccion;
    @FXML
    private TableColumn tcTipoRefaccion;
    @FXML
    private TableColumn tcPrecio;
    @FXML
    private TableColumn tcCantidad;
    @FXML
    private TableColumn tcPrecioNeto;
    @FXML
    private Label lbTotal;
    
    Document pedidoPDF = new Document();
    String destinoPDF = "C:\\Users\\je_zu\\Desktop\\Procesos\\PDFs\\pedido.pdf";
    int refaccionesCompradas = 0;
    float totalPedido = 0;
    private ObservableList<Proveedor> listaProveedores = FXCollections.observableArrayList();
    private ObservableList<Refaccion> listaRefacciones = FXCollections.observableArrayList();
    private ObservableList<RefaccionComprada> infoRefaccionesCompradas = FXCollections.observableArrayList();

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        lbCantidad.setText(String.valueOf(refaccionesCompradas));
        lbTotal.setText(String.valueOf(totalPedido));
        cargarInformacionProveedor();
        
   cbProveedores.valueProperty().addListener(new ChangeListener<Proveedor>(){
    @Override
    public void changed(ObservableValue<? extends Proveedor> observable, Proveedor oldValue, Proveedor newValue) {
        cbRefacciones.getSelectionModel().clearAndSelect(0);
            if(newValue != null){
                lbNombre.setText(newValue.getNombreProveedor());
                lbCorreoElectronico.setText(newValue.getCorreoElect());
                lbTelefono.setText(newValue.getTelefono());
                limpiarDatos();
                cargarInformacionRefaccionesFiltradas(newValue.getIdProovedor());
            }
        }  
    });
        
        
        cbRefacciones.valueProperty().addListener(new ChangeListener<Refaccion>() {
            @Override
            public void changed(ObservableValue<? extends Refaccion> observable, Refaccion oldValue, Refaccion newValue) {
                if(newValue != null){
                    lbNombreRefaccion.setText(newValue.getNombreRefaccion());
                    lbTipoRefaccion.setText(newValue.getTipoRefaccion());
                    lbPrecio.setText(String.valueOf(newValue.getPrecioCompra()));
                    lbPzasDisponibles.setText(String.valueOf(newValue.getPzasDisponiblesCompra()));
                    refaccionesCompradas = 0;
                    lbCantidad.setText(String.valueOf(refaccionesCompradas));
                }
            }
        });
    }    
    private void configurarTabla(){
        tcNombreRefaccion.setCellValueFactory(new PropertyValueFactory("refaccion"));
        tcTipoRefaccion.setCellValueFactory(new  PropertyValueFactory("tipoRefaccion"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory("precioCompra"));
        tcCantidad.setCellValueFactory(new PropertyValueFactory("refaccionesCompradas"));
        tcPrecioNeto.setCellValueFactory(new PropertyValueFactory("precioNetoRefacciones"));
        
    }
    
    private void cargarInformacionProveedor() {
        ArrayList<Proveedor> proveedoresRecuperados = ProveedorDAO.consultProveedores();
        if (proveedoresRecuperados != null) {
            if (!proveedoresRecuperados.isEmpty()) {
                listaProveedores.addAll(proveedoresRecuperados);
                cbProveedores.setItems(listaProveedores);
            }else{
                ShowMessage.showAlertSimple("Proveedores no encontrados", "No existen registros de proveedores en la base de datos", Alert.AlertType.WARNING);
            }
        }else{
            ShowMessage.showAlertSimple("Error de conexión", "Hubo un error al conectarse con la base de datos. Intentelo de nuevo mas tarde", Alert.AlertType.ERROR);
        }
    
    }
    
    private void cargarInformacionRefaccionesFiltradas(int idProveedor){
        ArrayList<Refaccion> refaccionesFiltradas = RefaccionDAO.consultRefaccionesProovedor(idProveedor);
        listaRefacciones.clear();
        if(refaccionesFiltradas != null){
            if (!refaccionesFiltradas.isEmpty()) {
                listaRefacciones.addAll(refaccionesFiltradas);
                cbRefacciones.setItems(listaRefacciones);
            }else{
                ShowMessage.showAlertSimple("Refacciones no encontradas", "No existen registros de refacciones del proveedor seleccionado", Alert.AlertType.WARNING);
            }
        }else{
            ShowMessage.showAlertSimple("Error de conexion", "Hubo un error al conectarse con la base de datos. Intentelo de nuevo mas tarde", Alert.AlertType.ERROR);
            
        }
    
    }
    
    private void limpiarDatos(){
        cbRefacciones.getSelectionModel().clearSelection();
        lbNombreRefaccion.setText("");
        lbTipoRefaccion.setText("");
        lbPrecio.setText("");
        lbCantidad.setText("0");
        lbPzasDisponibles.setText("0");
        refaccionesCompradas = 0;
        lbCantidad.setText(String.valueOf(refaccionesCompradas));
        totalPedido = 0;
        lbTotal.setText(String.valueOf(totalPedido));
        infoRefaccionesCompradas.clear();
    }

    @FXML
    private void btnAumentarCantidadRefacciones(ActionEvent event) {
        if (cbRefacciones.getSelectionModel().getSelectedIndex() != -1) {
          if(refaccionesCompradas < cbRefacciones.getSelectionModel().getSelectedItem().getPzasDisponiblesCompra()){
                refaccionesCompradas++;
                lbCantidad.setText(String.valueOf(refaccionesCompradas));       
            }   
        }
    }

    @FXML
    private void btnReducirCantidadRefacciones(ActionEvent event) {
        if(refaccionesCompradas > 0){
            refaccionesCompradas--;
            lbCantidad.setText(String.valueOf(refaccionesCompradas));
        }
    }

    @FXML
    private void btnAgregarRefaccion(ActionEvent event) {
        int validarProveedor = cbProveedores.getSelectionModel().getSelectedIndex();
        int validarRefaccion = cbRefacciones.getSelectionModel().getSelectedIndex();
        
        boolean verificarRefaccion = verificarRefaccionesAgregadas();

        
        
        if(validarProveedor != -1 && validarRefaccion != -1){
            if(verificarRefaccion == false){
                agregarRefaccionCompradaATabla();
            }else{
                ShowMessage.showAlertSimple("Refaccion agregada anteriormente", "La refaccion ya fue agregada, para modificar la piezas de compra seleccione la opcion Modificar", Alert.AlertType.WARNING);
            }
            
  
        }else{
            ShowMessage.showAlertSimple("Proveedor o refaccion no seleccionado(a)", "Seleccion un proveedor o refaccion para continuar", Alert.AlertType.WARNING);
        }
        
    }
    
    private void modificarRefaccionesCompradas(){
        
        int verificarSeleccion = verificarRefaccionReleccionada();
        
        if(verificarSeleccion >= 0){
            if(refaccionesCompradas > 0){
                totalPedido = totalPedido - infoRefaccionesCompradas.get(verificarSeleccion).getPrecioNetoRefacciones();
                infoRefaccionesCompradas.get(verificarSeleccion).setRefaccionesCompradas(refaccionesCompradas);
                infoRefaccionesCompradas.get(verificarSeleccion).setPrecioNetoRefacciones();
                totalPedido = totalPedido + infoRefaccionesCompradas.get(verificarSeleccion).getPrecioNetoRefacciones();
                tbRefaccionesCompradas.setItems(infoRefaccionesCompradas);
                tbRefaccionesCompradas.refresh();
                lbTotal.setText(String.valueOf(totalPedido));
            }else{
                ShowMessage.showAlertSimple("Dato faltante", "Ingrese el numero de refacciones que desea comprar", Alert.AlertType.WARNING);
            }
            
        }else{
            ShowMessage.showAlertSimple("Seleccion no valida", "Seleccione una refaccion del pedido para continuar", Alert.AlertType.WARNING);
        }
    }
    

    
    private boolean verificarRefaccionesAgregadas(){
        boolean status = false;
        for(int i = 0; i < infoRefaccionesCompradas.size(); i++){
            if(cbRefacciones.getSelectionModel().getSelectedItem().getIdRefaccion() == infoRefaccionesCompradas.get(i).getIdRefaccion()){
                status = true;
                return status;
            }
        }
        
        return  status;
    }
    
    private void agregarRefaccionCompradaATabla(){
        RefaccionComprada refaccionComprada = new RefaccionComprada();
        ArrayList<RefaccionComprada> listaRefaccionCompradas = new ArrayList<>();
        if(refaccionesCompradas > 0){
            refaccionComprada.setRefaccion(cbRefacciones.getSelectionModel().getSelectedItem());
            refaccionComprada.setRefaccionesCompradas(refaccionesCompradas);
            refaccionComprada.setPrecioNetoRefacciones();
            infoRefaccionesCompradas.add(refaccionComprada);
            tbRefaccionesCompradas.setItems(infoRefaccionesCompradas);
            totalPedido = totalPedido + refaccionComprada.getPrecioNetoRefacciones();
            lbTotal.setText(String.valueOf(totalPedido));
            limpiarDatosRefaccionSeleccionada();
            
        }else{
            ShowMessage.showAlertSimple("Dato faltante", "Ingrese el numero de refacciones que desea comprar", Alert.AlertType.WARNING);
        }
    }
    
    private void limpiarDatosRefaccionSeleccionada(){
        lbNombreRefaccion.setText("");
        lbTipoRefaccion.setText("");
        lbPrecio.setText("");
        lbPzasDisponibles.setText("0");
        refaccionesCompradas = 0;
        lbCantidad.setText(String.valueOf(refaccionesCompradas));
    }

    @FXML
    private void btnEliminarRefaccion(ActionEvent event) {
        eliminarRefaccionComprada();
        limpiarDatosRefaccionSeleccionada();
    }
    
    private void eliminarRefaccionComprada(){
        int verificarSeleccion = verificarRefaccionReleccionada();
        
        if(verificarSeleccion >= 0){
            totalPedido = totalPedido - infoRefaccionesCompradas.get(verificarSeleccion).getPrecioNetoRefacciones();
            lbTotal.setText(String.valueOf(totalPedido));
            infoRefaccionesCompradas.remove(verificarSeleccion);
            tbRefaccionesCompradas.setItems(infoRefaccionesCompradas);
        }else{
            ShowMessage.showAlertSimple("Seleccion no valida", "Seleccione una fila de la tabla para continuar", Alert.AlertType.WARNING);
        }
    }
    
    
    
    private int verificarRefaccionReleccionada(){
        int filaSeleccionada = tbRefaccionesCompradas.getSelectionModel().getSelectedIndex();
        return filaSeleccionada;
    }

    @FXML
    private void btnRealizarPedido(ActionEvent event) {
        if(!infoRefaccionesCompradas.isEmpty()){
            realizarPedido();
            modificarPzasDisponibles();
            crearPDF();
        }else{
            ShowMessage.showAlertSimple("No hay datos en el pedido", "Ingrese refacciones en el pedido para continuar", Alert.AlertType.WARNING);
        }
 
    }
    
    private void realizarPedido(){
       
    }
    
    private void modificarPzasDisponibles(){
        for(int i = 0; i<infoRefaccionesCompradas.size(); i++){
           int pzasRefaccionesGuardadas = infoRefaccionesCompradas.get(i).getRefaccion().getPzasDisponiblesCompra();
           int pzasRefaccionesCompradas = infoRefaccionesCompradas.get(i).getRefaccionesCompradas();
           int nuevasPzasDisponibles = pzasRefaccionesGuardadas - pzasRefaccionesCompradas;
           infoRefaccionesCompradas.get(i).getRefaccion().setPzasDisponiblesCompra(nuevasPzasDisponibles);
       }
    
    }
    
    private void guardarRefaccionesCompradas(){
    
    
    }
    
    
    
    
    private void crearPDF(){
         try {
            try {
                PdfWriter.getInstance(pedidoPDF, new FileOutputStream(destinoPDF));
                pedidoPDF.open();
                
                Phrase encabezado = new Phrase();
                encabezado.add("SGMEC\n"+ "Pedido de refacciones\n" + "numero de pedido: " + "" +"\n" + "Proveedor: " + cbProveedores.getSelectionModel().getSelectedItem().getNombreProveedor() + "\n\n");
                pedidoPDF.add(encabezado);
                
                crearTablaPDF();
                
                
                pedidoPDF.close();
                
                ShowMessage.showAlertSimple("Pedido Realizado", "Su pedido ha sido realizado con exito", Alert.AlertType.INFORMATION);
            } catch (DocumentException ex) {
                Logger.getLogger(FXMLSolicitudRefaccionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLSolicitudRefaccionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crearTablaPDF() throws DocumentException{
        PdfPTable tablaPedidoRefaccion = new PdfPTable(5);
                tablaPedidoRefaccion.setWidthPercentage(100);
                PdfPCell celdaNombreRefaccion = new PdfPCell(new Phrase("Nombre refaccion"));
                PdfPCell celdaTipoRefaccion = new PdfPCell(new Phrase("Tipo refaccion"));
                PdfPCell celdaPrecio = new PdfPCell(new Phrase("Precio"));
                PdfPCell celdaCantidad = new PdfPCell(new Phrase("Cantidad"));
                PdfPCell celdaPrecioNetoRefaccion = new PdfPCell(new Phrase("Precio neto"));
                
                tablaPedidoRefaccion.addCell(celdaNombreRefaccion);
                tablaPedidoRefaccion.addCell(celdaTipoRefaccion);
                tablaPedidoRefaccion.addCell(celdaPrecio);
                tablaPedidoRefaccion.addCell(celdaCantidad);
                tablaPedidoRefaccion.addCell(celdaPrecioNetoRefaccion);
                
                for(int i = 0; i< infoRefaccionesCompradas.size(); i++){
                    tablaPedidoRefaccion.addCell(infoRefaccionesCompradas.get(i).getNombreRefaccion());
                    tablaPedidoRefaccion.addCell(infoRefaccionesCompradas.get(i).getTipoRefaccion());
                    tablaPedidoRefaccion.addCell(String.valueOf(infoRefaccionesCompradas.get(i).getPrecioCompra()));
                    tablaPedidoRefaccion.addCell(String.valueOf(infoRefaccionesCompradas.get(i).getRefaccionesCompradas()));
                    tablaPedidoRefaccion.addCell(String.valueOf(infoRefaccionesCompradas.get(i).getPrecioNetoRefacciones()));
                }
                
                pedidoPDF.add(tablaPedidoRefaccion);
    }
    

    @FXML
    private void btnCancelar(ActionEvent event) {
        boolean respuestaDialogo = ShowMessage.showConfirmationDialog("Cancelar pedido", "¿Desea cancelar el pedido?");
        if(respuestaDialogo == true){
            limpiarDatos();
            cerrarVentana();
        }
    }
    
    @FXML
    private void btnModificarRefaccionComprada(ActionEvent event) {
        modificarRefaccionesCompradas();
    }
    
     @FXML
    private void clicSeleccionarRefaccion(MouseEvent event) {
       int verificarSeleccion = verificarRefaccionReleccionada();
        
        if(verificarSeleccion >= 0){
            lbNombreRefaccion.setText(String.valueOf(infoRefaccionesCompradas.get(verificarSeleccion).getNombreRefaccion()));
            lbTipoRefaccion.setText(String.valueOf(infoRefaccionesCompradas.get(verificarSeleccion).getTipoRefaccion()));
            lbPrecio.setText(String.valueOf(infoRefaccionesCompradas.get(verificarSeleccion).getPrecioCompra()));
            lbPzasDisponibles.setText(String.valueOf(infoRefaccionesCompradas.get(verificarSeleccion).getPzasDisponiblesCompra()));
            refaccionesCompradas = infoRefaccionesCompradas.get(verificarSeleccion).getRefaccionesCompradas();
            lbCantidad.setText(String.valueOf(refaccionesCompradas));
        }
    }
    
    private void cerrarVentana(){
        Stage escenario = (Stage) lbNombre.getScene().getWindow();
        escenario.close();
    }


}
