package Begin;


import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;


public class Jugador implements Constantes{
    
    public int posicionX;
    public int posicionY;
    public Escenario escenario;
    public BusquedaInformada inteligencia;
    private TimerTask energy;
    private int energia;
    public int recolectados;
    
    public Jugador(Escenario escenario) {
        posicionX=9;
        posicionY=18;
        this.escenario=escenario;
        inteligencia=new BusquedaInformada(escenario);
        energia=10;
        recolectados=0;
        
        energy = new TimerTask(){
            @Override
            public void run(){
                energia--;
                escenario.dondeSeDibuja.repaint();//actualizar canvas
            }
        };
        Timer ejecutador = new Timer();
        ejecutador.scheduleAtFixedRate(energy, 5000, 5000);
    }
    
    public void moverArriba(){
        if (posicionY > 0 ) {
           if ( escenario.celdas[posicionX][posicionY-1].tipo != 'O' ) {
               if(escenario.celdas[posicionX][posicionY-1].tipo == 'R'){
                   if(escenario.celdas[posicionX][posicionY-1].tipo == 'A'){
                        escenario.dondeSeDibuja.lanzadorTareas.cancel();
                        escenario.adversario[0].busqueda.cancel();
                        escenario.adversario[1].busqueda.cancel();
                        escenario.adversario[2].busqueda.cancel();
                        escenario.quitaVida.cancel();
                        
                        escenario.cronometro.cancel();
                        System.out.println("haz tocado enemigo hacia arriba");
                    }
                   this.energia+=10;
                   this.recolectados++;
               }else if(escenario.celdas[posicionX][posicionY-1].tipo == 'D'){
                            escenario.dondeSeDibuja.lanzadorTareas.cancel();
                            JOptionPane.showMessageDialog(null, "hola");
                            System.out.println("fin!!!!!!!!!!!");
                        }
              escenario.celdas[posicionX][posicionY].tipo='V';
              posicionY=posicionY-1;
              escenario.celdas[posicionX][posicionY].tipo='J';
           }
        }
    }
    
    public void moverAbajo(){
       if (posicionY+1 < NUMERO_CELDAS_LARGO ) {
           if ( escenario.celdas[posicionX][posicionY+1].tipo != 'O' ) {
                if(escenario.celdas[posicionX][posicionY+1].tipo=='R'){
                    if(escenario.celdas[posicionX][posicionY+1].tipo == 'A'){
                        escenario.dondeSeDibuja.lanzadorTareas.cancel();
                        escenario.adversario[0].busqueda.cancel();
                        escenario.adversario[1].busqueda.cancel();
                        escenario.adversario[2].busqueda.cancel();
                        escenario.quitaVida.cancel();
                        escenario.cronometro.cancel();
                        System.out.println("haz tocado enemigo hacia abajo");
                    }
                    this.energia+=10;
                    this.recolectados++;
                }else if(escenario.celdas[posicionX][posicionY+1].tipo == 'D'){
                            escenario.dondeSeDibuja.lanzadorTareas.cancel();
                            JOptionPane.showMessageDialog(null, "hola");
                            System.out.println("fin!!!!!!!!!!!");
                        }
                escenario.celdas[posicionX][posicionY].tipo='V';
                posicionY=posicionY+1;
                escenario.celdas[posicionX][posicionY].tipo='J'; 
           }
        }
    }
    
    public void moverIzquierda(){
         if (posicionX > 0 ) {
           if (escenario.celdas[posicionX-1][posicionY].tipo != 'O' ) {
               if(escenario.celdas[posicionX-1][posicionY].tipo=='R'){
                   if(escenario.celdas[posicionX-1][posicionY].tipo == 'A'){
                        escenario.dondeSeDibuja.lanzadorTareas.cancel();
                        escenario.adversario[0].busqueda.cancel();
                        escenario.adversario[1].busqueda.cancel();
                        escenario.adversario[2].busqueda.cancel();
                        escenario.quitaVida.cancel();
                        escenario.cronometro.cancel();
                        System.out.println("haz tocado enemigo hacia la izquierda");
                    }
                    this.energia+=10;
                    this.recolectados++;
                }else if(escenario.celdas[posicionX-1][posicionY].tipo == 'D'){
                            escenario.dondeSeDibuja.lanzadorTareas.cancel();
                            JOptionPane.showMessageDialog(null, "hola");
                            System.out.println("fin!!!!!!!!!!!");
                        }
                escenario.celdas[posicionX][posicionY].tipo='V';
                posicionX=posicionX-1;
                escenario.celdas[posicionX][posicionY].tipo='J'; 
           }
        }
    }
    
    public void moverDerecha(){
        if (posicionX+1 < NUMERO_CELDAS_ANCHO  ) {
           if (escenario.celdas[posicionX+1][posicionY].tipo != 'O' ) { 
               if(escenario.celdas[posicionX+1][posicionY].tipo == 'R'){
                   if(escenario.celdas[posicionX+1][posicionY].tipo == 'A'){
                        escenario.dondeSeDibuja.lanzadorTareas.cancel();
                        escenario.adversario[0].busqueda.cancel();
                        escenario.adversario[1].busqueda.cancel();
                        escenario.adversario[2].busqueda.cancel();
                        escenario.cronometro.cancel();
                        System.out.println("haz tocado enemigo hacia la derecha");
                    }
                   this.energia+=10;
                   this.recolectados++;
               }else if(escenario.celdas[posicionX+1][posicionY].tipo == 'D'){
                            escenario.dondeSeDibuja.lanzadorTareas.cancel();
                            JOptionPane.showMessageDialog(null, "Juego Terminado");
                            System.out.println("fin!!!!!!!!!!!");
                        }
              escenario.celdas[posicionX][posicionY].tipo='V';
              posicionX=posicionX+1;
              escenario.celdas[posicionX][posicionY].tipo='J'; 
           }
           
        }
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }
    
}

