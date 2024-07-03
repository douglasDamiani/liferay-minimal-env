// import './App.css';
import React, { useRef, useState } from 'react';

function App() {
  const [title, setTitle] = useState('child');
  const [show, setShow] = useState(true);
  const reactChildRef = useRef(null);
  
  return (
    <div className="app">
      <h1>root ola</h1>
    </div>
  );
}
export default App;