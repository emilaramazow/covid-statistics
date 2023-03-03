package bg.demo.covid19stats.service.impl;

import bg.demo.covid19stats.model.entity.GlobalEntity;
import bg.demo.covid19stats.model.entity.GlobalSummary;
import bg.demo.covid19stats.repository.GlobalRepository;
import bg.demo.covid19stats.service.GlobalService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import static bg.demo.covid19stats.constants.GlobalConstants.COVID_DATA_FILE;
import static bg.demo.covid19stats.constants.GlobalConstants.FILE_PATH;

@Service
@AllArgsConstructor
public class GlobalServiceImpl implements GlobalService {

    private final ObjectMapper objectMapper;
    private final GlobalRepository globalRepository;

    public void readGlobalCases() throws IOException {
        String jsonFileContent = Files.readString(Path.of(FILE_PATH + COVID_DATA_FILE));

        if (globalRepository.count() > 0) {
            return;
        }

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GlobalSummary globalSummary = objectMapper.readValue(jsonFileContent, GlobalSummary.class);

        GlobalEntity global = new GlobalEntity();
        global.setNewConfirmed(globalSummary.getGlobal().getNewConfirmed());
        global.setTotalConfirmed(globalSummary.getGlobal().getTotalConfirmed());
        global.setNewDeaths(globalSummary.getGlobal().getNewDeaths());
        global.setTotalDeaths(globalSummary.getGlobal().getTotalDeaths());
        global.setNewRecovered(globalSummary.getGlobal().getNewRecovered());
        global.setTotalRecovered(globalSummary.getGlobal().getTotalRecovered());
        global.setDate(globalSummary.getGlobal().getDate());

       globalRepository.save(global);

    }
}
