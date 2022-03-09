package com.semi.flix.animation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semi.flix.comment.CommentDto;
import com.semi.flix.animation.AnimationDto;

@Controller
public class AnimationController {
	@Resource(name="animationService")
	AnimationService service;
	
	@RequestMapping("/animation/list")
	String animation_list(Model model, AnimationDto dto) {
		dto.setPageSize(12);
		dto.setStart(dto.getPg() * dto.getPageSize());

		List<AnimationDto> list = service.getList(dto);
		int cnt = service.getTotal(dto);
		
		
		model.addAttribute("animationList", list);
		model.addAttribute("totalCnt", cnt);
		
		

		return "animation/animation_list";
	}
	
	// 웹툰 상세보기 페이지
		@RequestMapping("/animation/view")
		String animation_view(AnimationDto dto, CommentDto Cdto, Model model) {

			Cdto.setPageSize(3);
			Cdto.setStart(Cdto.getPg() * Cdto.getPageSize());
			
			List<CommentDto> list = service.commentList(Cdto);
			AnimationDto resultDto = service.getView(dto);
			int commentCnt = service.commentTotal(Cdto);
			model.addAttribute("animationDto", resultDto);
			model.addAttribute("commentList", list);
			model.addAttribute("commentTotalCnt", commentCnt);
			return "animation/animation_view";
		}
		
		
		
		
}
