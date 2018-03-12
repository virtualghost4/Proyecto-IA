package Begin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javafx.util.Pair;
import javax.swing.JComponent;

public class Escenario extends JComponent implements Constantes {

    public Celda[][] celdas;
    public Jugador jugador;
    public Adversario[] adversario;
    public Lienzo dondeSeDibuja;
    public Celda celdaMovimiento;
    public QuitaVidas quitaVida;
    public Cronometro cronometro;
    public int vida, tiempo, puntaje, encontrados = 0, faltantes = 0;
    public CalculoCalidad calculo;

    public Escenario(Lienzo lienzo) {
        
        
        vida = 10;
        tiempo=0;
        dondeSeDibuja = lienzo;
        celdas = new Celda[NUMERO_CELDAS_ANCHO][NUMERO_CELDAS_LARGO];
        //inicializar el array de celdas
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                celdas[i][j] = new Celda( (i * PIXEL_CELDA),  (j * PIXEL_CELDA), 'V'); //sumar i y j para separar lo negrito
            }
        }
        
        jugador = new Jugador(this);
        celdas[9][18].tipo = 'J';
        quitaVida = new QuitaVidas(this);
        cronometro = new Cronometro(this);
        calculo = new CalculoCalidad(this);
        adversario=new Adversario[3];
        adversario[0] = new Adversario(this, 8, 8);
        adversario[1] = new Adversario(this, 9, 8);
        adversario[2] = new Adversario(this, 10, 8);

        //INICIO PAREDES
       //matriz 19x20
	//INICIO PAREDES
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                if (i == 0 || j == 0) {
                    celdas[i][j].esPared();
                }
                if (i == NUMERO_CELDAS_ANCHO - 1 || j == NUMERO_CELDAS_LARGO - 1) {
                    celdas[i][j].esPared();
                }
            }
        }

        celdas[8][0].esPiso();
        celdas[9][0].esPiso();
        celdas[10][0].esPiso();
        celdas[0][14].esPiso();
        celdas[0][15].esPiso();
        celdas[0][16].esPiso();
        celdas[18][14].esPiso();
        celdas[18][15].esPiso();
        celdas[18][16].esPiso();
        celdas[3][8].esPared();
        celdas[3][9].esPared();
        celdas[2][9].esPared();
        celdas[15][8].esPared();
        celdas[15][9].esPared();
        celdas[16][9].esPared();
        celdas[9][10].esPared();
        celdas[9][1].esPared();
        celdas[2][2].esPared();
        celdas[3][2].esPared();
        celdas[5][2].esPared();
        celdas[6][2].esPared();
        celdas[7][2].esPared();
        celdas[9][2].esPared();
        celdas[11][2].esPared();
        celdas[12][2].esPared();
        celdas[13][2].esPared();
        celdas[15][2].esPared();
        celdas[16][2].esPared();
        celdas[2][4].esPared();
        celdas[3][4].esPared();
        celdas[5][4].esPared();
        celdas[7][4].esPared();
        celdas[8][4].esPared();
        celdas[9][4].esPared();
        celdas[10][4].esPared();
        celdas[11][4].esPared();
        celdas[13][4].esPared();
        celdas[15][4].esPared();
        celdas[16][4].esPared();
        celdas[5][5].esPared();
        celdas[9][5].esPared();
        celdas[13][5].esPared();
        celdas[0][6].esPared();
        celdas[1][6].esPared();
        celdas[3][6].esPared();
        celdas[5][6].esPared();
        celdas[6][6].esPared();
        celdas[7][6].esPared();
        celdas[9][6].esPared();
        celdas[11][6].esPared();
        celdas[12][6].esPared();
        celdas[13][6].esPared();
        celdas[15][6].esPared();
        celdas[17][6].esPared();
        celdas[0][7].esPared();
        celdas[1][7].esPared();
        celdas[3][7].esPared();
        celdas[5][7].esPared();
        celdas[13][7].esPared();
        celdas[15][7].esPared();
        celdas[17][7].esPared();
        celdas[7][8].esPared();
        celdas[11][8].esPared();
        celdas[5][9].esPared();
        celdas[7][9].esPared();
        celdas[8][9].esPared();
        celdas[9][9].esPared();
        celdas[10][9].esPared();
        celdas[11][9].esPared();
        celdas[13][9].esPared();
        celdas[0][10].esPared();
        celdas[2][10].esPared();
        celdas[3][10].esPared();
        celdas[5][10].esPared();
        celdas[13][10].esPared();
        celdas[15][10].esPared();
        celdas[16][10].esPared();
        celdas[0][11].esPared();
        celdas[2][11].esPared();
        celdas[3][11].esPared();
        celdas[5][11].esPared();
        celdas[6][11].esPared();
        celdas[7][11].esPared();
        celdas[9][11].esPared();
        celdas[11][11].esPared();
        celdas[12][11].esPared();
        celdas[13][11].esPared();
        celdas[15][11].esPared();
        celdas[16][11].esPared();
        celdas[9][12].esPared();
        celdas[2][13].esPared();
        celdas[3][13].esPared();
        celdas[5][13].esPared();
        celdas[6][13].esPared();
        celdas[7][13].esPared();
        celdas[9][13].esPared();
        celdas[11][13].esPared();
        celdas[12][13].esPared();
        celdas[13][13].esPared();
        celdas[15][13].esPared();
        celdas[16][13].esPared();
        celdas[3][14].esPared();
        celdas[15][14].esPared();
        celdas[1][15].esPared();
        celdas[3][15].esPared();
        celdas[5][15].esPared();
        celdas[7][15].esPared();
        celdas[8][15].esPared();
        celdas[9][15].esPared();
        celdas[10][15].esPared();
        celdas[11][15].esPared();
        celdas[13][15].esPared();
        celdas[15][15].esPared();
        celdas[17][15].esPared();
        celdas[5][16].esPared();
        celdas[9][16].esPared();
        celdas[13][16].esPared();
        celdas[2][17].esPared();
        celdas[3][17].esPared();
        celdas[4][17].esPared();
        celdas[5][17].esPared();
        celdas[6][17].esPared();
        celdas[7][17].esPared();
        celdas[9][17].esPared();
        celdas[11][17].esPared();
        celdas[12][17].esPared();
        celdas[13][17].esPared();
        celdas[14][17].esPared();
        celdas[15][17].esPared();
        celdas[16][17].esPared();
        // PAREDES FIN

        //inicio jugadores
        //celdas[9][18].esJugador();
        //celdas[8][8].esEnemigo();
        //celdas[9][8].esEnemigo();
        //celdas[10][8].esEnemigo();
        //fin jugadores
        
        
        //inicio recompensas
        celdas[17][13].esRecompensa();
        celdas[0][16].esRecompensa();
        celdas[9][0].esRecompensa();
        celdas[14][1].esRecompensa();
        //fin recompensas
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                celdas[i][j].paintComponent(g);
            }
        }

    }

    @Override
    public void update(Graphics g) {
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                celdas[i][j].paintComponent(g);
            }
        }

    }

    public Pair<Integer, Integer> darCeldaTipo(char tipoC) {
        Pair<Integer, Integer> celda = null;
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                if (celdas[i][j].tipo == tipoC) {
                    celda = new Pair(i, j);

                    break;
                }
            }
        }

        return celda;
    }

    public void moverJugador(KeyEvent evento) {

        switch (evento.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("Mover arriba");
                jugador.moverArriba();

                break;

            case KeyEvent.VK_DOWN:
                System.out.println("Mover abajo");
                jugador.moverAbajo();

                break;

            case KeyEvent.VK_LEFT:
                System.out.println("Mover izquierda");
                jugador.moverIzquierda();

                break;

            case KeyEvent.VK_RIGHT:
                System.out.println("Mover derecha");
                jugador.moverDerecha();

                break;
        }

        dondeSeDibuja.repaint();
    }

}
