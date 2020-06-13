package com.myproject.BookManage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.BookManage.VO.BookEntityVO;
import com.myproject.BookManage.entity.ExtraMoney;
import com.myproject.BookManage.service.BorrowedService;
import com.myproject.BookManage.util.JsonResult;

@RestController
@RequestMapping("borrowed")
public class BorrowedController extends BaseController {

	@Autowired
	BorrowedService service;
	
	@GetMapping("show")
	public JsonResult<List<BookEntityVO>> showBorrowedBook(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<BookEntityVO> result = service.showBookVOByUid(uid);
		for (BookEntityVO bookEntityVO : result) {
			System.err.println(bookEntityVO);
		}
		return new JsonResult<List<BookEntityVO>>(OK,result);
	}
	
	@PostMapping("returnBook")
	public JsonResult<Void> removeBookByBids(Integer bid){
		service.removeBookFromTableByBid(bid);
		return new JsonResult<Void>(OK);
	}
	
}
