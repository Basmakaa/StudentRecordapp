
/*
import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    students: []
  };

  async componentDidMount() {
    try {
      const response = await fetch('http://localhost:8080/students');
      const body = await response.json();
      console.log(body); // Add this line to log the response
      this.setState({ students: body });
    } catch (error) {
      console.error('Error fetching students:', error);
    }
  }

  render() {
    const { students } = this.state;
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro">
            <h2>Students</h2>
            {students.length > 0 ? (
              students.map(student => (
                <div key={student.id}>
                  {student.name} ({student.dateOfBirth})
                </div>
              ))
            ) : (
              <p>No students found</p>
            )}
          </div>
        </header>
      </div>
    );
  }
}

export default App;
*/

/* App.js file */ 
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import StudentList from './StudentList';
import StudentEdit from './StudentEdit';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/students" element={<StudentList />} />
                <Route path="/students/:id" element={<StudentEdit />} />
                <Route path="/students/new" element={<StudentEdit />} />
                {/* Add other routes as needed */}
            </Routes>
        </Router>
    );
}

export default App;
