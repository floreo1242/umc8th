package umc.study.service.member;

import umc.study.domain.Member;
import umc.study.web.dto.member.MemberRequestDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDTO request);
}
