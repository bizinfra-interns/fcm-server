import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import util.FcmUtil;

import javax.annotation.Resource;

@SpringBootTest
public class FcmUtilTest {

    @Resource
    private FcmUtil fcmUtil;

    @Test
    public void 푸시알림_발신() {
        String tokenId = "ep1RekaVSMSOUdokM0FYDE:APA91bEkwKdd-99fzOU1FK7NGvoSrq88G4AIvLDNdtG-zz2GyDoqfaSE_uFKJ47K9ZKjIt0CqQhkhjSx-myV0P0gMp4tKWgdS_Vql72-b7d9eIhVBZjdsnWm9sjuhP-NGtQFnYy9fMK3";
        String title = "제목입니다";
        String content = "내용입니다";

        fcmUtil.sendFCM(tokenId, title, content);
    }
}
