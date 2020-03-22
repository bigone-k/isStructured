package group.bigone.api.controller.v1;

import group.bigone.api.Service.BoardService;
import group.bigone.api.Service.ResponseService;
import group.bigone.api.entity.Board;
import group.bigone.api.entity.Post;
import group.bigone.api.model.dto.ParamsPost;
import group.bigone.api.model.response.CommonResult;
import group.bigone.api.model.response.ListResult;
import group.bigone.api.model.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Board"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/board")
public class BoardController {

    private final BoardService boardService;
    private final ResponseService responseService;

    @ApiOperation(value = "게시판 정보 조회", notes = "게시판 정보를 조회한다.")
    @GetMapping(value = "/{boardName}")
    public SingleResult<Board> boardInfo(@PathVariable String boardName) {
        return responseService.getSingleResult(boardService.findBoard(boardName));
    }

    @ApiOperation(value = "게시판 글 리스트", notes = "게시판 게시글 리스트를 조회한다.")
    @GetMapping(value = "/{boardName}/posts")
    public ListResult<Post> posts(@PathVariable String boardName) {
        return responseService.getListResult(boardService.findPosts(boardName));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시판 글 작성", notes = "게시판에 글을 작성한다.")
    @PostMapping(value = "/{boardName}")
    public SingleResult<Post> post(@PathVariable String boardName, @Valid @ModelAttribute ParamsPost post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        return responseService.getSingleResult(boardService.writePost(userId, boardName, post));
    }

    @ApiOperation(value = "게시판 글 상세", notes = "게시판 글 상세정보를 조회한다.")
    @GetMapping(value = "/post/{postId}")
    public SingleResult<Post> post(@PathVariable long postId) {
        return responseService.getSingleResult(boardService.getPost(postId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시판 글 수정", notes = "게시판의 글을 수정한다.")
    @PutMapping(value = "/post/{postId}")
    public SingleResult<Post> post(@PathVariable long postId, @Valid @ModelAttribute ParamsPost post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        return responseService.getSingleResult(boardService.updatePost(postId, userId, post));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "게시판 글 삭제", notes = "게시판의 글을 삭제한다.")
    @DeleteMapping(value = "/post/{postId}")
    public CommonResult deletePost(@PathVariable long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        boardService.deletePost(postId, userId);

        return responseService.getSuccessResult();
    }
}