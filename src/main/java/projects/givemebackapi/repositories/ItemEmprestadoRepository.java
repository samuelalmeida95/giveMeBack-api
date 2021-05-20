package projects.givemebackapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projects.givemebackapi.model.ItemEmprestado;

@Repository
public interface ItemEmprestadoRepository extends JpaRepository<ItemEmprestado, Integer>{

    Optional<ItemEmprestado> findByNomeItem(String nomeItem);
    
}
