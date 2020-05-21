package com.techhunters.borrowmyproducts.service;

import com.techhunters.borrowmyproducts.dto.UserDTO;

import java.util.List;

public interface UserService {


    public void save(UserDTO user);

    public List<UserDTO> findAll();

    public UserDTO findById(int id);

    public void delete(UserDTO user);

    public UserDTO findByEmail(String email);

    public UserDTO findByPhone(String phone);

//	public User findByUserName(String userName);

}
