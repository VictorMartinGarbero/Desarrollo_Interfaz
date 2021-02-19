/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanzaayuda;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;


/**
 *
 * @author Victor
 */
public class LanzaAyuda 
{

    private JMenuBar menuBar;
    private JMenu menu;
    private static JMenuItem menuItem1, menuItem2;
    private static JButton btnGuardar;
    private static JButton btnAbrir;
    
    public JMenuBar createMenuBar()
    {
        menuBar = new JMenuBar();
        menu = new JMenu("Ayuda");
        menuItem1 = new JMenuItem("Contenido ayuda");
        menuItem2 = new JMenuItem("About");
        
        menuBar.add(menu);
        menu.add(menuItem1);
        menu.add(menuItem2);
        
        return menuBar;
    }
    
    
    public static void main(String[] args) 
    {
        //creamos la ventana
        JFrame frame = new JFrame("Aplicación Swing con ayuda");
        
        //creamos el panel
        JPanel panel = new JPanel();
        panel.setSize(300,300);
        
        LanzaAyuda lanza = new LanzaAyuda();
        
        frame.setJMenuBar(lanza.createMenuBar());
        
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(300,300);
        
        HelpSet hs = obtenFichAyuda();
        HelpBroker hb = hs.createHelpBroker();
        hb.enableHelpOnButton(menuItem1, "Contenido de la ayuda", hs);
        
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        hb.enableHelpKey(menuItem1, "Contenido de la ayuda", hs);
        
        hb.enableHelp(btnGuardar, "guardar", hs);
        hb.enableHelp(btnAbrir, "abrir", hs);
        
        
    }
    
    public static HelpSet obtenFichAyuda()
    {
    
        try 
        {
            ClassLoader cl = LanzaAyuda.class.getClassLoader();
            File file = new File(LanzaAyuda.class.getResource("Help/HelpSet.hs").getFile());
            URL url = file.toURI().toURL();
            System.out.println("Etiqueta url: " + url.toString());
            
            // Crea Helpset
            HelpSet hs = new HelpSet(null,url);
            return hs;           
            
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null,"Fichero HelpSet no encontrado");
            return  null;
        }
    
    }
    
}