package jpa.dsd;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped @Dependent
public class EntityManagerProducer {
	private EntityManagerFactory emf;
	
	public EntityManagerProducer() {
		System.out.println("EntityManagerProducer.EntityManagerProducer()");
	}
	
	@PostConstruct
	public void setup() {
		System.out.println("EntityManagerProducer.setup()");
		emf = Persistence.createEntityManagerFactory("jpademo");
	}

	@Produces // you can also make this @RequestScoped
	public EntityManager create() {
		return emf.createEntityManager();
	}

	public void close(@Disposes EntityManager em) {
		if (em.isOpen()) {
			em.close();
		}
	}
}