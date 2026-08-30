package com.roleta.roleta;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class RoletaService {

    private final int[] premiosBaixos = {3, 5, 10};
    private final int[] premiosAltos = {40, 50};
    private final Random random = new Random();

    // Simulação do banco de dados em memória
    private final Map<String, Usuario> usuariosDB = new HashMap<>();

    public int processarGiro(RoletaRequestDTO dto) {
        String cpf = dto.getCpf();

        // Se o usuário ainda não existe no mapa, cadastra
        Usuario usuario = usuariosDB.computeIfAbsent(cpf, k -> 
            new Usuario(dto.getCpf(), dto.getNome(), dto.getTelefone(), dto.getEmail())
        );

        // Incrementa o giro individual
        usuario.incrementarGiro();
        int giros = usuario.getGirosContador();

        // 10º giro do usuário -> prêmio alto (40 ou 50) e reseta o ciclo
        if (giros >= 10) {
            usuario.setGirosContador(0);
            return premiosAltos[random.nextInt(premiosAltos.length)];
        }

        // 1º ao 9º giro: 5% de chance de cair 20, 95% entre 3, 5 e 10
        int chance = random.nextInt(100);
        if (chance < 5) {
            return 20;
        } else {
            return premiosBaixos[random.nextInt(premiosBaixos.length)];
        }
    }
}