package com.educorp.eduinteractive.test;

import java.util.Date;

import com.educorp.eduinteractive.model.Sesion;
import com.educorp.eduinteractive.service.MailService;

public class MailServiceTest {

	public static void main (String args[]) {
		Sesion[] sesiones = new Sesion[1];

		MailService mailService = new MailService();
		Sesion sesion1 = new Sesion();
		sesion1.setEmail("acorralfdez@gmail.com");
		sesion1.setIdSesion("456");
		sesion1.setFechaInicio(new Date());
		sesion1.setFechaFin(new Date());
		sesion1.setIdEstudiante(25);
		sesion1.setIdProfesor(325);
		sesion1.setPrecio(8);
		sesion1.setFechaEstimadaInicio(new Date());
		sesion1.setRecordable(false);

//		Sesion sesion2 = new Sesion();
//		sesion2.setEmail("seijasiago@gmail.com");
//		sesion2.setIdSesion("456");
//		sesion2.setFechaInicio(new Date());
//		sesion2.setFechaFin(new Date());
//		sesion2.setIdEstudiante("25");
//		sesion2.setIdProfesor("325");
//		sesion2.setPrecio(8);
//		sesion2.setFechaEstimadaInicio(new Date());
//		sesion2.setRecordable(false);

		String subject = "Tes unha sesion moi pronto";
		String message = "<html>\r\n" + 
				"<body>\r\n" + 
				"    <p>Recorda que tes unha sesion as:</p>\r\n" + sesion1.getFechaEstimadaInicio() +
				"</body>\r\n" + 
				"</html>";

			mailService.sendMail(subject, message, sesion1.getEmail());


	}

}
