package com.jsp.Agrow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agrow.dao.CommentDao;
import com.jsp.Agrow.dao.PostDao;
import com.jsp.Agrow.dao.UserDao;
import com.jsp.Agrow.dao.exception.CommentNotFound;
import com.jsp.Agrow.dao.exception.PostNotFound;
import com.jsp.Agrow.dao.exception.UserDoesNotExit;
import com.jsp.Agrow.dao.utils.ResponseStructure;
import com.jsp.Agrow.entity.Comment;
import com.jsp.Agrow.entity.Post;
import com.jsp.Agrow.entity.User;

@Service
public class CommentService {
	@Autowired
	CommentDao dao;
	@Autowired
	UserDao udao;
	@Autowired
	PostDao pdao; 
	
//	===========createComment==========================================================
	public ResponseEntity<ResponseStructure<Comment>> makeComment(int pid,int uid,String message){
		Post post=pdao.findPostById(pid);
		if(post!=null) {
			User user=udao.findUserById(uid);
			if(user!=null) {
				Comment comment=new Comment();
				comment.setComment(message);
				comment.setUser(user);
				Comment com=dao.createComment(comment);
				List<Comment> list=post.getComments();
				list.add(com);
				post.setComments(list);
				pdao.updatePost(post);
				ResponseStructure<Comment> rs=new ResponseStructure<Comment>();
				rs.setData(com);
				rs.setMessage("Commented Successfully!!!!");
				rs.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<Comment>>(rs,HttpStatus.ACCEPTED);
			}
			else {
				throw new UserDoesNotExit("No user with id:"+uid);
			}
		}
		else {
			throw new PostNotFound("No post with id: "+pid);
		}
	}
//	====================================================================================
	
	
//	========================Delete Comment===============================================
	public ResponseEntity<ResponseStructure<Comment>> deleteComment(int id){
		Comment comment=dao.findCommentById(id);
		if(comment!=null) {
			List<Post> posts=pdao.FetchAll();
			for(Post p:posts) {
				List<Comment> cmts=p.getComments();
				if(cmts.contains(comment)) {
					cmts.remove(comment);
					pdao.updatePost(p);
					dao.deleteComment(id);
					break;
				}
			}
			ResponseStructure<Comment> rs=new ResponseStructure<Comment>();
			rs.setMessage("post deleted");
			rs.setData(comment);
			rs.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<Comment>>(rs,HttpStatus.OK);
			
		}
		else {
			throw new CommentNotFound("no comment with id:"+id);
		}
	}
	
//	====================================================================================


}
