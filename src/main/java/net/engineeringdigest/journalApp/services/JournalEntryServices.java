package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.JournalEntity;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userservice;

    public List<JournalEntity> getJournals(String username) {
        Users byUsername = userservice.findByUsername(username);
        return byUsername.getJournals();
    }

    public void addJournal(JournalEntity entity, String username) {
        entity.setDate(LocalDateTime.now());
        Users user = userservice.findByUsername(username);
        List<JournalEntity> journals = user.getJournals();
        JournalEntity save = journalEntryRepository.save(entity);
        journals.add(save);
        userservice.saveOldUser(user);

    }

    public Optional<JournalEntity> findJournalById(String username, ObjectId id) {
        Users user = userservice.findByUsername(username);

        boolean exists = user.getJournals().stream()
                .anyMatch(u -> u.getId().equals(id));

        if (exists) {

            return journalEntryRepository.findById(id);
        }
        return Optional.empty();

    }

    public boolean deleteJournal(ObjectId id, String username) {
        Users byUsername = userservice.findByUsername(username);
        boolean b = byUsername.getJournals().removeIf(x -> x.getId().equals(id));
        if (b) {
            userservice.saveOldUser(byUsername);
            journalEntryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
