package br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto;

public class DadosUsuario {

	public final TipoUsuario padrao;
	public final int idUsuario;

	public DadosUsuario(TipoUsuario padrao, int idUsuario) {
		this.padrao = padrao;
		this.idUsuario = idUsuario;
	}

	public boolean isPadrao() {
		return TipoUsuario.PADRAO.equals(padrao);
	}

	public boolean isPesquisador() {
		return TipoUsuario.PESQUISADOR.equals(padrao);
	}

}
