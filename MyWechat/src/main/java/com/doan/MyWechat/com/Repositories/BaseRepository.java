package com.doan.MyWechat.com.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface BaseRepository<T,ID > extends Repository<T, ID>{
	public Optional<T> findById(ID id);
	public T findObjectById(ID id);
	public T save(T entity);
	
	public List<T> findAll();
	
	public T deleteById(ID id);
	
}
