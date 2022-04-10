package services;

import org.junit.Test;
import play.libs.ws.WSClient;

import static org.junit.Assert.assertEquals;

/**
 * test class for FreelancerApiService
 * @author Seung Hyun Hong, Tamanna Jahin, Nastaran Naseri, Herve Ngomseu Fosting
 */
public class FreelancerApiServiceTest {
    WSClient ws;
    FreelancerApiService service = new FreelancerApiService(ws);

    /**
     * test Getter of the Projects
     */
    @Test
    public void getProjects() {
    }

    /**
     * test Getter of ownerResult
     */
    @Test
    public void getOwnerResult() {
    }

    /**
     * test Getter of Id of Projects
     */
    @Test
    public void getIDProjects() {
    }

    /**
     * test Setter of BaseURL
     */
    @Test
    public void setBaseUrl() {
        service.setBaseUrl("");
        assertEquals("",service.getBaseUrl());

    }

    /**
     * test Getter of BaseURL
     */
    @Test
    public void getBaseUrl() {
        service.setBaseUrl("");
        assertEquals("",service.getBaseUrl());
    }
}