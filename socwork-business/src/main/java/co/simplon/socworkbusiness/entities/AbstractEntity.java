package co.simplon.socworkbusiness.entities;

import jakarta.persistence.*;

@MappedSuperclass
abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    public long getId() {
	return id;
    }

    public AbstractEntity() {
    }

   @SuppressWarnings("unused")
    private void setId(long id) {
	this.id = id;
    }

}
