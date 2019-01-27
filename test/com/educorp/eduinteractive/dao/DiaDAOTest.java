package com.educorp.eduinteractive.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.educorp.eduinteractive.dao.impl.DiaDAOImpl;
import com.educorp.eduinteractive.dao.service.ConnectionManager;
import com.educorp.eduinteractive.model.Dia;

public class DiaDAOTest {

	public static void main(String[] args) {
		List<Dia> dias = new ArrayList<Dia>();
		DiaDAO dao = new DiaDAOImpl();
		Connection c;
		try {
			c = ConnectionManager.getConnection();

			dias = dao.findAll(c);
			
			for (Dia d: dias) {
				System.out.println("" + d);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
