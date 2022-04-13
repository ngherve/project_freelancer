package modules;

import com.google.inject.AbstractModule;
import services.FreelancerApiService;
import services.IApiService;


public class FreelancerModule  extends AbstractModule {
    @Override
    protected void configure() {
        bind(IApiService.class).to(FreelancerApiService.class);
    }
}
