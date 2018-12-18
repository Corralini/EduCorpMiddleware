package com.jal.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jal.training.dao.util.ConnectionManager;
import com.jal.training.dao.util.JDBCUtils;
import com.jal.training.exceptions.DataException;
import com.jal.training.model.Empleado;

public class EmpleadoDAO {
	
	private DepartamentoDAO departamentoDAO = null;
	
	public EmpleadoDAO(){
		departamentoDAO = new DepartamentoDAO();
	}
	
	public Empleado findById(Integer id)
			throws Exception {
		
		Empleado e = null;
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();

			String sql;
			sql =  "SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, HIRE_DATE, MANAGER_ID "
				  +"FROM EMPLOYEES "
				  +"WHERE EMPLOYEE_ID = ? ";
			
			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);
			
			
			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set						
			if (resultSet.next()) {				
				e = loadNext(resultSet);				
			} else {
				throw new Exception("Non se encontrou o empleado "+id);
			}
			if (resultSet.next()) {
				throw new Exception("Estudainte"+id+" duplicado");
			}
						
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}  	
			
		return e;
	}
	

	public List<Empleado> findByNombre(String criterioNombre, String ap1)
			throws Exception {	
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			
			connection = ConnectionManager.getConnection();

			String sql;
			sql =    " SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, HIRE_DATE, MANAGER_ID " 
					+" FROM EMPLOYEES "
					+" WHERE "
					+"	UPPER(FIRST_NAME) LIKE ? " 
					+"  AND"
					+"  UPPER(LAST_NAME) LIKE  ? ";
			
			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			// Establece os parámetros
			int i = 1;
			preparedStatement.setString(i++, "%" + criterioNombre.toUpperCase() + "%");
			preparedStatement.setString(i++, "%" + ap1.toUpperCase() + "%");			
			
			resultSet = preparedStatement.executeQuery();			

			//STEP 5: Extract data from result set			
			List<Empleado> empleados = new ArrayList<Empleado>();
			Empleado e = null;
			while (resultSet.next()) {
				e = loadNext(resultSet);						
				empleados.add(e);
			} 
			return empleados;
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}  	
	}
	
	public List<Empleado> findByDepartamento(Integer idDepartamento)
			throws Exception {
		return null;
	}
	
	
	private Empleado loadNext(ResultSet resultSet) throws Exception {
		Empleado e = new Empleado();
		int i = 1;
		Integer id = resultSet.getInt(i++);
		String nombre = resultSet.getString(i++);
		String apellido1 = resultSet.getString(i++);	
		String email = resultSet.getString(i++);
		Date fechaContratacion = resultSet.getDate(i++);
		Integer idJefe = resultSet.getInt(i++); 

		
		e = new Empleado();
		e.setId(id);
		e.setNombre(nombre);
		e.setApellido1(apellido1);
		e.setEmail(email);
		e.setFechaContratacion(fechaContratacion);
		e.setIdJefe(idJefe);
		
//		Departamento d = departamentoDAO.findByIdEmpleado(id);
//		e.setDepartamento(d);
		
		return e;
	}
	
	public Empleado create(Empleado e)
			throws Exception {
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		try {          

			connection = ConnectionManager.getConnection();
			//Check if the primary key already exists
//			if (exists(connection, e.getId())) {
//				throw new Exception("Duplicate employee "+e.getId());
//			}
				
			
			String queryString = "INSERT INTO EMPLOYEES(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, MANAGER_ID, HIRE_DATE, DEPARTMENT_ID, JOB_ID) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(queryString);
			
			int i = 1;     
			preparedStatement.setInt(i++, e.getId());
			preparedStatement.setString(i++, e.getNombre());
			preparedStatement.setString(i++, e.getApellido1());
			preparedStatement.setString(i++, e.getEmail());
			preparedStatement.setInt(i++, e.getIdJefe());
			preparedStatement.setDate(i++, new java.sql.Date(e.getFechaContratacion().getTime()));
			preparedStatement.setInt(i++, e.getDepartamento().getId());
			preparedStatement.setString(i++, "FI_ACCOUNT");
			
			

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Employees'");
			}
			
			//...
			return e;					

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}
		
	}
	
	public boolean update(Empleado e)
			throws Exception {
		return false;
	}
	
	public void delete(Empleado e)
			throws Exception {
		
	}
		
}
