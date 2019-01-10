package com.oibss.chronoomega.hibernate.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account", schema = "dbo", catalog = "chrono")
public class AccountEntity {
    private String mail;
    private Object id;
    private String password;

    @Basic
    @Column(name = "mail", nullable = false, length = 50)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return Objects.equals(mail, that.mail) &&
                Objects.equals(id, that.id) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, id, password);
    }
}
