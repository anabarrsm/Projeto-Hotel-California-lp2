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
		this.adm = new Usuario("adm1", "Joao Costa", "ADM", 123456);
		this.quartos = new HashMap<>();

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

	public String cadastrarUsuario(String idAutenticacao, String nome, String tipoUsuario, long documento) {
//			/**
//			 * Administrador {id=ADM1, nome=João Costa, tipo=ADM, documento=123456}, já vem
//			 * cadastrado por padrão.
//			 */
//
//			usuarios.add(new Usuario("ADM1", "João Costa", "ADM", 123456));
		
		Usuario cadastrante = encontrarUsuarioPorId(idAutenticacao);

		if (cadastrante == null) {
			throw new NullPointerException("USUÁRIO NÃO ENCONTRADO!");
		}

		/**
		 * confere se já há um gerente cadastrado
		 */

		if (tipoUsuario.equals("GER") && gerenteCadastrado) {
			return "JÁ EXISTE UM GERENTE CADASTRADO";

		}

		/**
		 * Administrador só pode ser cadastrado por outro Administrador.
		 */

		if (tipoUsuario.equals("ADM")) {
			if (!cadastrante.getTipo().equals("ADM")) {
				return "APENAS UM ADMINISTRADOR PODE CADASTRAR OUTRO ADMINISTRADOR.";
			}

			/**
			 * Gerente só pode ser cadastrado por Administrador.
			 */

		} else if (tipoUsuario.equals("GER")) {
			if (!cadastrante.getTipo().equals("ADM")) {
				return "APENAS UM ADMINISTRADOR PODE CADASTRAR UM GERENTE.";
			}
			gerenteCadastrado = true;

			/**
			 * Funcionário só pode ser cadastrado por Administrador ou Gerente.
			 */

		} else if (tipoUsuario.equals("FUN")) {
			if (!cadastrante.getTipo().equals("ADM") && !cadastrante.getTipo().equals("GER")) {
				return "APENAS UM ADMINISTRADOR OU GERENTE PODEM CADASTRAR UM FUNCIONÁRIO.";
			}

			/**
			 * Cliente pode ser cadastrado por qualquer Usuário, exceto por outro Cliente.
			 */

		} else if (tipoUsuario.equals("CLI")) {
			if (cadastrante.getTipo().equals("CLI")) {
				return "CLIENTES NÃO PODEM CADASTRAR OUTROS CLIENTES.";
			}
		}

		if (encontrarUsuarioPorId(idAutenticacao) != null) {
			return "JÁ EXISTE UM USUÁRIO COM O ID ESPECIFICADO.";
		}

		usuarios.add(new Usuario(idAutenticacao, nome, tipoUsuario, documento));
		return "USUÁRIO CADASTRADO COM SUCESSO!";
	}

	/**
	 * Atualiza o tipo de usuário
	 * 
	 * @param idAutenticacao
	 * @param idUsuario
	 * @param novoTipoUsuario
	 * @return
	 */

	public String atualizarUsuario(String idAutenticacao, String idUsuario, String novoTipoUsuario) {
		Usuario autenticante = encontrarUsuarioPorId(idAutenticacao);

		if (autenticante == null) {
			throw new NullPointerException("USUÁRIO NÃO ENCONTRADO!");
		}

		/**
		 * Apenas o Administrador pode atualizar os Usuários.
		 */

		if (!autenticante.getTipo().equals("ADM")) {
			return "APENAS UM ADMINISTRADOR PODE ATUALIZAR OS USUÁRIOS.";
		}

		Usuario usuario = encontrarUsuarioPorId(idUsuario);

		if (usuario == null) {
			throw new NullPointerException("USUÁRIO NÃO ENCONTRADO!");
		}

		/**
		 * Quando um novo Gerente é adicionado, o antigo será posto com o cargo de
		 * Funcionário.
		 */

		if (novoTipoUsuario.equals("GER")) {
			if (usuario.getTipo().equals("GER")) {
				usuario.setTipo("FUN");
				gerenteCadastrado = false;
			}
		}

		usuario.setTipo(novoTipoUsuario);
		return "USUÁRIO ATUALIZADO!";

	}

	/**
	 * Exibe informações sobre o Usuário.
	 * 
	 * @param idUsuario
	 * @return
	 */

	public String exibirUsuario(String idUsuario) {
		Usuario usuario = encontrarUsuarioPorId(idUsuario);
		if (usuario != null) {
			return "[" + usuario.getId() + "] " + usuario.getNome() + " (No. Doc. " + usuario.getDocumento() + ")";
		} else {
			return "USUÁRIO NÃO ENCONTRADO!";
		}
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

	/**
	 * método para encontrar um usuário pelo seu ID
	 * 
	 * @param idAutenticacao
	 * @return
	 */

	public Usuario encontrarUsuarioPorId(String idAutenticacao) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId().equals(idAutenticacao)) {
				return usuario;
			}
		}
		return null;
	}

	/**
	 * @author maria helena
	 * 
	 * @param idAutenticacao
	 * @param idQuartoNum
	 * @param precoPorPessoa
	 * @param precoBase
	 * @return
	 */

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
