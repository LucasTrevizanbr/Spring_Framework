package br.com.luquita.veraodata.service;

import br.com.luquita.veraodata.entidade.Funcionario;
import br.com.luquita.veraodata.repository.FuncionarioRepository;
import br.com.luquita.veraodata.specification.SpecificationFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioDinamico {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void iniciar(Scanner scanner){

        scanner.nextLine();
        System.out.println("Informe os critérios de pesquisa que deseja usar, se não quiser apenas pule com ENTER");
        System.out.println("Informe o nome: ");
        String nome = scanner.nextLine();
        if(nome.isEmpty()){
            nome = null;
        }

        System.out.println("Informe o CPF:");
        String cpf = scanner.nextLine();
        if(cpf.isEmpty()){
            cpf = null;
        }

        List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification.where(
                SpecificationFuncionario.nome(nome)
                .or(SpecificationFuncionario.cpf(cpf))
        ));

        funcionarios.forEach(System.out::println);

    }
}
