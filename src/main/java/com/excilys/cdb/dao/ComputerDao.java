package com.excilys.cdb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Computer;

@Repository
public interface ComputerDao extends PagingAndSortingRepository<Computer, Long> {
	/**
	 * allow access to an computer with is id.
	 *
	 * @param l asName
	 * @return Computer
	 */
	public Optional<Computer> findById(long id);
	
	/**
	 * find all computers.
	 */
	public List<Computer> findAll();

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Long countByNameContaining(String name);

	/**
	 * 
	 * @param p
	 * @param name
	 * @return
	 */
	public Page<Computer> findByNameContainingOrCompanyNameContaining(String name,String companyName,Pageable p);


	/**
	 * Delete computer from Db and return 1 in case of success and 0 if not.
	 *
	 * @param id asName
	 * @return (0 or 1)
	 */
	public Long deleteAllByIdIn(Iterable<Long> ids);
	
	/**
	 * 
	 * @param l
	 * @return
	 */
	public Long deleteAllByCompanyIdIn(Long id);

}
