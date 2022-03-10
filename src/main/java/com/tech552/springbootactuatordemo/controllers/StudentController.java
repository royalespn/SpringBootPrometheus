package com.tech552.springbootactuatordemo.controllers;

import com.tech552.springbootactuatordemo.models.Student;
import com.tech552.springbootactuatordemo.services.StudentService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(path = "/api/v1")
public class StudentController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public Counter hitCounter;

    @Autowired
    private StudentService studentService;

    public StudentController() {
    }

    @PostMapping("/student")
    public void addStudent(@RequestBody Student student){
        addLatency(10, 100);
        studentService.addStudent(student);
    }

    @GetMapping("/student")
    public List<Student> getAllStudents(){
        hitCounter.increment();
        addLatency(10, 100);
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id){
        addLatency(1, 20);
        return studentService.getStudentById(id);
    }

    @PutMapping("/student/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody Student student ){
        studentService.updateStudent(id, student);
    }
    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id){
        addLatency(0, 40);
        studentService.deleteStudent(id);
    }

    @RequestMapping("/external/activity")
    public String api1() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://randomuser.me/api/", String.class);
        return "Response from outbound request was " + responseEntity.getBody();
    }

    @RequestMapping("/external/ip")
    public String api2() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://api.ipify.org?format=json", String.class);
        return "Response from outbound request was " + responseEntity.getBody();
    }

    private String apiResponse(int apiNumber) {
        return String.format("API %s response from %s", apiNumber, "course-tracker");
    }

    private void addLatency(int minimumMs, int maximumMs) {
        long sleepDuration = ThreadLocalRandom.current().nextInt(minimumMs, maximumMs + 1);
        try {
            Thread.sleep(sleepDuration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
