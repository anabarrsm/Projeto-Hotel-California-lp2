package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;

public class UsuarioController {
	private ArrayList<Usuario> usuarios;
	private HashMap<String, Quarto> quartos;
	private boolean gerenteCadastrado = false;

	public UsuarioController() {
			this.usuarios = new ArrayList<Usuario>();
			Usuario adm1 = new Usuario("ADM1", "João Costa", "ADM", "123456");
			this.quartos = new HashMap<>();

		}
	
	public Usuario encontrarUsuarioPorId(String idAutenticacao) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId().equals(idAutenticacao)) {
				return usuario;
			}
		}
		return null;
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
		
		Usuario cadastrante = encontrarUsuarioPorId(idAutenticacao);
		
		if((nome == null || (documento == null))) {
			throw new NullPointerException("PARÂMETRO INVÁLIDO!");
		
		} else if (encontrarUsuarioPorId(idAutenticacao) == null) {
			throw new NullPointerException("ID NÃO EXISTE!");
			
		} else if (validaTipo(tipoUsuario) == false) {
			throw new IllegalArgumentException("TIPO INVÁLIDO!");
		}
		
		
		
		/**
		 * confere se já há um gerente cadastrado
		 */

		if (tipoUsuario.equals("GER")) {
			for (Usuario u : usuarios) {
				if(u.getTipo().equals("GER")) {
					return "JÁ EXISTE UM GERENTE CADASTRADO";
				}
			}

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

}
