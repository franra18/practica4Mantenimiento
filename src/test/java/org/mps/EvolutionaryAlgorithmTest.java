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

    // ESTOS DOS TESTS LOS HA HECHO COPILOT, NO LOS TERMINO DE ENTENDER. SOLO CONSIGUEN PROBAR UNA RAMA MÁS.
    @Test
    @DisplayName("El método optimize maneja correctamente cuando el operador de selección devuelve null")
    public void optimize_selectionDevuelveNull_manejaCorrectamente() throws EvolutionaryAlgorithmException {
        // Arrange
        // Operador de selección que devuelve null
        SelectionOperator selectionOperator = new SelectionOperator() {
            @Override
            public int[] select(int[] individual) {
                return null; // Devuelve null para probar esa rama
            }
        };
        
        // Operadores que mantienen los valores intactos
        CrossoverOperator crossoverOperator = new CrossoverOperator() {
            @Override
            public int[][] crossover(int[] parent1, int[] parent2) {
                // Como puede recibir null, devolvemos arrays nulos o vacíos
                return new int[][] { new int[0], new int[0] };
            }
        };
        
        MutationOperator mutationOperator = new MutationOperator() {
            @Override
            public int[] mutate(int[] individual) {
                return individual; // Devuelve null si recibe null
            }
        };
        
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        int[][] population = {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };
        
        int[][] expected = {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };
        
        // Act
        int[][] result = algoritmo.optimize(population);

        // Assert
        // La población original debe mantenerse ya que better devuelve false cuando alguno de los arrays es null
        assertArrayEquals(expected, result, "La población original se mantiene cuando better compara con arrays nulos");
    }

    @Test
    @DisplayName("El método optimize maneja correctamente arrays de diferentes longitudes")
    public void optimize_arraysDiferentesLongitudes_manejaCorrectamente() throws EvolutionaryAlgorithmException {
        // Arrange
        // Operador de selección que devuelve arrays de diferente longitud
        SelectionOperator selectionOperator = new SelectionOperator() {
            private int count = 0;
            
            @Override
            public int[] select(int[] individual) {
                count++;
                if (count % 2 == 0) {
                    return new int[]{1, 1, 1, 1}; // Array de longitud 4
                } else {
                    return new int[]{1, 1}; // Array de longitud 2
                }
            }
        };
        
        CrossoverOperator crossoverOperator = new CrossoverOperator() {
            @Override
            public int[][] crossover(int[] parent1, int[] parent2) {
                // Devolvemos los mismos arrays
                return new int[][] { parent1, parent2 };
            }
        };
        
        MutationOperator mutationOperator = new MutationOperator() {
            @Override
            public int[] mutate(int[] individual) {
                return individual;
            }
        };
        
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        int[][] population = {
                { 10, 10, 10 }, // Array original de longitud 3
                { 10, 10, 10 }  // Array original de longitud 3
        };
        
        int[][] expected = {
                { 10, 10, 10 },
                { 10, 10, 10 }
        };
        
        // Act
        int[][] result = algoritmo.optimize(population);

        // Assert
        // La población original debe mantenerse ya que better devuelve false cuando los arrays tienen diferentes longitudes
        assertArrayEquals(expected, result, "La población original se mantiene cuando better compara arrays de diferentes longitudes");
    }
}