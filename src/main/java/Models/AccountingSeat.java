package Models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="accounting_seat")
@XmlRootElement(name = "seat")
public class AccountingSeat implements Serializable {

    @Id
    public  int id;
    @Column
    private String number;
    @Column
    private Date date;
    @Column
    private String description;
    @Column
    private String accountingAccount;
    @Column
    private String type;
    @Column
    private double amount;
    @Column
    private boolean wasExported;

    public AccountingSeat() {
        this.wasExported = false;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    @XmlElement(name = "date")
    public void setDate(Date date) {
        this.date = date;
    }


    public String getDescription() {
        return description;
    }
    @XmlElement(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountingAccount() {
        return accountingAccount;
    }
    @XmlElement(name = "accounting-account")
    public void setAccountingAccount(String accountingAccount) {
        this.accountingAccount = accountingAccount;
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    @XmlElement(name = "amount")
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isWasExported() {
        return wasExported;
    }

    @XmlElement(name = "was-exported")
    public void setWasExported(boolean wasExported) {
        this.wasExported = wasExported;
    }
}
