package br.com.lucastrevizan.forum;

import br.com.lucastrevizan.forum.modelo.Curso;
import br.com.lucastrevizan.forum.repository.CursoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EntityManager em;



    @Test
    public void deveriaRetornarUmCursoQuandoBuscadoPeloNome(){
        String nomeCurso = "Spring Boot";

        Curso curso = new Curso();
        curso.setNome(nomeCurso);
        curso.setCategoria("Programacao");
        em.persist(curso);

        Curso cursoEncontrado = cursoRepository.findByNome(nomeCurso);

        Assertions.assertTrue(cursoEncontrado != null);
        Assertions.assertEquals(nomeCurso, cursoEncontrado.getNome());
    }

    @Test
    public void deveriaRetornarNullQuandoCursoNaoEncontradoPeloNome(){
        String nomeCurso = "Jobster";
        Curso curso = cursoRepository.findByNome(nomeCurso);

        Assertions.assertTrue(curso == null);
    }

}
