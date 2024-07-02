import './App.css';
import React, { useRef, useState } from 'react';

function App() {
  const [title, setTitle] = useState('child');
  const [show, setShow] = useState(true);
  const reactChildRef = useRef(null);
  
  return (
    <div className="app">
      <h1>root</h1>
      <input onChange={(e) => setTitle(e?.target?.value)} />
      <button onClick={() => setShow(!show)}>Mostrar</button>
      <react-child ref={reactChildRef} title={title}></react-child>
    </div>
  );
}
export default App;