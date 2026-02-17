package sheridan.runze.midtermexam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "students")
public interface StudentRepository extends JpaRepository<Student, String>
 {

    List<Student> findByNameContainingIgnoreCase(@Param("name") String name);

    List<Student> findByEmailIgnoreCase(@Param("email") String email);

    List<Student> findByGpaGreaterThan(@Param("min") double min);

    List<Student> findByGpaLessThan(@Param("max") double max);

    List<Student> findByGpaBetween(@Param("min") double min, @Param("max") double max);
}

