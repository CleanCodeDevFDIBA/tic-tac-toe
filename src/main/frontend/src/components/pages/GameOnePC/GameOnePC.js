import React, { useState, useEffect } from 'react';
import './GameOnePC.css';
import axios from 'axios';
import xImage from './X-img.png';
import oImage from './O-img.png';
import { useLocation } from 'react-router-dom';
import { saveGame } from '../../../js/apiCalls.js'


function GameOnePc() {

  const [board, setBoard] = useState(Array(9).fill(null));
  const [currentPlayer, setCurrentPlayer] = useState('X');
  const [gameOver, setGameOver] = useState(false);
  const [winner, setWinner] = useState(null);
  const [winningLine, setWinningLine] = useState([]);
  const location = useLocation();
  const { player1Name, player2Name } = location.state || {};

  const checkWinner = (board) => {
    const lines = [
      [0, 1, 2],
      [3, 4, 5],
      [6, 7, 8],
      [0, 3, 6],
      [1, 4, 7],
      [2, 5, 8],
      [0, 4, 8],
      [2, 4, 6],
    ];
    for (let line of lines) {
      const [a, b, c] = line;
      if (board[a] && board[a] === board[b] && board[a] === board[c]) {
        return { winner: board[a], line };
      }
    }
    return null;
  };

  const handleSquareClick = (index) => {
    if (board[index] || gameOver) return;

    const updatedBoard = [...board];
    updatedBoard[index] = currentPlayer;
    setBoard(updatedBoard);

    const winnerInfo = checkWinner(updatedBoard);

    if (winnerInfo) {
      setGameOver(true);
      setWinner(winnerInfo);
      setWinningLine(winnerInfo.line);

      if (winnerInfo.winner == "X"){
            saveGame(player1Name, player2Name, player1Name)
        }
      else{
        saveGame(player1Name, player2Name, player2Name)
       }

    } else if (!updatedBoard.includes(null)) {
      setGameOver(true);
      setWinner('Tie');
      saveGame(player1Name, player2Name, null)
    } else {
      setCurrentPlayer(currentPlayer === 'X' ? 'O' : 'X');
    }
  };

  const resetGame = async () => {
    setBoard(Array(9).fill(null));
    setCurrentPlayer('X');
    setGameOver(false);
    setWinner(null);
    setWinningLine([]);
  };

  return (
      <div className='container'>
        <h1 className='game-title'>Tic-Tac-Toe Game</h1>
        <div className="turn-indicator">
          <p>Current Turn: {currentPlayer=='X'?player1Name:player2Name}</p>
        </div>
        <div className='board'>
        <div className='row1'>
            {board.slice(0, 3).map((value, index) => (
                <div key={index} className={`boxes ${winningLine.includes(index) ? 'glow' : ''}`}
                onClick={() => handleSquareClick(index)}>
                  {value === 'X' && <img src={xImage} alt='X'/>}
                  {value === 'O' && <img src={oImage} alt='O'/>}
                </div>

            ))}
          </div>
          <div className='row2'>
            {board.slice(3, 6).map((value, index) => (
                <div key={index + 3} className={`boxes ${winningLine.includes(index+3) ? 'glow' : ''}`}
                onClick={() => handleSquareClick(index + 3)}>
                  {value === 'X' && <img src={xImage} alt='X'/>}
                  {value === 'O' && <img src={oImage} alt='O'/>}
                </div>
            ))}
          </div>
          <div className='row3'>
            {board.slice(6, 9).map((value, index) => (
                <div key={index + 6} className={`boxes ${winningLine.includes(index+6) ? 'glow' : ''}`}
                onClick={() => handleSquareClick(index + 6)}>
                  {value === 'X' && <img src={xImage} alt='X'/>}
                  {value === 'O' && <img src={oImage} alt='O'/>}
                </div>
            ))}
          </div>
        </div>
        <div className='frame1'>
          {gameOver && (
              <div className='message'>
                {winner === 'Tie' ? "It's a tie!" : `Winner: ${board[winningLine[0]]=='X'? player1Name:player2Name}`}
              </div>
          )}


            <div className="buttons">
                <div className="home-button">
                    <a href="/">
                        <button>Home</button>
                    </a>
                </div>
                <div className="score-button">
                    <a href="/Scoreboard">
                        <button>Scoreboard</button>
                    </a>
                </div>
                <div className="new-game">
                    <a href="/GameOnePC" onClick={resetGame}>
                        <button>Play again</button>
                    </a>
                </div>

            </div>
        </div>
      </div>
  );

}

export default GameOnePc;
