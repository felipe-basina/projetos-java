package generic.dao.pattern.tests;

import generic.dao.context.SpringContainer;
import generic.dao.interfaces.business.ActionProfileService;
import generic.dao.interfaces.business.ActionService;
import generic.dao.interfaces.business.ProfileService;
import generic.dao.interfaces.business.impl.ActionProfileServiceImpl;
import generic.dao.interfaces.business.impl.ActionServiceImpl;
import generic.dao.interfaces.business.impl.ProfileServiceImpl;
import generic.dao.model.Action;
import generic.dao.model.ActionProfile;
import generic.dao.model.ActionProfilePK;
import generic.dao.model.Profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class CompoundKeyTest {

	private static SpringContainer context;

	private static Logger log = Logger.getLogger(CompoundKeyTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("log4j inicializado com sucesso!");
		context = SpringContainer.getInstance();
	}

	@Test
	public void adicionarPerfil() {

		// Cria o perfil
		Profile perfil = new Profile();
		perfil.setName("Perfil de teste");
		perfil.setCreationDate(new Date());

		ProfileService pService = context.getBean(ProfileServiceImpl.class);
		perfil = pService.create(perfil);

		// Cria a ação
		Action acao = new Action();
		acao.setDescAcao("Acao xyz");

		ActionService aService = context.getBean(ActionServiceImpl.class);
		acao = aService.create(acao);

		// Cria a chave composta
		ActionProfilePK pk = new ActionProfilePK();
		pk.setAction(acao);
		pk.setProfile(perfil);

		ActionProfile ap = new ActionProfile();
		ap.setId(pk);
		ap.setMsisdnCreationUser("Usuario XPTO");
		ap.setMsisdnUpdateUser("Usuario XPTO");

		ActionProfileService apService = context
				.getBean(ActionProfileServiceImpl.class);
		ap = apService.save(ap);
		System.err.println(">>>>>>> " + ap.getId().getAction() + "\n>>>>>>> "
				+ ap.getId().getProfile());
	}

	@Test
	public void getAllById() {
		ProfileService ps = context.getBean(ProfileServiceImpl.class);

		Query consulta = ps.getEntityManager().createQuery(
				" select p from Profile p " + 
				" join fetch p.actionProfile ap " +
				" join fetch ap.id.action " + 
				" where p = :profile ");

		Profile profile = new Profile();
		profile.setCdProfile(9);

		consulta.setParameter("profile", profile);

		profile = (Profile) consulta.getResultList().get(0);

		for (ActionProfile ap : profile.getActionProfile()) {
			System.out.println("Action : " + ap.getId().getAction());
			System.err.println("Profile : " + ap.getId().getProfile());
			System.out.println("ActionProfile : " + ap);
		}
	}
	
	@Test
	public void insertProfileWithAllActions() {
		ActionService as = context.getBean(ActionServiceImpl.class);
		List<Action> acoes = as.getAllActions();
		
		List<ActionProfile> actionsProfile = new ArrayList<ActionProfile>();
		
		Profile profile = new Profile();
		profile.setName("Novo perfil");
		profile.setCreationDate(new Date());
		
		int indice = 0;
		for (Action acao : acoes) {
			ActionProfilePK pk = new ActionProfilePK();
			pk.setAction(acao);
			pk.setProfile(profile);
			
			ActionProfile ap = new ActionProfile();
			ap.setMsisdnCreationUser("Usuario " + indice);
			ap.setMsisdnUpdateUser("Usuario " + indice++);
			ap.setId(pk);
			
			actionsProfile.add(ap);
		}
		
		profile.setActionProfile(actionsProfile);
		
		ProfileService ps = context.getBean(ProfileServiceImpl.class);
		ps.create(profile);
	}
	
	@Test
	public void deleteProfileById() {
		int id = 8;
		
		ProfileService ps = context.getBean(ProfileServiceImpl.class);
		ps.delete(id);
	}

}
