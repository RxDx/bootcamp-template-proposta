package br.com.zup.bootcamp.proposta.model;

import br.com.zup.bootcamp.proposta.validator.CpfOrCnpj;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @CpfOrCnpj
    @Column(unique = true)
    private String document;
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @Positive
    private BigDecimal salary;

    public Proposal(
            @NotBlank @CpfOrCnpj String document,
            @Email @NotBlank String email,
            @NotBlank String name,
            @NotBlank String address,
            @Positive BigDecimal salary
    ) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Proposal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}