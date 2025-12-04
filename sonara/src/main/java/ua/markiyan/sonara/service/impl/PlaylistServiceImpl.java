package ua.markiyan.sonara.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.markiyan.sonara.dto.request.PlaylistRequest;
import ua.markiyan.sonara.dto.response.PlaylistResponse;
import ua.markiyan.sonara.entity.Playlist;
import ua.markiyan.sonara.entity.User;
import ua.markiyan.sonara.exception.NotFoundException;
import ua.markiyan.sonara.repository.PlaylistRepository;
import ua.markiyan.sonara.repository.UserRepository;
import ua.markiyan.sonara.service.PlaylistService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public PlaylistResponse create(Long userId, PlaylistRequest req) {
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User %d not found".formatted(userId)));
        Playlist p = Playlist.builder()
                .user(user)
                .title(req.title())
                .isPublic(Boolean.TRUE.equals(req.isPublic()))
                .build();
        p = playlistRepo.save(p);
        return toResponse(p);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlaylistResponse> listByUser(Long userId) {
        if (!userRepo.existsById(userId)) throw new NotFoundException("User %d not found".formatted(userId));
        return playlistRepo.findAllByUser_Id(userId).stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PlaylistResponse get(Long id) {
        Playlist p = playlistRepo.findById(id).orElseThrow(() -> new NotFoundException("Playlist %d not found".formatted(id)));
        return toResponse(p);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!playlistRepo.existsById(id)) throw new NotFoundException("Playlist %d not found".formatted(id));
        playlistRepo.deleteById(id);
    }

    private PlaylistResponse toResponse(Playlist p) {
        return new PlaylistResponse(p.getId(), p.getUser().getId(), p.getTitle(), p.isPublic());
    }
}

