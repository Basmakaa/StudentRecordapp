import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';

class StudentList extends Component {
    constructor(props) {
        super(props);
        this.state = { students: [], isLoading: true };
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.fetchStudents();
    }

    async fetchStudents() {
        try {
            const response = await fetch('http://localhost:8080/students');
            if (!response.ok) {
                throw new Error(`Error fetching students: ${response.statusText}`);
            }
            const data = await response.json();
            this.setState({ students: data, isLoading: false });
        } catch (error) {
            console.error('Error fetching students:', error);
            this.setState({ isLoading: false });
        }
    }

    async remove(id) {
        try {
            console.log(`Attempting to delete student with id: ${id}`);
            const response = await fetch(`http://localhost:8080/students/${id}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error(`Error deleting student: ${response.statusText}`);
            }

            console.log(`Student with id: ${id} successfully deleted`);
            const updatedStudents = this.state.students.filter(student => student.id !== id);
            this.setState({ students: updatedStudents });
        } catch (error) {
            console.error('Error deleting student:', error);
        }
    }

    render() {
        const { students, isLoading } = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const studentList = students.map(student => (
            <tr key={student.id}>
                <td style={{ whiteSpace: 'nowrap' }}>{student.name}</td>
                <td>{student.dateOfBirth}</td>
                <td>
                    <ButtonGroup>
                        <a href={`/students/${student.id}`}>
                            <Button color="primary">Edit</Button>
                        </a>
                        <Button size="sm" color="danger" onClick={() => this.remove(student.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        ));

        return (
            <div>
                <AppNavbar />
                <Container fluid>
                    <div className="float-right">
                        <a href="/students/new">
                            <Button color="success">Add Student</Button>
                        </a>
                    </div>
                    <h3>Students</h3>
                    <Table className="mt-4">
                        <thead>
                            <tr>
                                <th width="30%">Name</th>
                                <th width="30%">Date of Birth</th>
                                <th width="40%">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {studentList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default StudentList;
