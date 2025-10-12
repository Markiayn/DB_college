package ua.markiyan.sonara.service;

import ua.markiyan.sonara.dto.request.ArtistRequest;
import ua.markiyan.sonara.dto.response.ArtistResponse;


public interface ArtistService {
    ArtistResponse create(ArtistRequest request);
    ArtistResponse get(Long id);
}
