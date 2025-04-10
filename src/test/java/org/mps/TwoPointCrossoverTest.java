package org.mps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.crossover.TwoPointCrossover;

public class TwoPointCrossoverTest {

    @Test
    @DisplayName("El test de cruce de dos puntos se crea correctamente")
    public void constructor_llamarFuncion_seCreaCorrectamente() {
        // Arrange

        // Act
        TwoPointCrossover twoPointCrossover = new TwoPointCrossover();

        // Assert
        assertNotNull(twoPointCrossover);
    }

    @Test
    @DisplayName("El metodo crossover se ejecuta correctamente con dos padres del mismo tamaño")
    public void crossover_padresMismoTamanyo_seEjecutaCorrectamente() throws EvolutionaryAlgorithmException {
        // Arrange
        TwoPointCrossover twoPointCrossover = new TwoPointCrossover();
        int[] parent1 = { 1, 2, 3, 4, 5 };
        int[] parent2 = { 6, 7, 8, 9, 10 };

        // Act
        int[][] offspring = twoPointCrossover.crossover(parent1, parent2);

        // Assert
        assertNotNull(offspring);
        assertEquals(2, offspring.length);
        assertEquals(parent1.length, offspring[0].length);
        assertEquals(parent1.length, offspring[1].length);
    }

    @Test
    @DisplayName("El metodo crossover lanza una excepcion con padres de diferente tamaño")
    public void crossover_padresDiferenteTamanyo_lanzaExcepcion() {
        // Arrange
        TwoPointCrossover twoPointCrossover = new TwoPointCrossover();
        int[] parent1 = { 1, 2, 3, 4, 5 };
        int[] parent2 = { 6, 7, 8 };

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            twoPointCrossover.crossover(parent1, parent2);
        });
    }

    @Test
    @DisplayName("El metodo crossover lanza una excepcion con padres de longitud menor que 1")
    public void crossover_padresLongitudMenorUno_lanzaExcepcion() {
        // Arrange
        TwoPointCrossover twoPointCrossover = new TwoPointCrossover();
        int[] parent1 = { 1 };
        int[] parent2 = { 2 };

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            twoPointCrossover.crossover(parent1, parent2);
        });
    }

    @Test
    @DisplayName("El metodo crossover lanza una excepcion con padre 1 nulo")
    public void crossover_padre1Nulo_lanzaExcepcion() {
        // Arrange
        TwoPointCrossover twoPointCrossover = new TwoPointCrossover();
        int[] parent1 = null;
        int[] parent2 = { 6, 7, 8, 9, 10 };

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            twoPointCrossover.crossover(parent1, parent2);
        });
    }

    @Test
    @DisplayName("El metodo crossover lanza una excepcion con padre 2 nulo")
    public void crossover_padre2Nulo_lanzaExcepcion() {
        // Arrange
        TwoPointCrossover twoPointCrossover = new TwoPointCrossover();
        int[] parent1 = { 1, 2, 3, 4, 5 };
        int[] parent2 = null;

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            twoPointCrossover.crossover(parent1, parent2);
        });
    }
}
