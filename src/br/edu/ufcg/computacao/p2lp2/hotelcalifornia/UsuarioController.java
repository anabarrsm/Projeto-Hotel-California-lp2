package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;

/**
 * 
 * @author Ana Laura
 *
 */

public class UsuarioController {
	private ArrayList<Usuario> usuarios;
	private Usuario adm1;

	public UsuarioController() {
		this.usuarios = new ArrayList<Usuario>();
		this.adm1 = new Usuario("João Costa", "ADM", 123456);
		this.adm1.setId("ADM1");
		this.usuarios.add(adm1);

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

	/**
	 * método para validar os 4 tipos de usuários possíveis
	 * 
	 * @param tipoUsuario
	 * @return true se pertencer aos quatro tipos possíves, false caso contrário
	 */

	public boolean validaTipo(String tipoUsuario) {
		if ((tipoUsuario.equals("ADM")) || (tipoUsuario.equals("GER")) || (tipoUsuario.equals("CLI"))
				|| (tipoUsuario.equals("FUN"))) {
			return true;
		}
		return false;
	}

	/**
	 * Cadastra um usuário no sistema.
	 * 
	 * @param idAutenticacao id de quem está cadastrando o usuário
	 * @param nome           nome do usuário que será cadastrado
	 * @param tipoUsuario    tipo do usuário que será cadastrado
	 * @param documento      documento do usuário que será cadastrado
	 * @return
	 */

	public String cadastrarUsuario(String idAutenticacao, String nome, String tipoUsuario, long documento) {
		if (tipoUsuario.equals("GER")) {
			for (Usuario usuario : usuarios) {
				if (usuario.getTipo().contains("GER")) {
					return "JÁ EXISTE UM GERENTE CADASTRADO!";
				}
			}

		}
		if ((nome == null)) {
			return "PARÂMETRO INVÁLIDO";
			// throw new NullPointerException("PARÂMETRO INVÁLIDO!");

		}
		// atribuindo um novo numero ao usuario novo.
		int numeroAtual = usuarios.size() + 1;

		// concatenando o numero a string passada como parametro do metodo.
		String usuarioComNumero = tipoUsuario + numeroAtual;

		// verificando se o id com o numero ja existe no array.
		if (verificaDocumentoRepetido(documento)) {
			// return "ID INVÁLIDO!";
			return "USUÁRIO JÁ CADASTRADO!";
			// throw new NullPointerException("ID INVÁLIDO!");
		}

		if (validaTipo(tipoUsuario) == false) {
			return "TIPO INVÁLIDO!";
			// throw new IllegalArgumentException("TIPO INVÁLIDO!");

		}
		if (idAutenticacao.contains("CLI")) {
			return "CLIENTE NÃO PODE CADASTRAR USUÁRIO!";

		}
		if (tipoUsuario.equals("FUN") && !idAutenticacao.contains("ADM") && !idAutenticacao.contains("GER")) {
			return "FUNCIONÁRIO SÓ PODE SER CADASTRADO POR ADMINISTRADOR OU GERENTE";

		}
		if (tipoUsuario.equals("GER") && !idAutenticacao.contains("ADM")) {
			return "GERENTE SÓ PODE SER CADASTRADO POR ADMINISTRADOR";

		}
		if (tipoUsuario.equals("ADM") && !idAutenticacao.contains("ADM")) {
			return "ADMINISTRADOR SÓ PODE SER CADASTRADO POR OUTRO ADMINISTRADOR";

		} else {
			Usuario usuario = new Usuario(nome, usuarioComNumero, documento);
			this.usuarios.add(usuario);

			usuario.setId(usuarioComNumero);

			return "USUÁRIO CADASTRADO COM SUCESSO!";
		}

	}

	/**
	 * Atualiza o tipo de usuário
	 * 
	 * @param idAutenticacao  id do usuário que fará a atualização
	 * @param idUsuario       id do usuário que será atualizado
	 * @param novoTipoUsuario novo tipo que será atribuido ao usuário
	 * @return
	 * 
	 */

	public String atualizarUsuario(String idAutenticacao, String idUsuario, String novoTipoUsuario) {
		if ((encontrarUsuarioPorId(idAutenticacao) == false) && ((encontrarUsuarioPorId(idUsuario) == false))) {
			throw new IllegalArgumentException("ID NÃO CADASTRADO!");

		} else if (validaTipo(novoTipoUsuario) == false) {
			throw new IllegalArgumentException("TIPO INVÁLIDO!");

		}

		else if (!idAutenticacao.contains("ADM")) {
			return "APENAS UM ADMINISTRADOR PODE ATUALIZAR OS USUÁRIOS.";
		}

		else if (novoTipoUsuario.equals("GER")) {
			for (int i = 0; i < usuarios.size(); i++) {
				if (usuarios.get(i).getTipo().equals("GER")) {
					usuarios.get(i).setTipo("FUN");
					usuarios.get(i).setTipo("FUN" + (i + 1));

				} else if (usuarios.get(i).getId().equals(novoTipoUsuario)) {
					usuarios.get(i).setTipo("GER");
					usuarios.get(i).setId("GER" + (i + 1));
				}
			}

		} else {
			for (int i = 0; i < usuarios.size(); i++) {
				if (usuarios.get(i).getId().equals(novoTipoUsuario)) {
					usuarios.get(i).setTipo("GER");
					usuarios.get(i).setId("GER" + (i + 1));
				}
			}
		}
		return "USUÁRIO ATUALIZADO!";

	}

	/**
	 * Exibe informações sobre o Usuário.
	 * 
	 * @param idUsuario identificador do usuário
	 * @return Representação textual do usuário
	 */

	public String exibirUsuario(String idUsuario) {
		String saida = "";
		if (encontrarUsuarioPorId(idUsuario) == false) {
			throw new IllegalArgumentException("ID NÃO EXISTE!");

		} else {
			for (Usuario usuario : usuarios) {
				if (usuario.getId().equals(idUsuario)) {
					saida = usuario.toString();
				}
			}
		}
		return saida;
	}

	/**
	 * Lista os usuários cadastrados no Array
	 * 
	 * @return Representação textual dos usuários cadastrados
	 */

	public String[] listarUsuarios() {
		String[] usuariosExistentes = new String[usuarios.size()];
		for (int i = 0; i < usuariosExistentes.length; i++) {
			usuariosExistentes[i] = usuarios.get(i).toString();
		}
		return usuariosExistentes;

	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * Método para verificar se existem dois usuários iguais, ou seja, com o mesmo
	 * número de documento
	 * 
	 * @param documento Numero do documento do usuário
	 * @return True se já existir um usuário cadastrado com o número de documento
	 *         passado como parâmetro. False caso contrário.
	 */
	public boolean verificaDocumentoRepetido(long documento) {
		for (Usuario usuario : usuarios) {
			if (usuario.getDocumento() == documento) {
				return true;
			}
		}
		return false;
	}

	public Usuario retornaUsuarioPorId(String id) {
		for (Usuario usuario : usuarios) {

			if (usuario.getId().equals(id)) {
				return usuario;
			}
		}
		throw new NullPointerException("USUARIO INEXISTENTE");
	}

}
