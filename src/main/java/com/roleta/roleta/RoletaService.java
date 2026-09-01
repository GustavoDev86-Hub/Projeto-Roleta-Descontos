package com.roleta.roleta;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class RoletaService {

    private final int[] premiosBaixos = {3, 5, 10};
    private final int[] premioQuebrado = {20}; // Corrigido a digitação para Quebrado
    private final int[] premiosAltos = {40, 50};
    private final Random random = new Random();

    private final Map<String, Usuario> usuariosDB = new HashMap<>();

    public int processarGiro(RoletaRequestDTO dto) {
        String cpf = dto.getCpf();

        Usuario usuario = usuariosDB.computeIfAbsent(cpf, k -> 
            new Usuario(dto.getCpf(), dto.getNome(), dto.getTelefone(), dto.getEmail())
        );

        usuario.incrementarGiro();
        int giros = usuario.getGirosContador();

        // 10º giro do usuário -> prêmio alto e reseta o ciclo
        if (giros >= 10) {
            usuario.setGirosContador(0);
            return sortearPremio(premiosAltos);
        }

        // 1º ao 9º giro: 5% de chance para prêmio quebrado, 95% para prêmio baixo
        int chance = random.nextInt(100);
        if (chance < 5) {
            return sortearPremio(premioQuebrado);
        }

        return sortearPremio(premiosBaixos);
    }

    // Método auxiliar para evitar repetição do sorteio de arrays
    private int sortearPremio(int[] opcoes) {
        return opcoes[random.nextInt(opcoes.length)];
    }
}