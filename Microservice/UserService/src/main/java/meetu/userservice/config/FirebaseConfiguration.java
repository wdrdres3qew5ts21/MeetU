/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import meetu.userservice.controller.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Test
 */
//@Configuration
public class FirebaseConfiguration {

//    @Bean
    public void initFirebaseApp() {
        FileInputStream serviceAccount = null;
        FirebaseApp.getInstance().delete();
        try {
            System.out.println("---------- Firebase Configuration Start ---------------");
            System.out.println(FirebaseApp.DEFAULT_APP_NAME);
            serviceAccount = new FileInputStream("C:\\ProjectCode\\MeetU\\Microservice\\UserService\\meetu-firebase-key.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://meetu-69b29.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FirebaseConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FirebaseConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FirebaseConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serviceAccount.close();
            } catch (IOException ex) {
                Logger.getLogger(FirebaseConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
