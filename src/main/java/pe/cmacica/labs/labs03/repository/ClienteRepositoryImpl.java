package pe.cmacica.labs.labs03.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pe.cmacica.labs.labs03.dominio.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository // necesario tambien para que se inyecte
public class ClienteRepositoryImpl implements ClienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class ClienteMapper implements RowMapper<Cliente>{

        @Override
        public Cliente mapRow(ResultSet rs, int i) throws SQLException {
            Cliente cliente = new Cliente();

            cliente.setId(rs.getInt("id"));
            cliente.setNombres(rs.getString("nombres"));

            return cliente;
        }
    }

    @Override
    public List<Cliente> listar() {
        /*List<Cliente> list = new ArrayList<>();

        for(int i=0;i<=10;i++){
            Cliente c = new Cliente();
            c.setId(i);
            c.setNombres("Nombres" + i);
            list.add(c);
        }
        return list;*/
        return jdbcTemplate.query("select * from cliente",new ClienteMapper());
    }

    @Override
    public Cliente getCliente(int id) {
        /*
        Cliente c = new Cliente();
        c.setId(id);
        c.setNombres("Algun cliente");
        */

        Cliente c = jdbcTemplate.queryForObject("select * from cliente where id = ?",
                                                    new Object[]{id},
                                                    new ClienteMapper());
        return c;
    }

    @Override
    public int eliminar(int id){
        return jdbcTemplate.update("delete from cliente where id = ?",
                                        new Object[]{id});

    }

    @Override
    public int actualizar(Cliente cliente) {
        return jdbcTemplate.update("update cliente set nombres=? where id=?",
                                        new Object[]{cliente.getNombres(),cliente.getId()});
    }
}
