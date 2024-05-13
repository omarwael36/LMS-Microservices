// src/UserSignUp.js
import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';

const UserSignUp = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('');
  const history = useHistory();

  const handleSignUp = async () => {
    try {
      const response = await axios.post('http://localhost:8080/User_Servicer-1.0-SNAPSHOT/api/user/UserSignUp', {
        email: email,
        password: password
      });
      if (role === 'student') {
        history.push('/student-signup', { userID: response.data.userID });
      } else if (role === 'instructor') {
        history.push('/instructor-signup', { userID: response.data.userID });
      }
    } catch (error) {
      console.error('Error signing up:', error);
    }
  };

  return (
    <div>
      <h2>User Sign Up</h2>
      <form>
        <div>
          <label>Email:</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>
        <div>
          <label>Password:</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        <div>
          <label>Role:</label>
          <input type="radio" name="role" value="student" onChange={(e) => setRole(e.target.value)} />
          <label>Student</label>
          <input type="radio" name="role" value="instructor" onChange={(e) => setRole(e.target.value)} />
          <label>Instructor</label>
        </div>
        <button type="button" onClick={handleSignUp}>Sign Up</button>
      </form>
    </div>
  );
};

export default UserSignUp;
