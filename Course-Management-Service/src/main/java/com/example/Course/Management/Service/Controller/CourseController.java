package com.example.Course.Management.Service.Controller;


import com.example.Course.Management.Service.Service.CourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {
    CourseService courseService = new CourseService();

    
    

}
