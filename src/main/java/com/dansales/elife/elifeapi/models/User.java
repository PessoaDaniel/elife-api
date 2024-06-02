package com.dansales.elife.elifeapi.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "user")
public class User extends BaseEntity {

    @Column()
    protected String name;

    @Column()
    protected String email;

    @Column()
    protected String password;

    @Column()
    protected String login;

    @Column()
    protected String cpf;

    @Column()
    protected String phone;

    @Column()
    protected String rg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
