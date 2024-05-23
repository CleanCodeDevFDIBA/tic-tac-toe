package com.fdibaProject.tictactoe.controllers;

import com.fdibaProject.tictactoe.entities.Game;
import com.fdibaProject.tictactoe.entities.Player;
import com.fdibaProject.tictactoe.entities.Scoreboard;
import com.fdibaProject.tictactoe.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(GameController.class)
public class GameControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    void when_createGame_shouldReturnCreated() throws Exception {
        // Given
        final Game createdGame = new Game();
        when(gameService.createGame(anyString(), anyString(), anyString()))
                .thenReturn(createdGame);

        // When
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/createGame")
                                .content("{\"player1\":\"Player1\",\"player2\":\"Player2\",\"winner\":\"Player1\"}")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetScoreboard() throws Exception {
        // Given
        List<Player> players = Arrays.asList(
                new Player(5, "Player1"),
                new Player(3, "Player2"),
                new Player(2, "Player3")
        );
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.setScoreboard(players);

        // When
        when(gameService.getScoreboard()).thenReturn(players);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/scoreboard")
                        .accept(MediaType.APPLICATION_JSON))

                //Then
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

