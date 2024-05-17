import axios from 'axios'


export async function createGame (username1, username2) {
      let gameId = null;
      try {
        let gameDataJSON = {
            player1: username1,
            player2 : username2
        }
       const response = await axios.post('http://localhost:8080/api/createGame', gameDataJSON)

       if (response.status >= 200 && response.status < 300) {

         let gameInfo = response.data;
         console.log('Response data: ', gameInfo);
         gameId = gameInfo["id"];
         console.log("game id after api call:" + gameId);

       } else {
         console.error('Request failed with status:', response.status);
       }

      } catch (error) {
        console.error('Error creating game:', error);
      }
      return gameId;
};

export function sendResult (gameId, winner) {

    let gameDataJSON = {
        id: gameId,
        winner : winner
    }
    try {
    axios.post('http://localhost:8080/api/announceWinner', gameDataJSON)
           .then(response => {

                   if (response.status >= 200 && response.status < 300) {
                     // The request was successful
                     let gameInfo = response.data;
                     console.log('Response data: ', gameInfo);

                   } else {
                     // The request was not successful
                     console.error('Request failed with status:', response.status);
                   }
                 });
          } catch (error) {
            console.error('Error creating game:', error);
          }
  };