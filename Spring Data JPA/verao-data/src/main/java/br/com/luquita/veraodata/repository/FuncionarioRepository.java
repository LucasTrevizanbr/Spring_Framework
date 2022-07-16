package br.com.luquita.veraodata.repository;

import br.com.luquita.veraodata.entidade.Funcionario;
import br.com.luquita.veraodata.projecao.FuncionarioProjecao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long>,
        JpaSpecificationExecutor<Funcionario> {

    @Query("SELECT f.nome as nome, f.salario as salario, f.id as id FROM Funcionario f")
    List<FuncionarioProjecao> buscaProjetada();

    @Query("from Funcionario f JOIN FETCH f.cargo WHERE f.nome = :nome")
    List<Funcionario> findByNome(String nome);

    @Query("from Funcionario f JOIN FETCH f.cargo WHERE f.salario >= :salario")
    List<Funcionario> buscarPorSalarioMaiorQue(BigDecimal salario);

    @Query(value = "SELECT * FROM funcionarios WHERE funcionarios.data_contratacao >= :dataContratacao",
    nativeQuery = true)
    List<Funcionario> buscarPorDataContratacao(LocalDate dataContratacao);
}
