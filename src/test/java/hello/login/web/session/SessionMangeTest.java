package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.*;

class SessionMangeTest {

    SessionManage sessionManage = new SessionManage();

    @Test
    void sessionTest() {

        // 새션 생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManage.createSession(member, response);

        // 요청에 응답 쿠키가 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        // 세션 조회
        Object result = sessionManage.getSession(request);
        assertThat(result).isEqualTo(member);

        // 새션 만료
        sessionManage.expire(request);
        Object expired = sessionManage.getSession(request);
        assertThat(expired).isNull();
    }
}
