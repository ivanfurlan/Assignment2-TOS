////////////////////////////////////////////////////////////////////
// IVAN FURLAN 1161622
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Throwable {
    private String messaggio;
    
    public TakeAwayBillException(String mes) {
        this.messaggio=mes;
    }
    
    public String getMessage() {
        return this.messaggio;
    }
}