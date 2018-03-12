package Begin;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

public class Lienzo extends Canvas implements Constantes {

    public Escenario escenario;
    public Timer lanzadorTareas;
    //public Jugador jugador;
    public int i;
    public int velocidad = 500;
    public String str = " ";

    public Lienzo() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                agregarDestino(evt);
                repaint();
            }
        });
        
        
        escenario = new Escenario(this);
        lanzadorTareas = new Timer();
        lanzadorTareas.scheduleAtFixedRate(escenario.calculo, 1, 10);
        lanzadorTareas.scheduleAtFixedRate(escenario.jugador.inteligencia, 0, 1000);
        lanzadorTareas.scheduleAtFixedRate(escenario.quitaVida, 5500, 5000);
        lanzadorTareas.scheduleAtFixedRate(escenario.adversario[0].busqueda, 1, 1001);
        lanzadorTareas.scheduleAtFixedRate(escenario.adversario[1].busqueda, 1, 1001);
        lanzadorTareas.scheduleAtFixedRate(escenario.adversario[2].busqueda, 1, 1001);
        lanzadorTareas.scheduleAtFixedRate(escenario.cronometro, 0, 1000);

    }

    public void agregarDestino(MouseEvent evt) {
        int aX = evt.getX();
        int aY = evt.getY();
        if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
            for (int j = 0; j < NUMERO_CELDAS_ANCHO; j++) {
                for (int k = 0; k < NUMERO_CELDAS_LARGO; k++) {

                    if (escenario.celdas[aX / PIXEL_CELDA][aY / PIXEL_CELDA].tipo == 'V') {
                        System.out.println("Boton Izquierdo - Poner Destino");
                        escenario.celdas[aX / PIXEL_CELDA][aY / PIXEL_CELDA].tipo = 'R';
                    }
                }

            }

        } else {
            System.out.println("Boton Izquierdo - Poner Obstaculo");
            escenario.celdas[aX / PIXEL_CELDA][aY / PIXEL_CELDA].tipo = 'O';
        }
    }

    @Override
    public void paint(Graphics g) {
        update(g);

    }

    @Override
    public void update(Graphics g) {
        escenario.paintComponent(g);
        repaint();
        g.clearRect(100, 645, 500, 100);
        g.drawString("Vidas: " + Integer.toString(escenario.jugador.getEnergia()), 100, 655);
        
        g.drawString("Encontrados: " + Integer.toString(escenario.jugador.recolectados), 200, 655);
        g.drawString("Current Location: ["+ Integer.toString(escenario.jugador.posicionX)+ "] ,"
                + " ["+Integer.toString(escenario.jugador.posicionY)+"]", 50, 695);
        g.drawString("Location Enemy 1: ["+Integer.toString(escenario.adversario[0].posicionX)+"],["+
                Integer.toString(escenario.adversario[0].posicionY)+"]", 330, 655);
        g.drawString("Location Enemy 2: ["+Integer.toString(escenario.adversario[1].posicionX)+"],["+
                Integer.toString(escenario.adversario[1].posicionY)+"]", 330, 675);
        g.drawString("Location Enemy 3: ["+Integer.toString(escenario.adversario[2].posicionX)+"],["+
                Integer.toString(escenario.adversario[2].posicionY)+"]", 330, 695);
        g.setColor(Color.BLUE);
        g.drawString("Objetivo: "+ escenario.jugador.inteligencia.objetivo, 100, 675);
        g.setColor(Color.RED);
        g.drawString("Time Stand: "+Integer.toString(escenario.tiempo) , 200, 675);
    }

    public String xGenerator() {
        str = " ";
        for (int i = 0; i < escenario.faltantes; i++) {
            str = str + 'X' + ' ';
        }
        return str;
    }

}
