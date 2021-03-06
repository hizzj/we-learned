package com.caitou.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.caitou.bean.Comment;

/**
 * 对Comment表进行操作的Dao层
 * 
 * @author caitou
 *
 */
@Repository
public interface ICommentDao {

	// 新建评论
	int insertComment(Comment comment);

	// 通过id删除评论
	int deleteById(int id);

	// 通过文章id查询评论
	List<Comment> queryByEssayId(int essayId);
}
