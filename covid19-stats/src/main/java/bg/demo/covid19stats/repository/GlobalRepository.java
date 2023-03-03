package bg.demo.covid19stats.repository;

import bg.demo.covid19stats.model.entity.GlobalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalRepository extends JpaRepository<GlobalEntity, Integer> {

}
