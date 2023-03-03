package bg.demo.covid19stats.controller;

import bg.demo.covid19stats.model.entity.CountryEntity;
import bg.demo.covid19stats.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country")
@AllArgsConstructor
public class CountryController {

    private final CountryRepository countryRepository;

    @GetMapping("/{countryCode}")
    public ResponseEntity<CountryEntity> getCountryByCode(@PathVariable String countryCode) {

        Optional<CountryEntity> country = countryRepository.findByCountryCode(countryCode);

        if (country.isPresent()) {
            return ResponseEntity.ok(country.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CountryEntity>> getAllCountries() {
        List<CountryEntity> countries = countryRepository.findAll();

        if (countries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
}


