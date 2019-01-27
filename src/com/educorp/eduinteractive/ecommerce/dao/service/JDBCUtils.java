package com.educorp.eduinteractive.ecommerce.dao.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.educorp.eduinteractive.ecommerce.exceptions.DataException;

/**
 * Factoriza los bloques de c�digo habituales de JDBC 
 * que deben colocarse en los bloques <code>finally { ... }</code>.
 *
 * @author https://www.linkedin.com/in/joseantoniolopezperez
 * @version 0.2
 */
public final class JDBCUtils {

	private JDBCUtils() {}
	
	/**
	 * Obtencion del total de filas de un resultSet, sin repetir consulta.
	 * Metodo orientado a implementar paginación.
	 * No existe una solución en el API estandar de JDBC.
	 * Esta es un solución para todas las BD pero NO ES LA MEJOR EN RENDIMIENTO.
	 * Por ello en este caso es habitual usar soluciones propietarias
	 * de cada BD (rownum de Oracle, y similar en MySQL).
	 * (En Hibernate, con ScrollableResults, no lo vemos porque lo implementa con el dialecto de cada DB).
	 * 
	 * Encantado de recibir mensajes son soluciones mejores (válidas para todas las BD): 
	 * @author https://www.linkedin.com/in/joseantoniolopezperez
	 * @version 0.2  
	 */
	public static final int getTotalRows(ResultSet resultSet) throws SQLException {
		int totalRows = 0;
		if(resultSet.last()) {
			totalRows = resultSet.getRow();
		}
		resultSet.beforeFirst();
		return totalRows;
	}

	/**
     * Cierra el ResultSet.
     */
	public static void closeResultSet(ResultSet resultSet)
		throws DataException {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DataException(e);
			}
		}

	}

	/**
     * Cierra el Statement.
     */
	public static void closeStatement(Statement statement)
		throws DataException {

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DataException(e);
			}
		}

	}

	/**
	 * Cierra la conexion.
	 * 
	 * Metodo de cierre <b>para el caso de sentencias con autocommit = true</b>.
	 * 
	 * En caso de autocommit = false deber�a usarse: 
	 * {@link #closeConnection(Connection, Boolean)} 
	 * para que ejecute previamente commit() o rollback() 
	 * previamente al cierre.
	 * 
	 * @param connection Conexion a cerrar.

	 */
	public static void closeConnection(Connection connection)
			throws DataException {
		
		if (connection != null) {
			try {
				// Previene un mal uso 
				if (!connection.getAutoCommit()) {
					throw new DataException("Autocommit disabled. Commit or Rollback should be done explicitly.");
				}			
				
				connection.close();
			} catch (SQLException e) {
				throw new DataException(e);
			}
		}
	}
	
	/**
	 * Metodo de finalizaci�n de transacci�n <b>para el caso de autocommit = false</b>
	 * y de cierre de conexi�n.
	 *  
	 * Ejecuta commit() o rollback() y a continuacion cierra la conexi�n.
	 *  
	 * @param connection Conexi�n a cerrar.
	 * @param commitOrRollback Si es true ejecuta commit() y en caso contrario ejecuta rollback().
	 */
	public static void closeConnection(Connection connection, boolean commitOrRollback)
		throws DataException {
        try {
            if (connection != null) {
                if (commitOrRollback) {
                	connection.commit();
                } else {
                	connection.rollback();                        
                }
                connection.close();
            }
        } catch (SQLException e) {
            throw new DataException(e);
        }
	}

}
