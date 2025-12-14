//package net.engineeringdigest.journalApp.controller;
//
//import net.engineeringdigest.journalApp.entity.JournalEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping ("/journal")
//public class JournalEntryControllerWithoutMongo {
//    private final Map<Integer, JournalEntity> entity = new HashMap<>();
//
//    @GetMapping()
//    public ArrayList<JournalEntity> getEntity() {
//        return new ArrayList<>(entity.values());
//    }
//
//    @PostMapping()
//    public boolean createEntity(@RequestBody JournalEntity newEntry) {
//        if (entity.containsKey(newEntry.getId())) {
//            return false;
//        } else {
//            entity.put(newEntry.getId(), newEntry);
//            return true;
//        }
//    }
//
//    @GetMapping("id/{myid}")
//    public JournalEntity getEntityById(@PathVariable int myid) {
//        return entity.get(myid);
//
//    }
//
//    @PutMapping("update/{myid}")
//    public JournalEntity updateById(@PathVariable int myid, @RequestBody JournalEntity newEntity) {
//        entity.put(myid, newEntity);
//        return newEntity;
//
//    }
//
//    @DeleteMapping("delete/{myid}")
//    public boolean deleteEntry(@PathVariable int myid) {
//        if (entity.containsKey(myid)) {
//            entity.remove(myid);
//            return true;
//        }
//        return false;
//    }
//}
//
//
//
