package br.com.lucastrevizan.forum.repository;

import br.com.lucastrevizan.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCursoNome(String nomeCurso);

    @Query("SELECT t FROM Topico t JOIN FETCH t.curso WHERE t.id = :id")
    Optional<Topico> buscarTopicoDetalhe(Long id);
}
