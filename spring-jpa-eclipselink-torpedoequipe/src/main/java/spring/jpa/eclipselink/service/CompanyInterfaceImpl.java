package spring.jpa.eclipselink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.eclipselink.domain.Company;
import spring.jpa.eclipselink.repository.CompanyDAO;

@Service
public class CompanyInterfaceImpl {

	@Autowired
	private CompanyDAO companyDao;

	public Company recuperarPorNome(String companyName, boolean activeRegister) {
		return companyDao.findByName(companyName, activeRegister);
	}
	
	public List<Company> recuperarEmpresasPorUsuarioMaster() {
		return companyDao.findCompanyMaster();
	}
	
	public List<Company> recuperarTodasAtivas() {
		return companyDao.findAll(true);
	}
	
	public void criarEmpresas(List<Company> companies) {
		for (Company company : companies) {
			companyDao.save(company);
		}
	}
}