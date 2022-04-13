package modules;

import com.google.inject.AbstractModule;
import services.FreelancerApiService;
import services.IApiService;

/**
 * Module for Freelancer class
 * @author Seung Hyun Hong, Nastaran Naseri, Herve Ngomseu Fosting
 */

public class FreelancerModule  extends AbstractModule {

    @Override
    /**
     * configurig the binding from IAPIService to FreelancerApiService clas
     */
    protected void configure() {
        bind(IApiService.class).to(FreelancerApiService.class);
    }
}
