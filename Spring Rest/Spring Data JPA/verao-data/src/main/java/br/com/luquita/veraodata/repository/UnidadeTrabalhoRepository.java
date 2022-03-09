package br.com.luquita.veraodata.repository;

import br.com.luquita.veraodata.entidade.Funcionario;
import br.com.luquita.veraodata.entidade.UnidadeTrabalho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeTrabalhoRepository extends CrudRepository<UnidadeTrabalho, Integer> {
}
