package si.fri.kp.KpRest.api.rest.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Avtor {
    private int a_id;
    private String ime;
    private String priimek;

    public Avtor(){
    }

    public Avtor(int id, String ime, String priimek){
        this.a_id=id;
        this.ime=ime;
        this.priimek=priimek;
    }

    public int getId() {
        return a_id;
    }
    public void setId(int id) {
        this.a_id = id;
    }
    public String getIme() {
        return ime;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public String getPriimek() {
        return priimek;
    }
    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }
}
