package si.fri.kp.KpRest.api.rest.dtos;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


@Provider
public class MyContextResolver implements ContextResolver<JAXBContext> {
    public JAXBContext getContext(Class<?> aType) {
        if(aType == Avtor.class){
            try {
                JAXBContext myContext = JAXBContext.newInstance(Avtor.class);
                return myContext;
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        if(aType == Vic.class){
            try {
                JAXBContext myContext = JAXBContext.newInstance(Vic.class);
                return myContext;
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
