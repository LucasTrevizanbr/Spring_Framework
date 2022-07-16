package br.com.luquita.veraodata;

import br.com.luquita.veraodata.entidade.Cargo;
import br.com.luquita.veraodata.repository.CargoRepository;
import br.com.luquita.veraodata.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class VeraoDataApplication implements CommandLineRunner {

	@Autowired
	private CrudCargoService cargoService;

	@Autowired
	private CrudFuncionarioService funcionarioService;

	@Autowired
	private CrudUnidadeTrabalhoService unidadeTrabalhoService;

	@Autowired
	private RelatorioService relatorioService;

	@Autowired
	private RelatorioDinamico relatorioDinamico;

	private boolean system = true;

	public static void main(String[] args)  {
		SpringApplication.run(VeraoDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner leitor = new Scanner(System.in);

		while(system) {
			System.out.println("Qual ação você quer executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade de trabalho");
			System.out.println("4 - Relatorios");
			System.out.println("5 - Relatorios dinamicos");

			int acao = leitor.nextInt();

			switch (acao) {
				case 1:
					cargoService.iniciar(leitor);
					break;
				case 2:
					funcionarioService.iniciar(leitor);
					break;
				case 3:
					unidadeTrabalhoService.iniciar(leitor);
					break;
				case 4:
					relatorioService.iniciar(leitor);
					break;
				case 5:
					relatorioDinamico.iniciar(leitor);
					break;
				default:
					system = false;
					break;
			}
		}
	}
}
