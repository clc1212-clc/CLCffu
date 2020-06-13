package com.myproject.BookManage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.BookManage.VO.BookEntityVO;
import com.myproject.BookManage.entity.BorrowedEntity;
import com.myproject.BookManage.entity.ExtraMoney;
import com.myproject.BookManage.mapper.BorrowedMapper;
import com.myproject.BookManage.service.BorrowedService;
import com.myproject.BookManage.service.ex.BookAlreadyReturnException;
import com.myproject.BookManage.service.ex.FindInfoException;
import com.myproject.BookManage.service.ex.HaveExtraMoneyException;
import com.myproject.BookManage.service.ex.RemoveException;

@Service
public class BorrowedServiceImpl implements BorrowedService {

	@Autowired
	BorrowedMapper mapper;
	
	@Override
	public List<BookEntityVO> showBookVOByUid(Integer uid) {
		List<BookEntityVO> result = findBookByUid(uid);
		if(result.size() < 0) {
			throw new FindInfoException("查询失败!查询过程中出现异常!");
		}
		return result;
	}
	
	@Override
	public void removeBookFromTableByBid(Integer bid) {
		BorrowedEntity result = findBookByBid(bid);
		if(result == null) {
			throw new BookAlreadyReturnException("归还失败!图书已经归还!");
		}
	
		long returnTime = result.getReturnTime().getTime();
		long nowTime = new Date().getTime();
		long extraTime = nowTime - returnTime;
		if(extraTime < 0) {
			
		} else {
			Integer days = (int) (extraTime/1000/60/60/24);
			Integer money = days * 2;
			System.out.println("days" + days);
			System.out.println("money" + money);
			throw new HaveExtraMoneyException("还书失败!您有"+money+"元的逾期费用未还!(每逾期1天2元额外费用)");
		}
		Integer rows = deleteBookByBid(bid);
		if(rows != 1) {
			throw new RemoveException("归还失败!数据改变出现未知错误!");
		}
	};

	
	//------------------------------------------------------->
	
	/**
	 * 通过bid查询借阅表中图书的信息
	 * @param bid 图书id
	 * @return 借阅表实体类
	 */
	private BorrowedEntity findBookByBid(Integer bid) {
		return mapper.findBookByBid(bid);
	};
	
	/**
	 * 根据图书id从借阅表中删除数据
	 * @param bid 图书id
	 * @return 受影响的行数
	 */
	private Integer deleteBookByBid(Integer bid) {
		return mapper.deleteBookByBid(bid);
	};
	/**
	 * 根据用户id查询用户已租借的书本数量
	 * @param uid 用户id
	 * @return 已租借的书本数量
	 */
	private Integer countByUid(Integer uid) {
		return mapper.countByUid(uid);
	};
	
	/**
	 * 根据用户id查询用户已租借的书的信息
	 * @param uid 用户id
	 * @return 已租借的书的信息的集合
	 */
	private List<BookEntityVO> findBookByUid(Integer uid){
		return mapper.findBookByUid(uid);
	}


	
}
