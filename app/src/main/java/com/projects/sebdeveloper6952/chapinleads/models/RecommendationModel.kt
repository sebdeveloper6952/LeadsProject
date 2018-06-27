package com.projects.sebdeveloper6952.chapinleads.models

data class RecommendationModel(
        var id: Int,
        var title: String,
        var details: String,
        var category: String,
        var imgUri: String
)

object DummyRecs {
    var RECS = arrayListOf(
            RecommendationModel(0, "Nvidia", "American technology company. Designs GPUs", "Tecnología", "uri_missing"),
            RecommendationModel(0, "Recommendation2", "Recommendation2 details...", "Matemática", "uri_missing"),
            RecommendationModel(0, "Recommendation3", "Recommendation3 details...", "Deportes", "uri_missing"),
            RecommendationModel(0, "Recommendation4", "Recommendation4 details...", "Publicidad", "uri_missing"),
            RecommendationModel(0, "Recommendation5", "Recommendation5 details...", "Deportes", "uri_missing"),
            RecommendationModel(0, "Recommendation6", "Recommendation6 details...", "Finanzas", "uri_missing")
    )
}