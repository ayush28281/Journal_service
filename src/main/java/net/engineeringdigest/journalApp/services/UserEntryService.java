package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {
    @Autowired
    private UserEntryRepository UserRepository;
    private static final PasswordEncoder encoder=new BCryptPasswordEncoder();

    public List<Users> showUsers(){

        return UserRepository.findAll();
    }

    public void saveUser(Users entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        entity.setRoles(Arrays.asList("USER"));
        UserRepository.save(entity);

    }
    public void saveAdmin(Users entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        entity.setRoles(Arrays.asList("USER","ADMIN"));
        UserRepository.save(entity);

    }
    public void saveOldUser(Users entity) {

        UserRepository.save(entity);

    }

    public Optional<Users> findUserById(ObjectId id){
        return UserRepository.findById(id);


    }
    public Users findByUsername(String username) {
        return UserRepository.findByUsername(username);
    }
    public void delete(String name) {
        UserRepository.deleteByUsername(name);
    }
}
