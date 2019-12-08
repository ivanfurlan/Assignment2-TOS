////////////////////////////////////////////////////////////////////
// IVAN FURLAN 1161622
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;
import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

public class TakeAwayBillImpl implements TakeAwayBill {
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
        double totale=0;
        
        for(MenuItem element : itemsOrdered) {
            totale += element.getPrezzo();
        }

        return totale;
    }
}