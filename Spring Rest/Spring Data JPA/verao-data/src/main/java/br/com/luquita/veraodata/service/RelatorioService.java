package br.com.luquita.veraodata.service;

import br.com.luquita.veraodata.entidade.Funcionario;
import br.com.luquita.veraodata.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService {

    private boolean system = true;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void iniciar(Scanner leitor) {

        while (system){
            System.out.println("O que deseja fazer?");
            System.out.println("0 - sair");
            System.out.println("1 - buscar funcionários por nome");
            System.out.println("2 - buscar por salário");
            int opcao = leitor.nextInt();

            switch (opcao){
                case 1:
                    buscarFuncionarioPorNome(leitor);
                    break;
                case 2:
                    buscarPorSalarioAPartirDe(leitor);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscarPorSalarioAPartirDe(Scanner leitor) {
        System.out.println("A partir de quanto: ");
        String salario = leitor.next();

        List<Funcionario> funcionarios = funcionarioRepository.buscarPorSalarioMaiorQue(new BigDecimal(salario));
        funcionarios.forEach(funcionario -> System.out.println(
                "Nome: " + funcionario.getNome() + ", cargo: "+funcionario.getCargo()
                        +", Salario: "+funcionario.getSalario() +", id: "+funcionario.getId()
        ));

    }

    private void buscarFuncionarioPorNome(Scanner leitor) {
        System.out.println("Qual o nome do funcionário?");
        String nome = leitor.next();

        List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
        System.out.println("Funcionários encontrados: ");
        funcionarios.forEach(funcionario -> System.out.println(
                "Nome: " + funcionario.getNome() + ", cargo: "+funcionario.getCargo()
                +", Salario: "+funcionario.getSalario() +", id: "+funcionario.getId()
        ));
    }
}
