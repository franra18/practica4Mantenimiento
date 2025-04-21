// Grupo formado por Francisco Ramírez Cañadas y Jorge Repullo Serrano.
package org.mps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.selection.TournamentSelection;

public class TournamentSelectionTest {

    @Test
    @DisplayName("El constructor se crea correctamente para un tamaño de torneo mayor que 0")
    public void constructor_llamarFuncion_seCreaCorrectamente() throws EvolutionaryAlgorithmException {
        // Arrange

        // Act
        TournamentSelection tournamentSelection = new TournamentSelection(3);

        // Assert
        assertNotNull(tournamentSelection);
    }

    @Test
    @DisplayName("El constructor lanza una excepcion para un tamaño de torneo menor o igual a 0")
    public void constructor_tamanoTorneoMenorIgualCero_lanzaExcepcion() {
        // Arrange

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            new TournamentSelection(0);
        });
    }

    @Test
    @DisplayName("El método select funciona correctamente con una población válida")
    public void select_poblacionValida_seleccionCorrecta() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection tournamentSelection = new TournamentSelection(3);
        int[] population = { 1, 2, 3, 4, 5 };

        // Act
        int[] selected = tournamentSelection.select(population);

        // Assert
        assertNotNull(selected);
        assertEquals(population.length, selected.length);
    }

    @Test
    @DisplayName("El método select lanza una excepción con una población nula")
    public void select_poblacionNula_lanzaExcepcion() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection tournamentSelection = new TournamentSelection(3);

        // Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            tournamentSelection.select(null);
        });
    }

    @Test
    @DisplayName("El método select lanza una excepción con una población vacía")
    public void select_poblacionVacia_lanzaExcepcion() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection tournamentSelection = new TournamentSelection(3);
        int[] population = new int[0];

        // Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            tournamentSelection.select(population);
        });
    }

    @Test
    @DisplayName("El método select lanza una excepción con una población de tamaño menor que el tamaño del torneo")
    public void select_poblacionTamanoMenorQueTorneo_lanzaExcepcion() throws EvolutionaryAlgorithmException {
        // Arrange
        TournamentSelection tournamentSelection = new TournamentSelection(3);
        int[] population = { 1, 2 };

        // Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            tournamentSelection.select(population);
        });
    }
}