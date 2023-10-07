package com.momento.entity;

import com.momento.constant.Role;
import com.momento.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Members")
@Getter @Setter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "insta_id", unique = true, nullable = false)
    private String instaId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "member")
    private List<LikeItem> likeItems;

    @OneToMany(mappedBy = "member")
    private List<OrderItem> orderItems;


    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setEmail(memberFormDto.getEmail());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setName(memberFormDto.getName());
        member.setNickname(memberFormDto.getNickname());
        member.setPhoneNumber(memberFormDto.getPhoneNumber());
        member.setAddress(memberFormDto.getAddress());
        member.setInstaId(memberFormDto.getInstaId());
        //member.setRole(Role.USER);
        member.setRole(Role.ADMIN);
        return member;
    }

}
