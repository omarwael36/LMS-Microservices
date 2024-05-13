// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Login from './Login';
import UserSignUp from './UserSignUp';
import StudentSignUp from './StudentSignUp';
import InstructorSignUp from './InstructorSignUp';

function App() {
  return (
    <Router>
      <div className="App">
        <Switch>
          <Route path="/signup" component={UserSignUp} />
          <Route path="/student-signup" component={StudentSignUp} />
          <Route path="/instructor-signup" component={InstructorSignUp} />
          <Route path="/" component={Login} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
