package ua.markiyan.sonara.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.markiyan.sonara.dto.request.ArtistRequest;
import ua.markiyan.sonara.dto.response.ArtistResponse;
import ua.markiyan.sonara.entity.Artist;
import ua.markiyan.sonara.exception.NotFoundException;
import ua.markiyan.sonara.mapper.ArtistMapper;
import ua.markiyan.sonara.repository.ArtistRepository;
import ua.markiyan.sonara.service.ArtistService;


@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository repo;


    @Override
    @Transactional
    public ArtistResponse create(ArtistRequest req) {
        if (repo.existsByNameIgnoreCase(req.name())) {
            throw new IllegalArgumentException("Artist with the same name already exists");
        }

        // мапимо DTO в ентіті
        Artist entity = ArtistMapper.toEntity(req);


        Artist saved = repo.save(entity);
        return ArtistMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ArtistResponse get(Long id) {
        Artist u = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Artist %d not found".formatted(id)));
        return ArtistMapper.toResponse(u);
    }

}
