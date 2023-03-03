package bg.demo.covid19stats.repository;

import bg.demo.covid19stats.model.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

    Optional<CountryEntity> findByCountryCode(String countryCode);
}
