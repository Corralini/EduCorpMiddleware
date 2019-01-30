package com.educorp.eduinteractive.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.educorp.eduinteractive.ecommerce.dao.impl.EstudianteDAOImpl;
import com.educorp.eduinteractive.ecommerce.dao.service.ConnectionManager;
import com.educorp.eduinteractive.ecommerce.dao.service.JDBCUtils;
import com.educorp.eduinteractive.ecommerce.dao.spi.EstudianteDAO;
import com.educorp.eduinteractive.ecommerce.exceptions.DataException;
import com.educorp.eduinteractive.ecommerce.model.Estudiante;
import com.educorp.eduinteractive.ecommerce.service.PasswordEncryptionUtil;
import com.educorp.eduinteractive.ecommerce.service.criteria.EstudianteCriteria;
import com.educorp.eduinteractive.ecommerce.service.spi.EstudianteService;
import com.educorp.eduinteractive.ecommerce.service.spi.MailService;

public class EstudianteServiceImpl implements EstudianteService{
	
	private EstudianteDAO dao = null;
	private MailService mailService = new MailServiceImpl();
	
	public EstudianteServiceImpl () {
		dao = new EstudianteDAOImpl();
	}
	
	@Override
	public Estudiante findById(Integer id) 
		throws DataException {
		boolean commit = false;
		Connection c = ConnectionManager.getConnection();
		try {
			c.setAutoCommit(true);
			return dao.findById(c, id);
		
		}catch (DataException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {   
				JDBCUtils.closeConnection(c);
		}  	
		
	}
	
	@Override
	public Estudiante login(String email, String psswd)
		throws DataException{
		Connection c = ConnectionManager.getConnection();
		try {
			
			if (email == null || psswd == null) {
				return null;
			}
			
			Estudiante e = dao.findByEmail(c, email);
			
			if(e==null) {
				return e;
			}
			
			if(PasswordEncryptionUtil.checkPassword(psswd, e.getPsswd())) {
				System.out.println("Usuario " + e.getEmail() + " autenticado a las " + new Date());
				return e;
			}
			
			return null;
		}catch(DataException de) {
			de.printStackTrace();
			throw de;
		}
		finally {
			
		}
	}

	@Override
	public Estudiante signUp(Estudiante e)
		throws DataException {
		boolean commit = false;
		Connection c = ConnectionManager.getConnection();

        try {
          
            c = ConnectionManager.getConnection();

            c.setTransactionIsolation(
                    Connection.TRANSACTION_READ_COMMITTED);

            c.setAutoCommit(false);

            String mssg = "Hola " + e.getNombre()
			+ " " + e.getApellido1()
			+ " el equipo de Educorp Interactive le da la bienvenida a Educorp ";
            
            Estudiante result = dao.create(c, e);
            
            mailService.sendEmail(e.getEmail(), "Bienvenido a Educorp",mssg);
            commit = true;
            
            return result;

        } catch (SQLException ex) {
            throw new DataException(ex);

        } finally {
        	JDBCUtils.closeConnection(c, commit);
        }
			
			
				
	}

	@Override
	public Estudiante update(Estudiante e)
		throws DataException {

		return null;
	}

	@Override
	public List<Estudiante> findByCriteria(EstudianteCriteria criteria)
			throws com.educorp.eduinteractive.ecommerce.service.spi.DataException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
