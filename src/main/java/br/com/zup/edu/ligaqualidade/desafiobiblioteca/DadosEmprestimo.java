package br.com.zup.edu.ligaqualidade.desafiobiblioteca;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoExemplar;

import java.time.LocalDate;

public class DadosEmprestimo {

	public final int idLivro;
	public final int idUsuario;
	//vai ser usado para posterior consulta em cima da solução
	public final int idPedido;
	public final int tempo;
	public final TipoExemplar tipoExemplar;

	public DadosEmprestimo(int idLivro, int idUsuario,int tempo,TipoExemplar tipoExemplar, int idPedido) {
		this.idLivro = idLivro;
		this.idUsuario = idUsuario;
		this.tempo = tempo;
		this.tipoExemplar = tipoExemplar;
		this.idPedido = idPedido;
	}

	public boolean isLivre() {
		return TipoExemplar.LIVRE.equals(tipoExemplar);
	}

	public boolean isRestrito() {
		return TipoExemplar.RESTRITO.equals(tipoExemplar);
	}

	public boolean isTempoValido(LocalDate expiracao) {
		return LocalDate.now().plusDays(tempo).isBefore(expiracao);
	}

}
