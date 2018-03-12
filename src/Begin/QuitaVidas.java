/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Begin;

import java.util.TimerTask;

/**
 *
 * @author ale
 */
public class QuitaVidas extends TimerTask{
    public Escenario e;
    public QuitaVidas(Escenario e){
        this.e=e;
    }

    @Override
    public void run() {
        if(e.vida>=1)
            e.vida--;
    }
    
    
}
