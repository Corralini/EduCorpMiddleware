package com.educorp.eduinteractive.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.educorp.eduinteractive.dao.EstudianteDAO;
import com.educorp.eduinteractive.dao.impl.EstudianteDAOImpl;
import com.educorp.eduinteractive.dao.service.ConnectionManager;
import com.educorp.eduinteractive.dao.service.JDBCUtils;
import com.educorp.eduinteractive.exceptions.DataException;
import com.educorp.eduinteractive.model.Estudiante;
import com.educorp.eduinteractive.service.EstudianteService;
import com.educorp.eduinteractive.service.MailService;
import com.educorp.eduinteractive.services.PasswordEncryptionUtil;

public class EstudianteServiceImpl implements EstudianteService{
	
	private EstudianteDAO dao = null;
	private MailService mailService = new MailServiceImpl();
	
	public EstudianteServiceImpl () {
		dao = new EstudianteDAOImpl();
	}
	
	@Override
	public Estudiante findById(Integer id) 
		throws Exception {
		boolean commit = false;
		Connection c = ConnectionManager.getConnection();
		try {
			c.setAutoCommit(true);
			return dao.findById(c, id);
		
		}catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {   
				JDBCUtils.closeConnection(c, commit);
		}  	
		
	}
	
	@Override
	public Estudiante login(String email, String psswd)
		throws Exception{
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
		}finally {
			
		}
	}

	@Override
	public Estudiante signUp(Estudiante e)
		throws Exception {
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
		throws Exception {

		return null;
	}

	
	
}
