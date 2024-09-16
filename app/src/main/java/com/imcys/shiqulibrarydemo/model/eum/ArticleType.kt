package com.imcys.shiqulibrarydemo.model.eum


/**
 * 文章分类
 */
enum class ArticleType(val id: Int, val color: String) {
    ARTIFICIAL_INTELLIGENCE(1, "#f44336"), // 人工智能
    FRONTIER_TECHNOLOGY(2, "#9c27b0"), // 前沿技术
    SPACE_UNIVERSE(3, "#3f51b5"), // 太空宇宙
    BIOLOGICAL_MEDICAL(4, "#03a9f4"), // 生物医疗
    NATURAL_SCIENCE(5, "#00bcd4"), // 自然科学
    ENVIRONMENT_ECOLOGY(6, "#009688"), // 环境生态
    HISTORICAL_CULTURE(7, "#4caf50"), // 历史文化
    ARTS_LITERATURE(8, "#8bc34a"), // 艺术文学
    LEISURE_LIFE(9, "#7bd2d8"), // 休闲生活
    SOCIAL_PHENOMENA(10, "#ffc107"), // 社会现象
    EDUCATION_GROWTH(11, "#ff9800"), // 成长教育
    PSYCHOLOGICAL_EMOTION(12, "#9e9e9e"); // 心理情感
}