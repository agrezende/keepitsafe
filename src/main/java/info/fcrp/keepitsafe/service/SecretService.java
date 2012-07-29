package info.fcrp.keepitsafe.service;

import java.util.List;

import info.fcrp.keepitsafe.dao.KeepDAO;
import info.fcrp.keepitsafe.dao.SecretDAO;
import info.fcrp.keepitsafe.model.Keep;
import info.fcrp.keepitsafe.model.Secret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Transactional
public class SecretService {
	@Autowired
	private SecretDAO secretDAO;
	
	@Autowired
	private KeepDAO keepDAO;
	
	@RequestMapping(value = "/keep/{keepId}/secret", method = RequestMethod.GET)
	public @ResponseBody
	List<Secret> findByKeepId(@PathVariable long keepId) {
		List<Secret> secrets = secretDAO.findByKeepId(keepId);
		return secrets;
	}
	
	@RequestMapping(value = "/secret/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Secret findById(@PathVariable long id) {
		Secret secret = secretDAO.find(id);
		return secret;
	}
	
	@RequestMapping(value = "/keep/{keepId}/secret", method = RequestMethod.POST)
	public @ResponseBody
	Secret create(@PathVariable long keepId, @RequestBody Secret secret) {
		Keep keep = keepDAO.find(keepId);
		secret.setKeep(keep);
		secretDAO.save(secret);
		return secret;
	}
	
	@RequestMapping(value = "/secret/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	Secret update(@PathVariable long id, @RequestBody Secret secret) {
		secret.setId(id);
		secretDAO.update(secret);
		return secret;
	}

	@RequestMapping(value = "/secret/{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable long id) {
		Secret secret = secretDAO.find(id);
		secretDAO.delete(secret);
	}
	
	
}