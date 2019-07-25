package concurs.service.rest;

import concurs.model.Proba;
import concurs.repository.ProbaDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/probe")
public class ProbaController {
    private static final String template = "Hello, %s!";

    @Autowired
    private ProbaDbRepository probaDbRepository;



    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, name);
    }

    @CrossOrigin
    @RequestMapping( method= RequestMethod.GET)
    public Proba[] getAll(){
        Proba[] probe=new Proba[probaDbRepository.size()];
        int i=0;
        for (Proba proba : probaDbRepository.findProbeAndNrParticipanti()) {
            probe[i]=proba;
            i++;
        }
        return probe;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id){

        Proba proba=probaDbRepository.findOne(id);
        if (proba==null)
            return new ResponseEntity<String>("Proba not found",HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Proba>(proba, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public Proba create(@RequestBody Proba proba){
        probaDbRepository.save(proba);
        return proba;

    }

    @CrossOrigin
    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        System.out.println("Deleting user ... "+id);
        probaDbRepository.delete(id);
        return new ResponseEntity<Proba>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Proba update(@RequestBody Proba proba) {
        System.out.println("Updating user ...");
        probaDbRepository.update(proba);
        return proba;

    }
}
