import React, {Component} from 'react';
import {
  Text,
  StyleSheet,
  TextInput,
  View,
  TouchableOpacity,
  ImageBackground,
} from 'react-native';

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
  input: {
    height: 40,
    borderColor: 'black',
    backgroundColor: 'white',
    marginBottom: 15,
    opacity: 0.9,
    padding: 10,
  },
  forgotPassword: {
    marginBottom: 20,
  },
  forgotPasswordText: {},
  buttonSubmit: {
    padding: 10,
    marginBottom: 25,
    backgroundColor: 'black',
    color: 'white',
  },
  buttonSubmitText: {
    fontSize: 18,
    textAlign: 'center',
    color: 'white',
  },
  registerText: {
    fontSize: 15,
    color: 'white',
    textAlign: 'center',
  },
});

const LoginForm = ({navigation}) => {
  return (
    <View style={styles.container}>
      <TextInput
        style={styles.input}
        placeholder="Login"
        placeholderTextColor="gray"
      />
      <TextInput
        style={styles.input}
        placeholder="Senha"
        placeholderTextColor="gray"
      />

      <TouchableOpacity style={styles.forgotPassword}>
        <Text style={styles.forgotPasswordText}>Esqueci minha senha</Text>
      </TouchableOpacity>

      <TouchableOpacity style={styles.buttonSubmit}>
        <Text style={styles.buttonSubmitText}>Entrar</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={styles.register}
        onPress={() => {
          navigation.navigate('Register');
        }}>
        <Text style={styles.registerText}>Ainda não está cadastrado?</Text>
        <Text style={styles.registerText}>Crie uma conta!</Text>
      </TouchableOpacity>
    </View>
  );
};

const LoginScreen = ({navigation}) => {
  return (
    <ImageBackground
      style={styles.image}
      source={require('../media/background.jpeg')}>
      <LoginForm navigation={navigation} />
    </ImageBackground>
  );
};

export default LoginScreen;
