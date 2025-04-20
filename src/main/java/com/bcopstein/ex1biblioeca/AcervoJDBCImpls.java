package com.bcopstein.ex1biblioeca;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AcervoJDBCImpls implements IAcervoRepository {
    private JdbcTemplate jdbcTemplate;

    public AcervoJDBCImpls(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Livro> getAll() {
        List<Livro> livros = jdbcTemplate.query("SELECT * FROM livros",
                (rs, rowNum) -> new Livro(rs.getInt("id"),rs.getString("titulo"),rs.getString("autor"),rs.getInt("ano")));
        return livros;
    }

    @Override
    public List<String> getTitulos() {
        List<String> livrosNomes = jdbcTemplate.query("SELECT * FROM livros",
                (rs, rowNum) -> rs.getString("titulo"));

        return livrosNomes;
    }

    @Override
    public List<String> getAutores() {
        List<String> autores = jdbcTemplate.query("SELECT * FROM livros",
                (rs, rowNum) -> rs.getString("autor"));
        return autores;
    }

    @Override
    public List<Livro> getLivrosDoAutor(String autor) {
        List<Livro> livrosDoAutor = jdbcTemplate.query("SELECT * FROM Livros where autor=?",new Object[]{autor}, new BeanPropertyRowMapper<>(Livro.class));

        return livrosDoAutor;
    }

    @Override
    public Livro getLivroTitulo(String titulo) {
        return jdbcTemplate.queryForObject("SELECT * FROM Livros where titulo=?", new Object[]{titulo}, new BeanPropertyRowMapper<>(Livro.class));
    }

    @Override
    public boolean cadastraLivroNovo(Livro livro) {
        String sql = "INSERT INTO Livros (titulo,autor,ano) VALUES (?,?,?)";
        jdbcTemplate.update(sql, livro.getTitulo(), livro.getAutor(), livro.getAno());
        return true;
    }

    @Override
    public boolean removeLivro(int codigo) {
        String sql = "DELETE FROM Livros WHERE id=?";
        jdbcTemplate.update(sql, codigo);
        return true;
    }
}
