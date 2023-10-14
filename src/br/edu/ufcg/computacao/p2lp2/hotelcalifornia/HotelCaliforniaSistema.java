package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoDouble;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoFamily;
import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.QuartoSingle;

public class HotelCaliforniaSistema {
	private ArrayList<Usuario> usuarios;
	private boolean gerenteCadastrado = false;
	private HashMap<String, Quarto> quartos;
	private Usuario adm;

	public HotelCaliforniaSistema() {
		this.usuarios = new ArrayList<Usuario>();
		Usuario adm1 = new Usuario("João Costa", "ADM", "123456");
		adm1.setId("ADM1");
		this.quartos = new HashMap<>();

	}
	/**
	 * método para encontrar um usuário pelo seu ID
	 * 
	 * @param idAutenticacao
	 * @return
	 */
	
	public boolean encontrarUsuarioPorId(String id) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean validaTipo(String tipoUsuario) {
		if(!(tipoUsuario.equals("ADM")) || !(tipoUsuario.equals("GER")) || !(tipoUsuario.equals("CLI")) || !(tipoUsuario.equals("FUN"))) {
			return false;
		}
		return true;
	}

	/**
	 * Cadastra um usuário.
	 * 
	 * @param idAutenticacao
	 * @param nome
	 * @param tipoUsuario
	 * @param documento
	 * @return
	 */

	public String cadastrarUsuario(String idAutenticacao, String nome, String tipoUsuario, String documento) {
		
		if((nome == null || (documento == null))) {
			throw new NullPointerException("PARÂMETRO INVÁLIDO!");
		
		} else if (encontrarUsuarioPorId(idAutenticacao) == false) {
			throw new NullPointerException("ID NÃO EXISTE!");
			
		} else if (validaTipo(tipoUsuario) == false) {
			throw new IllegalArgumentException("TIPO INVÁLIDO!");
			
		} else if (idAutenticacao.contains("CLI")) {
			return "CLIENTE NÃO PODE CADASTRAR USUÁRIO!";
			
		} else if((idAutenticacao.contains("FUN")) && ((tipoUsuario.equals("ADM")) || (tipoUsuario.equals("GER")) || (tipoUsuario.equals("FUN")))) {
			return "FUNCIONÁRIO SÓ PODE CADASTRAR CLIENTE!";
			
		} else if ((idAutenticacao.contains("GER")) && (tipoUsuario.equals("ADM"))) {
			return "GERENTE NÃO PODE CADASTRAR ADMINISTRADOR!";
			
		} else if (tipoUsuario.equals("GER")) {
			for(Usuario usuario : usuarios) {
				if (usuario.getTipo().equals("GER")) {
					return "JÁ EXISTE UM GERENTE CADASTRADO!";
				}
			}
		} else {
			Usuario usuario = new Usuario(nome, tipoUsuario, documento);
			this.usuarios.add(usuario);
			
			for(int i = 0; i < usuarios.size(); i++) {
				if(usuarios.get(i).equals(usuario)) {
					usuarios.get(i).setId(tipoUsuario + (i + 1));
				}
			}
		}
		return "USUÁRIO CADASTRADO COM SUCESSO!";
	}
		
		
		/**
		 * confere se já há um gerente cadastrado
		 */

		
	/**
	 * Atualiza o tipo de usuário
	 * 
	 * @param idAutenticacao
	 * @param idUsuario
	 * @param novoTipoUsuario
	 * @return
	 */

	public String atualizarUsuario(String idAutenticacao, String idUsuario, String novoTipoUsuario) {
		if ((encontrarUsuarioPorId(idAutenticacao) == false) || ((encontrarUsuarioPorId(idUsuario) == false))) {
			throw new IllegalArgumentException("ID NÃO EXISTE!");
			
		} else if (validaTipo(novoTipoUsuario) == false) {
			throw new IllegalArgumentException("TIPO INVÁLIDO!");
			
		}
		
		else if (!idAutenticacao.contains("ADM")) {
			return "APENAS UM ADMINISTRADOR PODE ATUALIZAR OS USUÁRIOS.";
		}
		
		else if(novoTipoUsuario.equals("GER")) {
			for (int i = 0; i < usuarios.size(); i++) {
				if(usuarios.get(i).getTipo().equals("GER")) {
					usuarios.get(i).setTipo("FUN");
					usuarios.get(i).setTipo("FUN" + (i+1));
					
				} else if(usuarios.get(i).getId().equals(novoTipoUsuario)) {
					usuarios.get(i).setTipo("GER");
					usuarios.get(i).setId("GER" + (i+1));
				}
			}
			
		} else {
			for (int i = 0; i < usuarios.size(); i++) {
				if(usuarios.get(i).getId().equals(novoTipoUsuario)) {
					usuarios.get(i).setTipo("GER");
					usuarios.get(i).setId("GER" + (i+1));
				}
			}
		}
		return "USUÁRIO ATUALIZADO!";
		
	}

