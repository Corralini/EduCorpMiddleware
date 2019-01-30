package com.educorp.eduinteractive.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.service.DAOUtils;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.HorarioDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.exceptions.DuplicateInstanceException;
import com.educorp.eduinteractive.ecommerce.exceptions.InstanceNotFoundException;
import com.educorp.eduinteractive.ecommerce.model.Horario;
import com.educorp.eduinteractive.ecommerce.service.criteria.HorarioCriteria;

public class HorarioDAOImpl implements HorarioDAO{

	@Override
	public Horario findById(Connection connection, Integer id) 
			throws InstanceNotFoundException, DataException {
		Horario h = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			String sql;
			sql =  "SELECT ID_HORARIO, ID_PROFESOR, ID_ESTUDIANTE, ID_DIA, ID_HORA "
					+"FROM HORARIO "
					+"WHERE ID_HORARIO = ? ";

			// Preparar a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establece os parámetros
			int i = 1;
			preparedStatement.setLong(i++, id);


			resultSet = preparedStatement.executeQuery();			
			//STEP 5: Extract data from result set						
			if (resultSet.next()) {				
				h = loadNext(resultSet);				
			} else {
				throw new DataException("Non se encontrou o horario "+id);
			}
			if (resultSet.next()) {
				throw new DataException("horario"+id+" duplicado");
			}

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  	

		return h;
	}
	
	@Override
	public List<Horario> findByCriteria (Connection connection, HorarioCriteria horario) 
			throws DataException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;

		try {
    
			queryString = new StringBuilder(
					"SELECT id_hora "
					+" FROM horario ");
			
			boolean first = true;
			
			if (horario.getIdProfesor() != null) {
				DAOUtils.addClause(queryString, first, " id_profesor =  ? ");
				first = false;
			}
			
			if(horario.getIdDia() != null) {
				DAOUtils.addClause(queryString, first, " id_dia = ? ");
			}
			


			
			
			preparedStatement = connection.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;  
			
			if (horario.getIdProfesor() != null)
				preparedStatement.setInt(i++, horario.getIdProfesor());
			if (horario.getIdDia()!=null) 
				preparedStatement.setInt(i++,horario.getIdDia() );
			

			resultSet = preparedStatement.executeQuery();
			
			List<Horario> estudiantes = new ArrayList<Horario>();                        
			Horario e = null;

			while (resultSet.next()) {
				e = loadNext(resultSet);						
				estudiantes.add(e);
			}

			return estudiantes;
	
			} catch (SQLException e) {
				throw new DataException("Hemos encontrado un problema");
			} finally {
				JDBCUtils.closeResultSet(resultSet);
				JDBCUtils.closeStatement(preparedStatement);
		}
	}
 
	@Override
	public List<Horario> findBy(Connection connection, Integer idProfesor, Integer idDia) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;

		try {
    
			queryString = new StringBuilder(
					"SELECT ID_HORARIO, ID_PROFESOR, ID_ESTUDIANTE, ID_DIA, ID_HORA "
					+" FROM HORARIO ");
			
			boolean first = true;
			
			if (idProfesor != null) {
				DAOUtils.addClause(queryString, first, " ID_PROFESOR =  ? ");
				first = false;
			}	
			
			if (idDia != null) {
				DAOUtils.addClause(queryString, first, "ID_DIA = ?");
				first = false;
			}
			

			
			
			preparedStatement = connection.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;  
			
			if (idProfesor != null)
				preparedStatement.setInt(i++, idProfesor);
			if (idDia != null)
				preparedStatement.setInt(i++, idDia);



			resultSet = preparedStatement.executeQuery();
			
			List<Horario> horarios = new ArrayList<Horario>();                        
			Horario h = null;

			while (resultSet.next()) {
				h = loadNext(resultSet);						
				horarios.add(h);
			}

			return horarios;
	
			} catch (SQLException e) {
				throw new DataException(e);
			} finally {
				JDBCUtils.closeResultSet(resultSet);
				JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public void update(Connection connection, Horario h) 
			throws InstanceNotFoundException, DataException {
		
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {	
			
			queryString = new StringBuilder(
					" UPDATE Estudiante" 
					);
			
			boolean first = true;
			
			if (h.getIdHorario() != null) {
				DAOUtils.addUpdate(queryString, first, " ID_HORARIO =  ? ");
				first = false;
			}
			
			if (h.getIdProfesor() != null) {
				DAOUtils.addUpdate(queryString, first, " ID_PROFESOR =  ? ");
				first = false;
			}
			
			
			if (h.getIdDia() != null) {
				DAOUtils.addUpdate(queryString, first, " ID_DIA =  ? ");
				first = false;
			}
			
			if (h.getIdHora() != null) {
				DAOUtils.addUpdate(queryString, first, " ID_HORA =  ? ");
				first = false;
			}
			
						
			queryString.append("WHERE id_estudiante = ?");
			
			preparedStatement = connection.prepareStatement(queryString.toString());
			

			int i = 1;
			if (h.getIdHorario() != null)
				preparedStatement.setInt(i++, h.getIdHorario());
			if (h.getIdProfesor() != null)
				preparedStatement.setInt(i++, h.getIdProfesor());
			if (h.getIdDia() != null)
				preparedStatement.setInt(i++, h.getIdDia());
			if (h.getIdHora() != null)
				preparedStatement.setInt(i++, h.getIdHora());
			

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new DataException("Non se actualizou o horario");
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row ");
			}     
			
		} catch (SQLException ex) {
			throw new DataException(ex);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public Horario create(Connection connection, Horario h) 
			throws DuplicateInstanceException, DataException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {          

			// Creamos el preparedstatement
			String queryString = "INSERT INTO HORARIO (ID_PROFESOR, ID_DIA, ID_HORA) "
					+ "VALUES (?, ?, ?)";

			preparedStatement = connection.prepareStatement(queryString,
									Statement.RETURN_GENERATED_KEYS);

			// Rellenamos el "preparedStatement"
			int i = 1;    
			preparedStatement.setInt(i++, h.getIdProfesor());
			preparedStatement.setInt(i++, h.getIdDia());
			preparedStatement.setInt(i++, h.getIdHora());
			
			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Estudiante'");
			}

			// Recuperamos la PK generada
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Integer pk = resultSet.getInt(1); 
				h.setIdHorario(pk);
			} else {
				throw new DataException("Unable to fetch autogenerated primary key");
			}

			// Return the DTO
			return h;

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public Horario delete(Connection connection, Horario h) throws DataException {
		
		return null;
	}

	private Horario loadNext(ResultSet resultSet) throws SQLException, DataException {

		Horario h = new Horario();
		int i = 1;
		Integer idHorario = resultSet.getInt(i++);
		Integer idProfesor = resultSet.getInt(i++);
		Integer idDia = resultSet.getInt(i++);
		Integer idHora = resultSet.getInt(i++);
		


		h = new Horario();
		h.setIdHorario(idHorario);
		h.setIdProfesor(idProfesor);
		h.setIdDia(idDia);
		h.setIdHora(idHora);
		

		return h;

	}
}
