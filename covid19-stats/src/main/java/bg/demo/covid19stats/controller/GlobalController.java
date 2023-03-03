package bg.demo.covid19stats.controller;

import bg.demo.covid19stats.model.entity.GlobalEntity;
import bg.demo.covid19stats.repository.GlobalRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/global")
@AllArgsConstructor
public class GlobalController {

    private GlobalRepository globalRepository;

    @GetMapping("")
    public ResponseEntity<List<GlobalEntity>> getAllGlobal() {
        List<GlobalEntity> globalList = globalRepository.findAll();
        if (globalList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(globalList);
        }
    }

}