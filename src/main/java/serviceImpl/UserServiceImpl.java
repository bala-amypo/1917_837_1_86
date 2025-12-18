@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public User register(User user) {
        if (repo.findByEmail(user.getEmail()).isPresent())
            throw new BadRequestException("Email");
        return repo.save(user);
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Email"));
    }

    public User findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new BadRequestException("User"));
    }
}
