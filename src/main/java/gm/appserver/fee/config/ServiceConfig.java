package gm.appserver.fee.config;

import gm.appserver.fee.service.CenterWmsService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class ServiceConfig {
    @Autowired
    Bus bus;

    @Autowired
    CenterWmsService centerWmsService;

    @Value("${gm.appserver.centerwms.name}")
    String serviceAddr;


    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, centerWmsService);
        endpoint.publish("/"+serviceAddr);
        return endpoint;
    }
}
