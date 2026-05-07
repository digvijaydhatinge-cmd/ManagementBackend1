package in.sp.main.controller;

import in.sp.main.entity.Student;
import in.sp.main.entity.Status;
import in.sp.main.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    @PostMapping("/register")
    public Student register(@RequestBody Student student) {
        student.setRole("STUDENT"); 
        student.setStatus(Status.PENDING);
        return service.registerStudent(student);
    }

    @PostMapping("/login")
    public Student login(@RequestBody Map<String, String> data) {
        return service.login(data.get("email"), data.get("password"));
    }

    // THIS FIXES THE 400 ERROR
    @PutMapping("/apply/{id}")
    public Student applyCourse(@PathVariable Integer id, @RequestBody Map<String, String> data) {
        Student s = service.getById(id);
        if(s != null) {
            s.setCourse(data.get("course")); // Matches the 'course' key from React
            s.setStatus(Status.PENDING);
            return service.updateStudent(s);
        }
        return null;
    }

    @PutMapping("/status/{id}")
    public Student updateStatus(@PathVariable Integer id, @RequestBody Map<String, String> data) {
        Student s = service.getById(id);
        if(s != null) {
            String statusValue = data.get("status").toUpperCase();
            s.setStatus(Status.valueOf(statusValue));
            return service.updateStudent(s);
        }
        return null;
    }
}