package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosDevolucao;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.EmprestimoConcedido;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosLivro;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoExemplar;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Solucao {

  /**
   * Você precisa implementar o código para executar o fluxo o completo de empréstimo e devoluções a
   * partir dos dados que chegam como argumento.
   *
   * <p>Caso você queira pode adicionar coisas nas classes que já existem, mas não pode alterar
   * nada.
   */

  /**
   * @param livros dados necessários dos livros
   * @param exemplares tipos de exemplares para cada livro
   * @param usuarios tipos de usuarios
   * @param emprestimos informações de pedidos de empréstimos
   * @param devolucoes informações de devoluções, caso exista.
   * @param dataParaSerConsideradaNaExpiracao aqui é a data que deve ser utilizada para verificar
   *     expiração
   * @return
   */
  public static Set<EmprestimoConcedido> executa(
      Set<DadosLivro> livros,
      Set<DadosExemplar> exemplares,
      Set<DadosUsuario> usuarios,
      Set<DadosEmprestimo> emprestimos,
      Set<DadosDevolucao> devolucoes,
      LocalDate dataParaSerConsideradaNaExpiracao) {

    Set<EmprestimoConcedido> emprestimosConcedidos = new HashSet<>();

    for (DadosEmprestimo emprestimo : emprestimos) {

      Optional<DadosUsuario> usuarioDesejado =
          usuarios.stream()
              .filter(usuario -> usuario.idUsuario == emprestimo.idUsuario)
              .findFirst();

      if (usuarioDesejado.isPresent()) {
        DadosUsuario usuario = usuarioDesejado.get();

        if ((usuario.isPadrao() && emprestimo.isLivre()) || usuario.isPesquisador()) {

          Optional<DadosExemplar> exemplarDesejado =
              buscaExemplar(livros, exemplares, emprestimo.idLivro, emprestimo.tipoExemplar);

          if (exemplarDesejado.isPresent()) {
            DadosExemplar exemplar = exemplarDesejado.get();
            EmprestimoConcedido emprestimoConcedido =
                new EmprestimoConcedido(
                    usuario.idUsuario,
                    exemplar.idExemplar,
                    LocalDate.now().plusDays(emprestimo.tempo));
            emprestimosConcedidos.add(emprestimoConcedido);
          }
        }
      }
    }

    return emprestimosConcedidos;
  }

  private static Optional<DadosExemplar> buscaExemplar(
      Set<DadosLivro> livros,
      Set<DadosExemplar> exemplares,
      int idLivro,
      TipoExemplar tipoExemplar) {
    Optional<DadosLivro> livroDesejado =
        livros.stream().filter(livro -> livro.id == idLivro).findFirst();

    if (livroDesejado.isPresent()) {
      Optional<DadosExemplar> exemplarDesejado =
          exemplares.stream()
              .filter(exemplar -> exemplar.idLivro == idLivro && exemplar.tipo == tipoExemplar)
              .findFirst();

      if (exemplarDesejado.isPresent()) {
        return exemplarDesejado;
      }
    }

    return Optional.empty();
  }
}
