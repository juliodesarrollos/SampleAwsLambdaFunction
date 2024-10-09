package org.test.awsdb.SQL;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.test.awsdb.SCHEMA.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListarClientes {
    static String noConeccion = "No se pudo conectar a: ";
    static  String columnaId = "cliente_id";
    static String columnaCorreo = "cliente_correo";
    static String sqlSelecQuery = "select cliente_id, cliente_correo from clientes";

    private List<Cliente> clientes;
    private final DataSourceProperties dataSourceProperties;

    public ListarClientes () {
        this.dataSourceProperties = new DataSourceProperties();
    }

    public ListarClientes (DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    public List<Cliente> handleRequest(){
        clientes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dataSourceProperties.getJDBCURL(),dataSourceProperties.getUsername(), dataSourceProperties.getPassword())){
            if (!connection.isValid(0)){
                System.out.println(noConeccion + dataSourceProperties.getJDBCURL());
                System.exit(0);
            }
            PreparedStatement selectStatement = connection.prepareStatement(sqlSelecQuery);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()){
                clientes.add(new Cliente(rs.getInt(columnaId),rs.getString(columnaCorreo)));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(clientes));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

}
