////////////////////////////////////////////////////////////////////
// FEDERICO CHIARELLO 1187598
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import java.time.LocalTime;

public interface TakeAwayBill {
    double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime time) throws TakeAwayBillException;
}
