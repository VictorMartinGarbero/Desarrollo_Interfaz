/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//ACTUALIZAR-----------------------------

package appsondeos;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author Victor
 */
public class PestanaViajes 
{
    private VBox principal;
    private VBox datospersonales;
    
    private HBox nombre;
    private Label lb_nombre;
    private TextField tf_nombre;
    
    private HBox apellidos;
    private Label lb_apellidos;
    private TextField tf_apellidos;
    
    private HBox profesion; 
    private Label lb_profesion;
    private TextField tf_profesion;
    
    private Label lb_edad;
    private DatePicker dp_edad;
    private Label lb_hermanos;
    private ChoiceBox cb_hermanos;
    private HBox edadhermanos;
    
    private Label lb_sexo;
    private ToggleGroup tg_sexo;
    private RadioButton hombre;
    private RadioButton mujer;
    private HBox sexo;
    
    private Label lbCantidad;
    private ToggleGroup tgCantidad;
    private RadioButton rbUnavez;
    private RadioButton rbDosveces;
    private RadioButton rbMasde;
    private HBox hbCantidad;
    
    private Label lbPreferencias;
    private ToggleGroup tgPreferencias;
    private RadioButton opc1;
    private RadioButton opc2;
    private RadioButton opc3;
    private RadioButton opc4;
    private HBox hbPref;
    
    private Label lbTransporte;
    private ToggleGroup tgTransporte;
    private RadioButton tren;
    private RadioButton barco;
    private RadioButton avion;
    private RadioButton coche;
    private RadioButton autobus;
    private HBox hbTrans;
    
    private Label lbAlojamiento;
    private ToggleGroup tgAlojamiento;
    private RadioButton hotel;
    private RadioButton apartamento;
    private RadioButton camping;
    private RadioButton otros;
    private HBox hbAloj;
    
    private Label lbCompañia;
    private ToggleGroup tgCompañia;
    private RadioButton solo;
    private RadioButton pareja;
    private RadioButton familia;
    private RadioButton amigos;
    private RadioButton otro;
    private HBox hbComp;
    
    private Label lbGasto;
    private Label result;
    private ToggleGroup tgGasto;
    private HBox hbGasto;
    
    private Button enviar;
    private HBox cuadroenviar;
    
