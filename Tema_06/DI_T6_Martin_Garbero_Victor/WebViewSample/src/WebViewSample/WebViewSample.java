/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebViewSample;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import jdk.nashorn.api.scripting.JSObject;

/**
 *
 * @author Victor
 */
public class WebViewSample extends Application
{
    private Scene scene;

    @Override
    public void start(Stage stage) 
    {
        // Crea la escena
        stage.setTitle("WebView");
        scene = new Scene(new Browser(),750,500, Color.web("#666970"));
        stage.setScene(scene);
        //scene.getStylesheets().add(WebViewSample.class.getResource("BrowserToolBar.css").toExternalForm());
        stage.show();
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }  
}

class Browser extends Region
{
    private HBox toolBar;
    private static String[] imageFiles = new String[]
    {
      "Images/facebook.jpg", "Images/blog.png", "Images/documentation.png", "Images/partners.png", "Images/help.png"   
    };
    
    private static String[] captions = new String[]
    {
      "Facebook", "Intranet", "Moodle", "Partners" ,"Help" 
    }; 
    
    private static String[] urls = new String[]
    {
        "https://es-es.facebook.com/ieslosmontecillos",
        "http://intranet.ieslosmontecillos.es/intra2021/login.php",
        "http://aula.ieslosmontecillos.es/",
        "https://www.oracle.com/partners/index.html",
        WebViewSample.class.getResource("help.html").toExternalForm()
    };
    
    final ImageView selectedImage = new ImageView();
    final Hyperlink[] hpls = new Hyperlink[captions.length];
    final Image[] images = new Image[imageFiles.length];
    
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    
    final Button showPrevDoc = new Button("Toggle Previous Docs");
    final WebView smallView = new WebView();
    private boolean needDocumentationButton = false;
    
    
    
    public Browser()
    {
        final ComboBox comboBox = new ComboBox();
        // Aplicar los estilos
        getStyleClass().add("browser");
        // Para tratar los tres enlaces
        for(int i = 0; i < captions.length; i++)
        {
            Hyperlink hpl = hpls[i] = new Hyperlink(captions[i]);
            Image image = images[i] =
                new Image(getClass().getResourceAsStream(imageFiles[i]));
            hpl.setGraphic(new ImageView (image));
            final String url = urls[i];
            final boolean addButton = (hpl.getText().equals("Documentation"));
            
            // Proceso del evento
            hpl.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) 
                {
                    //needDocumentationButton = addButton;
                    webEngine.load(url);
                }
            });
        }
        // Cargar la página web
        //webEngine.load("http://www.ieslosmontecillos.es/wp/");
        // Crea el toolBar
        toolBar = new HBox();
        // toolBar.setAlignment(Pos.CENTER);
        toolBar.getStyleClass().add("browser-toolbar");
        toolBar.getChildren().addAll(hpls);
        // toolBar.getChildren().add(createSpacer());
        
        // Crear acciones para los botones
        showPrevDoc.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event) 
            {
                webEngine.executeScript("toggleDisplay('PrevRel')");
            }
        });
        
        smallView.setPrefSize(120, 80);
        
        // Ventana PopUp
        webEngine.setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>()
        {
            @Override
            public WebEngine call(PopupFeatures config) 
            {
                smallView.setFontScale(0.8);
                if(!toolBar.getChildren().contains(smallView))
                {
                    toolBar.getChildren().add(smallView);
                }
                return smallView.getEngine();
            }
            
        }
        );
        
        // Carga de la página
        
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> ov, State oldState, State newState) 
            {
                toolBar.getChildren().remove(showPrevDoc);
                
                if(newState == State.SUCCEEDED)
                {
                    JSObject win = (JSObject) webEngine.executeScript("window");
                    win.setMember("app", new JavaApp());
                    if(needDocumentationButton)
                    {
                        toolBar.getChildren().add(showPrevDoc);
                    }
                }
            }
        }
        );
        webEngine.load("http://www.ieslosmontecillos.es/wp/");
        // Añade la web a la escena
        
        //comboBox.setPrefWidth(60);
        // toolBar.getChildren().add(comboBox);
        
        getChildren().add(toolBar);
        getChildren().add(browser);
        
        
        
        // declaramos el manejador del historico
        
        final WebHistory history = webEngine.getHistory();
        history.getEntries().addListener(new ListChangeListener<WebHistory.Entry>()
        {
            @Override
            public void onChanged(Change<? extends Entry> c) 
            {
                c.next();
                for (Entry e : c.getRemoved())
                {
                    comboBox.getItems().remove(e.getUrl());
                }
                for (Entry e : c.getAddedSubList())
                {
                    comboBox.getItems().add(e.getUrl());
                }
            }
        }
        );
        
        // Definir el comportamiento del comboBox
        comboBox.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent ev) 
            {
                int offset = comboBox.getSelectionModel().getSelectedIndex() - history.getCurrentIndex();
                history.go(offset);
            }
        }
        );
        
    }

    
    
    private Node createSpacer()
    {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override
    protected void layoutChildren() 
    {
        double w = getWidth();
        double h = getHeight();
        double tbHeight = toolBar.prefHeight(w);
        layoutInArea(browser,0,0,w,h-tbHeight,0, HPos.CENTER, VPos.CENTER);
        layoutInArea(toolBar, 0, h-tbHeight, w, tbHeight, 0, HPos.CENTER, VPos.CENTER);
    }
    
    

    @Override
    protected double computePrefHeight(double width) 
    {
        return 750;
    }

    @Override
    protected double computePrefWidth(double height) 
    {
        return 500;
    }
    
    //Interfaz del objeto JavaScript
    public class JavaApp
    {
        public void exit()
        {
            Platform.exit();
        }
    }
}
    

