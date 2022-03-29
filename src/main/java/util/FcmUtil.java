package util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
public class FcmUtil {
    public void sendFCM(String tokenId, String title, String content) {
        try {
            ClassPathResource resource = new ClassPathResource("static/google-services.json");
            FileInputStream refreshToken = (FileInputStream) resource.getInputStream();

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl("https://fcmtestapp-e505a-default-rtdb.firebaseio.com/")
                    .build();

            // Firebase 처음 호출 시에만 initializing 처리
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            // String registrationToken = 안드로이드 토큰
            String registrationToken = tokenId;

            // message 작성
            Message msg = Message.builder()
                    .setAndroidConfig(AndroidConfig.builder()
                            .setTtl(3600 * 1000) // 1 hour in milliseconds
                            .setPriority(AndroidConfig.Priority.NORMAL)
                            .setNotification(AndroidNotification.builder()
                                    .setTitle(title)
                                    .setBody(content)
                                    .setColor("#f35342")
                                    .build())
                            .build())
                    .setToken(registrationToken)
                    .build();

            // 메시지를 FirebaseMessging에 전송
            String response = FirebaseMessaging.getInstance().send(msg);
            System.out.println("Sueccessfully sent message: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
