package ua.markiyan.sonara.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.markiyan.sonara.dto.request.ArtistRequest;
import ua.markiyan.sonara.dto.response.ArtistResponse;


public interface ArtistService {
    ArtistResponse create(ArtistRequest request);
    ArtistResponse get(Long id);
    Page<ArtistResponse> search(String name, String country, Pageable pageable);
}
