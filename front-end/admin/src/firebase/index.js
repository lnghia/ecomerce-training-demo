import firebase from 'firebase/app'
import 'firebase/storage'

const firebaseConfig = {
    apiKey: "AIzaSyD97BBTQVPbYybSbILU-2UDhi74L40QCSI",
    authDomain: "zippy-catwalk-327613.firebaseapp.com",
    projectId: "zippy-catwalk-327613",
    storageBucket: "zippy-catwalk-327613.appspot.com",
    messagingSenderId: "683891169025",
    appId: "1:683891169025:web:872a370b182c0e75706d00",
    measurementId: "G-YYS4G1L2QM"
};

firebase.initializeApp(firebaseConfig);
const storage = firebase.storage()

export default storage