package Begin;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame implements Constantes{
    
    public Lienzo lienzo;
   
    //constructor
    public VentanaPrincipal() {
        lienzo=new Lienzo();
        this.getContentPane().add(lienzo);
        this.setSize(ANCHURA_ESCENARIO,LARGO_ESCENARIO+100); 
        this.setLocation(600,0);
    }
    
}
