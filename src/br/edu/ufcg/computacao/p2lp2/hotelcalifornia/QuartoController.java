package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDateTime;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoDouble;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoFamily;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoSingle;

/**
 * @author maria helena cria Quarto Controller
 * 
 */
public class QuartoController {
	private HashMap<String, Quarto> quartos;
	
	public QuartoController() {
		this.quartos = new HashMap<>();
		
	}

	public String disponibilizarQuartoSingle(String idAutenticacao, int idQuartoNum, double precoPorPessoa,
			double precoBase) {
		if (idAutenticacao == null || idAutenticacao.isEmpty()) {
			throw new IllegalArgumentException("ID DE AUTENTICAÇÃO INVÁLIDO");
		}

		if (idQuartoNum <= 0) {
			throw new IllegalArgumentException("ID DO QUARTO INVÁLIDO");
		}

		if (precoPorPessoa < 0 || precoBase < 0) {
			throw new IllegalArgumentException("OS PREÇOS NÃO PODEM SER NEGATIVOS");
		}
		
		if(idAutenticacao.contains("ADM")) {
		QuartoSingle quartoSingle = new QuartoSingle(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase);
		quartos.put(idAutenticacao, quartoSingle);
	return "QUARTO SINGLE DISPONÍVEL";
		}
		return "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS";
		
	}

	public String disponibilizarQuartoDouble(String idAutenticacao, int idQuartoNum, double precoPorPessoa,
			double precoBase, String[] pedidos) {
		if (idAutenticacao == null || idAutenticacao.isEmpty()) {
			throw new IllegalArgumentException("ID DE AUTENTICAÇÃO INVÁLIDO");
		}

		if (idQuartoNum <= 0) {
			throw new IllegalArgumentException("ID DO QUARTO INVÁLIDO");
		}

		if (precoPorPessoa < 0 || precoBase < 0) {
			throw new IllegalArgumentException("OS PREÇOS NÃO PODEM SER NEGATIVOS");
		}
		
		if(idAutenticacao.contains("ADM")) {
			QuartoDouble quartoDouble = new QuartoDouble(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase, pedidos);
			quartos.put(idAutenticacao, quartoDouble);
			return "QUARTO DOUBLE DISPONÍVEL";
	}

		return "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS";
	}

	public String disponibilizarQuartoFamily(String idAutenticacao, int idQuartoNum, double precoPorPessoa,
			double precoBase, String[] pedidos, int qtdMaxPessoas) {
		if (idAutenticacao == null || idAutenticacao.isEmpty()) {
			throw new IllegalArgumentException("ID DE AUTENTICAÇÃO INVÁLIDO");
		}

		if (idQuartoNum <= 0) {
			throw new IllegalArgumentException("ID DO QUARTO INVÁLIDO");
		}

		if (precoPorPessoa < 0 || precoBase < 0) {
			throw new IllegalArgumentException("OS PREÇOS NÃO PODEM SER NEGATIVOS");
		}

		if (qtdMaxPessoas < 0 || qtdMaxPessoas > 10) {
			throw new IllegalArgumentException("QUANTIDADE DE PESSOAS INVÁLIDAS");
		}
		
		if(idAutenticacao.contains("ADM")) {

		QuartoFamily quartoFamily = new QuartoFamily(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase, pedidos,
				qtdMaxPessoas);
		quartos.put(idAutenticacao, quartoFamily);
		return "QUARTO FAMILY DISPONÍVEL";
		} 
		
		return "APENAS ADMINISTRADORES PODEM GERENCIAR OS QUARTOS";
	}

	public String exibirQuarto(int idQuartoNum) {

		if (!quartos.containsKey(idQuartoNum)) {
			throw new IllegalArgumentException("ESSE QUARTO NÃO ESTÁ DISPONÍVEL");
		}

		Quarto quarto = quartos.get(idQuartoNum);
		return quarto.exibirQuarto();

	}

	public String[] listarQuartos() {
		int tamanhoArray = quartos.size();
		String[] quartosArray = new String[tamanhoArray];

		int i = 0;
		for (String idQuartoNum : quartos.keySet()) {
			Quarto quarto = quartos.get(idQuartoNum);
			quartosArray[i] = quarto.exibirQuarto();
			i++;

		}
		return quartosArray;
	}

	public String reservarQuartoSingle(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio, LocalDateTime dataFim, String[] idRefeicoes) {
		return idCliente;
		
	}
}
