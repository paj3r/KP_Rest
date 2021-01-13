package si.fri.kp.KpRest.api.rest.zrna;

import si.fri.kp.KpRest.api.rest.dtos.Avtor;
import si.fri.kp.KpRest.api.rest.dtos.Vic;
import si.fri.kp.KpRest.api.rest.viri.AvtorjiVir;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@ApplicationScoped
public class PodatkiZrno {
    private List<Avtor> avtorji = new ArrayList<>();
    private List<Vic> vici = new ArrayList<>();
    Logger log = Logger.getLogger(PodatkiZrno.class.getName());

    @PostConstruct
    private void init() {
        avtorji.add(new Avtor(0,"Ivo","Pajer"));
        avtorji.add(new Avtor(1,"Luka","Kljuka"));
        vici.add(new Vic(0,avtorji.get(1),"Koliko sklec naredi Chuck Norris? -Vse"));
        this.reformat();
    }

    public List<Vic> getVici(){
        this.reformat();
        return vici;
    }

    public List<Avtor> getAvtorji(){
        this.reformat();
        return avtorji;
    }

    public void reformat(){
        for(int i=0;i<avtorji.size();i++){
            avtorji.get(i).setId(i);
        }
        for(int j=0;j<vici.size();j++){
            vici.get(j).setV_id(j);
        }

    }
    public Vic addVic(Vic vic){
        Avtor findavt=avtorji.get(vic.getAvtor().getId());
        Vic novi=new Vic(0,findavt, vic.getBesedilo());
        vici.add(novi);
        this.reformat();
        return vici.get(vici.indexOf(novi));
    }
    public Vic updateVic(Vic vic, int id){
        if(id>=this.avtorji.size()){
            return null;
        }
        Avtor findavt=avtorji.get(vic.getAvtor().getId());
        Vic novi=new Vic(id,findavt,vic.getBesedilo());
        vici.set(id, novi);
        this.reformat();
        return vici.get(vici.indexOf(novi));
    }
    public boolean deleteVic(int id){
        if(id>=this.vici.size()){
            return false;
        }
        Vic remove=vici.remove(id);
        if(vici.contains(remove)){
           return false;
        }
        this.reformat();
        return true;
    }
    public Vic dobiNakljucnega(){
        if(vici.size()==0){
            return null;
        }
        Random r = new Random();
        int randomNum = r.nextInt(vici.size());
        //log.info(vici.size()+"__"+randomNum);
        return vici.get(randomNum);
    }
    public Avtor addAvtor(Avtor avt){
        Avtor novi=new Avtor(0,avt.getIme(),avt.getPriimek());
        avtorji.add(novi);
        this.reformat();
        return avtorji.get(avtorji.indexOf(novi));
    }
    public Avtor updateAvtor(Avtor avt, int id){
        if(id>=this.avtorji.size()){
            return null;
        }
        Avtor novi=new Avtor(id, avt.getIme(), avt.getPriimek());
        avtorji.set(id,novi);
        this.reformat();
        for(int i=0;i<vici.size();i++){
            if(vici.get(i).getAvtor().getId()==novi.getId())
                vici.get(i).setAvtor(novi);
        }
        return avtorji.get(avtorji.indexOf(novi));
    }
    public boolean deleteAvtor(int id){
        if(id>=this.avtorji.size()){
            return false;
        }
        Avtor remove=avtorji.remove(id);
        if(avtorji.contains(remove)){
            return false;
        }
        for(int i=0;i<vici.size();i++){
            if(vici.get(i).getAvtor()==remove){
                vici.remove(i);
            }
        }
        this.reformat();
        return true;
    }
    public Avtor getAvtorbyId(int id){
        if(id>=avtorji.size()){
            return null;
        }
        return avtorji.get(id);
    }
    public List<Vic> getVicFromAvtor(int id){
        List<Vic> avtVic = new ArrayList<>();
        for(int i=0;i<this.vici.size();i++){
            if(this.vici.get(i).getAvtor().getId()==id){
                avtVic.add(this.vici.get(i));
            }
        }
        return avtVic;
    }
}
