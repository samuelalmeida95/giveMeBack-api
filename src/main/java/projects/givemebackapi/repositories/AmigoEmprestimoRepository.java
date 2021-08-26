package projects.givemebackapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import projects.givemebackapi.model.AmigoEmprestimo;

@Repository
public interface AmigoEmprestimoRepository extends JpaRepository<AmigoEmprestimo, Integer>{

    Optional<AmigoEmprestimo> findByNome(String nome);
    
    @Query(value = "select * from  public.amigo_emprestimo where avaliacao = 'BOA' or avaliacao = 'OTIMA'" , nativeQuery = true)
    List<AmigoEmprestimo> findByAvaliacaoBoaOrOtima();

    @Query(value = "select * from  public.amigo_emprestimo where avaliacao = 'PESSIMA' or avaliacao = 'RUIM'" , nativeQuery = true)
    List<AmigoEmprestimo> findByAvaliacaoPessimaOrRuim();

    
    @Query(value = "select * from  public.amigo_emprestimo where id =:idAmigo and dono_item_id =:idDono", nativeQuery = true)
    Optional<AmigoEmprestimo> findyByIdDonoAndIdAmigoEmprestimo(Integer idAmigo, Integer idDono);

    Optional <AmigoEmprestimo> findByWhatsapp(String whatsapp);
}
