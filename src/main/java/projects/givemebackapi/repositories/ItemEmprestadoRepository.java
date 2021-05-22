package projects.givemebackapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;

@Repository
public interface ItemEmprestadoRepository extends JpaRepository<ItemEmprestado, Integer>{

    Optional<ItemEmprestado> findByNomeItem(String nomeItem);

    List<ItemEmprestado> findByStatus(TipoStatus tipo);

    Optional<List<ItemEmprestado>> findByAmigoEmprestimoId(Integer nomeAmigo);

    Optional<List<ItemEmprestado>> findByDonoItemId(Integer idDono);
    
}
