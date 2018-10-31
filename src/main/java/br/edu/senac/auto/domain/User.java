package br.edu.senac.auto.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USUARIO")
public class User {

    @Id
    @Column(name = "COD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "DES_EMAIL")
    @Length(max = 80)
    private String email;

    @NotNull
    @Column(name = "NOM_USUARIO")
    @Length(max = 200)
    private String name;

    @Column(name = "DES_CPF")
    @Length(max = 14)
    private String cpf;

    @NotNull
    @Column(name = "IND_ADMIN")
    private Boolean admin;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
