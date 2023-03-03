package bg.demo.covid19stats;

import bg.demo.covid19stats.service.impl.CountryServiceImpl;
import bg.demo.covid19stats.service.impl.GlobalServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
@AllArgsConstructor
public class Covid19StatisticsApplicationInit implements CommandLineRunner {

    private final CountryServiceImpl countryService;
    private final GlobalServiceImpl globalService;

    @Override
    public void run(String... args) throws Exception {

        globalService.readGlobalCases();
        countryService.readCountryCases();

    }
}