    private Text actiontarget;
    private HBox cuadroenviado;
    
    
    public PestanaViajes()
    {
        lb_nombre = new Label("Nombre:");
        tf_nombre = new TextField();
        nombre = new HBox(lb_nombre, tf_nombre);
        nombre.setSpacing(10);
        
        lb_apellidos = new Label("Apellidos:");
        tf_apellidos = new TextField();
        apellidos = new HBox(lb_apellidos, tf_apellidos);
        apellidos.setSpacing(10);
        
        lb_profesion = new Label("Profesion:");
        tf_profesion = new TextField();
        profesion = new HBox(lb_profesion, tf_profesion);
        profesion.setSpacing(10);
        
        lb_edad = new Label("Edad:");
        dp_edad = new DatePicker(LocalDate.now());
        dp_edad.setEditable(false);
        lb_hermanos = new Label("Nº Hermanos:");
        cb_hermanos = new ChoiceBox(FXCollections.observableArrayList("Ninguno", "Uno", "Dos", "Más de dos"));
        edadhermanos = new HBox(lb_edad, dp_edad, lb_hermanos, cb_hermanos);
        edadhermanos.setSpacing(10);
        
        tg_sexo = new ToggleGroup();
        lb_sexo = new Label("Sexo:");
        hombre = new RadioButton("Hombre");
        hombre.setToggleGroup(tg_sexo);
        mujer = new RadioButton("Mujer");
        mujer.setToggleGroup(tg_sexo);
        sexo = new HBox (lb_sexo, hombre, mujer);
        sexo.setSpacing(10);
        
        //Contenido cada cuanto
        tgCantidad = new ToggleGroup();
        lbCantidad = new Label("¿Cada cuanto viajas?");
        rbUnavez = new RadioButton("1 vez a año");
        rbUnavez.setToggleGroup(tgCantidad);
        rbDosveces = new RadioButton("2 veces al año");
        rbDosveces.setToggleGroup(tgCantidad);
        rbMasde = new RadioButton("Mas de 2 veces al año");
        rbMasde.setToggleGroup(tgCantidad);
        hbCantidad = new HBox(lbCantidad, rbUnavez,rbDosveces,rbMasde);
        hbCantidad.setSpacing(10);
       
        
        //Contenido preferencias
        tgPreferencias = new ToggleGroup();
        lbPreferencias = new Label("Preferencias organización");
        opc1 = new RadioButton("Todo organizado");
        opc1.setToggleGroup(tgPreferencias);
        opc2 = new RadioButton("Organizado sólo hotel y transporte");
        opc2.setToggleGroup(tgPreferencias);
        opc3 = new RadioButton("Me encargo personalmente");
        opc3.setToggleGroup(tgPreferencias);
        opc4 = new RadioButton("Me da igual");
        opc4.setToggleGroup(tgPreferencias);
        hbPref = new HBox(lbPreferencias,opc1,opc2,opc3,opc4);
        hbPref.setSpacing(10);
        
        //Contenido transporte
        tgTransporte = new ToggleGroup();
        lbTransporte = new Label("Medios transporte");
        tren = new RadioButton("Tren");
        tren.setToggleGroup(tgTransporte);
        barco = new RadioButton("Barco"); 
        barco.setToggleGroup(tgTransporte);
        avion = new RadioButton("Avión");
        avion.setToggleGroup(tgTransporte);
        coche = new RadioButton("Coche");
        coche.setToggleGroup(tgTransporte);
        autobus = new RadioButton("Autobús");
        autobus.setToggleGroup(tgTransporte);
        hbTrans = new HBox(lbTransporte,tren,barco,avion,coche,autobus);
        hbTrans.setSpacing(10);
        
        //Contenido alojamiento
        tgAlojamiento = new ToggleGroup();
        lbAlojamiento = new Label("Tipo de alojamiento");
        hotel = new RadioButton("Hotel");
        hotel.setToggleGroup(tgAlojamiento);
        apartamento = new RadioButton("Apartamento");
        apartamento.setToggleGroup(tgAlojamiento);
        camping = new RadioButton("Camping");
        camping.setToggleGroup(tgAlojamiento);
        otros = new RadioButton("Otros");
        otros.setToggleGroup(tgAlojamiento);
        hbAloj = new HBox(lbAlojamiento,hotel, apartamento,camping,otros);
        hbAloj.setSpacing(10);
        
        //Contenido compañia
        tgCompañia = new ToggleGroup();
        lbCompañia = new Label("Compañía");
        solo = new RadioButton("Solo");
        solo.setToggleGroup(tgCompañia);
        pareja = new RadioButton("En pareja");
        pareja.setToggleGroup(tgCompañia);
        familia = new RadioButton("Con familia");
        familia.setToggleGroup(tgCompañia);
        amigos = new RadioButton("Con amigos");
        amigos.setToggleGroup(tgCompañia);
        otro = new RadioButton("Otros");
        otro.setToggleGroup(tgCompañia);
        hbComp = new HBox(lbCompañia,solo,pareja,familia,amigos,otro);
        hbComp.setSpacing(10);
        
        //Contenido gasto
        tgGasto = new ToggleGroup();
        lbGasto = new Label("¿Cuánto gasta en sus vacaciones?");
        hbGasto = new HBox(lbGasto);
        //result = new Label();
        Slider slider = new Slider(300,1500,0.5);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMin(300);
        slider.setMax(1500);
        slider.setValue(300);
        
        enviar = new Button("Enviar");
        cuadroenviar = new HBox(enviar);
        cuadroenviar.setAlignment(Pos.BOTTOM_RIGHT);
        
        actiontarget = new Text();
        enviar.setOnAction(new EventHandler<ActionEvent>() 
        {

            @Override
            public void handle(ActionEvent e) 
            {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Encuesta enviada con éxito");
            }
        });
        cuadroenviado = new HBox(actiontarget);
        
        //
        principal = new VBox();
        principal.setPadding(new Insets(30,30,30,30));
        principal.setSpacing(40);
        datospersonales = new VBox();
        datospersonales.setPadding(new Insets(30, 30, 30, 30));
        datospersonales.setSpacing(20);
        //añade Padding
        principal.getChildren().add(datospersonales);
        datospersonales.getChildren().add(nombre);
        datospersonales.getChildren().add(apellidos);
        datospersonales.getChildren().add(profesion);
        datospersonales.getChildren().add(edadhermanos);
        datospersonales.getChildren().add(sexo);
        principal.getChildren().add(hbCantidad);
        principal.getChildren().add(hbPref);
        principal.getChildren().add(hbTrans);
        principal.getChildren().add(hbAloj);
        principal.getChildren().add(hbComp);
        principal.getChildren().add(hbGasto);
        principal.getChildren().add(slider);
        principal.getChildren().add(cuadroenviar);
        principal.getChildren().add(cuadroenviado);
                
    
    }
    public VBox getVBoxViajes() 
    {
        return principal;
    }
    
}
