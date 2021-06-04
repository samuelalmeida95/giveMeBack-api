package projects.givemebackapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import projects.givemebackapi.model.ItemEmprestado;
import projects.givemebackapi.model.TipoStatus;

@Repository
public interface ItemEmprestadoRepository
  extends JpaRepository<ItemEmprestado, Integer> {
    
  Optional<ItemEmprestado> findByNomeItem(String nomeItem);

  List<ItemEmprestado> findByStatus(TipoStatus tipo);

  List<ItemEmprestado> findByAmigoEmprestimoId(Integer nomeAmigo);

  List<ItemEmprestado> findByDonoItemId(Integer idDono);

  
  @Query(value = "select * from public.item_emprestado where id_item=:idItem and amigo_emprestimo_id=:amigoBuscadoId", nativeQuery = true)
  Optional<ItemEmprestado> findByIdItemAndAmigoId(Integer amigoBuscadoId, Integer idItem);
}
