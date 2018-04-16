import React, {Component} from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    NativeModules,
} from 'react-native';

import One from './component/One';

export default class App extends Component {
 render() {
        return (
            <View style={styles.container}>

                <Text style={styles.welcome} onPress={() => this.onclickBtn()}>
                    跳转
                </Text>

                <Text style={styles.welcome} onPress={() => this.openNativeActivity()}>
                    原生跳转
                </Text>

            </View>
        );
    }



      onclickBtn = () => {
          this.props.navigation.navigate("One", {name: 'hello tom', age: 25})
        }

        openNativeActivity = () => {
            NativeModules.INTENT_MODULE.openActivity("com.liuying.rndemo.activity.FirstActivity", "successful come from RN");
        }


    //onclickBtn = () => {
    //    this.props.navigation.navigate("One", {name: 'hello tom', age: 25})
    //}
    //
    //openNativeActivity = () => {
    //    NativeModules.INTENT_MODULE.openActivity("com.liuying.rndemo.activity.FirstActivity", "successful come from RN");
    //}

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});
