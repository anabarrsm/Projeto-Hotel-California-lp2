package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.util.Objects;

/**
 * Classe que cria um Usuário no sistema
 * @author Ana Laura Barros de Melo
 */

public class Usuario {
	private String idAutenticacao;
	private String nome;
	private String tipoUsuario;
	private long documento;
	
	public Usuario(String idAutenticacao, String nome, String tipoUsuario, long documento) {
		
		this.idAutenticacao = idAutenticacao;
		this.nome = nome;
		this.tipoUsuario = tipoUsuario;
		this.documento = documento;
	}
	
	public String getId() {
		return idAutenticacao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getTipo() {
		return tipoUsuario;
	}
	
	public void setTipo(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public long getDocumento() {
		return documento;
	}
	
	@Override
	
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Usuario usuario)) return false;
		return Objects.equals(idAutenticacao, usuario.idAutenticacao);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(idAutenticacao);
    }
	
	public String toString() {
		return "[" + idAutenticacao + "] " + nome + " (No. Doc. " + documento + ")";
	}
}