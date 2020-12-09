////////////////////////////////////////////////////////////////////
// FEDERICO CHIARELLO 1187598
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;
import java.time.LocalTime;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class BillCalculator implements TakeAwayBill {

    private double total;
    
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime time) throws TakeAwayBillException {

        total = 0.0;

        if (itemsOrdered == null) {
            throw new TakeAwayBillException("La lista è vuota");
        }
        
        if (itemsOrdered.contains(null)) {
            throw new TakeAwayBillException("La lista contiene un elemento vuoto");
        }
        
        for (MenuItem i: itemsOrdered) {
            total += i.getPrice();
        }
        
        return total;

    }
}
