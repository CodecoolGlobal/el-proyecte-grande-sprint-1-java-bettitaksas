import {useState} from 'react';

function Login(){
    const [showLoginPanel,setShowLoginPanel] = useState(true);

    function handleLoginClick(){
        setShowLoginPanel(false)
    }
return(
    <div className="loginWrapper">
      {showLoginPanel && (
        <div className="loginPanel">
          <input type="text" placeholder="Username" />
          <input type="password" placeholder="Password" />
          <button onClick={handleLoginClick}>Login</button>
        </div>
      )}
    </div>

)
}

export default Login;