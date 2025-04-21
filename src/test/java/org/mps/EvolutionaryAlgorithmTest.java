package org.mps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.selection.*;
import org.mps.crossover.*;
import org.mps.mutation.*;

public class EvolutionaryAlgorithmTest {

    @Test
    @DisplayName("El constructor de EvolutionaryAlgorithm crea una instancia correctamente")
    public void constructor_valoresCorrectos_seCreaCorrectamente() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection selectionOperator = new TournamentSelection(2);
        GaussianMutation mutationOperator = new GaussianMutation();
        TwoPointCrossover crossoverOperator = new TwoPointCrossover();

        // Act
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator,
                crossoverOperator);

        // Assert
        assertNotNull(algoritmo);
    }

    @Test
    @DisplayName("El constructor de EvolutionaryAlgorithm lanza una excepción si el operador de selección es nulo")
    public void constructor_seleccionNulo_lanzaExcepcion() {
        // Arrange
        GaussianMutation mutationOperator = new GaussianMutation();
        TwoPointCrossover crossoverOperator = new TwoPointCrossover();

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            new EvolutionaryAlgorithm(null, mutationOperator, crossoverOperator);
        });
    }

    @Test
    @DisplayName("El constructor de EvolutionaryAlgorithm lanza una excepción si el operador de mutación es nulo")
    public void constructor_mutacionNulo_lanzaExcepcion() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection selectionOperator = new TournamentSelection(2);
        TwoPointCrossover crossoverOperator = new TwoPointCrossover();

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            new EvolutionaryAlgorithm(selectionOperator, null, crossoverOperator);
        });
    }

    @Test
    @DisplayName("El constructor de EvolutionaryAlgorithm lanza una excepción si el operador de cruce es nulo")
    public void constructor_cruceNulo_lanzaExcepcion() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection selectionOperator = new TournamentSelection(2);
        GaussianMutation mutationOperator = new GaussianMutation();

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            new EvolutionaryAlgorithm(selectionOperator, mutationOperator, null);
        });
    }

    @Test
    @DisplayName("El método optimize devuelve la población optimizada correctamente para una población impar")
    public void optimize_poblacionValidaImpar_seOptimizaCorrectamente() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection selectionOperator = new TournamentSelection(2);
        GaussianMutation mutationOperator = new GaussianMutation();
        TwoPointCrossover crossoverOperator = new TwoPointCrossover();
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator,
                crossoverOperator);

        int[][] population = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        // Act
        int[][] result = algoritmo.optimize(population);

        // Assert
        assertNotNull(result);
        assertEquals(population.length, result.length);
    }

    @Test
    @DisplayName("El método optimize devuelve la población optimizada correctamente para una población par")
    public void optimize_poblacionValidaPar_seOptimizaCorrectamente() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection selectionOperator = new TournamentSelection(2);
        GaussianMutation mutationOperator = new GaussianMutation();
        TwoPointCrossover crossoverOperator = new TwoPointCrossover();
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator,
                crossoverOperator);

        int[][] population = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 },
                { 10, 11, 12 }
        };

        // Act
        int[][] result = algoritmo.optimize(population);

        // Assert
        assertNotNull(result);
        assertEquals(population.length, result.length);
    }

    @Test
    @DisplayName("El método optimize lanza una excepción si la población es nula")
    public void optimize_poblacionNula_lanzaExcepcion() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection selectionOperator = new TournamentSelection(2);
        GaussianMutation mutationOperator = new GaussianMutation();
        TwoPointCrossover crossoverOperator = new TwoPointCrossover();
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator,
                crossoverOperator);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            algoritmo.optimize(null);
        });
    }

    @Test
    @DisplayName("El método optimize lanza una excepción si la población es vacía")
    public void optimize_poblacionVacia_lanzaExcepcion() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection selectionOperator = new TournamentSelection(2);
        GaussianMutation mutationOperator = new GaussianMutation();
        TwoPointCrossover crossoverOperator = new TwoPointCrossover();
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator,
                crossoverOperator);

        int[][] population = {};

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            algoritmo.optimize(population);
        });
    }

}