package com.example.backend;
import com.example.backend.Util.CORSFilter;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
//import org.graalvm.compiler.nodes.java.RegisterFinalizerNode;

@ApplicationPath("/api")
public class HelloApplication extends Application {
    public  HelloApplication(){
        final ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(CORSFilter.class);
        resourceConfig.packages("com.example.backend");
    }
}