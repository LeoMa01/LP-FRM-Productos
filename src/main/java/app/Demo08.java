package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;
//SIRVE PARA HACER UN LOGIN CON SELECT Y GETSINGLERESULT QUE SOLO DEVUELVE UNA FILA Y NECESITA TRY CATCH
public class Demo08 {
	public static void main(String[] args) {
		System.out.println();
		// Obtener la conexión -> según unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		// Manejador de entidades parecido a los Dao usando fabrica
		EntityManager em = fabrica.createEntityManager();
		//Validar un Usuario según su usuario y clave
	
		String sql ="Select u from Usuario u where u.usuario = :xusr and u.clave = :xcla"; // <-- JPA
		//Lis --> control+espacio JAVA UTIL
		TypedQuery<Usuario> query=em.createQuery(sql,Usuario.class);
		query.setParameter("xusr", "U002@gmail.com");
		query.setParameter("xcla", "10002");
		
		//GETRESULTLIST DEVUELVE UNA FILA
		//GETSINGLERESULT DEVUELVE SOLO UNA FILA
		Usuario u=null;
		try {
			u = query.getSingleResult();
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
	//TRYCATH SIN IF ELSE
	/*Usuario u=null;
	try {
		u = query.getSingleResult();
		System.out.println("Bienvenido : "+u.getNombre());
		System.out.println(u);
	} catch (Exception e) {
		System.out.println("Codigo No existe");		
	}
}*/
}
