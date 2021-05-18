package projects.givemebackapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projects.givemebackapi.model.ItemEmprestado;

@Repository
public interface ItemEmprestadoRepository extends JpaRepository<ItemEmprestado, Integer>{
    
}
