package br.com.luquita.veraodata.service;

import br.com.luquita.veraodata.entidade.Cargo;
import br.com.luquita.veraodata.entidade.UnidadeTrabalho;
import br.com.luquita.veraodata.repository.CargoRepository;
import br.com.luquita.veraodata.repository.UnidadeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    @Autowired
    private UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    private boolean system = true;

    public void iniciar(Scanner leitor) {

        while (system){
            System.out.println("O que deseja fazer em Cargo");
            System.out.println("0 - sair");
            System.out.println("1 - Inserir uma nova Unidade de trabalho");
            System.out.println("2 - atualizar uma unidade de trabalho");
            System.out.println("3 - consultar unidades de trabalho ");
            System.out.println("4 - remover uma unidade de trabalho ");
            int opcao = leitor.nextInt();

            switch (opcao){
                case 1:
                    salvar(leitor);
                    break;
                case 2:
                    atualizar(leitor);
                    break;
                case 3:
                    listarTodos();
                    break;
                case 4:
                    deletar(leitor);
                default:
                    system = false;
                    break;
            }
        }
    }

    private void salvar(Scanner scanner){
        scanner.nextLine();
        System.out.println("Insira o endereco da unidade de trabalho");
        String endereco = scanner.nextLine();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setEndereco(endereco);

        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println(unidadeTrabalho +" inserido");
    }

    private void atualizar(Scanner scanner){
        System.out.println("Insira o Id do registro que quer atualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Insira o novo endereço da Unidade");
        String endereco = scanner.nextLine();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setEndereco(endereco);

        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println(unidadeTrabalho+" atualizado");
    }

    private void listarTodos(){
        Iterable<UnidadeTrabalho> unidadesTrabalho = unidadeTrabalhoRepository.findAll();
        unidadesTrabalho.forEach(unidade -> System.out.println(
                unidade.getEndereco() + " | ID: "+unidade.getId()
        ));
    }

    private void deletar(Scanner scanner){
        System.out.println("Insira o Id do registro que quer deletar:");
        int id = scanner.nextInt();

        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Deletado");
    }

}
