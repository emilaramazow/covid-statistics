package bg.demo.covid19stats.service.impl;

import bg.demo.covid19stats.model.entity.CountryEntity;
import bg.demo.covid19stats.model.entity.GlobalSummary;
import bg.demo.covid19stats.repository.CountryRepository;
import bg.demo.covid19stats.service.CountryService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static bg.demo.covid19stats.constants.GlobalConstants.COVID_DATA_FILE;
import static bg.demo.covid19stats.constants.GlobalConstants.FILE_PATH;

@Service
@Data
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final ObjectMapper objectMapper;
    private final CountryRepository countryRepository;

    public void readCountryCases() throws IOException {
        String jsonFileContent = Files.readString(Path.of(FILE_PATH + COVID_DATA_FILE));

        if (countryRepository.count() > 0) {
            return;
        }

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GlobalSummary globalSummary = objectMapper.readValue(jsonFileContent, GlobalSummary.class);

        List<CountryEntity> countries = globalSummary.getCountries();

        countries.stream()
                .map(c -> {
                    CountryEntity country = new CountryEntity();
                    country.setCountry(c.getCountry());
                    country.setCountryCode(c.getCountryCode());
                    country.setSlug(c.getSlug());
                    country.setNewConfirmed(c.getNewConfirmed());
                    country.setTotalConfirmed(c.getTotalConfirmed());
                    country.setNewDeaths(c.getNewDeaths());
                    country.setTotalDeaths(c.getTotalDeaths());
                    country.setNewRecovered(c.getNewRecovered());
                    country.setTotalRecovered(c.getTotalRecovered());
                    country.setDate(c.getDate());

                    return country;
                })
                .forEach(countryRepository::save);
    }
}
