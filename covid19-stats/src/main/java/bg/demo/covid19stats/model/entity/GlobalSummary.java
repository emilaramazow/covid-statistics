package bg.demo.covid19stats.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalSummary extends BaseEntity{

    private String message;
    @JsonProperty("Global")
    @OneToOne(cascade = CascadeType.ALL)
    private GlobalEntity global;
    @JsonProperty("Countries")
    @OneToMany(cascade = CascadeType.ALL)
    private List<CountryEntity> countries;

}
