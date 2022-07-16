package br.com.luquita.veraodata.specification;

import br.com.luquita.veraodata.entidade.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class SpecificationFuncionario {

    public static Specification<Funcionario> nome(String nome){
        return ((root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("nome"), "%"+nome+"%"));
    }

    public static Specification<Funcionario> cpf(String cpf){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("cpf"), cpf));
    }

    public static Specification<Funcionario> salario(BigDecimal salario){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("salario"), salario));
    }

    public static Specification<Funcionario> unidadeTrabalho(String endereco){
        return ((root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("unidadeTrabalho.endereco"),"%"+endereco+"%"));
    }

}
