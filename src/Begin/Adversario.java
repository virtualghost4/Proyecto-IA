package Begin;

import java.util.TimerTask;

public class Adversario extends TimerTask implements Constantes {

    public int posicionX;
    public int posicionY;
    public Escenario escenario;
    public AnchuraAdversario busqueda;
    public int i;
    public boolean swapReward = false;

    public Adversario(Escenario escenario, int xi, int yi) {
        posicionX = xi;
        posicionY = yi;
        this.escenario = escenario;
        busqueda = new AnchuraAdversario(escenario, this);

    }

    public void moverArriba() {
        if (posicionY > 0) {
            if (escenario.celdas[posicionX][posicionY - 1].tipo != 'O' && escenario.celdas[posicionX][posicionY - 1].tipo != 'A') {
                if (escenario.celdas[posicionX][posicionY - 1].tipo == 'J') {
                    escenario.dondeSeDibuja.lanzadorTareas.cancel();
                    escenario.adversario[0].busqueda.cancel();
                    escenario.adversario[1].busqueda.cancel();
                    escenario.adversario[2].busqueda.cancel();
                    escenario.quitaVida.cancel();
                    escenario.cronometro.cancel();
                    System.out.println("te han pillado por abajo");
                    return;
                }
                if (swapReward == true) {
                    escenario.celdas[posicionX][posicionY].tipo = 'R';
                    swapReward = false;
                } else {
                    escenario.celdas[posicionX][posicionY].tipo = 'V';
                }
                if (escenario.celdas[posicionX][posicionY - 1].tipo == 'R') {
                    swapReward = true;
                }
                posicionY = posicionY - 1;
                escenario.celdas[posicionX][posicionY].tipo = 'A';
            }
        }
    }

    public void moverAbajo() {
        if (posicionY + 1 < NUMERO_CELDAS_LARGO) {
            if (escenario.celdas[posicionX][posicionY + 1].tipo != 'O' && escenario.celdas[posicionX][posicionY + 1].tipo != 'A') {
                if (escenario.celdas[posicionX][posicionY + 1].tipo == 'J') {
                    escenario.dondeSeDibuja.lanzadorTareas.cancel();
                    escenario.adversario[0].busqueda.cancel();
                    escenario.adversario[1].busqueda.cancel();
                    escenario.adversario[2].busqueda.cancel();
                    escenario.quitaVida.cancel();
                    escenario.cronometro.cancel();
                    System.out.println("pillado por arriba");
                    return;
                }

                if (swapReward == true) {
                    escenario.celdas[posicionX][posicionY].tipo = 'R';
                    swapReward = false;
                } else {
                    escenario.celdas[posicionX][posicionY].tipo = 'V';
                }
                if (escenario.celdas[posicionX][posicionY + 1].tipo == 'R') {
                    swapReward = true;
                }
                posicionY = posicionY + 1;
                escenario.celdas[posicionX][posicionY].tipo = 'A';
            }
        }
    }

    public void moverIzquierda() {
        if (posicionX > 0) {
            if (escenario.celdas[posicionX - 1][posicionY].tipo != 'O' && escenario.celdas[posicionX - 1][posicionY].tipo != 'A') {
                if (escenario.celdas[posicionX - 1][posicionY].tipo == 'J') {
                    escenario.dondeSeDibuja.lanzadorTareas.cancel();
                    escenario.adversario[0].busqueda.cancel();
                    escenario.adversario[1].busqueda.cancel();
                    escenario.adversario[2].busqueda.cancel();
                    escenario.quitaVida.cancel();
                    escenario.cronometro.cancel();
                    System.out.println("te han pillado por la derecha");
                    return;
                }

                if (swapReward == true) {
                    escenario.celdas[posicionX][posicionY].tipo = 'R';
                    swapReward = false;
                } else {
                    escenario.celdas[posicionX][posicionY].tipo = 'V';
                }
                if (escenario.celdas[posicionX - 1][posicionY].tipo == 'R') {
                    swapReward = true;
                }
                posicionX = posicionX - 1;
                escenario.celdas[posicionX][posicionY].tipo = 'A';
            }
        }
    }

    public void moverDerecha() {
        if (posicionX + 1 < NUMERO_CELDAS_ANCHO) {
            if (escenario.celdas[posicionX + 1][posicionY].tipo != 'O' && escenario.celdas[posicionX + 1][posicionY].tipo != 'A') {
                if (escenario.celdas[posicionX + 1][posicionY].tipo == 'J') {
                    escenario.dondeSeDibuja.lanzadorTareas.cancel();
                    escenario.adversario[0].busqueda.cancel();
                    escenario.adversario[1].busqueda.cancel();
                    escenario.adversario[2].busqueda.cancel();
                    escenario.quitaVida.cancel();
                    escenario.cronometro.cancel();
                    System.out.println("te han pillado por la izquierda");
                    return;
                }

                if (swapReward == true) {
                    escenario.celdas[posicionX][posicionY].tipo = 'R';
                    swapReward = false;
                } else {
                    escenario.celdas[posicionX][posicionY].tipo = 'V';
                }
                if (escenario.celdas[posicionX + 1][posicionY].tipo == 'R') {
                    swapReward = true;
                }
                posicionX = posicionX + 1;
                escenario.celdas[posicionX][posicionY].tipo = 'A';
            }

        }
    }

    @Override
    public void run() {

    }

}
