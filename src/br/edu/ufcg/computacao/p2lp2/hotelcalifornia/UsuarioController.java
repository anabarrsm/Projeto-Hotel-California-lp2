package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ufcg.computacao.p2lp2.hotelcalifornia.quarto.Quarto;

public class UsuarioController {
	private ArrayList<Usuario> usuarios; 


	public UsuarioController() {
			this.usuarios = new ArrayList<Usuario>();
			Usuario adm1 = new Usuario("João Costa", "ADM", "123456");
			adm1.setId("ADM1");

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
		if((tipoUsuario.equals("ADM")) || (tipoUsuario.equals("GER")) || (tipoUsuario.equals("CLI")) || (tipoUsuario.equals("FUN"))) {
			return true;
		}
		return false;
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
		if (tipoUsuario.equals("GER")) {	
			for(Usuario usuario : usuarios) {
				if (usuario.getTipo().equals("GER")) {
					return "JÁ EXISTE UM GERENTE CADASTRADO!";
				}
			}
		
		} if((nome == null || (documento == null))) {
			return "PARÂMETRO INVÁLIDO";
			//throw new NullPointerException("PARÂMETRO INVÁLIDO!");
		
		} else if (encontrarUsuarioPorId(idAutenticacao) == true) {
			return "ID INVÁLIDO!";
			//throw new NullPointerException("ID INVÁLIDO!");
			
		} else if (validaTipo(tipoUsuario) == false) {
			return "TIPO INVÁLIDO!";
			//throw new IllegalArgumentException("TIPO INVÁLIDO!");
			
		} else if (idAutenticacao.contains("CLI")) {
			return "CLIENTE NÃO PODE CADASTRAR USUÁRIO!";
			
		} else if (tipoUsuario.equals("FUN") && !idAutenticacao.contains("ADM") && !idAutenticacao.contains("GER")) {
	        return "FUNCIONÁRIO SÓ PODE SER CADASTRADO POR ADMINISTRADOR OU GERENTE";
			
		} else if (tipoUsuario.equals("GER") && !idAutenticacao.contains("ADM")) {
	        return "GERENTE SÓ PODE SER CADASTRADO POR ADMINISTRADOR";
	        
	    } else if (tipoUsuario.equals("ADM") && !idAutenticacao.contains("ADM")) {
	        return "ADMINISTRADOR SÓ PODE SER CADASTRADO POR OUTRO ADMINISTRADOR";
			
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
		if ((encontrarUsuarioPorId(idAutenticacao) == false) && ((encontrarUsuarioPorId(idUsuario) == false))) {
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
		String[] usuariosExistentes = new String[usuarios.size()];
		for (int i = 0; i < usuariosExistentes.length; i++) {
			usuariosExistentes[i] = usuarios.get(i).toString();
		}
		return usuariosExistentes;

	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	
}
