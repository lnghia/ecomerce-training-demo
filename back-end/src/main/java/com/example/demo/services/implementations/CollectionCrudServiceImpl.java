package com.example.demo.services.implementations;

import com.example.demo.entities.CollectionEntity;
import com.example.demo.exceptions.CollectionNotFoundException;
import com.example.demo.repositories.CollectionRepository;
import com.example.demo.services.interfaces.CollectionCrudService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class CollectionCrudServiceImpl implements CollectionCrudService {
    private CollectionRepository collectionRepository;

    @Override
    public CollectionEntity findById(Long id) {
        Optional<CollectionEntity> collectionEntity = collectionRepository.findById(id);

        if (collectionEntity.isPresent()) {
            return collectionEntity.get();
        }

        throw new CollectionNotFoundException();
    }
}
