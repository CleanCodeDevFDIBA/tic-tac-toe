import React, { useState, useEffect } from 'react';
import  './GameOnePC.css'
import axios from 'axios';
import xImage from './cross-image.jpg';
import oImage from './circle-image.png';

const API_BASE_URL = 'http://localhost:8080/api/games';

//let data = []
function GameOnePc(){

const [game, setGame] = useState(null);
  const [board, setBoard] = useState(Array(9).fill(null));
  const [currentPlayer, setCurrentPlayer] = useState('X');
  const [gameOver, setGameOver] = useState(false);
  const [winner, setWinner] = useState(null);

  useEffect(() => {
    const fetchGame = async () => {
      try {
        const response = await axios.get('http://localhost:8080/games');
        setGame(response.data);
      } catch (error) {
        console.error('Error while in-game:', error);
      }
    };

    fetchGame();
  }, []);

  const handleSquareClick = async (index) => {
        if (board[index] || gameOver) return;
        const updatedBoard = [...board];
        updatedBoard[index] = currentPlayer;
        setBoard(updatedBoard);

        };


    return (
            <div className='container'>
              <h1 className='title'>Tic-Tac-Toe Game</h1>
              <div className='board'>
                <div className='row1'>
                  {board.slice(0, 3).map((value, index) => (
                    <div key={index} className='boxes' onClick={() => handleSquareClick(index)}>
                     {value === 'X' && <img src={xImage} alt='X' />}
                      {value === 'O' && <img src={oImage} alt='O' />}
                    </div>
                  ))}
                </div>
                <div className='row2'>
                  {board.slice(3, 6).map((value, index) => (
                    <div key={index + 3} className='boxes' onClick={() => handleSquareClick(index + 3)}>
                     {value === 'X' && <img src={xImage} alt='X' />}
                      {value === 'O' && <img src={oImage} alt='O' />}
                    </div>
                  ))}
                </div>
                <div className='row3'>
                  {board.slice(6, 9).map((value, index) => (
                    <div key={index + 6} className='boxes' onClick={() => handleSquareClick(index + 6)}>
                     {value === 'X' && <img src={xImage} alt='X' />}
                     {value === 'O' && <img src={oImage} alt='O' />}
                    </div>
                  ))}
                </div>

              </div>
              <button className='reset' onClick={() => window.location.reload()}>Play again!</button>
            </div>
          );
}

export default GameOnePc;