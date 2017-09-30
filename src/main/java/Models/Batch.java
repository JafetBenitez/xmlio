package Models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class Batch implements Serializable {

    private Date date;
    private List<AccountingSeat> accountingSeats;

    public Batch() {
    }

    public Date getDate() {
        return date;
    }

    @XmlElement(name = "date")
    public void setDate(Date date) {
        this.date = date;
    }

    public List<AccountingSeat> getAccountingSeats() {
        return accountingSeats;
    }

    //adds a wrapper element around the XML representation
    @XmlElementWrapper(name = "accounting-seats")

    //override the name for the XML element
    @XmlElement(name = "seat")
    public void setAccountingSeats(List<AccountingSeat> accountingSeats) {
        this.accountingSeats = accountingSeats;
    }
}
