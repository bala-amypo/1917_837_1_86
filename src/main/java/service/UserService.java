public interface UserService {
    User register(User user);
    User findByEmail(String email);
    User findById(Long id);
}
