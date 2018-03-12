package Begin;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TimerTask;

public class BusquedaInformada extends TimerTask implements Constantes {

    public Escenario escenario;
    public PriorityQueue<Estado> colaEstados;
    public ArrayList<Estado> historial;
    public ArrayList<Character> pasos;
    public PriorityQueue<Estado> busquedaObjs;
    public int index_pasos;
    public Estado inicial;
    public Estado objetivo;
    public Estado temp;
    public boolean exito;
    public boolean runAway;

    public BusquedaInformada(Escenario escenario) {

        this.escenario = escenario;
        busquedaObjs = new PriorityQueue<>();
        colaEstados = new PriorityQueue<>();
        historial = new ArrayList<>();
        pasos = new ArrayList<>();
        index_pasos = 0;
        exito = false;
        runAway = false;
    }

    public void buscar(int x1, int y1, int x2, int y2) {
        inicial = new Estado(x1, y1, 'N', null);
        objetivo = new Estado(x2, y2, 'R', null);
        inicial.calidad = 0;
        colaEstados.add(inicial);
        historial.add(inicial);

        if (inicial.equals(objetivo)) {
            exito = true;
        }

        while (!colaEstados.isEmpty() && !exito) {
            temp = colaEstados.peek(); //cambiar a poll
            //System.out.println(temp.toString());
            colaEstados.poll(); // borrar 
            moverArriba(temp);
            moverAbajo(temp);
            moverIzquierda(temp);
            moverDerecha(temp);
        }

        if (exito) {

        } else {
            //System.out.println("La ruta no pudo calcularse");
        }

    }

    private void resetear() {
        //System.out.println("reseteando");
        colaEstados.clear();
        pasos.clear();
        historial.clear();
        index_pasos = 0;
        exito = false;
    }

    private void moverArriba(Estado e) {
        if (e.y > 0) {
            if (escenario.celdas[e.x][e.y - 1].tipo != 'O' && escenario.celdas[e.x][e.y - 1].tipo != 'A') {
                Estado arriba = new Estado(e.x, e.y - 1, 'U', e);
                arriba.distancia(objetivo);
                arriba.calidad += escenario.celdas[e.x][e.y - 1].calidad;
                if (!historial.contains(arriba)) {
                    colaEstados.add(arriba);
                    historial.add(arriba);
                    if (arriba.equals(objetivo)) {
                        objetivo = arriba;
                        exito = true;
                    }

                }
            }
        }
    }

    private void moverAbajo(Estado e) {

        if (e.y + 1 < NUMERO_CELDAS_LARGO) {
            if (escenario.celdas[e.x][e.y + 1].tipo != 'O' && escenario.celdas[e.x][e.y + 1].tipo != 'A') {
                Estado abajo = new Estado(e.x, e.y + 1, 'D', e);
                abajo.distancia(objetivo);
                abajo.calidad += escenario.celdas[e.x][e.y + 1].calidad;
                if (!historial.contains(abajo)) {
                    colaEstados.add(abajo);
                    historial.add(abajo);
                    //laberinto.celdas[e.x][e.y+1].tipo='A';
                    if (abajo.equals(objetivo)) {
                        //laberinto.celdas[e.x][e.y+1].tipo='P';
                        objetivo = abajo;
                        exito = true;
                    }
                }
            }
        }
    }

    private void moverIzquierda(Estado e) {
        if (e.x > 0) {
            if (escenario.celdas[e.x - 1][e.y].tipo != 'O' && escenario.celdas[e.x - 1][e.y].tipo != 'A') {
                Estado izquierda = new Estado(e.x - 1, e.y, 'L', e);
                izquierda.distancia(objetivo);
                izquierda.calidad += escenario.celdas[e.x - 1][e.y].calidad;
                if (!historial.contains(izquierda)) {

                    colaEstados.add(izquierda);
                    historial.add(izquierda);

                    if (izquierda.equals(objetivo)) {

                        objetivo = izquierda;
                        exito = true;
                    }
                }
            }
        }
    }

