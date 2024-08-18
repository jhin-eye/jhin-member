package com.yanoos.member.controller.post;

import com.yanoos.member.controller.dto.AsideMenu;
import com.yanoos.member.controller.dto.GetPostsOut;
import com.yanoos.member.controller.dto.PostOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/view/posts")
public class PostViewController {
    @GetMapping()
    public String getPosts(Model model){
        List<PostOut> postOutList = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            PostOut postOut = PostOut.builder()
                    .postId((long) i)
                    .boardNameEng("BoardEng_" + i)
                    .boardNameKor("게시판_" + i)
                    .postNo("PostNo_" + i)
                    .postTitle("Sample Post Title " + i)
                    .postWriteDate(LocalDateTime.now().minusDays(i))
                    .postDepartment("Department_" + i)
                    .postUrl("http://example.com/post/" + i)
                    .monitorTime(LocalDateTime.now())
                    .build();

            postOutList.add(postOut);
        }

        AsideMenu asideMenu = new AsideMenu("게시글 목록","/api/view/posts");
        AsideMenu asideMenu2 = new AsideMenu("게시글 목록2","/api/view/posts2");

        GetPostsOut getPostsOut = new GetPostsOut(new ArrayList<>(List.of(asideMenu,asideMenu2)),"게시글 목록",postOutList);
        model.addAttribute("asideMenus",getPostsOut.getAsideMenus());
        model.addAttribute("title",getPostsOut.getTitle());
        model.addAttribute("posts",getPostsOut.getPosts());
        return "posts";
    }
}
