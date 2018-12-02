package pe.cmacica.labs.labs03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.cmacica.labs.labs03.dominio.Cliente;
import pe.cmacica.labs.labs03.repository.ClienteRepository;

import java.util.List;

@Service // para que se inyecte cada vez que lo pidan
public class ClienteServiceImpl implements ClienteService {

    @Autowired // para que se inyecte
    private ClienteRepository clienteRepository;


    @Override
    public List<Cliente> listar() {
        return clienteRepository.listar();
    }

    @Override
    public Cliente getCliente(int id) {
        return clienteRepository.getCliente(id);
    }

    @Override
    public int eliminar(int id) {
        return clienteRepository.eliminar(id);
    }

    @Override
    public int actualizar(Cliente cliente) {
        return clienteRepository.actualizar(cliente);
    }
}
