package umc.study.web.dto;

import lombok.Getter;
import umc.study.validation.annotation.ExistCategory;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDTO {

        String name;
        Integer gender;
        Integer birthYear;
        Integer birthMonth;
        Integer birthDay;
        String address;
        String specAddress;
        @ExistCategory
        List<Long> preferCategory;
    }
}
