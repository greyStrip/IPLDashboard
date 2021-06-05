import { React } from 'react';
import { Link } from 'react-router-dom';

import "./MatchDetailCard.scss";

export const MatchSmallCard = ({match, teamName}) => {
    if(!match) return null;
    //console.log("team1: "+ match.team1 + " teamName: "+ teamName);  
    const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
    const otherTeamRoute = `/teams/${otherTeam}`;
    const isMatchWon = teamName === match.matchWinner;
    return (
      <div className={isMatchWon ? 'MatchSmallCard won-card' : 'MatchSmallCard lost-card'}>
        {/* <h4>Match Details</h4> */}
        <span className="vs">vs</span>
        <h1><Link to={ otherTeamRoute }> {otherTeam}</Link></h1>
          <p className="match-result">{match.matchWinner} won by {match.resultMargin} {match.result}</p>
        </div>
      );
    }