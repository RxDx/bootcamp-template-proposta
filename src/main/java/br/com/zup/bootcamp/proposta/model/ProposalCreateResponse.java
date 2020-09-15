package br.com.zup.bootcamp.proposta.model;

import br.com.zup.bootcamp.proposta.validator.CpfOrCnpj;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ProposalCreateResponse {

    private @NotBlank @CpfOrCnpj String document;
    private @NotBlank @Email String email;
    private @NotBlank String name;
    private @NotBlank String address;
    private @Positive Double salary;

    public ProposalCreateResponse(Proposal proposal) {
        document = proposal.getDocument();
        email = proposal.getEmail();
        name = proposal.getName();
        address = proposal.getAddress();
        salary = proposal.getSalary().doubleValue();
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
