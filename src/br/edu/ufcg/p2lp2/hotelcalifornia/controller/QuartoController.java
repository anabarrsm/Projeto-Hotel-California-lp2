package br.edu.ufcg.p2lp2.hotelcalifornia.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoDouble;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoFamily;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoSingle;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.reserva.Reserva;
import br.edu.ufcg.p2lp2.hotelcalifornia.exception.HotelCaliforniaException;

/**
 * Quarto Controller é a classe responsável por gerenciar as operações
 * relacionadas aos quartos do hotel.
 * 
 * @author Maria Helena
 * 
 */
public class QuartoController {
	private HashMap<Integer, Quarto> quartos;
	private UsuarioController usuarioController;

	public QuartoController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
		this.quartos = new HashMap<>();

	}

	/**
	 * Método que faz o cadastro (torna disponível) de um Quarto do Tipo Single.
	 * 
	 * @param idAutenticacao identificador do Usuario
	 * @param idQuartoNum    numero do quarto a ser disponibilizado
	 * @param precoPorPessoa preço por pessoa
	 * @param precoBase      preço base do quarto
	 * @return "ESSE ADMINISTRADOR NÃO ESTÁ CADASTRADO NO SISTEMA" caso não exista
	 *         um Usuario Cadastrado com o idAutenticacao fornecido; "APENAS
	 *         ADMINISTRADORES PODEM GERENCIAR OS QUARTOS" caso outro tipo de
	 *         usuário tente realizar essa operação "QUARTO SINGLE DISPONÍVEL" caso
	 *         contrário.
	 */
	public String disponibilizarQuartoSingle(String idAutenticacao, int idQuartoNum, double precoBase,
			double precoPorPessoa) {
		if (idAutenticacao == null || idAutenticacao.isEmpty()) {
			throw new IllegalArgumentException("ID DE AUTENTICAÇÃO INVÁLIDO");
		}

		if (idQuartoNum <= 0) {
			throw new IllegalArgumentException("ID DO QUARTO INVÁLIDO");
		}

		if (precoPorPessoa < 0 || precoBase < 0) {
			throw new IllegalArgumentException("OS PREÇOS NÃO PODEM SER NEGATIVOS");
		}

		if (idAutenticacao.contains("ADM")) {

			if (usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
				
				QuartoSingle quartoSingle = new QuartoSingle(idQuartoNum, precoBase, precoPorPessoa);
				quartos.put(idQuartoNum, quartoSingle);
				
				return quartoSingle.exibirQuarto();

			}
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}

		throw new HotelCaliforniaException("USUARIO NAO E ADMINISTRADOR");

	}

	/**
	 * Método que faz o cadastro (torna disponível) de um Quarto do Tipo Double.
	 *
	 * @param idAutenticacao identificador do Usuario
	 * @param idQuartoNum    numero do quarto a ser disponibilizado
	 * @param precoPorPessoa preço por pessoa
	 * @param precoBase      preço base do quarto
	 * @param pedidos        Array de String que contém uma lista de
	 *                       pedidos/melhorias
	 * 
	 * @return "ESSE ADMINISTRADOR NÃO ESTÁ CADASTRADO NO SISTEMA" caso não exista
	 *         um Usuario Cadastrado com o idAutenticacao fornecido; "APENAS
	 *         ADMINISTRADORES PODEM GERENCIAR OS QUARTOS" caso outro tipo de
	 *         usuário tente realizar essa operação "QUARTO DOUBLE DISPONÍVEL" caso
	 *         contrário.
	 */
	public String disponibilizarQuartoDouble(String idAutenticacao, int idQuartoNum, double precoBase,
			double precoPorPessoa, String[] pedidos) {
		if (idAutenticacao == null || idAutenticacao.isEmpty()) {
			throw new IllegalArgumentException("ID DE AUTENTICAÇÃO INVÁLIDO");
		}

		if (idQuartoNum <= 0) {
			throw new IllegalArgumentException("ID DO QUARTO INVÁLIDO");
		}

		if (precoPorPessoa < 0 || precoBase < 0) {
			throw new IllegalArgumentException("OS PREÇOS NÃO PODEM SER NEGATIVOS");
		}

		if (idAutenticacao.contains("ADM")) {

			if (usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
				
				if(quartos.containsKey(idQuartoNum)) {
					throw new HotelCaliforniaException("QUARTO JA EXISTE");
				}

				QuartoDouble quartoDouble = new QuartoDouble(idAutenticacao, idQuartoNum, precoBase, precoPorPessoa,
						pedidos);
				quartos.put(idQuartoNum, quartoDouble);
				return quartoDouble.exibirQuarto();

			}
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}

		throw new HotelCaliforniaException("USUARIO NAO E ADMINISTRADOR");
	}

	/**
	 * Método que faz o cadastro (torna disponível) de um Quarto do Tipo Family.
	 * 
	 * @param idAutenticacao identificador do Usuario
	 * @param idQuartoNum    numero do quarto a ser disponibilizado
	 * @param precoPorPessoa preço por pessoa
	 * @param precoBase      preço base do quarto
	 * @param pedidos        Array de String que contém uma lista de
	 *                       pedidos/melhorias
	 * @param qtdMaxPessoas  quantidade máxima de pessoas possíveis
	 * 
	 * @return "ESSE ADMINISTRADOR NÃO ESTÁ CADASTRADO NO SISTEMA" caso não exista
	 *         um Usuario Cadastrado com o idAutenticacao fornecido; "APENAS
	 *         ADMINISTRADORES PODEM GERENCIAR OS QUARTOS" caso outro tipo de
	 *         usuário tente realizar essa operação "QUARTO FAMILY DISPONÍVEL" caso
	 *         contrário.
	 */

	public String disponibilizarQuartoFamily(String idAutenticacao, int idQuartoNum, double precoBase,
			double precoPorPessoa, String[] pedidos, int qtdMaxPessoas) {
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

		if (idAutenticacao.contains("ADM")) {

			if (usuarioController.encontrarUsuarioPorId(idAutenticacao)) {
	
				
				if(quartos.containsKey(idQuartoNum)) {
					throw new HotelCaliforniaException("QUARTO JA EXISTE");
				}

				QuartoFamily quartoFamily = new QuartoFamily(idQuartoNum, precoBase, precoPorPessoa, pedidos,
						qtdMaxPessoas);
				quartos.put(idQuartoNum, quartoFamily);
				return quartoFamily.exibirQuarto();

			}
			throw new HotelCaliforniaException("USUARIO NAO EXISTE");
		}

		throw new HotelCaliforniaException("USUARIO NAO E ADMINISTRADOR");
	}

	/**
	 * Método que exibe as informações de um quarto.
	 * 
	 * @param idQuartoNum numero do quarto a ser exibido
	 * @return representação textual do quarto
	 */

	public String exibirQuarto(int idQuartoNum) {

		if (!quartos.containsKey(idQuartoNum)) {
			throw new IllegalArgumentException("ESSE QUARTO NÃO ESTÁ DISPONÍVEL");
		}

		Quarto quarto = quartos.get(idQuartoNum);
		return quarto.exibirQuarto();

	}

	/**
	 * Método para listar todos os quartos cadastrados
	 * 
	 * @returnr Array de String dos quartos cadastrados
	 */

	public String[] listarQuartos() {
		int tamanhoArray = quartos.size();
		String[] quartosArray = new String[tamanhoArray];

		int i = 0;
		for (Integer idQuartoNum : quartos.keySet()) {
			Quarto quarto = quartos.get(idQuartoNum);
			quartosArray[i] = quarto.exibirQuarto();
			i++;

		}
		return quartosArray;
	}

	public HashMap<Integer, Quarto> getQuartos() {
		return quartos;
	}


}