package services;

import org.junit.Test;
import play.libs.ws.WSClient;

import static org.junit.Assert.assertEquals;

public class FreelancerApiServiceTest {
    WSClient ws;
    FreelancerApiService service = new FreelancerApiService(ws);

    @Test
    public void getProjects() {
    }

    @Test
    public void getOwnerResult() {
    }

    @Test
    public void getIDProjects() {
    }

    @Test
    public void setBaseUrl() {
        service.setBaseUrl("");
        assertEquals("",service.getBaseUrl());

    }
    @Test
    public void getBaseUrl() {
        service.setBaseUrl("");
        assertEquals("",service.getBaseUrl());
    }
}