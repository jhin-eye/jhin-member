package com.yanoos.member.entity;

import com.yanoos.member.controller.dto.MapMemberPostOut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "map_member_post")
public class MapMemberPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_member_post_id")
    private Long mapMemberPostId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private boolean checked;



    public MapMemberPostOut toDto(){
        return MapMemberPostOut.builder()
                .mapMemberPostId(this.mapMemberPostId)
                .memberOut(member.toDto())
                .postOut(post.toDto())
                .checked(this.checked)
                .build();
    }

    public void updateChecked() {
        this.checked = !this.checked;
    }
}