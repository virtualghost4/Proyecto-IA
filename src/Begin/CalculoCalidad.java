/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Begin;

import java.util.TimerTask;

/**
 *
 * @author virtualghost4
 */
public class CalculoCalidad extends TimerTask implements Constantes {

    public Escenario escenario;

    public CalculoCalidad(Escenario e) {
        escenario = e;
    }

    @Override
    public void run() {
        CalcularCalidad();
    }

    

    public void CalcularCalidad() {
        for (int i = 0; i < NUMERO_CELDAS_ANCHO; i++) {
            for (int j = 0; j < NUMERO_CELDAS_LARGO; j++) {
                escenario.celdas[i][j].calidad =0;
                Estado e = new Estado(i, j, 'N', null);
                e.distancia(new Estado(escenario.adversario[0].posicionX, escenario.adversario[0].posicionY, 'N', null));   
                e.distancia(new Estado(escenario.adversario[1].posicionX, escenario.adversario[1].posicionY, 'N', null));
                e.distancia(new Estado(escenario.adversario[2].posicionX, escenario.adversario[2].posicionY, 'N', null));
                escenario.celdas[i][j].calidad = -e.calidad/3;
                
            }
        }
    }

}
