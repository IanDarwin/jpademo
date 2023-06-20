package jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import domain.PersonRecord;

public class PersonRecordDemo {

    public static void main(String[] args) {
        // IRL you'd have the EntityManager injected  by JakartaEE or by Spring
        final EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<PersonRecord> peeps = entityManager
                .createQuery("""
                select new domain.PersonRecord(
                    pc.id,
                    pc.firstName,
                    pc.lastName)
                    from Person pc
                    order by pc.lastName
                    """, PersonRecord.class).getResultList();

        peeps.forEach(System.out::println);
    }
}
