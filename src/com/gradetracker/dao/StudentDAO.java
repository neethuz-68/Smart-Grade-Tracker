public interface StudentDAO {
    boolean validateUser(String email, String password);
    boolean createStudent(Student student);
}
