package br.com.luquita.veraodata.service;

import br.com.luquita.veraodata.entidade.Cargo;
import br.com.luquita.veraodata.repository.CargoRepository;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class CrudCargoService {

    @Autowired
    private CargoRepository cargoRepository;

    private boolean system = true;


    public void iniciar(Scanner leitor) {

        while (system){
            System.out.println("O que deseja fazer em Cargo");
            System.out.println("0 - sair");
            System.out.println("1 - Inserir novo cargo");
            System.out.println("2 - atualizar um cargo");
            System.out.println("3 - consultar cargos");
            System.out.println("4 - deletar um cargo");
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
        System.out.println("Insira a descrição do cargo");
        String descricao = scanner.nextLine();

        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);

        cargoRepository.save(cargo);
        System.out.println(cargo+" inserido");
    }

    private void atualizar(Scanner scanner){
        System.out.println("Insira o Id do registro que quer atualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Insira a nova descrição do cargo");
        String descricao = scanner.nextLine();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);

        cargoRepository.save(cargo);
        System.out.println(cargo+" atualizado");
    }

    private void listarTodos(){
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.print("| " + cargo));
        System.out.println();
    }

    private void deletar(Scanner scanner){
        System.out.println("Insira o Id do registro que quer atualizar:");
        int id = scanner.nextInt();

        cargoRepository.deleteById(id);
        System.out.println("Deletado");
    }

}
