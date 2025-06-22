package umc.study.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import umc.study.domain.Member;
import umc.study.web.dto.MemberResponseDTO;

import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);

    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}
