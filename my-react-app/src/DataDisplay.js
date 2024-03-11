import React from 'react';

const DataDisplay = ({ data }) => {
    return (
        <div>
            <h2>Data from Server</h2>
            <pre>{JSON.stringify(data, null, 2)}</pre>
        </div>
    );
};

export default DataDisplay;