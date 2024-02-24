package com.galaxy.backend.dtos;

import java.util.List;

public record SeguradoPageDTO(List<SeguradoDTO> segurados, int totalPages, long totalElements) {
}
