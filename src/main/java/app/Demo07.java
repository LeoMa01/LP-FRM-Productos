package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;
//REGISTRAR UN PRODUCTO 
public class Demo07 {
	public static void main(String[] args) {
		System.out.println();
		// Obtener la conexión -> según unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		// Manejador de entidades parecido a los Dao usando fabrica
		EntityManager em = fabrica.createEntityManager();
		//Proceso para: registrar un nuevo usuario
		Producto p = new Producto();
		p.setIdprod("P0050");
		p.setDescripcion("Azucar");
		p.setStock(10);
		p.setPrecio(10.50);
		p.setIdcategoria(1);
		p.setEstado("1");
		
		//para reg, act, eli, --> transacciones
		em.getTransaction().begin();
		em.persist(p);//para registrar
		em.getTransaction().commit();
		System.out.println("Registro Producto OK");	
		em.close();
	}
}
