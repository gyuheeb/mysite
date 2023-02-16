package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;



@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model,GalleryVo vo) {
		List<GalleryVo> gallerylist = galleryService.getImages();
		System.out.println(gallerylist);
		model.addAttribute("gallerylist",gallerylist);
		return "gallery/index";
	}

	@RequestMapping("/upload")
	public String upload(
		@RequestParam("comments") String comments,
		@RequestParam("file") MultipartFile file,GalleryVo vo,
		Model model) {
		System.out.println("comments:" + comments);
		String url = fileuploadService.restore(file);
		model.addAttribute("url", url);
		System.out.println(url);
		vo.setUrl(url);
		
		galleryService.addImage(vo);
		return "redirect:/gallery";
	}
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no){
		galleryService.removeImage(no);
		return "redirect:/gallery";
	}
}
