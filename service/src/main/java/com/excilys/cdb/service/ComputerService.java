package com.excilys.cdb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.ComputerDao;
import com.excilys.cdb.exceptions.CdbException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.validator.ComputerValidator;

/**
 * Computer service.
 *
 * @author sanogo
 *
 */
@Service
public class ComputerService {

	static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);
	private ComputerValidator computerValidator = new ComputerValidator();
	private ComputerDao computerDao;
	
	private ComputerService(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}


	/**
	 * find computer by id.
	 *
	 * @param l
	 *          asName
	 * @return Computer
	 */
	public Computer findById(long id) {
		return computerDao.findById(id).get();
	}

	/**
	 * Save computer.
	 *
	 * @param computer
	 *          asName
	 * @return (0 or 1)
	 * @throws CdbException
	 *           asName
	 */
	public Computer save(Computer computer) throws CdbException {
		if (computerValidator.verifComputer(computer)) {
			return computerDao.save(computer);
		}else {
			return new Computer();
		}
		
	}

	/**
	 * delete computer.
	 *
	 * @param id
	 *          asName
	 * @return (0 or 1)
	 */
	public int delete(long id) {
		if (findById(id).getId() == 0) {
			LOGGER.info("Le computer que vous voulez supprimer n'esxiste pas");
			return 0;
		} else {
			computerDao.delete(findById(id));
			return 1;
		}
	}

	/**
	 * update computer.
	 *
	 * @param computer
	 *          asName
	 * @return (0 or 1)
	 * @throws CdbException
	 *           asName
	 */
	public Computer update(Computer computer) throws CdbException {

		if (findById(computer.getId()).getId() != 0 & computerValidator.verifComputer(computer)) {
			 computerDao.save(computer);
			 return  computerDao.save(computer);
		} else {
			LOGGER.info("Le computer que vous voulez modifier n'esxiste pas");
			return new Computer();
		}

	}
	
	public long countByNameContaining(String name) {
		return computerDao.countByNameContaining(name);
	}


	/**
	 * 
	 * @param name
	 * @param pageIndex
	 * @param numberOfResultByPage
	 * @return
	 */
	public Page<Computer> findByComputerAndCompanyNameLimit(String name, int pageIndex, int numberOfResultByPage) {
		Pageable pageable = new PageRequest(pageIndex,numberOfResultByPage);
		return computerDao.findByNameContainingOrCompanyNameContaining(name, name, pageable);
	}

}
