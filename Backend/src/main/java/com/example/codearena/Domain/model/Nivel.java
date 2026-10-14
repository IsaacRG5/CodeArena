package com.example.codearena.Domain.model;

public enum Nivel {
    ROOKIE(0),
    JUNIOR(500),
    DEVELOPER(1500),
    SENIOR(3000),
    MASTER(5000),
    LEGEND(10000);

    private final int experienciaMinima;

    Nivel(int experienciaMinima) {
        this.experienciaMinima = experienciaMinima;
    }

    public int getExperienciaMinima() {
        return experienciaMinima;
    }

    public static Nivel  calcucalarSegunExperiencia(int experienciaAcumulada) {
        Nivel[] nivels = values();
        for (int i = nivels.length - 1; i >= 0; i--) {
            if (experienciaAcumulada >= nivels[i].experienciaMinima) {
                return nivels[i];
            }
        }
        return ROOKIE;
    }
}
