package com.marcosladeira.colaborador.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Colaborador {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "O campo nome não pode ser vazio.")
	@Column(nullable = false)
	private String nome;
	@CPF
	@NotBlank(message = "O campo cpf não pode ser vazio.")
	@Column(unique = true)
	private String cpf;
	@NotBlank(message = "O campo café da manhã não pode ser vazio.")
	@Column(unique = true)
	private String cafemanha;
	
	@Deprecated
	public Colaborador() {
		
	}
	
	public Colaborador(String nome) {
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCafemanha() {
		return cafemanha;
	}
	public void setCafemanha(String cafemanha) {
		this.cafemanha = cafemanha;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colaborador other = (Colaborador) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Colaborador [nome=" + nome + "]";
	}
	
}
