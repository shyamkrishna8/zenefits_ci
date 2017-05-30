package com.shyam.zenefits.ci.dao;

import org.springframework.stereotype.Service;

import com.shyam.zenefits.ci.entities.User;

@Service
public class UserDAO extends AbstractDAO {

	public void create(User p){
		try {
			if (p != null) {
				update(p);
			}
		} catch (Exception ex) {
			System.out.println("ex:" + ex.getMessage());
		}
	}
	
	public void update(User p){
		try{
			if(p!=null){
				updateEntity(p);
			}
		}catch(Exception ex){
			System.out.println("ex:" + ex.getMessage());
		}
	}
}
