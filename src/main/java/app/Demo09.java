package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Usuario;
//SIRVE PARA HACER UN LOGIN CON PROCEDIMIENTOS ALMACENADOS CON CREATENATIVEQUERY
//CREA UNA CLASE Y ESA SI ES COMPATIBLE CON LOS PROCEDIMIENTOS ALMACENADOS
//CREATENATIVEQUERY SIRVE PARA IMPORTAR PERSISTENCIAS
public class Demo09 {
	public static void main(String[] args) {
		System.out.println();
		// Obtener la conexión -> según unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		// Manejador de entidades parecido a los Dao usando fabrica
		EntityManager em = fabrica.createEntityManager();
		
		//Validar un Usuario según su usuario y clave--> usar procedimiento almacenado
	
		String sql ="{call usp_validaAcceso(:xusr,:xcla)}"; // <-- JPA
		//Lis --> control+espacio JAVA UTIL
		//Ya no se usa para procedimiento almacenado --> TypedQuery<Usuario> query=em.createQuery(sql,Usuario.class);
		
		Query query =em.createNativeQuery(sql,Usuario.class);//object importar java persistencia
		query.setParameter("xusr", "U002@gmail.com");
		query.setParameter("xcla", "10002");
		
		Usuario u=null;
		try {
			u = (Usuario) query.getSingleResult();
		} catch (Exception e) {
			
		}
		
		if(u==null) {
			System.out.println("Codigo No existe");
		}else {
			System.out.println("Bienvenido : "+u.getNombre());
			System.out.println(u);
		}	
		em.close();
	}		
}
