package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

public class HotelCaliforniaFachada {
	private UsuarioController usuarioController;
	private QuartoController quartoController;
	private RefeicaoController refeicaoController;

	public HotelCaliforniaFachada() {
		this.usuarioController = new UsuarioController();
		this.quartoController = new QuartoController();
	}

	public String cadastrarUsuario(String idAutenticacao, String nome, String tipoUsuario, String documento) {
		return this.usuarioController.cadastrarUsuario(idAutenticacao, nome, tipoUsuario, documento);

	}

	public String atualizarUsuario(String idAutenticacao, String idUsuario, String novoTipoUsuario) {
		return this.usuarioController.atualizarUsuario(idAutenticacao, idUsuario, novoTipoUsuario);
	}

	public String exibirUsuario(String idUsuario) {
		return this.usuarioController.exibirUsuario(idUsuario);

	}

	public String[] listarUsuarios() {
		return this.usuarioController.listarUsuarios();
	}

	public String disponibilizarQuartoSingle(String idAutenticacao, int idQuartoNum, double precoPorPessoa,
			double precoBase) {
		return this.quartoController.disponibilizarQuartoSingle(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase);
	}

	public String disponibilizarQuartoDouble(String idAutenticacao, int idQuartoNum, double precoPorPessoa,
			double precoBase, String[] pedidos) {
		return this.quartoController.disponibilizarQuartoDouble(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase,
				pedidos);
	}

	public String disponibilizarQuartoFamily(String idAutenticacao, int idQuartoNum, double precoPorPessoa,
			double precoBase, String[] pedidos, int qtdMaxPessoas) {
		return this.quartoController.disponibilizarQuartoFamily(idAutenticacao, idQuartoNum, precoPorPessoa, precoBase,
				pedidos, qtdMaxPessoas);
	}
}