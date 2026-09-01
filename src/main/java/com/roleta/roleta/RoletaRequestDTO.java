package com.roleta.roleta;

public class RoletaRequestDTO {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private int tentativasRestantes; // Campo adicionado para sincronizar com o front-end

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

    public int getTentativasRestantes() {
        return tentativasRestantes;
    }

    public void setTentativasRestantes(int tentativasRestantes) {
        this.tentativasRestantes = tentativasRestantes;
    }
}
