// Grupo formado por Francisco Ramírez Cañadas y Jorge Repullo Serrano.
package org.mps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import org.mps.mutation.GaussianMutation;

public class GaussianMutationTest {

    @Test
    @DisplayName("El constructor sin parámetros inicializa correctamente los valores por defecto")
    public void constructor_sinParametros_inicializaCorrecta() throws Exception {
        // Arrange

        // Act
        GaussianMutation gaussianMutation = new GaussianMutation();

        // Assert
        assertNotNull(gaussianMutation);
    }

    @Test
    @DisplayName("El constructor con parámetros inicializa correctamente los valores asignados")
    public void constructor_conParametros_inicializaCorrecta() throws Exception {
        // Arrange

        // Act
        GaussianMutation gaussianMutation = new GaussianMutation(0.3, 1.5);

        // Assert
        assertNotNull(gaussianMutation);
    }

    @Test
    @DisplayName("El método mutate lanza una excepción con individual nulo")
    public void mutate_individualNulo_lanzaExcepcion() {
        // Arrange
        GaussianMutation gaussianMutation = new GaussianMutation();

        // Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            gaussianMutation.mutate(null);
        });
    }

    @Test
    @DisplayName("El método mutate lanza una excepción con individual vacío")
    public void mutate_individualVacio_lanzaExcepcion() {
        // Arrange
        GaussianMutation gaussianMutation = new GaussianMutation();
        int[] individual = new int[0];

        // Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            gaussianMutation.mutate(individual);
        });
    }

    @Test
    @DisplayName("El método mutate no modifica el individual con tasa de mutación 0")
    public void mutate_tasaMutacionCero_noModificaIndividual() throws Exception {
        // Arrange
        GaussianMutation gaussianMutation = new GaussianMutation(0.0, 1.0);
        int[] individual = { 1, 2, 3, 4, 5 };

        // Act
        int[] resultado = gaussianMutation.mutate(individual);

        // Assert
        assertArrayEquals(individual, resultado);
    }

    @Test
    @DisplayName("El método mutate modifica al individual con tasa de mutación 1 y desviación positiva")
    public void mutate_tasaMutacionUnoDesviacionPositiva_modificaAlMenosUnValor() throws Exception {
        // Arrange
        GaussianMutation gaussianMutation = new GaussianMutation(1.0, 2.0);
        int[] individual = { 10, 20, 30, 40, 50 };

        // Act
        int[] resultado = gaussianMutation.mutate(individual);

        // Assert
        assertFalse(Arrays.equals(individual, resultado));
    }

    @Test
    @DisplayName("El método mutate con tasa de mutación 1 y desviación 0 no modifica los valores")
    public void mutate_tasaMutacionUnoDesviacionCero_noModificaValores() throws Exception {
        // Arrange
        GaussianMutation gaussianMutation = new GaussianMutation(1.0, 0.0);
        int[] individual = { 10, 20, 30, 40, 50 };

        // Act
        int[] resultado = gaussianMutation.mutate(individual);

        // Assert
        assertArrayEquals(individual, resultado);
    }
}