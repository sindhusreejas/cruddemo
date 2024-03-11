// ... (other imports and styling remain unchanged)

import {useState} from "react";
import axios from 'axios';

function App() {
    const [dataFromServer, setDataFromServer] = useState(null);
    const [newDataInput, setNewDataInput] = useState("");
    const [newDataInputPut, setNewDataInputPut] = useState("");
    const [newDataInputDelete, setNewDataInputDelete] = useState("");

    // Separate state variables for each form
    const [showPostForm, setShowPostForm] = useState(false);
    const [showPutForm, setShowPutForm] = useState(false);
    const [showDeleteForm, setShowDeleteForm] = useState(false);

    const handleButtonClick = async (button) => {
        try {
            switch (button) {
                case 'button1':
                    // Fetch data only when Button 1 is clicked
                    const response = await fetch('http://localhost:8080/api/employees');
                    const data = await response.json();
                    setDataFromServer(data);
                    setShowPostForm(false);
                    setShowPutForm(false);
                    setShowDeleteForm(false); // Hide the input form
                    break;

                case 'button2':
                    // Show input text box when Button 2 is clicked
                    setShowPostForm(true);
                    setShowPutForm(false);
                    setShowDeleteForm(false);
                    setDataFromServer(null); // Clear previous data
                    break;

                case 'button3':
                    // Implement logic for Button 3 if needed
                    setShowPostForm(false);
                    setShowPutForm(true);
                    setShowDeleteForm(false);
                    setDataFromServer(null); // Clear previous data
                    break;

                case 'button4':
                    setShowPostForm(false);
                    setShowPutForm(false);
                    setShowDeleteForm(true);
                    setDataFromServer(null);// Implement logic for Button 4 if needed
                    break;

                default:
                    break;
            }
        } catch (error) {
            console.error(`Error handling ${button} click:`, error);
        }
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        try {

            const formattedData = JSON.parse(newDataInput);

            console.log('newDataInput:', formattedData); // Check the value here

            // Insert data when the form is submitted
            await axios.post('http://localhost:8080/api/employees', formattedData,{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            // Optionally, you can clear the input after posting
            setNewDataInput('');
            setShowPostForm(false); // Hide the input form after posting
        } catch (error) {
            console.error('Error posting data:', error);
        }
    };

    const handlePutFormSubmit = async (e) => {
        e.preventDefault();
        try {
            const formattedPutData = JSON.parse(newDataInputPut);

            // Check if newDataInputPut is not empty
            if (!newDataInputPut.trim()) {
                console.error('Error: Input for PUT request is empty.');
                return;
            }

            console.log('newDataPutInput:', formattedPutData);

            await axios.put('http://localhost:8080/api/employees', formattedPutData, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            // Rest of the code...
        } catch (error) {
            console.error('Error Updating data:', error);
        }
    };
    const handleDeleteFormSubmit = async (e) => {
        e.preventDefault();
        try {
            // Check if newDataInputDelete is not empty
            if (!newDataInputDelete.trim()) {
                console.error('Error: Input for DELETE request is empty.');
                return;
            }

            console.log('newDataDelInput:', newDataInputDelete);

            const employeeId = newDataInputDelete; // Assuming newDataInputDelete is the employee ID
            const URL = `http://localhost:8080/api/employees/${employeeId}`;

            // Insert data when the form is submitted
            await axios.delete(URL, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            // Optionally, you can clear the input after posting
            setNewDataInputDelete('');
            setShowDeleteForm(false); // Hide the input form after posting
        } catch (error) {
            console.error('Error Deleting data:', error);
        }
    };


    return (
        <div className="App">
            <h1>React CRUD Application</h1>
            <div>
                <button onClick={() => handleButtonClick('button1')}>Button 1</button>
                <button onClick={() => handleButtonClick('button2')}>Button 2</button>
                <button onClick={() => handleButtonClick('button3')}>Button 3</button>
                <button onClick={() => handleButtonClick('button4')}>Button 4</button>
            </div>

            {dataFromServer && (
                <div className="DataDisplay">
                    <h2>Data from Server</h2>
                    <pre>{JSON.stringify(dataFromServer, null, 2)}</pre>
                </div>
            )}

            {showPostForm && (
                <div className="postDataForm">
                    <h2>Post Data</h2>
                    <form onSubmit={handleFormSubmit}>
                        <label>
                            New Data:
                            <input
                                type="text"
                                value={newDataInput}
                                onChange={(e) => setNewDataInput(e.target.value)}
                            />
                        </label>
                        <button type="submit">Post Data</button>
                    </form>
                </div>
            )}
            {showPutForm && (
                <div className="DataFormPut">
                    <h2>Put Data</h2>
                    <form onSubmit={handlePutFormSubmit}>
                        <label>
                            Update Data:
                            <input
                                type="text"
                                value={newDataInputPut}
                                onChange={(e) => setNewDataInputPut(e.target.value)}
                            />
                        </label>
                        <button type="submit">Put Data</button>
                    </form>
                </div>
            )}
            {showDeleteForm && (
                <div className="DataFormDel">
                    <h2>Delete Data</h2>
                    <form onSubmit={handleDeleteFormSubmit}>
                        <label>
                            Delete Data:
                            <input
                                type="text"
                                value={newDataInputDelete}
                                onChange={(e) => setNewDataInputDelete(e.target.value)}
                            />
                        </label>
                        <button type="submit">Delete Data</button>
                    </form>
                </div>
            )}
        </div>
    );
}

export default App;
