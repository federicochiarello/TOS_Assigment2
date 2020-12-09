////////////////////////////////////////////////////////////////////
// FEDERICO CHIARELLO 1187598
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Exception {

    private String message;

    public TakeAwayBillException(String errorMessage) {
        this.message = errorMessage;
    }

    public String toString() {
        return "TakeAwayBillException: " + this.message;
    }
}