    private void moverDerecha(Estado e) {

        if (e.x < NUMERO_CELDAS_ANCHO - 1) {
            if (escenario.celdas[e.x + 1][e.y].tipo != 'O' && escenario.celdas[e.x + 1][e.y].tipo != 'A') {
                Estado derecha = new Estado(e.x + 1, e.y, 'R', e);
                derecha.distancia(objetivo);
                derecha.calidad += escenario.celdas[e.x + 1][e.y].calidad;
                if (!historial.contains(derecha)) {
                    colaEstados.add(derecha);
                    historial.add(derecha);

                    if (derecha.equals(objetivo)) {
                        objetivo = derecha;
                        exito = true;
                    }
                }
            }
        }
    }

    public void calcularRuta() {
        Estado predecesor = objetivo;
        do {
            pasos.add(predecesor.oper);
            predecesor = predecesor.predecesor;
        } while (predecesor != null);
        index_pasos = pasos.size() - 1;

    }

    public char darMovimiento() {

        return pasos.get(index_pasos - 1);

    }

    @Override
    public void run() {
        resetear();

        if (escenario.jugador.getEnergia() == 0) {
            escenario.dondeSeDibuja.lanzadorTareas.cancel();
            escenario.adversario[0].busqueda.cancel();
            escenario.adversario[1].busqueda.cancel();
            escenario.adversario[2].busqueda.cancel();
            escenario.quitaVida.cancel();
            System.out.println("cancel by zero energy");
        }
        int xJ = escenario.jugador.posicionX;
        int yJ = escenario.jugador.posicionY;
        metodoBuscar();

        //int xR=escenario.darCeldaTipo('R').getKey();
        //int yR=escenario.darCeldaTipo('R').getValue();
        if (!busquedaObjs.isEmpty()) {

            // System.out.println("voy donde :"+busquedaObjs.peek());
            escenario.jugador.inteligencia.buscar(xJ, yJ, busquedaObjs.peek().x, busquedaObjs.peek().y);
            escenario.jugador.inteligencia.calcularRuta();
            if (index_pasos >= 1 && !pasos.isEmpty()) {
                switch (darMovimiento()) {
                    case 'D':
                        escenario.jugador.moverAbajo();
                        break;
                    case 'U':
                        escenario.jugador.moverArriba();
                        break;
                    case 'R':
                        escenario.jugador.moverDerecha();
                        break;
                    case 'L':
                        escenario.jugador.moverIzquierda();
                        break;
                }
            }
            escenario.dondeSeDibuja.repaint();
        }
    }

    public void metodoBuscar() {
        busquedaObjs.clear();//wipe
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                enemyNear();
                if (escenario.jugador.getEnergia() > 15) {
                    runAway = true;
                }
                if (escenario.celdas[i][j].tipo == 'R' && runAway == false) {
                    Estado e = new Estado(i, j, 'N', null);//estado de la recompensa
                    e.distancia(new Estado(escenario.jugador.posicionX, escenario.jugador.posicionY, 'N', null));
                    busquedaObjs.add(e);
                }
            }
        }

        if (busquedaObjs.isEmpty()) {
            for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
                for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                    if (escenario.celdas[i][j].tipo == 'V') {
                        Estado e = new Estado(i, j, 'N', null);
                        e.calidad = escenario.celdas[i][j].calidad;
                        busquedaObjs.add(e);
                        runAway = false;
                    }
                }
            }
        }
    }

    public void enemyNear() {
        runAway = false;
        Estado jugador = new Estado(escenario.jugador.posicionX, escenario.jugador.posicionY, 'N', null);
        Estado oponente;
        for (int i = 0; i < 3; i++) {
            oponente = new Estado(escenario.adversario[i].posicionX, escenario.adversario[i].posicionY, 'N', null);
            jugador.distancia(oponente);
            double cercano = jugador.calidad;
            if (cercano < 5) {
                runAway = true;
                System.out.println("yuta INCOMING");
                if (escenario.jugador.getEnergia() < 7) {
                    runAway = false;
                }
            }

        }
    }
}
