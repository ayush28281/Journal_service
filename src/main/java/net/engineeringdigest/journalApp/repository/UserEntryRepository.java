package net.engineeringdigest.journalApp.repository;


import net.engineeringdigest.journalApp.entity.JournalEntity;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserEntryRepository extends MongoRepository<Users, ObjectId> {
    public Users findByUsername(String username);
    public Users deleteByUsername(String username);
}
