////////////////////////////////////////////////////////////////////
// IVAN FURLAN 1161622
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.ItemType;
import it.unipd.tos.model.MenuItem;

public class TakeAwayBillImpl implements TakeAwayBill {
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
        double totale = 0;
        int countPanini = 0;
        double prezzoPaninoMenoCaro = 0;
        int countElementi = 0; 

        for(MenuItem element : itemsOrdered) {
            if(element.getTipo()==ItemType.PANINO) {
                if(countPanini == 0 || element.getPrezzo() < prezzoPaninoMenoCaro) {
                    prezzoPaninoMenoCaro = element.getPrezzo();
                }
                countPanini++;
            }
            countElementi++;
            if(countElementi>30) {
                throw new TakeAwayBillException("Non si può acquistare più di 30 elementi.");
            }
            totale += element.getPrezzo();
        }

        if(countPanini>=5){
            totale-=prezzoPaninoMenoCaro/2;
        }

        if(totale>=50) {
            totale-=totale*0.1;
        }

        if(totale<10) {
            totale+=0.5;
        }

        return totale;
    }
}