package com.fdibaProject.tictactoe.service;

import com.fdibaProject.tictactoe.entities.Game;
import com.fdibaProject.tictactoe.entities.Player;
import com.fdibaProject.tictactoe.factory.GameFactory;
import com.fdibaProject.tictactoe.repositories.GameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;


    @Test
    void when_getAllGamesIsEmpty_shouldReturnEmptyList() {
        // Given
        final List<Game> emptyList = new ArrayList<>();
        when(gameRepository.findAll()).thenReturn(emptyList);

        // When
        final List<Game> foundGames = gameService.getAllGames();

        // Then
        verify(gameRepository, Mockito.times(1)).findAll();
        Assertions.assertTrue(foundGames.isEmpty());
    }

    @Test
    void when_getAllGamesIsNotEmpty_shouldReturnNonEmptyList() {
        // Given
        final Game game = GameFactory.createGame();
        final List<Game> gameList = List.of(game);
        when(gameRepository.findAll()).thenReturn(gameList);

        // When
        final List<Game> foundGames = gameService.getAllGames();

        // Then
        verify(gameRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(foundGames.isEmpty());
        assertEquals(gameList.size(), foundGames.size());
    }



    @Test
    void when_createGameWithValidData_shouldSaveGame() {
        // Given
        final Game game = GameFactory.createGame();
        when(gameRepository.save(Mockito.any(Game.class))).thenReturn(game);

        // When
        final Game createdGame = gameService.createGame(game.getPlayer1(), game.getPlayer2(), game.getWinner());

        // Then
        verify(gameRepository, Mockito.times(1)).save(Mockito.any(Game.class));
        Assertions.assertNotNull(createdGame);
        assertEquals(game.getPlayer1(), createdGame.getPlayer1());
        assertEquals(game.getPlayer2(), createdGame.getPlayer2());
        assertEquals(game.getWinner(), createdGame.getWinner());
    }

    @Test
    void when_addRankings_givenScoreboard_shouldAssignCorrectRankings() throws Exception {
        // Given
        Player player1 = new Player(5, "Player1");
        Player player2 = new Player(3, "Player2");
        Player player3 = new Player(5, "Player3");
        Player player4 = new Player(1, "Player4");

        List<Player> scoreboard = Arrays.asList(player1, player3, player2, player4);

        // When
        Method method = GameService.class.getDeclaredMethod("addRankings", List.class);
        method.setAccessible(true);
        method.invoke(gameService, scoreboard);

        // Then
        Assertions.assertEquals(1, player1.ranking);
        Assertions.assertEquals(1, player3.ranking);
        Assertions.assertEquals(3, player2.ranking);
        Assertions.assertEquals(4, player4.ranking);
    }

    @Test
    void testSortScoreboard() throws Exception {
        // Given
        Map<String, Integer> winsPerPlayerMap = new HashMap<>();
        winsPerPlayerMap.put("Player1", 3);
        winsPerPlayerMap.put("Player2", 5);
        winsPerPlayerMap.put("Player3", 1);

        Method method = GameService.class.getDeclaredMethod("sortScoreboard", Map.class);
        method.setAccessible(true);

        GameService gameService = new GameService();

        // When
        @SuppressWarnings("unchecked")
        List<Player> sortedScoreboard = (List<Player>) method.invoke(gameService, winsPerPlayerMap);

        // Then
        assertEquals(3, sortedScoreboard.size());
        assertEquals("Player2", sortedScoreboard.get(0).name);
        assertEquals(5, sortedScoreboard.get(0).wins);
        assertEquals("Player1", sortedScoreboard.get(1).name);
        assertEquals(3, sortedScoreboard.get(1).wins);
        assertEquals("Player3", sortedScoreboard.get(2).name);
        assertEquals(1, sortedScoreboard.get(2).wins);
    }

    @Test
    void testGetScoreboard() {
        // Given
        List<Game> games = Arrays.asList(
                mockGame("Player1", "Player2", "Player1"),
                mockGame("Player2", "Player3", "Player3"),
                mockGame("Player1", "Player3", "Player1")
        );
        when(gameRepository.findAll()).thenReturn(games);

        // When
        List<Player> scoreboard = gameService.getScoreboard();

        // Then
        assertEquals(3, scoreboard.size());

        assertEquals("Player1", scoreboard.get(0).name);
        assertEquals(2, scoreboard.get(0).wins);
        assertEquals(1, scoreboard.get(0).ranking);

        assertEquals("Player3", scoreboard.get(1).name);
        assertEquals(1, scoreboard.get(1).wins);
        assertEquals(2, scoreboard.get(1).ranking);

        assertEquals("Player2", scoreboard.get(2).name);
        assertEquals(0, scoreboard.get(2).wins);
        assertEquals(3, scoreboard.get(2).ranking);
    }

    private Game mockGame(String player1, String player2, String winner) {
        Game game = mock(Game.class);
        when(game.getPlayer1()).thenReturn(player1);
        when(game.getPlayer2()).thenReturn(player2);
        when(game.getWinner()).thenReturn(winner);
        return game;
    }

    @Test
    void testGetAllGames() {
        // Given
        List<Game> expectedGames = Arrays.asList(
                new Game(/* game details */),
                new Game(/* game details */),
                new Game(/* game details */)
        );
        when(gameRepository.findAll()).thenReturn(expectedGames);

        // When
        List<Game> actualGames = gameService.getAllGames();

        // Then
        assertEquals(expectedGames.size(), actualGames.size());
        for (int i = 0; i < expectedGames.size(); i++) {
            assertEquals(expectedGames.get(i), actualGames.get(i));
        }
    }

    @Test
    void testGetAllGamesWithEmptyList() {
        // Given
        List<Game> emptyList = new ArrayList<>();
        when(gameRepository.findAll()).thenReturn(emptyList);

        // When
        List<Game> actualGames = gameService.getAllGames();

        // Then
        assertEquals(0, actualGames.size());
    }

    @Test
    void when_saveGame_shouldReturnSavedGame() {
        // Given
        Game game = new Game();
        game.setPlayer1("Player1");
        game.setPlayer2("Player2");
        game.setWinner("Player1");

        when(gameRepository.save(any(Game.class))).thenReturn(game);

        // When
        Game savedGame = gameService.saveGame(game);

        // Then
        verify(gameRepository, times(1)).save(game);
        Assertions.assertNotNull(savedGame, "Expected saved game to be not null");
        Assertions.assertEquals("Player1", savedGame.getPlayer1(), "Expected player1 to match");
        Assertions.assertEquals("Player2", savedGame.getPlayer2(), "Expected player2 to match");
        Assertions.assertEquals("Player1", savedGame.getWinner(), "Expected winner to match");
    }

    @Test
    void when_saveNullGame_shouldHandleGracefully() {
        // Given
        when(gameRepository.save(null)).thenReturn(null);

        // When
        Game savedGame = gameService.saveGame(null);

        // Then
        verify(gameRepository, times(1)).save(null);
        assertNull(savedGame, "Expected the saved game to be null when saving a null game");
    }



}
