package com.example.codearena.Domain.model;

public enum Dificultad {

        EASY(100),
        MEDIUM(250),
        HARD(500),
    LEGENDARY(1000);

        private final int experienciaOtorgada;
        Dificultad(int experienciaOtorga) {
            this.experienciaOtorgada = experienciaOtorga;
        }

        public int getExperienciaOtorgada() {
            return experienciaOtorgada;
        }



}
