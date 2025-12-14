package net.engineeringdigest.journalApp.entity;

import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document(collection = "users")
public class Users
{
    @Id
    private ObjectId id;

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public List<JournalEntity> getJournals() {
        return journals;
    }

    public void setJournals(List<JournalEntity> journals) {
        this.journals = journals;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    public List<String> getRoles() {
        return Roles;
    }

    public void setRoles(List<String> roles) {
        Roles = roles;
    }

    @DBRef
    private List<JournalEntity> journals= new ArrayList<JournalEntity>();
    private List<String> Roles;


}
