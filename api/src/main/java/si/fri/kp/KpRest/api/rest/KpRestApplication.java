package si.fri.kp.KpRest.api.rest;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@DeclareRoles({"user", "admin"})
/*
@Keycloak(json =
        "{"+
        "\"realm\": \"customers-realm\","+
        "\"auth-server-url\": \"http://localhost:8082/auth/\","+
        "\"ssl-required\": \"external\","+
        "\"resource\": \"customers-api\","+
        "\"public-client\": true,"+
        "\"confidential-port\": 0"+
        "}"
)*/
@ApplicationPath("rest")
public class KpRestApplication extends Application {

}
