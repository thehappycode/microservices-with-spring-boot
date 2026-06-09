package com.thehappycode.api.core.recommendation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface RecommendationService {

    /**
     * Lấy danh sách Recommendation theo productId
     * 
     * @param productId
     * @return List<Recommendation>
     */
    @GetMapping(value = "/recommendation", produces = "application/json")
    List<Recommendation> getRecommendations(
            @RequestParam(value = "productId", required = true) int productId);
}
