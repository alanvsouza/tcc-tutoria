import React, {Component, useState} from 'react';
import {
  Text,
  StyleSheet,
  TextInput,
  View,
  TouchableOpacity,
  ImageBackground,
} from 'react-native';

import {Picker} from '@react-native-community/picker';

const styles = StyleSheet.create({
  container: {
    alignSelf: 'stretch',
    paddingHorizontal: 40,
  },
  image: {
    flex: 1,
    resizeMode: 'cover',
    justifyContent: 'center',
  },
  title: {
    fontSize: 25,
    fontWeight: 'bold',
    textAlign: 'center',
  },
  subtitle: {
    fontSize: 20,
    color: 'white',
    textAlign: 'center',
    marginBottom: 20,
  },
  input: {
    height: 40,
    borderColor: 'black',
    backgroundColor: 'white',
    marginBottom: 20,
    opacity: 0.9,
    padding: 10,
  },
  buttonSubmit: {
    padding: 10,
    marginBottom: 50,
    backgroundColor: 'black',
    color: 'white',
  },
  buttonSubmitText: {
    fontSize: 18,
    textAlign: 'center',
    color: 'white',
  },
  picker: {
    height: 40,
    opacity: 0.9,
    backgroundColor: 'white',
    marginBottom: 20,
    color: '#A9A9A9',
  },
});

const cursos = [
  'Informática',
  'Geodésia e Cartografia',
  'Qualidade',
  'Edificações',
  'Mecânica',
];

const anosIngresso = ['2020', '2019', '2018'];

class RegisterForm extends Component {
  state = {
    curso: 0,
    anoIngresso: 0,
  };

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.title}>Sistema de Cadastro</Text>
        <Text style={styles.subtitle}>Cotil Amigável</Text>

        <TextInput
          style={styles.input}
          placeholder="Nome"
          placeholderTextColor="#A9A9A9"
        />
        <TextInput
          style={styles.input}
          placeholder="Email"
          placeholderTextColor="#A9A9A9"
        />

        <Picker
          selectedValue={this.state.curso}
          style={styles.picker}
          onValueChange={(itemValue, itemIndex) =>
            this.setState({curso: itemValue})
          }>
          {cursos.map((item, index) => {
            return <Picker.Item label={item} value={index} />;
          })}
        </Picker>

        <Picker
          selectedValue={this.state.anoIngresso}
          style={styles.picker}
          onValueChange={(itemValue, itemIndex) =>
            this.setState({anoIngresso: itemIndex})
          }>
          {anosIngresso.map((item, index) => {
            return <Picker.Item label={item} value={index} />;
          })}
        </Picker>

        <TextInput
          style={styles.input}
          placeholder="Login"
          placeholderTextColor="#A9A9A9"
        />
        <TextInput
          style={styles.input}
          placeholder="Senha"
          placeholderTextColor="#A9A9A9"
        />

        <TouchableOpacity style={styles.buttonSubmit}>
          <Text style={styles.buttonSubmitText}>Cadastrar</Text>
        </TouchableOpacity>
      </View>
    );
  }
}

const RegisterScreen = () => {
  return (
    <ImageBackground
      style={styles.image}
      source={require('../media/background.jpeg')}>
      <RegisterForm />
    </ImageBackground>
  );
};

export default RegisterScreen;
