package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;

public class UsuarioController {
	private ArrayList<Usuario> usuarios;
	private HashMap<String, Quarto> quartos;

	public UsuarioController() {
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
			throw new NullPointerException("ID INVÁLIDO!");
			
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

}
