function Login(){
  const handleSubmit = (event) => {
    event.preventDefault();
    window.location.href = '/';
  };

return(
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
              <input type="text" placeholder="USERNAME" />
              <input type="password" placeholder="PASSWORD" />
              <button className="submit">LET'S GO</button>
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