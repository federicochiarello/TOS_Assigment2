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
    private double cheaperIceCreamPrice;
    private int numberOfIceCream;
    
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime time) throws TakeAwayBillException {

        total = 0.0;
        cheaperIceCreamPrice = 0.0;
        numberOfIceCream = 0;

        if (itemsOrdered == null) {
            throw new TakeAwayBillException("La lista è vuota");
        }
        
        if (itemsOrdered.contains(null)) {
            throw new TakeAwayBillException("La lista contiene un elemento vuoto");
        }
        
        for (MenuItem i: itemsOrdered) {
            total += i.getPrice();
            
            if (i.getItemType() == MenuItem.ItemType.Gelato) {    
                numberOfIceCream++;
                if ((cheaperIceCreamPrice == 0.0) || (cheaperIceCreamPrice > i.getPrice())) {
                     cheaperIceCreamPrice = i.getPrice();
                }
            }
        }
        
        if (numberOfIceCream > 5) {
            total -= cheaperIceCreamPrice * 0.5;
        }
        
        return total;

    }
}
