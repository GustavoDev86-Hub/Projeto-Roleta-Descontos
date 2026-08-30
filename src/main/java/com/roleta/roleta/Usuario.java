package com.roleta.roleta;

public class Usuario {

    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private int girosContador; // Composição do contador de giros para cada usuário

    public Usuario() {

    }

    public Usuario(String cpf, String nome, String telefone, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.girosContador = 0; // Inicia com 0 giros
    }

    // Getters e Setters
    public String getCpf() { 
        return cpf;
    }
    public void setCpf(String cpf) { 
        this.cpf = cpf;
    }

    public String getNome() { 
        return nome; }
    public void setNome(String nome) { 
        this.nome = nome; }

    public String getTelefone() { 
        return telefone; 
    }
    public void setTelefone(String telefone) { 
        this.telefone = telefone; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    public int getGirosContador() { 
        return girosContador; 
    }
    public void setGirosContador(int girosContador) { 
        this.girosContador = girosContador; 
    }
    
    public void incrementarGiro() { 
        this.girosContador++; 
    }
}
