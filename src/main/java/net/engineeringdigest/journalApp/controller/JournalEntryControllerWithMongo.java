package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntity;
import net.engineeringdigest.journalApp.services.JournalEntryServices;
import net.engineeringdigest.journalApp.services.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journals")
public class JournalEntryControllerWithMongo {
    @Autowired
    private JournalEntryServices journalServices;
    @Autowired
    private UserEntryService userServices;

    @GetMapping
    public  ResponseEntity<List<JournalEntity>> getJournalsOfUser(){
        Authentication user= SecurityContextHolder.getContext().getAuthentication();
        String username = user.getName();
        List<JournalEntity> journals = journalServices.getJournals(username);
        if (!journals.isEmpty()){
            return new ResponseEntity<>(journals,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping()
    public ResponseEntity<JournalEntity> addJournals(@RequestBody JournalEntity entity){
        Authentication user= SecurityContextHolder.getContext().getAuthentication();
        String username = user.getName();

try {
    journalServices.addJournal(entity,username);

    return new ResponseEntity<>(entity,HttpStatus.CREATED);
} catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
}

    }
    @DeleteMapping("/delete/{myid}")
    public ResponseEntity<JournalEntity> deleteJournal(@PathVariable ObjectId myid){
        Authentication user= SecurityContextHolder.getContext().getAuthentication();
        String username = user.getName();
        Optional<JournalEntity> entity=journalServices.findJournalById(username,myid);
        boolean b = journalServices.deleteJournal(myid, username);
        if(b){

            return new ResponseEntity<>(entity.get(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);


    }
    @GetMapping("/find/{myid}")
    public ResponseEntity<JournalEntity> findById(@PathVariable ObjectId myid){
        Authentication user= SecurityContextHolder.getContext().getAuthentication();
        String username = user.getName();
        Optional<JournalEntity> journalById = journalServices.findJournalById(username,myid);
        if(journalById.isPresent()){
            return new ResponseEntity<>(journalById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @PutMapping("/update/{myid}")
    public ResponseEntity<JournalEntity> updateJournalBYId(@PathVariable ObjectId myid,@RequestBody JournalEntity entity){
        Authentication user= SecurityContextHolder.getContext().getAuthentication();
        String username = user.getName();
        Optional<JournalEntity> old = journalServices.findJournalById(username,myid);
        if(old.isPresent()) {
            old.get().setTitle((entity.getTitle() != null && !entity.getTitle().isEmpty()) ? entity.getTitle() : old.get().getTitle() );
            old.get().setDescription((entity.getDescription() != null && !entity.getDescription().isEmpty()) ? entity.getDescription() : old.get().getDescription() );
            journalServices.addJournal(old.get(),username);
           return new ResponseEntity<>(old.get(),HttpStatus.OK);
        }

        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
}
