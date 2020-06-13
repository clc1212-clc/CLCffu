package com.myproject.BookManage.controller;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.BookManage.controller.ex.UserIsNotManageException;
import com.myproject.BookManage.entity.BookEntity;
import com.myproject.BookManage.service.BookService;
import com.myproject.BookManage.util.JsonResult;
import com.myproject.BookManage.util.PageData;


/**
 * 书本类的控制层
 * @author clc
 *
 */
@RestController
@RequestMapping("books")
public class BookController extends BaseController{

	@Autowired
	private BookService service;
	
	// http://localhost:8080/books/addnew?title=aaa
	/**
	 * 图书入库的处理
	 * @param bookEntity 图书实体类
	 * @return JsonResult
	 */
	@RequestMapping("addnew")
	public JsonResult<Void> addNewBook (BookEntity bookEntity,HttpSession session) {
		Integer isManage = getisManageFromSession(session);
		if(isManage == 0) {
			throw new UserIsNotManageException("操作失败!用户权限不足!");
		}
		service.addBook(bookEntity);
		return new JsonResult<Void>(OK);
	}
	
	@RequestMapping("showAllBooks")
	public JsonResult<List<BookEntity>> showAllBooks(){
		List<BookEntity> result = service.findAllBooks();
		return new JsonResult<List<BookEntity>>(OK,result);
	}
	
	@GetMapping("page")
	public JsonResult<PageData> showListByPage(Integer page) {
		PageData pageData = service.showListByPage(page);
		return new JsonResult<>(OK, pageData);
	}
	
	@GetMapping("searchPage")
	public JsonResult<PageData> fuzzySearchBookPage(String keyword, Integer page) {
		PageData pageData = service.fuzzySearchBookPage(keyword, page);
		System.err.println("aaaaaaaaaaaaa:" + pageData.getKeyword());
		return new JsonResult<>(OK, pageData);
	}
	
	@RequestMapping("{bid}/deleteBook")
	public JsonResult<Void> deleteBook(@PathVariable("bid") Integer bid, HttpSession session){
		Integer isManage = getisManageFromSession(session);
		if(isManage == 0) {
			throw new UserIsNotManageException("操作失败!用户权限不足!");
		}
		System.err.println(bid);
		System.out.println("BookController.deleteBook()");
		service.deleteBook(bid);
		return new JsonResult<Void>(OK);
	}
	
	@GetMapping("getNextBid")
	public Integer getNextBid() {
		return service.getNextBid();
	}
	
	// http://localhost:8080/books/getBookInfo?bid=6
	@GetMapping("getBookInfo")
	public JsonResult<BookEntity> getBookInfo(Integer bid){
		BookEntity book = service.getBookInfo(bid);
		return new JsonResult<BookEntity>(OK,book);
	}
	
	@PostMapping("updateBookInfo")
	public JsonResult<Void> updateBookInfo(BookEntity book, HttpSession session){
		Integer isManage = getisManageFromSession(session);
		if(isManage == 0) {
			throw new UserIsNotManageException("操作失败!用户权限不足!");
		}
		System.out.println(book.toString());
		service.updateBookInfo(book);
		return new JsonResult<Void>(OK);
	}
	
	// http://localhost:8080/books/addBorrowed?bids=11&bids=13&bids=14&bids=15
	@RequestMapping("addBorrowed")
	public JsonResult<Void> addBorrowed(Integer[] bids, HttpSession session){
		System.out.println("!!!!!" + bids.toString());
		Integer uid = getUidFromSession(session);
		service.addBorrowedBookAndUser(bids, uid);
		return new JsonResult<Void>(OK);
	}
	
	// http://localhost:8080/books/showConfirmBook?bids=11&bids=13&bids=14&bids=15
	@GetMapping("showConfirmBook")
	public JsonResult<List<BookEntity>> showConfirmBook(Integer[] bids){
		System.err.println("\t参数bids=" + Arrays.toString(bids));
		List<BookEntity> result = service.showConfirmedBook(bids);
		return new JsonResult<List<BookEntity>>(OK,result);
	}
	
	@GetMapping("fuzzySearch")
	public JsonResult<List<BookEntity>> fuzzySearchBook(String keyword){
		List<BookEntity> result = service.fuzzySearchBook(keyword);
		return new JsonResult<List<BookEntity>>(OK,result);
	}
}
