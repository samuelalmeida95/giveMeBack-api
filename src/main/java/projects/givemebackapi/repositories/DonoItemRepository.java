package projects.givemebackapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projects.givemebackapi.model.DonoItem;


@Repository
public interface DonoItemRepository extends JpaRepository<DonoItem, Integer>{
    
   Optional<DonoItem> findByNomeDono(String nomeDono);
}
