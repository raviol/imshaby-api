package by.imsha.service;

import by.imsha.domain.Parish;
import by.imsha.repository.ParishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParishService {
    private static Logger logger = LoggerFactory.getLogger(ParishService.class);

    @Autowired
    ParishRepository parishRepository;

    public Parish createParish(Parish parish){
        return parishRepository.save(parish);
    }

    public List<Parish> createParishesWithList(List<Parish> parishes){
        return parishRepository.save(parishes);
    }

    public Parish loginParish(String login, String password){
        return parishRepository.findByLoginAndPassword(login, password);
    }

    public Parish retrieveParish(String id){
        return parishRepository.findOne(id);
    }

    public Parish updateParish(Parish parish){
        return parishRepository.save(parish);
    }

    public void removeParish(String id){
        parishRepository.delete(id);
    }


}
