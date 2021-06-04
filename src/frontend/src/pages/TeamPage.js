import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';

export const TeamPage = () =>{

    const [team, setTeam] = useState({matches: []});
    const { teamName }  = useParams();

    // making useEffect async is not allowed
    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}`);
                const data = await response.json();
                setTeam(data);
            };
            fetchMatches();

        // eslint-disable-next-line    
        }, [teamName]
    );

    if (!team || !team.teamName ) {
        return <h1> Team Not Found </h1>
    }

    return (
      <div className="TeamPage">
        <h1>{team.teamName}</h1>
        <MatchDetailCard teamName={team.teamName} match = {team.matches[0]}/>
        {team.matches.slice(1).map(match => <MatchSmallCard match={match} />)}

      </div>
    );
  }

  