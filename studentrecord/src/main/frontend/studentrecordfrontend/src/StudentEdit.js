import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

const StudentEdit = () => {
    const [item, setItem] = useState({
        name: '',
        dateOfBirth: ''
    });
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id !== 'new') {
            fetch(`http://localhost:8080/students/${id}`)
                .then(response => response.json())
                .then(data => setItem(data))
                .catch(error => console.error('Error fetching student:', error));
        }
    }, [id]);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setItem(prevItem => ({ ...prevItem, [name]: value }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await fetch(`http://localhost:8080/students${item.id ? '/' + item.id : ''}`, {
                method: (item.id) ? 'PUT' : 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(item),
            });

            if (!response.ok) {
                throw new Error('Error creating/updating student: ' + response.statusText);
            }

            navigate('/students');
        } catch (error) {
            console.error('Error creating/updating student:', error);
        }
    };

    const title = <h2>{item.id ? 'Edit Student' : 'Add Student'}</h2>;

    return (
        <div>
            <AppNavbar />
            <Container>
                {title}
                <Form onSubmit={handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={item.name || ''}
                            onChange={handleChange} autoComplete="name" />
                    </FormGroup>
                    <FormGroup>
                        <Label for="dateOfBirth">Date of Birth</Label>
                        <Input type="date" name="dateOfBirth" id="dateOfBirth" value={item.dateOfBirth || ''}
                            onChange={handleChange} autoComplete="dateOfBirth" />
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" onClick={() => navigate('/students')}>Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    );
}

export default StudentEdit;

