import { useState } from 'react';
import { Link } from 'react-router-dom';
function Register() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [registrationStatus, setRegistrationStatus] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        fetch(`/api/user/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username: username, password: password }),
        });
        setRegistrationStatus(true);
        console.log('Registration successful!');
    };
    return registrationStatus ? (
        <div className='login'>
            <div className='outer_container'>
                <div className='feedback_box'>
                        <h2>Congrats, you registered successfully!</h2>
                    
                    
                    <button 
                        onClick={() => {
                            setRegistrationStatus(false);
                        }}
                    >
                        <Link className='back_btn' to='/fridges'>Let's see my fridge</Link>
                    </button>
                </div>
            </div>
        </div>
    ) : (
        <section className='login'>
            <div className='login_box'>
                <div className='left'>
                    <div className='top_link'>
                        <a href='/'>
                            <img
                                src='https://drive.google.com/u/0/uc?id=16U__U5dJdaTfNGobB_OpwAJ73vM50rPV&export=download'
                                alt=''
                            />
                            Return home
                        </a>
                    </div>
                    <div className='contact'>
                        <form action='' onSubmit={handleSubmit}>
                            <h3>SIGN UP</h3>
                            <input
                                type='text'
                                placeholder='USERNAME'
                                onChange={(e) => setUsername(e.target.value)}
                            />
                            <input
                                type='password'
                                placeholder='PASSWORD'
                                onChange={(e) => setPassword(e.target.value)}
                            />
                            <button className='submit'>LET'S GO</button>
                        </form>
                    </div>
                </div>
                <div className='right'>
                    <div className='right-text'>
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
    );
}

export default Register;