	/**
	 * Exibe informações sobre o Usuário.
	 * 
	 * @param idUsuario
	 * @return
	 */

	public String exibirUsuario(String idUsuario) {
		String saida = "";
		if (encontrarUsuarioPorId(idUsuario) == false) {
			throw new IllegalArgumentException("ID NÃO EXISTE!");
	
		} else {
			for (Usuario usuario : usuarios) {
				if(usuario.getId().equals(idUsuario)) {
					saida = usuario.toString();
				}
			}
		}
		return saida;
	}

	/**
	 * Lista Usuários
	 * 
	 * @return
	 */

	public String[] listarUsuarios() {
		ArrayList<String> usuariosExistentes = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			usuariosExistentes.add(
					"[" + usuario.getId() + "] " + usuario.getNome() + " (No. Doc. " + usuario.getDocumento() + ")");
		}
		return usuariosExistentes.toArray(new String[0]);

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

		QuartoSingle quartoSingle = new QuartoSingle(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase);
		quartos.put(idAutenticacao, quartoSingle);

		return "QUARTO SINGLE DISPONÍVEL";
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

		if (idAutenticacao == "ADM") {
			QuartoDouble quartoDouble = new QuartoDouble(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase,
					pedidos);
			quartos.put(idAutenticacao, quartoDouble);
			return "QUARTO DOUBLE DISPONÍVEL!";

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

		QuartoFamily quartoFamily = new QuartoFamily(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase, pedidos,
				qtdMaxPessoas);
		quartos.put(idAutenticacao, quartoFamily);

		return "QUARTO FAMILY DISPONÍVEL";
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

	// Metodos US03 --> Verificar se existe algo a acrescentar.
	public String reservaQuartoSingle(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes) {
		return null;
	}

	public String reservarQuartoDouble(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos) {
		return null;
	}

	public String reservaQuartoFamily(String idAutenticacao, String idCliente, int numQuarto, LocalDateTime dataInicio,
			LocalDateTime dataFim, String[] idRefeicoes, String[] pedidos, int numPessoas) {
		return null;
	}

	public String exibeReserva(String idAutenticacao, long idReserva) {
		return null;
	}

	public String[] listaReservasAtivasDoCliente(String idAutenticacao, String idCliente) {
		return null;
	}

	public String[] listaReservasAtivasDoClientePorTipo(String idAutenticacao, String idCliente, String tipo) {
		return null;
	}

	public String[] listaReservasAtivasPorTipo(String idAutenticacao, String tipo) {
		return null;
	}

	public String[] listaReservasAtivas(String idAutenticacao) {
		return null;
	}

	public String[] listaReservasTodas(String idAutenticacao) {
		return null;
	}

	// Metodos US04 --> Verificar se tem existe a acrescentar.
	/*
	public String disponibilizarRefeicao(String idAutenticacao, String tipoRefeicao, String titulo,
			LocalTime horarioInicio, LocalTime horarioFinal, double valor, boolean disponivel) {
		return null;
	}

	public String alterarRefeicao(long idRefeicao, LocalTime horarioInicio, LocalTime horarioFinal,
			boolean disponivel) {
		return null;
	}

	public String exibirRefeicao(long idRefeicao) {
		return null;
	}

	public String[] listarRefeicoes() {
		return null;
	}
	 */

	public String reservarRestaurante(String idAutenticacao, String idCliente, LocalDateTime dataInicio, LocalDateTime dataFim, int qtdePessoas, String refeicao){
		return null;
	}

	public String disponibilizarFormaDePagamento(String idAutenticacao, String formaPagamento, double percentualDesconto){
		return null;
	}
	public String alterarFormaDePagamento(String idAutenticacao, int idFormaPagamento, String formaPagamento, double percentualDesconto){
		return null;
	}
	public String exibirFormaPagamento(int idFormaPagamento){
		return null;
	}
	public String[] listarFormasPagamentos(){
		return null;
	}

	public String pagarReservaComDinheiro(String idCliente, long idReserva, String nomeTitular){
		return null;
	}
	public String pagarReservaComCartao(String idCliente, long idReserva, String nomeTitular, String numCartao, String validade, String codigoDeSeguranca, int qtdeParcelas){
		return null;
	}
	public String pagarReservaComPix(String idCliente, long idReserva, String nomeTitular, String cpf, String banco){
		return null;
	}

	public String cancelarReserva(String idCliente, String idReserva){
		return null;
	}

}
