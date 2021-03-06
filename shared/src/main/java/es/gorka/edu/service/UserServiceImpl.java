package es.gorka.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.repository.Dao;
import es.gorka.edu.repository.UserRepository;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDTO newEntity() {
		return new UserDTO();
	}

	public boolean insertNewEntityDto(UserDTO userDto) {
		repository.insert(userDto);
		return true;
	}

	@Override
	public List<UserDTO> listAll() {
		return repository.selectAll();
	}

	@Override
	public UserDTO search(UserDTO dto) {
		return repository.selectOneByEntity(dto);
	}

	@Override
	public boolean userExist(UserDTO userDto) {
		return repository.existUser(userDto);
	}

	@Override
	public List<UserDTO> searchAll(UserDTO dto) {
		return repository.searchAll(dto);
	}

	@Override
	public void setRepository(Dao repository) {
		// TODO Auto-generated method stub
		this.repository = (UserRepository) repository;
	}

}
