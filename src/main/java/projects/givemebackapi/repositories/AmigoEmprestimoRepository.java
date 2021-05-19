package projects.givemebackapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projects.givemebackapi.model.AmigoEmprestimo;

@Repository
public interface AmigoEmprestimoRepository extends JpaRepository<AmigoEmprestimo, Integer>{
    
}
