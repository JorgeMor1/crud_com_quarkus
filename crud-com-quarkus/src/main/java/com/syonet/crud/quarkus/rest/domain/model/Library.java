package com.syonet.crud.quarkus.rest.domain.model;

import java.util.Objects;
//import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Library extends PanacheEntityBase {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;


public long getId() {
    return id;
}
public void setId(long id) {
    this.id = id;
}
@Column(name = "titulo")
private String titulo;
public String getTitulo() {
    return titulo;
}
public void setTitulo(String titulo) {
    this.titulo = titulo;
}
@Column(name = "email")
private String email;
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
@Column(name = "age")
private Integer age;


public Integer getAge() {
    return age;
}
public void setAge(Integer age) {
    this.age = age;
}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return id == library.id &&
               Objects.equals(titulo, library.titulo) &&
               Objects.equals(email, library.email) &&
               Objects.equals(age, library.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, email, age);
    }



}
