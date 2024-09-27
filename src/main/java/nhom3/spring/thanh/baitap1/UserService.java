package nhom3.spring.thanh.baitap1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public void saveOrUpdate(UserDemo user)
    {
        userRepository.save(user);
    }

//    public Iterable<UserDemo> getAllUsers() {
//        return userRepository.findAll();
//    }
    public List<UserDemo> getAllUsers() {
        return (List<UserDemo>) userRepository.findAll();
    }

    public UserDemo findById(Integer id) {
        Optional<UserDemo> user = userRepository.findById(id);
        return user.orElse(null);
    }
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}