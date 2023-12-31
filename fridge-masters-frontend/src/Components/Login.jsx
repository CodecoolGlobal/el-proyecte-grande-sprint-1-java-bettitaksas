import { useState } from 'react';
import { Link } from 'react-router-dom';

function Login({LOGGED_IN_USER}){
  
const [loginStatus, setLoginStatus] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch(`/api/user/login`, {
      method: 'PUT',
      headers: {
          'Content-Type': 'application/json',
      },
      body: JSON.stringify({ login: LOGGED_IN_USER[0], username: LOGGED_IN_USER[0], password: LOGGED_IN_USER[1], role: 1 }),
  })
  .then(res => res.json())
  .then(responseEntity => {
    LOGGED_IN_USER[2] = responseEntity.fridgeId;
    LOGGED_IN_USER[3] = responseEntity.token;
  });
  setLoginStatus(true);
  console.log("You logged in successfully!")
  };
  function handleUsername(username){
    LOGGED_IN_USER[0] = username;
  }
  function handlePassword(password){
    LOGGED_IN_USER[1] = password;
  }

return loginStatus ? (
  <div className='login'>
      <div className='outer_container'>
          <div className='feedback_box'>
                  <h2>Congrats, you logged in successfully!</h2>
              
              
              <button 
                  onClick={() => {
                    setLoginStatus(false);
                  }}
              >
                   <Link className='back_btn' to='/fridges'>Let's see your fridge!</Link>
              </button>
          </div>
      </div>
  </div>
) : (

<section className="login">
      <div className="login_box">
        <div className="left">
          <div className="top_link">
            <a href="/">
              <img
                src="https://drive.google.com/u/0/uc?id=16U__U5dJdaTfNGobB_OpwAJ73vM50rPV&export=download"
                alt=""
              />
              Return home
            </a>
          </div>
          <div className="contact">
          <form action="" onSubmit={handleSubmit}>
              <h3>SIGN IN</h3>
              <input type="text" placeholder="USERNAME" onInput={(e)=> handleUsername(e.target.value)} />
              <input type="password" placeholder="PASSWORD" onInput={(e)=> handlePassword(e.target.value)}/>
              <button className="submit" onClick={handleSubmit}>LET'S GO</button>
            </form>
          </div>
        </div>
        <div className="right">
          <div className="right-text">
            <h2>FridgeMasters</h2>
            <h5>Fridge Masters</h5>
          </div>
          {/* <div className="right-inductor">
          <img
              src=""
              alt=""
            />
          </div> */}
        </div>
      </div>
    </section>
)
}

export default Login;