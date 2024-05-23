import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Scoreboard.css';

const Scoreboard = () => {
  const [players, setPlayers] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [pageCount, setPageCount] = useState(0);
  const itemsPerPage = 10;

  useEffect(() => {
    const fetchPlayers = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/scoreboard");
        const totalPlayers = getTotalPlayers(response.data.scoreboard);
        setPageCount(Math.ceil(response.data.totalPlayers / itemsPerPage));
        const playersAfterPagination = paginatePlayers(response.data.scoreboard, currentPage, itemsPerPage, totalPlayers);
        setPlayers(playersAfterPagination);
      } catch (error) {
        console.error('Error fetching the scoreboard data', error);
      }
    };

    fetchPlayers();
  }, [currentPage]);

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  function getTotalPlayers(players){
    let count = 0;
    for ( const player in players){
        count++;
    }

    return count;

  }

  function paginatePlayers(players, currentPage, playersPerPage, totalPlayers){
      let count = 0;
      let result = [];

      for (let i = (currentPage - 1) * playersPerPage; i < currentPage * playersPerPage && i < totalPlayers; i++) {
        result.push(players[i]);
      }

      return result;
    }


  const renderPagination = () => {
    if (pageCount <= 1) return null;

    const pageButtons = [];
    for (let i = 1; i <= pageCount; i++) {
      pageButtons.push(
        <button key={i} className={`pagination-btn ${currentPage === i ? 'active' : ''}`} onClick={() => handlePageChange(i)}>
          {i}
        </button>
      );
    }

    return (
      <div className="pagination">
        <button disabled={currentPage === 1} onClick={() => handlePageChange(currentPage - 1)}>
          Previous
        </button>
        {pageButtons}
        <button disabled={currentPage === pageCount} onClick={() => handlePageChange(currentPage + 1)}>
          Next
        </button>
      </div>
    );
  };

  return (
    <div className="scoreboard-page">
      <h2 className="scoreboard-title">Scoreboard</h2>
      <table>
        <thead>
          <tr>
            <th>Rank</th>
            <th>Player name</th>
            <th>Number of wins</th>
          </tr>
        </thead>
        <tbody>
          {players.map((player, index) => (
            <tr key={index}>
              <td>{player.ranking}</td>
              <td>{player.name}</td>
              <td>{player.wins}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {renderPagination()}

      <div className="homeButton">
        <a href="/">
          <button>Home</button>
        </a>
      </div>
    </div>
  );
};

export default Scoreboard;
