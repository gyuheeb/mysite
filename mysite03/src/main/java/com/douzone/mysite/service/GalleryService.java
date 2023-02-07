package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryRepository galleryrepository;
	

	public void removeImage(Long no) {
		galleryrepository.removeImage(no);
	}
	public void addImage(GalleryVo vo) {
		galleryrepository.addImage(vo);
	}

	public List<GalleryVo> getImages() {
		
		return galleryrepository.getImages();
	}
}
