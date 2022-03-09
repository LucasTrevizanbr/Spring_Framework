package br.com.luquita.veraodata.repository;

import br.com.luquita.veraodata.entidade.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {

}
