package si.fri.kp.KpRest.api.rest.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Vic {
    int v_id;
    Avtor avtor;
    String besedilo;
    String datum;

    public Vic(){}

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public Avtor getAvtor() {
        return avtor;
    }

    public void setAvtor(Avtor avtor) {
        this.avtor = avtor;
    }

    public String getBesedilo() {
        return besedilo;
    }

    public void setBesedilo(String besedilo) {
        this.besedilo = besedilo;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Vic(int id, Avtor avtor1, String besedilo){
        this.v_id=id;
        this.avtor=avtor1;
        this.besedilo=besedilo;
        SimpleDateFormat formatter= new SimpleDateFormat("dd. MM. yyyy");
        Date date = new Date(System.currentTimeMillis());
        this.datum=formatter.format(date);
    }
}
