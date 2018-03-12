package Begin;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Celda extends JComponent implements Constantes {
    
    public int x;
    public int y;
    public double calidad;
    public char tipo;
    public Escenario escenario;
    public BufferedImage obstacle ,enemy, camino,hero,reward, policia, path;
    //constructor
    public Celda(int x,int y,char tipo) {
        calidad=0;
        this.x=x;
        this.y=y;
        this.tipo=tipo;
        
       try{
            obstacle = ImageIO.read(new File("src/imagenes/glass.png"));
            enemy  = ImageIO.read(new File("src/imagenes/bomb.gif"));
            camino   = ImageIO.read(new File("src/imagenes/camino.png"));
            hero = ImageIO.read(new File("src/imagenes/mario.png"));
            policia = ImageIO.read(new File("src/imagenes/jugador.png"));
            reward = ImageIO.read(new File("src/imagenes/reward.png"));
            path = ImageIO.read(new File("src/imagenes/acera.png"));
            
        }catch(IOException e) {
            System.out.println("error al cargar imagenes "+e.toString());
    }
        
    }
    
    public void esPared() {
        tipo='O';
    }
    
     public void esFinal() {
        tipo='F';
    }
     public void esRecompensa(){
         tipo='R';
     }
     public void esPiso(){
         tipo='V';
     }
 
     
    //metodo para dibujar una casilla
    @Override
    public void update(Graphics g) {
        switch(tipo) {
          case  'J': g.drawImage(hero, x, y, this);break;
            //case  'J': g.setColor(COLOR_JUGADOR); break;
            case  'O': g.drawImage(obstacle, x, y, null); break;
            //case  'V' :g.setColor(COLOR_CAMINO);break;
            case  'V' :g.drawImage(camino,x,y, null);break;
            //case  'A': g.setColor(COLOR_ADVERSARIO); break;
            case 'A': g.drawImage(enemy,x,y, null); break;
            case 'X': g.drawImage(policia,x,y, null); break;
            case 'C': g.drawImage(path, x, y, this);break;
            case 'R': g.drawImage(reward, x, y, null); break;
       } 
        
        //g.drawString(Integer.toString((int)calidad), x+10, y-10);
    }
    @Override
        public void paintComponent(Graphics g) {
        update(g);
        }
   
}
