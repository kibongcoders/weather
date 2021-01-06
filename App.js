import { StatusBar } from 'expo-status-bar';
import React from 'react';
import Loading from './Loading.js';
import * as Location from "expo-location";
import axois from "axios";
import Weather from './Weather.js';

const API_KEY = "a34172bb1388172f7ab4da96a32fd27b";

export default class extends React.Component {
  state = {
    isLoading: true
  };
  getWeather = async(latitude,longitude) => {
    const { data : {
              main : {temp},
                      weather }
                    } = await axois.get(`http://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${API_KEY}&units=metric`);
    this.setState({isLoading:false, 
                    condition : weather[0].main,              
                    temp : temp});
    }
  
  getLocation = async () => {
    try {
      await Location.requestPermissionsAsync();
      const {coords : {latitude , longitude}} = await Location.getCurrentPositionAsync();      
      this.getWeather(latitude,longitude)      
    } catch (error) {
    }
  };

  componentDidMount() {
    this.getLocation();
  }
  render() {
    const {isLoading , temp, condition} = this.state;
    return isLoading ? <Loading /> : <Weather temp={Math.round(temp)}
                                              condition={condition}/>;
  }
}