package jpa.features;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import domain.Person;

/** This single JPA MetaModel Class was hand-made to avoid having
 * to run the pre-processor to create them every time;
 * that's awkward to do in both Eclipse and Maven.
 * and slow to invoke Maven on every file save.
 * @author Ian Darwin
 */
@StaticMetamodel(Person.class)
public class Person_ {

	public static SingularAttribute<Person, String> firstName;
	public static SingularAttribute<Person, String> lastName;
	public static SingularAttribute<Person, String> homePhone;
	public static SingularAttribute<Person, String> workPhone;

}
