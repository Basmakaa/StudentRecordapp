import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

class Home extends Component {
    render() {
        return (
            <div>
                <AppNavbar />
                <Container fluid>
                <a href="/students">
                    <Button color="link">
                        Students
                    </Button>
                </a>
                </Container>
            </div>
        );
    }
}

export default Home;


