// package jpa.features;
// 
// import java.io.Serializable;
// 
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// 
// import org.hibernate.annotations.Type;
// import org.hibernate.annotations.TypeDef;
// import org.hibernate.annotations.TypeDefs;
// 
// import io.hypersistence.utils.hibernate.type.array.StringArrayType;
// 
// @Entity
// @TypeDefs(@TypeDef(name="string-array", typeClass=StringArrayType.class))
// public class ArraysEntity implements Serializable {
// 
// 	private static final long serialVersionUID = 1L;
// 
// 	Long id;
//     String[] greetings;
// 
//     @Id @GeneratedValue(strategy=GenerationType.AUTO)
// 	public Long getId() {
// 		return id;
// 	}
// 
// 	public void setId(Long id) {
// 		this.id = id;
// 	}
// 
// 	@Type(type="string-array" )
// 	public String[] getGreetings() {
// 		return greetings;
// 	}
// 
// 	public void setGreetings(String[] greetings) {
// 		this.greetings = greetings;
// 	}
// 
// }
