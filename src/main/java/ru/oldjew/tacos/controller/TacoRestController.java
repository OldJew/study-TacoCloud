package ru.oldjew.tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oldjew.tacos.model.Taco;
import ru.oldjew.tacos.model.TacoOrder;
import ru.oldjew.tacos.repository.TacoRepository;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(value = "/api/tacos",
                    produces = {"application/json", "text/xml"})
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoRestController {

    private TacoRepository tacoRepository;

    public TacoRestController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    /**
     *
     * @return last 10 tacos
     */
    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageRequest).getContent();
    }

    /**
     * returns taco which id contains in url path. Ex: /api/tacos/7
     * @param id
     * @return taco and OK status OR null and NOT_FOUND status
     */
    @GetMapping("/{id}")
    public ResponseEntity<Taco> getTacoById(@PathVariable("id") Long id){
        Optional<Taco> taco = tacoRepository.findById(id);
        if (taco.isPresent()){
            return new ResponseEntity<>(taco.get(), HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepository.save(taco);
    }

}
