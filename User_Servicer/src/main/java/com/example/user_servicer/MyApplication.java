package com.example.user_servicer;

import com.example.user_servicer.Controllers.AdminController;
import com.example.user_servicer.Controllers.UserController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(UserController.class);
        classes.add(AdminController.class);
        // Add other controllers or resources here
        return classes;
    }
}
