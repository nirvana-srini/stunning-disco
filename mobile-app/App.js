import React from 'react';
import { SafeAreaView, StyleSheet, Text, View } from 'react-native';
import { StatusBar } from 'expo-status-bar';
import { WebView } from 'react-native-webview';

const WEB_APP_URL = process.env.EXPO_PUBLIC_WEB_APP_URL || 'http://localhost:5173';

export default function App() {
  return (
    <SafeAreaView style={styles.container}>
      <StatusBar style="auto" />
      <View style={styles.header}>
        <Text style={styles.title}>Pomodoro Mobile (React Native)</Text>
      </View>
      <WebView source={{ uri: WEB_APP_URL }} style={styles.webview} />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff'
  },
  header: {
    padding: 12,
    borderBottomWidth: 1,
    borderBottomColor: '#ddd'
  },
  title: {
    fontSize: 16,
    fontWeight: '600'
  },
  webview: {
    flex: 1
  }
});
