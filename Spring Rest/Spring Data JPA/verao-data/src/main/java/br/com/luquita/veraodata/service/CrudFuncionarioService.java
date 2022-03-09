package br.com.luquita.veraodata.service;

import br.com.luquita.veraodata.entidade.Cargo;
import br.com.luquita.veraodata.entidade.Funcionario;
import br.com.luquita.veraodata.entidade.UnidadeTrabalho;
import br.com.luquita.veraodata.projecao.FuncionarioProjecao;
import br.com.luquita.veraodata.repository.CargoRepository;
import br.com.luquita.veraodata.repository.FuncionarioRepository;
import br.com.luquita.veraodata.repository.UnidadeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    private boolean system = true;

    public void iniciar(Scanner leitor) {

        while (system){
            System.out.println("O que deseja fazer em Funcionario");
            System.out.println("0 - sair");
            System.out.println("1 - Inserir novo funcionario");
            System.out.println("2 - atualizar um funcionario");
            System.out.println("3 - consultar funcionarios");
            System.out.println("4 - remover um funcionario");
            System.out.println("5 - consulta projetada");
            int opcao = leitor.nextInt();

            switch (opcao){
                case 1:
                    salvar(leitor);
                    break;
                case 2:
                    atualizar(leitor);
                    break;
                case 3:
                    listarTodos(leitor);
                    break;
                case 4:
                    deletar(leitor);
                    break;
                case 5:
                    consultaProjetada();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void consultaProjetada(){
        List<FuncionarioProjecao> funcionario = funcionarioRepository.buscaProjetada();
        funcionario.forEach(f -> System.out.print("| "+f.getNome()+"| "+f.getId()+"| "+f.getSalario()));
        System.out.println();
    }

    private void listarTodos(Scanner leitor){
        System.out.println("Digite o número da página: ");
        int pagina = leitor.nextInt();

        Pageable paginacao = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "nome"));

        Page<Funcionario> funcionarios = funcionarioRepository.findAll(paginacao);

        System.out.println(funcionarios);
        System.out.println("Pagina atual: "+ funcionarios.getNumber());
        System.out.println("Total de funcionários: "+ funcionarios.getTotalElements());

        funcionarios.forEach(funcionario -> System.out.println(
                funcionario.getNome() +"| "+funcionario.getCargo()
                +"| "+funcionario.getCpf() +"| "+funcionario.getSalario() +"| "+funcionario.getId()
        ) );
    }

    private void salvar(Scanner scanner){
        Funcionario funcionario = new Funcionario();

        scanner.nextLine();
        System.out.println("Insira o nome do usuario");
        funcionario.setNome(scanner.nextLine());

        System.out.println("Insira o cpf do usuario");
        funcionario.setCpf(scanner.nextLine());

        System.out.println("Insira o salário do usuario");
        funcionario.setSalario(new BigDecimal(scanner.next()));
        scanner.nextLine();

        System.out.println("Insira o id do cargo do usuario");
        Optional<Cargo> cargo = cargoRepository.findById(scanner.nextInt());
        scanner.nextLine();
        if(cargo.isPresent()){
            funcionario.setCargo(cargo.get());
        }

        System.out.println("Insira o id da unidade de trabalho do usuario");
        Optional<UnidadeTrabalho> unidadeTrabalho = unidadeTrabalhoRepository.findById(scanner.nextInt());
        scanner.nextLine();
        if(unidadeTrabalho.isPresent()){
            funcionario.setUnidadesTrabalho(unidadeTrabalho.stream().toList());
        }

        funcionarioRepository.save(funcionario);
        System.out.println(cargo+" inserido");
    }

    private void atualizar(Scanner scanner){
        System.out.println("Insira o Id do registro que quer atualizar:");
        long id = scanner.nextLong();

        Funcionario funcionario = new Funcionario();

        scanner.nextLine();
        System.out.println("Insira o nome do usuario");
        funcionario.setNome(scanner.nextLine());

        System.out.println("Insira o salário do usuario");
        funcionario.setSalario(new BigDecimal(scanner.next()));

        System.out.println("Insira o id do cargo do usuario");
        Optional<Cargo> cargo = cargoRepository.findById(scanner.nextInt());
        scanner.nextLine();
        if(cargo.isPresent()){
            funcionario.setCargo(cargo.get());
        }

        System.out.println("Insira o id da unidade de trabalho do usuario");
        Optional<UnidadeTrabalho> unidadeTrabalho = unidadeTrabalhoRepository.findById(scanner.nextInt());
        scanner.nextLine();
        if(unidadeTrabalho.isPresent()){
            funcionario.setUnidadesTrabalho(unidadeTrabalho.stream().toList());
        }
        funcionario.setId(id);

        funcionarioRepository.save(funcionario);
        System.out.println(funcionario+" atualizado");
    }

    private void deletar(Scanner scanner){
        System.out.println("Insira o Id do registro que quer atualizar:");
        long id = scanner.nextLong();

        funcionarioRepository.deleteById(id);
        System.out.println("Deletado");
    }

}
