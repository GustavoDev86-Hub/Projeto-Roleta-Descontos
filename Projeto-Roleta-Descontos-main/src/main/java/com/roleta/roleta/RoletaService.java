package com.roleta.roleta;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class RoletaService {

    private final int[] valoresPossiveis = {5, 10, 30, 50};
    private final Random random = new Random();

    public int girarRoleta() {
        int indiceAleatorio = random.nextInt(valoresPossiveis.length);
        return valoresPossiveis[indiceAleatorio];
    }
}