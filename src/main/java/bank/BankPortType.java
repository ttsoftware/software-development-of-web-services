
package bank;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "BankPortType", targetNamespace = "http://fastmoney.imm.dtu.dk")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BankPortType {


    /**
     * 
     * @param amount
     * @param creditCardInfo
     * @param account
     * @param group
     * @return
     *     returns boolean
     * @throws CreditCardFaultMessage
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "chargeCreditCard", targetNamespace = "http://fastmoney.imm.dtu.dk", className = "bank.ChargeCreditCard")
    @ResponseWrapper(localName = "chargeCreditCardResponse", targetNamespace = "http://fastmoney.imm.dtu.dk", className = "bank.ChargeCreditCardResponse")
    @Action(input = "http://fastmoney.imm.dtu.dk/BankPortType/chargeCreditCardRequest", output = "http://fastmoney.imm.dtu.dk/BankPortType/chargeCreditCardResponse", fault = {
        @FaultAction(className = CreditCardFaultMessage.class, value = "http://fastmoney.imm.dtu.dk/BankPortType/chargeCreditCard/Fault/CreditCardFaultMessage")
    })
    public boolean chargeCreditCard(
        @WebParam(name = "group", targetNamespace = "")
        int group,
        @WebParam(name = "creditCardInfo", targetNamespace = "")
        CreditCardInfoType creditCardInfo,
        @WebParam(name = "amount", targetNamespace = "")
        int amount,
        @WebParam(name = "account", targetNamespace = "")
        AccountType account)
        throws CreditCardFaultMessage
    ;

    /**
     * 
     * @param amount
     * @param creditCardInfo
     * @param group
     * @return
     *     returns boolean
     * @throws CreditCardFaultMessage
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "validateCreditCard", targetNamespace = "http://fastmoney.imm.dtu.dk", className = "bank.ValidateCreditCard")
    @ResponseWrapper(localName = "validateCreditCardResponse", targetNamespace = "http://fastmoney.imm.dtu.dk", className = "bank.ValidateCreditCardResponse")
    @Action(input = "http://fastmoney.imm.dtu.dk/BankPortType/validateCreditCardRequest", output = "http://fastmoney.imm.dtu.dk/BankPortType/validateCreditCardResponse", fault = {
        @FaultAction(className = CreditCardFaultMessage.class, value = "http://fastmoney.imm.dtu.dk/BankPortType/validateCreditCard/Fault/CreditCardFaultMessage")
    })
    public boolean validateCreditCard(
        @WebParam(name = "group", targetNamespace = "")
        int group,
        @WebParam(name = "creditCardInfo", targetNamespace = "")
        CreditCardInfoType creditCardInfo,
        @WebParam(name = "amount", targetNamespace = "")
        int amount)
        throws CreditCardFaultMessage
    ;

    /**
     * 
     * @param amount
     * @param creditCardInfo
     * @param account
     * @param group
     * @return
     *     returns boolean
     * @throws CreditCardFaultMessage
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "refundCreditCard", targetNamespace = "http://fastmoney.imm.dtu.dk", className = "bank.RefundCreditCard")
    @ResponseWrapper(localName = "refundCreditCardResponse", targetNamespace = "http://fastmoney.imm.dtu.dk", className = "bank.RefundCreditCardResponse")
    @Action(input = "http://fastmoney.imm.dtu.dk/BankPortType/refundCreditCardRequest", output = "http://fastmoney.imm.dtu.dk/BankPortType/refundCreditCardResponse", fault = {
        @FaultAction(className = CreditCardFaultMessage.class, value = "http://fastmoney.imm.dtu.dk/BankPortType/refundCreditCard/Fault/CreditCardFaultMessage")
    })
    public boolean refundCreditCard(
        @WebParam(name = "group", targetNamespace = "")
        int group,
        @WebParam(name = "creditCardInfo", targetNamespace = "")
        CreditCardInfoType creditCardInfo,
        @WebParam(name = "amount", targetNamespace = "")
        int amount,
        @WebParam(name = "account", targetNamespace = "")
        AccountType account)
        throws CreditCardFaultMessage
    ;

}
