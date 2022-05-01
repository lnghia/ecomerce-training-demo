package com.example.demo.services.implementations.technology;

import com.example.demo.entities.TechnologyEntity;
import com.example.demo.exceptions.TechnologyNotFoundException;
import com.example.demo.repositories.TechnologyRepository;
import com.example.demo.services.interfaces.technology.TechnologyService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@NoArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {
    private TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<TechnologyEntity> findByIds(Set<Long> ids) {
        List<TechnologyEntity> technologyEntities = technologyRepository.findAllById(ids);

        if (technologyEntities.size() != ids.size()) {
            throw new TechnologyNotFoundException();
        }

        return technologyEntities;
    }
}
