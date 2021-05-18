package projects.givemebackapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projects.givemebackapi.model.DonoItem;


@Repository
public interface DonoItemRepositorie extends JpaRepository<DonoItem, Integer>{
    
}
