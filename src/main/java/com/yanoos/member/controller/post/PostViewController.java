package com.yanoos.member.controller.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yanoos.global.entity.board.Board;
import com.yanoos.global.entity.board.Post;
import com.yanoos.global.util.AuthUtil;
import com.yanoos.member.controller.dto.GetPostDetailSiteOut;
import com.yanoos.member.service.business_service.board.BoardBusinessService;
import com.yanoos.member.service.business_service.post.PostBusinessService;
import com.yanoos.member.controller.dto.AsideMenu;
import com.yanoos.member.controller.dto.GetPostsOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/view/posts")
public class PostViewController {
    private final PostBusinessService postBusinessService;
    private final BoardBusinessService boardBusinessService;
    private final AuthUtil authUtil;
    private final String TITLE = "Posts";
    @GetMapping()
    public String getPosts(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size,
            Model model){
        Pageable pageable = PageRequest.of(page, size);
        GetPostsOut mapMemberPosts = postBusinessService.getMapMemberPostsByMemberId(authUtil.getMemberId(), pageable);

        makePostsModel(model, mapMemberPosts);
        model.addAttribute("mapMemberPosts", mapMemberPosts.getMapMemberPostOuts());

        return "post/posts";
    }

    @GetMapping("/{boardId}/board")
    public String getPostsByBoardid(
            @PathVariable("boardId") Long boardId,
            @RequestParam(defaultValue = "0", name = "page") int page,
            Model model){
        Board board = boardBusinessService.getBoardByBoardId(boardId);
        int size = board.getPageSize();

        GetPostsOut boardPosts = boardBusinessService.getBoardPostsByBoardId(boardId, page, size);
        makePostsModel(model, boardPosts);
        model.addAttribute("posts", boardPosts.getPostOuts());
        model.addAttribute("board", board.toDto());

        return "board/posts";
    }
    private void makePostsModel(Model model, GetPostsOut mapMemberPosts) {
        model.addAttribute("asideMenus",getAsideMenus());
        model.addAttribute("title",TITLE);
        model.addAttribute("currentPage", mapMemberPosts.getCurrentPage());
        model.addAttribute("totalPages", mapMemberPosts.getTotalPages());
        model.addAttribute("totalElements", mapMemberPosts.getTotalElements());
    }



    @GetMapping("/{postId}/detail")
    public String getPostDetail(@PathVariable("postId") Long postId, Model model){
        Post post = postBusinessService.getPostByPostId(postId);
        model.addAttribute("content",post.getContent());

        return "post/post_detail";
    }

    @GetMapping("/{postId}/site")
    public String getPostDetailSite(@PathVariable("postId") Long postId, Model model) throws JsonProcessingException {
        Post post = postBusinessService.getPostByPostId(postId);
        GetPostDetailSiteOut postDetailSiteOut = new GetPostDetailSiteOut().from(post);
        model.addAttribute("post",postDetailSiteOut);


        return "post/move_detail_site";
    }

    @PostMapping("/{mapMemberPostId}/checked")
    public String putPost(@PathVariable("mapMemberPostId") Long mapMemberPostId){
        postBusinessService.updateCheckedByMapMemberPostId(authUtil.getMemberId(),mapMemberPostId);
        return "redirect:/api/view/posts";

    }

    private List<AsideMenu> getAsideMenus(){
        return new ArrayList<>(List.of(new AsideMenu("posts","/api/view/settings/keyword")));
    }
}
