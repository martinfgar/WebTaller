package com.Taller.WebTaller.dao;


import com.Taller.WebTaller.modelos.Marca;
import com.Taller.WebTaller.modelos.Pieza;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PiezaDaoTodos implements PiezaDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Pieza> LeerPiezasTodas() {
        String sql = "SELECT marca,modelo,precio,descripcion,stock,url,categorias.nombre as categoria FROM piezas inner join categorias on piezas.id_categoria = categorias.id_categoria";

        return  jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Pieza.class));
    }

    @Override
    public List<Pieza> LeerPorMarca(String marca) {
        String sql = "SELECT marca,modelo,precio,descripcion,stock,url,categorias.nombre as categoria FROM piezas inner join categorias on piezas.id_categoria = categorias.id_categoria where marca='"+marca+"'";

        return  jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Pieza.class));
    }

    @Override
    public List<Pieza> LeerPorCategoria(String categoria) {
        String sql = "SELECT marca,modelo,precio,descripcion,stock,url,categorias.nombre as categoria FROM piezas inner join categorias on piezas.id_categoria = categorias.id_categoria where categorias.nombre='"+categoria+"'";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Pieza.class));
    }

    @Override
    public List<Marca> ListaMarcas(){
        String sql = "select marca, count(*) as cantidad from PIEZAS group by marca";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Marca.class));
    }
    @Override
    public List<Marca> ListaCategorias(){
        String sql = "select nombre from categorias";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Marca.class));
    }
}