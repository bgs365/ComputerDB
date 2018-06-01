package com.excilys.cdb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;

@Repository
public interface CompanyDao extends PagingAndSortingRepository<Company, Long>{
	/**
	 * 
	 * @param id
	 * @return Company
	 */
	public Optional<Company> findById(Long id) ;

	/**
	 * 
	 * @return Companies
	 */
	public List<Company> findAll() ;

	/**
	 * find a bunch numberOfResultByPage of companies in db, which first company is
	 * pageIndex.
	 *
	 * @param pageIndex asName
	 * @param numberOfResultByPage asName
	 * @return List<Company>
	 */
	public Page<Company> findAll(Pageable pageable);

	/**
	 * find company by name.
	 *
	 * @param name asName
	 * @return Company
	 */
	public List<Company> findByNameContaining(String name) ;

	/**
	 * Delete company from Db and return 1 in case of success and 0 if not.
	 *
	 * @param id asName
	 * @return (0 or 1)
	 */
	public Long deleteById(Long id);
	
	public Long deleteAllByIdIn(Iterable<Long> ids);
}
