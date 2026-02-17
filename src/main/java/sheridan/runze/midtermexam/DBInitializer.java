package sheridan.runze.midtermexam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DBInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;

    public DBInitializer(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        studentRepository.deleteAll();

        ClassPathResource resource = new ClassPathResource("students.json");

        try (InputStream is = resource.getInputStream()) {

            ObjectMapper mapper = new ObjectMapper();

            StudentsWrapper wrapper = mapper.readValue(is, StudentsWrapper.class);

            if (wrapper != null && wrapper.getStudents() != null) {
                studentRepository.saveAll(wrapper.getStudents());
            }
        }
    }

    static class StudentsWrapper {

        private List<Student> students;

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }
    }
}