package in.sp.main.service;

import in.sp.main.entity.Student;
import in.sp.main.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public Student registerStudent(Student student) {
        return repository.save(student);
    }

    public Student login(String email, String password) {
        return repository.findByEmail(email)
                .filter(s -> s.getPassword().equals(password))
                .orElse(null);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student updateStudent(Student student) {
        return repository.save(student);
    }
    
    public Student getById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}