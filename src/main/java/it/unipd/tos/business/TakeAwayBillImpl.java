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
        
        for(MenuItem element : itemsOrdered) {
            totale += element.getPrezzo();
            if(element.getTipo()==ItemType.PANINO) {
                if(countPanini == 0 || element.getPrezzo() < prezzoPaninoMenoCaro)
                    prezzoPaninoMenoCaro = element.getPrezzo();
                countPanini++;
            }
        }
        
        if(countPanini>=5)
            totale-=prezzoPaninoMenoCaro/2;
        
        if(totale>=50)
            totale-=totale*0.1;
        
        return totale;
    }
}