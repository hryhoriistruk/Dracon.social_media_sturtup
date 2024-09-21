// For Firebase JS SDK v7.20.0 and later, measurementId is optional
import firebase from 'firebase/compat/app';
import 'firebase/compat/auth';
import 'firebase/compat/firestore';


const firebaseConfig = {
    apiKey: "AIzaSyAqPQ8YDp66Pwlo1NKLQGBQfzrFt0Amf0I",
    authDomain: "twitter-clone-6202d.firebaseapp.com",
    projectId: "twitter-clone-6202d",
    storageBucket: "twitter-clone-6202d.appspot.com",
    messagingSenderId: "637061123627",
    appId: "1:637061123627:web:c57e9b659aab61443ca46e",
    measurementId: "G-1W6YK21W1N"
  };

  // Use this to initialize the firebase App
  const firebaseApp = firebase.initializeApp(firebaseConfig);

  const db = firebaseApp.firestore();
  const auth = firebase.auth();

  export default db