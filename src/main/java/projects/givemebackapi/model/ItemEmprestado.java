package projects.givemebackapi.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
public class ItemEmprestado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    @NotEmpty(message = "Campo NOME é obrigatório!")
    @Length(min = 3, max = 100, message = "Campo NOME ITEM deve ter entre 3 e 100 caracteres")
    private String nomeItem;

    @NotEmpty(message = "Campo DESCRIÇÃO é obrigatório!")
    @Length(min = 3, max = 100, message = "Campo DESCRIÇÃO deve ter entre 3 e 100 caracteres")
    private String descricaoItem;

    private Calendar dataDevolucaoItem;

    private Calendar dataEmprestimoItem;

    @Enumerated(value = EnumType.STRING)
    private TipoStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "donoItem_id")
    private DonoItem donoItem;

    @ManyToOne
    @JoinColumn(name = "amigoEmprestimo_id")
    private AmigoEmprestimo amigoEmprestimo;

    public ItemEmprestado(Integer id, String nome, String desc, DonoItem dono,
            AmigoEmprestimo amigoEmp) {

        this.idItem = id;
        this.nomeItem = nome;
        this.descricaoItem = desc;
        this.dataEmprestimoItem = Calendar.getInstance();
        this.dataDevolucaoItem = null;
        this.status = TipoStatus.EMPRESTADO;
        this.donoItem = dono;
        this.amigoEmprestimo = amigoEmp;
    }

    public ItemEmprestado() {
    }

    public void update(ItemEmprestado novoItemEmprestado) {
        this.nomeItem = novoItemEmprestado.nomeItem;
        this.descricaoItem = novoItemEmprestado.descricaoItem;
    }

}
