import { React, useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';

import './TeamPage.scss';
import { PieChart } from 'react-minimal-pie-chart';

export const TeamPage = () =>{

    const [team, setTeam] = useState({matches: []});
    const { teamName }  = useParams();

    // making useEffect async is not allowed
    useEffect(
        () => {
            const fetchTeam = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}`);
                const data = await response.json();
                console.log(data);
                setTeam(data);
            };
            fetchTeam();

        // eslint-disable-next-line    
        }, [teamName]
    );

    if (!team || !team.teamName ) {
        return <h1> Team Not Found </h1>
    }

    return (
      <div className="TeamPage">
        <div className="team-name-section">
          <h1 className="team-name">{team.teamName}</h1>
        </div>
        <div className="win-loss-section">
          Wins / Losses
          <PieChart
              data={[
                { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#e15454' },
                { title: 'Wins', value: team.totalWins, color: '#407E4D' }
              ]}
          />
        </div>
        <div className="match-detail-section">
          <h3>Latest Matches</h3>
          <MatchDetailCard teamName={team.teamName} match = {team.matches[0]}/>
        </div>
        {team.matches.slice(1).map(match => <MatchSmallCard teamName={team.teamName} match={match} />)}
        <div className="more-link">
          <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More &gt;</Link>
        </div>
      </div>
    );
  }

  