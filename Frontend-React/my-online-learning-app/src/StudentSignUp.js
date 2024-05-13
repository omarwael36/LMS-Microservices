// src/StudentSignUp.js
import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';

const StudentSignUp = ({ location }) => {
  const [name, setName] = useState('');
  const [affiliation, setAffiliation] = useState('');
  const [bio, setBio] = useState('');
  const history = useHistory();
  const userID = location.state.userID;
  

  const handleSignUp = async () => {
    try {
      await axios.post('http://localhost:8080/User_Servicer-1.0-SNAPSHOT/api/user/StudentSignUp', {
        userID: userID,
        name: name,
        affiliation: affiliation,
        bio: bio
      });
      history.push('/login');
    } catch (error) {
      console.error('Error signing up as student:', error);
    }
  };

  return (
    <div>
      <h2>Student Sign Up</h2>
      <form>
        <div>
          <label>Name:</label>
          <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
        </div>
        <div>
          <label>Affiliation:</label>
          <input type="text" value={affiliation} onChange={(e) => setAffiliation(e.target.value)} />
        </div>
        <div>
          <label>Bio:</label>
          <textarea value={bio} onChange={(e) => setBio(e.target.value)} />
        </div>
        <button type="button" onClick={handleSignUp}>Sign Up</button>
      </form>
    </div>
  );
};

export default StudentSignUp;
