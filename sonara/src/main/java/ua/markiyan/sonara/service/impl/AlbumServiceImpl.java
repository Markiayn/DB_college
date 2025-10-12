package ua.markiyan.sonara.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.markiyan.sonara.dto.request.AlbumRequest;
import ua.markiyan.sonara.dto.response.AlbumResponse;
import ua.markiyan.sonara.entity.Album;
import ua.markiyan.sonara.entity.Artist;
import ua.markiyan.sonara.exception.NotFoundException;
import ua.markiyan.sonara.mapper.AlbumMapper;
import ua.markiyan.sonara.repository.AlbumRepository;
import ua.markiyan.sonara.repository.ArtistRepository;
import ua.markiyan.sonara.service.AlbumService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepo;
    private final ArtistRepository artistRepo;

    @Override
    @Transactional
    public AlbumResponse create(AlbumRequest req) {
        Artist artist = artistRepo.findById(req.artistId())
                .orElseThrow(() -> new NotFoundException("Artist %d not found".formatted(req.artistId())));

        if (albumRepo.existsByTitleIgnoreCaseAndArtist_Id(req.title(), req.artistId())) {
            throw new IllegalArgumentException("Album with the same title already exists for this artist");
        }

        Album entity = AlbumMapper.toEntity(req, artist);
        Album saved = albumRepo.save(entity);
        return AlbumMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AlbumResponse get(Long id) {
        Album album = albumRepo.findWithArtistById(id)
                .orElseThrow(() -> new NotFoundException("Album %d not found".formatted(id)));
        return AlbumMapper.toResponse(album);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlbumResponse> listByArtist(Long artistId) {
        if (!artistRepo.existsById(artistId)) {
            throw new NotFoundException("Artist %d not found".formatted(artistId));
        }
        return albumRepo.findAllByArtist_Id(artistId)
                .stream()
                .map(AlbumMapper::toResponse)
                .toList();
    }
}
