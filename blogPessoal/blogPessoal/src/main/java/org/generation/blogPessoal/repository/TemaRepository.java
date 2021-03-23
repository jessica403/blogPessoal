package org.generation.blogPessoal.repository;
//************AQUI SÃO OS METODOS DO BD************
import java.util.List;
import org.generation.blogPessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
}
