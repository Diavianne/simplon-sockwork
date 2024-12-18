package co.simplon.socworkbusiness.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "t_roles")
public class Role extends AbstractEntity {

    @Column(name = "role_name")
    private String name;

    @Column(name = "default")
    private boolean defaultRole;

    public boolean isDefaultRole() {
        return defaultRole;
    }

    public Role() {
        // ORM
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Role [name=" + name + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof Role other
                && this.name.equals(other.name);

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }


}