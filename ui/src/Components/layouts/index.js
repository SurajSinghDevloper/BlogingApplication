import React from 'react'
import NavBar from './NavBar'
import Footer from './Footer'

const Layouts = (props) => {
  return (
    <>
    <NavBar/>
    {props.children}
    <Footer/>
    </>
  )
}

export default Layouts