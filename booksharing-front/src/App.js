import React, { useEffect } from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import './App.css'
import Navbar from './Components/Navbar/Navbar'
import Auth from './Components/Authenticate/Auth'
import Homepage from './Components/Homepage/Homepage'
//setup redux
import { Provider } from 'react-redux'
import { getUser } from './actions/auth'
import store from './store'

function App() {

  useEffect(() => {
    store.dispatch(getUser())
  }, [])

  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Navbar />
          <Switch>
            <Route path="/" exact component={Homepage} />
            <Route path="/auth" component={Auth} />
          </Switch>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
