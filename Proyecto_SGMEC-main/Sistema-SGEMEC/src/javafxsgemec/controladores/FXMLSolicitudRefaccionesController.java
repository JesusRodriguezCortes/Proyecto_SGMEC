/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsgemec.controladores;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafxsgemec.dao.PedidoRefaccionDAO;
import javafxsgemec.dao.ProveedorDAO;
import javafxsgemec.dao.RefaccionCompradaDAO;
import javafxsgemec.dao.RefaccionDAO;
import javafxsgemec.dao.SucursalDAO;
import javafxsgemec.javafxsgemec;
import javafxsgemec.pojo.PedidoRefaccion;
import javafxsgemec.pojo.Proveedor;
import javafxsgemec.pojo.Refaccion;
import javafxsgemec.pojo.RefaccionComprada;
import javafxsgemec.pojo.Sucursal;
import javafxsgemec.util.ResultadoOperacion;
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
    @FXML
    private ComboBox<Sucursal> cbDireccionDeEntrega;
    @FXML
    private Label lbDireccionDeEntrega;

    private String destinoPDF = "";
    private String ficheroFinal = "";
    private String direccionEntrega = "";
    private int refaccionesCompradas = 0;
    private int numeroPedido = 0;
    private float totalPedido = 0;;
    private LocalDate fechaActual = null;
    private ObservableList<Proveedor> listaProveedores = FXCollections.observableArrayList();
    private ObservableList<Refaccion> listaRefacciones = FXCollections.observableArrayList();
    private ObservableList<RefaccionComprada> infoRefaccionesCompradas = FXCollections.observableArrayList();
    private ObservableList<Sucursal> listaSucursales = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        lbCantidad.setText(String.valueOf(refaccionesCompradas));
        lbTotal.setText(String.valueOf(totalPedido));
        cargarInformacionProveedor();
        cargarDireccionesSucursales();
        
        cbProveedores.valueProperty().addListener(new ChangeListener<Proveedor>(){
            @Override
            public void changed(ObservableValue<? extends Proveedor> observable, Proveedor oldValue, Proveedor newValue) {
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
        
        cbDireccionDeEntrega.valueProperty().addListener(new ChangeListener<Sucursal>(){
            @Override
            public void changed(ObservableValue<? extends Sucursal> observable, Sucursal oldValue, Sucursal newValue) {
                if(newValue != null){
                    lbDireccionDeEntrega.setText(newValue.getDireccionSucursal());
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
    
     private void cargarDireccionesSucursales(){
        ArrayList<Sucursal> sucursalesRecuperadas = SucursalDAO.consultSucurales();
        if (sucursalesRecuperadas != null) {
            if (!sucursalesRecuperadas.isEmpty()) {
                listaSucursales.addAll(sucursalesRecuperadas);
                cbDireccionDeEntrega.setItems(listaSucursales);
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
        cbRefacciones.getSelectionModel().select(-1);
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
        
        if(validarProveedor != -1 && validarRefaccion != -1 ){
            boolean verificarRefaccion = verificarRefaccionesAgregadas();
            if(verificarRefaccion == false){
                agregarRefaccionCompradaATabla();
            }else{
                ShowMessage.showAlertSimple("Refaccion agregada anteriormente", "La refaccion ya fue agregada, para modificar la piezas de compra seleccione la opcion Modificar", Alert.AlertType.WARNING);
            }
            
  
        }else{
            ShowMessage.showAlertSimple("Proveedor o refaccion no seleccionado(a)", "Seleccione un proveedor o refaccion para continuar", Alert.AlertType.WARNING);
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
                limpiarDatosRefaccionSeleccionada();
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
        cbRefacciones.getSelectionModel().select(-1);
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
            if(cbDireccionDeEntrega.getSelectionModel().getSelectedIndex() != -1){
                direccionEntrega = cbDireccionDeEntrega.getSelectionModel().getSelectedItem().getDireccionSucursal();
                
                boolean status = realizarPedido();
                System.out.println(String.valueOf(status));
                if(status == true){
                    modificarPzasDisponibles();
                    guardarRefaccionesCompradas();
                    crearPDF();
                    ShowMessage.showAlertSimple("Pedido registrado", "El pedido " + numeroPedido + " ha sido generado y guardado con exito", Alert.AlertType.INFORMATION);   
                    limpiarTodosLosCampos();
                }
            }else{
                ShowMessage.showAlertSimple("Dato faltante", "Ingrese la direccion de entrega para continuar", Alert.AlertType.INFORMATION);
            }
        }else{
            ShowMessage.showAlertSimple("No hay datos en el pedido", "Seleccione refacciones en el pedido para continuar", Alert.AlertType.WARNING);
        }
 
    }
   
    
    private void limpiarTodosLosCampos(){
        cbRefacciones.getSelectionModel().select(-1);
        lbNombre.setText("");
        lbCorreoElectronico.setText("");
        lbTelefono.setText("");
        lbDireccionDeEntrega.setText("");
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
        ficheroFinal = "";
        numeroPedido = 0;
        listaRefacciones.clear();
        cbDireccionDeEntrega.getSelectionModel().select(-1);
        cbProveedores.getSelectionModel().select(-1);
   
        
    }
    
    private boolean realizarPedido(){
        boolean status = false;
        ArrayList<String> numerosPedidoRecuperados = new ArrayList<>();

        PedidoRefaccion nuevoPedido = new PedidoRefaccion();
        fechaActual = LocalDate.now();
        numerosPedidoRecuperados = PedidoRefaccionDAO.obtenerNumeroPedidos();
        numeroPedido = (int) Math.floor(Math.random() * (9999 - 1000 +1)) + 1000;

        if(numerosPedidoRecuperados != null){
            for(int i = 0; i < numerosPedidoRecuperados.size();i++){
                if(String.valueOf(numeroPedido) == numerosPedidoRecuperados.get(i)){
                    numeroPedido = (int) Math.floor(Math.random() * (9999 - 1000 +1)) + 1000;
                    System.out.println(String.valueOf(numeroPedido));
                    i = 0;
                }
            }
        }else{
            ShowMessage.showAlertSimple("Error de conexion", "Hubo un erros con la base de datos. Intentelo de nuevo mas tarde", Alert.AlertType.NONE);
        }
            nuevoPedido.setNumeroPedido(String.valueOf(numeroPedido));
            nuevoPedido.setFechaPedido(String.valueOf(fechaActual));
            nuevoPedido.setTotalPedido(totalPedido);
            nuevoPedido.setDireccionEntrega(direccionEntrega);
            status = registrarPedido(nuevoPedido);
            
            return status;

    }
    
    private boolean registrarPedido(PedidoRefaccion pedidoNuevo){
        boolean status = false;
        try {
            ResultadoOperacion resultado = PedidoRefaccionDAO.registrarPedidoRefaccion(pedidoNuevo);
            if(!resultado.isError()){

                status = true;
            }else{
                ShowMessage.showAlertSimple("Error al guardar", resultado.getMensaje(), Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            ShowMessage.showAlertSimple("Error de conexion", e.getMessage(), Alert.AlertType.NONE);
        }
        
        return status;
    }
    
    private void modificarPzasDisponibles(){
        for(int i = 0; i<infoRefaccionesCompradas.size(); i++){
           int pzasRefaccionesGuardadas = infoRefaccionesCompradas.get(i).getRefaccion().getPzasDisponiblesCompra();
           int pzasRefaccionesCompradas = infoRefaccionesCompradas.get(i).getRefaccionesCompradas();
           int nuevasPzasDisponibles = pzasRefaccionesGuardadas - pzasRefaccionesCompradas;
           infoRefaccionesCompradas.get(i).getRefaccion().setPzasDisponiblesCompra(nuevasPzasDisponibles);
           modificarPzasRefacciones();
       }
    
    }
    
    private void modificarPzasRefacciones(){
        for(int i = 0; i < infoRefaccionesCompradas.size(); i++){
            try {
                ResultadoOperacion resultado;
                resultado = RefaccionDAO.modificarPzasDisponiblesCompraRefaccion(infoRefaccionesCompradas.get(i).getRefaccion(), cbProveedores.getSelectionModel().getSelectedItem().getIdProovedor());
            } catch (SQLException ex) {
                ShowMessage.showAlertSimple("Error de conexion", ex.getMessage(), Alert.AlertType.ERROR);
                return;
            }
        }
        
        
        
    }
    
    private void guardarRefaccionesCompradas(){
        PedidoRefaccion pedidoRefaccion = PedidoRefaccionDAO.ObtnenerPedidoRefaccion(String.valueOf(numeroPedido));
        System.out.println(String.valueOf(pedidoRefaccion.getNumeroPedido()));
        System.out.println(numeroPedido);
        if(pedidoRefaccion != null){
            if(pedidoRefaccion.getIdPedido() > 0){
                for(int i = 0; i<infoRefaccionesCompradas.size(); i++){
                    RefaccionComprada refaccionCompradaBD = infoRefaccionesCompradas.get(i);
                    try {
                        ResultadoOperacion resultado = RefaccionCompradaDAO.registrarRefaccionComprada(pedidoRefaccion.getIdPedido(), refaccionCompradaBD);
                    } catch (SQLException ex) {
                       ShowMessage.showAlertSimple("Error de conexion", ex.getMessage(), Alert.AlertType.ERROR);
                        return;
                    }
                }
            }else{
                ShowMessage.showAlertSimple("No se encontro el pedido", "No se encontro el pedido", Alert.AlertType.WARNING);
            }
        
        }
    }
    private void crearPDF(){
        DirectoryChooser directorio = new DirectoryChooser();
        directorio.setTitle("Selecciona una carpeta");
        directorio.setInitialDirectory(new File("C://"));
        Stage escenarioActual = (Stage) lbCantidad.getScene().getWindow();
        File directorioDestino = directorio.showDialog(escenarioActual);
        
        if(directorioDestino != null){
            destinoPDF = directorioDestino.toString();
            System.out.println(destinoPDF);
        }
        
         try {
            try {
                Document pedidoPDF = new Document();
                String archivoPDF = "/PedidoRefaccionNo"+ String.valueOf(numeroPedido) + ".pdf";
                ficheroFinal = destinoPDF + archivoPDF;
                PdfWriter.getInstance(pedidoPDF, new FileOutputStream(ficheroFinal));
                pedidoPDF.open();
                
                Phrase encabezado = new Phrase();
                encabezado.add("SGMEC\n"+ "Pedido de refacciones\n" + "numero de pedido: " + String.valueOf(numeroPedido) + "\n" + "Fecha Pedido: "+ String.valueOf(fechaActual) +"\n" + "Proveedor: " + cbProveedores.getSelectionModel().getSelectedItem().getNombreProveedor() + "\n" + "Direccion de entrega: " +direccionEntrega +"\n\n");
                pedidoPDF.add(encabezado);
                
                crearTablaPDF(pedidoPDF);
                
                Phrase total = new Phrase("\nTotal: " + totalPedido);
                pedidoPDF.add(total);
                
                pedidoPDF.close();
                
            } catch (DocumentException ex) {
                Logger.getLogger(FXMLSolicitudRefaccionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLSolicitudRefaccionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crearTablaPDF(Document pedidoPDF) throws DocumentException{
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
            cbRefacciones.getSelectionModel().select(verificarSeleccion);
            lbNombreRefaccion.setText(String.valueOf(infoRefaccionesCompradas.get(verificarSeleccion).getNombreRefaccion()));
            lbTipoRefaccion.setText(String.valueOf(infoRefaccionesCompradas.get(verificarSeleccion).getTipoRefaccion()));
            lbPrecio.setText(String.valueOf(infoRefaccionesCompradas.get(verificarSeleccion).getPrecioCompra()));
            lbPzasDisponibles.setText(String.valueOf(infoRefaccionesCompradas.get(verificarSeleccion).getPzasDisponiblesCompra()));
            refaccionesCompradas = infoRefaccionesCompradas.get(verificarSeleccion).getRefaccionesCompradas();
            lbCantidad.setText(String.valueOf(refaccionesCompradas));
        }
    }
    
    private void cerrarVentana(){
        try {
           Parent vista = FXMLLoader.load(javafxsgemec.class.getResource("vistas/FXMLAdministrador.fxml"));
           Scene escenaPrincipal = new Scene(vista);
           Stage escenarioBase = (Stage) lbCantidad.getScene().getWindow();
           escenarioBase.setScene(escenaPrincipal);
           escenarioBase.show(); 
        } catch (IOException ex) {
            Logger.getLogger(FXMLAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
