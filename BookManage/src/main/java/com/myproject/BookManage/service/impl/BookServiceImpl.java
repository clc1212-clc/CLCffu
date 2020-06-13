package com.myproject.BookManage.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.myproject.BookManage.entity.BookEntity;
import com.myproject.BookManage.mapper.BookMapper;
import com.myproject.BookManage.mapper.BorrowedMapper;
import com.myproject.BookManage.service.BookService;
import com.myproject.BookManage.service.ex.AddBookEmptyException;
import com.myproject.BookManage.service.ex.BookAlreadyBorrowed;
import com.myproject.BookManage.service.ex.BookAlreadyExistException;
import com.myproject.BookManage.service.ex.BookAlreadyRemovedException;
import com.myproject.BookManage.service.ex.BookNotExistException;
import com.myproject.BookManage.service.ex.BorrowedBookAlreadyFull;
import com.myproject.BookManage.service.ex.UpdateException;
import com.myproject.BookManage.util.PageData;
import com.myproject.BookManage.service.ex.InsertException;

/**
 * 书本类接口的实现类
 * @author clc
 *
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper mapper;
	@Autowired
	private BorrowedMapper borrowedMapper;	
	@Value("${project.favorite.record-count-per-page}")
	private Integer recordCountPerPage;

	@Override
	public void addBook(BookEntity book) {
		if(book == null) {
			throw new AddBookEmptyException("添加失败!图书信息不能为空");
		}
		// 输出日志，开始执行添加业务
		System.out.println("[执行添加业务]BookServiceImpl.addBook()");
		String title = book.getTitle();
		BookEntity result = findByTitle(title);
		if(result != null) {
			if(whetherExist(result, book)) {
				throw new BookAlreadyExistException("添加失败,图书已经存在!");
			}
		}
		Integer rows = insertBook(book);
		if(rows != 1) {
			throw new InsertException("添加图书失败!数据添加出现未知错误!请联系管理员解决!");
		}
	}



	@Override
	public void deleteBook(Integer bid) {
		System.out.println("[业务层]BookServiceImpl.deleteBook()");
		BookEntity book = findInfoByBid(bid);
		if(book == null) {
			throw new BookAlreadyRemovedException("删除失败!图书已经被删除!");
		}
		Integer rows = deleteByBid(bid);
		if(rows != 1) {
			throw new InsertException("删除失败!删除时出现未知错误!");
		}
		System.err.println(rows);
	}

	@Override
	public List<BookEntity> findAllBooks() {
		List<BookEntity> result = findBooks();
		return result;
	}

	@Override
	public Integer getNextBid() {
		Integer NextBid = 1;
		Integer MaxBid = findMaxBid();
		if(MaxBid != null) {
			NextBid = MaxBid + 1;
			return NextBid;
		}
		return NextBid;
	}

	@Override
	public BookEntity getBookInfo(Integer bid) {
		BookEntity book = findInfoByBid(bid);
		if(book == null) {
			throw new BookNotExistException("无法更改信息,无此图书存在!");
		}
		return book;
	};

	@Override
	public void updateBookInfo(BookEntity book) {
		Integer rows = updateBook(book);
		if(rows != 1) {
			throw new UpdateException("更改图书信息失败!出现未知错误!");
		}
	}

	@Override
	public void addBorrowedBookAndUser(Integer[] bids, Integer uid) {

		Integer count = countByUid(uid);
		Integer result = bids.length;
		System.err.println("************" + count);
		System.err.println("************" + result);
		if(count + result > 5) {
			throw new BorrowedBookAlreadyFull("借书失败!借书数量已经达到5本的上限!您还能借[" + (5-count) + "]本书,请重新选择!");
		}
		Integer rows = 0;
		for (int i = 0 ; i < bids.length ; i++) {
			BookEntity book = findInfoByBid(bids[i]);
			if(book.getIsBorrowed() == 1) {
				throw new BookAlreadyBorrowed("借书失败!"+ book.getTitle() +"已经被借走!");
			}
			rows = beBorrowedByUid(bids[i], uid, new Date());
			if(rows != 1) {
				throw new InsertException("借书时插入数据出现错误!");
			}
			rows = changeBookStatetoBorrowed(bids[i]);
			if(rows != 1) {
				throw new UpdateException("借书时更改数据出现错误!");
			}
		}
	}

	@Override
	public List<BookEntity> showConfirmedBook(Integer[] bids) {
		List<BookEntity> result = findConfirmedBook(bids);
		if(result.size() != bids.length) {
			throw new BookNotExistException("借书信息有误!图书信息发生变动!");
		}
		return result;
	}

	@Override
	public List<BookEntity> fuzzySearchBook(String keyword) {
		List<BookEntity> result = searchByKeyword(keyword);
		return result;
	};

	@Override
	public PageData showListByPage(Integer page) {
		// 创建PageData返回值对象
		PageData pageData = new PageData();
		// 调用countByUid()统计当前圖書的总量
		Integer count = countBooks();
		// 将PageData对象的maxRecordCount设置为总量
		pageData.setMaxRecordCount(count);
		// 判断总量是否为0
		if (count == 0) {
			// 是：直接返回PageData对象
			return pageData;
		}

		// 通过“总量/recordCountPerPage”结合“总量%recordCountPerPage”，计算得到最大页码
		Integer maxPage = count / recordCountPerPage;
		maxPage += count % recordCountPerPage == 0 ? 0 : 1;
		// 判断参数page是否不在 [1, 最大页码] 区间
		if (page == null || page < 1 || page > maxPage) {
			// 是：视为显示第1页，对参数page重新赋值，即page=1
			page = 1;
		}

		// 计算查询时的offset：(page - 1) * recordCountPerPage
		Integer offset = (page - 1) * recordCountPerPage;
		// 基于参数uid，计算得到的offset，全局属性recordCountPerPage，查询数据
		List<BookEntity> books = findBookByPage(offset, recordCountPerPage);
		System.err.println("lllllllllllllllllll=" + books.size());
		// 向PageData对象中封装数据：minPage > 1
		pageData.setMinPage(1);
		// 向PageData对象中封装数据：maxPage > 最大页码
		pageData.setMaxPage(maxPage);
		// 向PageData对象中封装数据：currentPage > 参数page
		pageData.setCurrentPage(page);
		// 向PageData对象中封装数据：recordCountPerPage > 全局属性recordCountPerPage
		pageData.setRecordCountPerPage(recordCountPerPage);
		// 向PageData对象中封装数据：currentRecordCount > 查询得到的收藏数据的列表的长度
		pageData.setCurrentRecordCount(books.size());
		// 向PageData对象中封装数据：listData > 查询得到的收藏数据的列表
		pageData.setListData(books);

		// 返回PageData对象
		return pageData;
	}
	
	@Override
	public PageData fuzzySearchBookPage(String keyword, Integer page) {
		System.err.println("keyword" + keyword);
		System.err.println("page" + page);
		// 创建PageData返回值对象
		PageData pageData = new PageData();
		// 调用countByUid()统计当前圖書的总量
		Integer count = fuzzyCountBooks(keyword);
		// 将PageData对象的maxRecordCount设置为总量
		pageData.setMaxRecordCount(count);
		// 判断总量是否为0
		if (count == 0) {
			// 是：直接返回PageData对象
			return pageData;
		}

		// 通过“总量/recordCountPerPage”结合“总量%recordCountPerPage”，计算得到最大页码
		Integer maxPage = count / recordCountPerPage;
		maxPage += count % recordCountPerPage == 0 ? 0 : 1;
		// 判断参数page是否不在 [1, 最大页码] 区间
		if (page == null || page < 1 || page > maxPage) {
			// 是：视为显示第1页，对参数page重新赋值，即page=1
			page = 1;
		}

		// 计算查询时的offset：(page - 1) * recordCountPerPage
		Integer offset = (page - 1) * recordCountPerPage;
		// 基于参数uid，计算得到的offset，全局属性recordCountPerPage，查询数据
		List<BookEntity> book1s = searchByKeyword(keyword);
		System.err.println("lllllllllllllllllllddddddddd=" + book1s.size());
		List<BookEntity> books = searchPageByKeyword(keyword, offset, recordCountPerPage);
		System.err.println("lllllllllllllllllll=" + books.size());
		// 向PageData对象中封装数据：minPage > 1
		pageData.setMinPage(1);
		// 向PageData对象中封装数据：maxPage > 最大页码
		pageData.setMaxPage(maxPage);
		// 向PageData对象中封装数据：currentPage > 参数page
		pageData.setCurrentPage(page);
		// 向PageData对象中封装数据：recordCountPerPage > 全局属性recordCountPerPage
		pageData.setRecordCountPerPage(recordCountPerPage);
		// 向PageData对象中封装数据：currentRecordCount > 查询得到的收藏数据的列表的长度
		pageData.setCurrentRecordCount(books.size());
		// 向PageData对象中封装数据：listData > 查询得到的收藏数据的列表
		pageData.setListData(books);
		
		pageData.setKeyword(keyword);
		System.err.println("kekekekekekeke:" + pageData.getKeyword());
		// 返回PageData对象
		return pageData;
	}
	
	//------------------------------------------------------------------------------------------------
	/**
	 * 根据图书的题目，作者，类型，出版商判断图书是否已经存在
	 * @param oldBook 数据库中的图书
	 * @param oldBook 要添加的图书
	 * @return 是否存在
	 */
	public boolean whetherExist(BookEntity newBook, BookEntity oldBook) {
		boolean exist = newBook.getTitle().equals(oldBook.getTitle())
				&&newBook.getAuthor().equals(oldBook.getAuthor())
				&&newBook.getPress().equals(oldBook.getPress())
				&&newBook.getType().equals(oldBook.getType());
		return exist;
	}


	//-----------------------------------------------------------------
	
	/**
	 * 模糊查询现有的图书数量
	 * @return
	 */
	Integer fuzzyCountBooks(String keyword) {
		return mapper.fuzzyCountBooks(keyword);
	};

	/**
	 * 分页模糊查询
	 * @param keyword 关键字
	 * @param offset  要跳过的查询的数量
	 * @param recordCountPerPage 每页的数量
	 * @return 图书的集合
	 */
	private List<BookEntity> searchPageByKeyword(
			@Param("keyword")String keyword,
			@Param("offset") Integer offset, 
			@Param("recordCountPerPage") Integer recordCountPerPage){
		return mapper.searchPageByKeyword(keyword, offset, recordCountPerPage);
	};
	
	private List<BookEntity> findBookByPage(
			@Param("offset") Integer offset, 
			@Param("recordCountPerPage") Integer recordCountPerPage){
		return mapper.findBookByPage(offset, recordCountPerPage);
	};
	
	/**
	 * 查询现有的图书数量
	 * @return
	 */
	private Integer countBooks() {
		return mapper.countBooks();
	};
	
	/**
	 * 通过关键字模糊查询图书信息
	 * @param keyword 关键字
	 * @return 图书信息的集合
	 */
	private List<BookEntity> searchByKeyword(String keyword){
		return mapper.searchByKeyword(keyword);
	};
	
	/**
	 * 根据用户id查询用户已租借的书本数量
	 * @param uid 用户id
	 * @return 已租借的书本数量
	 */
	private Integer countByUid(Integer uid) {
		return borrowedMapper.countByUid(uid);
	};
	
	/**
	 * 改变图书的为被租借状态
	 * @param bid 图书id
	 * @return 受影响的行数
	 */
	private Integer changeBookStatetoBorrowed(Integer bid) {
		return mapper.changeBookStatetoBorrowed(bid);
	};

	/**
	 * 改变图书为未被租借状态
	 * @param bid
	 * @return
	 */
	private Integer changeBookStatetoFree(Integer bid) {
		return mapper.changeBookStatetoFree(bid);
	};

	/**
	 * 根据借书人的id增加他借书的书的id与借书人的id
	 * @param bids 图书们id
	 * @param uid 借书人id
	 * @param date 
	 * @return 受影响的行数
	 */
	private Integer beBorrowedByUid(Integer bid, Integer uid, Date date){
		return mapper.beBorrowedByUid(bid, uid, date);
	};

	/**
	 * 添加图书至库中
	 * @param book 图书的实体类
	 * @return 受影响的行数
	 */
	private Integer insertBook(BookEntity book) {
		return mapper.insertBook(book);
	};

	/**
	 * 从库中根据书本id bid进行书本的删除
	 * @param bid 书本id
	 * @return 受影响的行数
	 */
	private Integer deleteByBid(Integer bid) {
		return mapper.deleteByBid(bid);
	};

	/**
	 * 图书信息的修改
	 * @param book 图书实体类
	 * @return 受影响的行数
	 */
	private Integer updateBook(BookEntity book) {
		return mapper.updateBook(book);
	};

	/**
	 * 根据图书名称查询图书的所有信息
	 * @param bid 图书id
	 * @return 受影响的行数
	 */
	private BookEntity findByTitle(String title) {
		return mapper.findByTitle(title);
	};

	/**
	 * 根据图书id查询图书的所有信息
	 * @param bid
	 * @return 图书的所有信息
	 */
	private BookEntity findInfoByBid(Integer bid) {
		return mapper.findInfoByBid(bid);
	};

	/**
	 * 查询所有的图书信息
	 * @return 图书信息的集合
	 */
	private List<BookEntity> findBooks(){
		return mapper.findBooks();
	};

	/**
	 * 查询目前最新一本书的id
	 * @return
	 */
	private Integer findMaxBid() {
		return mapper.findMaxBid();
	}

	/**
	 * 通过图书们的id得到图书们的信息
	 * @param bids 图书们的id
	 * @return 图书信息的集合
	 */
	private List<BookEntity> findConfirmedBook(Integer[] bids){
		return mapper.findConfirmedBook(bids);
	}



	

}
