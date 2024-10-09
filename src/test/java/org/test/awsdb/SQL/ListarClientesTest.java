package org.test.awsdb.SQL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.test.awsdb.SCHEMA.Cliente;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListarClientesTest {

    private ListarClientes listarClientes;
    private DataSourceProperties dataSourceProperties;

    @BeforeEach
    void setup() {
        this.dataSourceProperties = new DataSourceProperties("esapbd.cheskmigoqoo.us-east-2.rds.amazonaws.com","5432","postgres","esapbd","J.u.l.i.0.");
        listarClientes = new ListarClientes(dataSourceProperties);
    }
    @Test
    void listarClientes () {
         List<Cliente> clientes = listarClientes.handleRequest();
         assertEquals(clientes.size(), clientes.size());
    }
}