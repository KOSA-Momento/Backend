package com.momento.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberFormDto {

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    //@Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @NotEmpty(message = "전화번호는 필수 입력 값입니다.")
    private String phoneNumber;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;

    @NotEmpty(message = "인스타 아이디는 필수 입력 값입니다.")
    private String instaId;

}
