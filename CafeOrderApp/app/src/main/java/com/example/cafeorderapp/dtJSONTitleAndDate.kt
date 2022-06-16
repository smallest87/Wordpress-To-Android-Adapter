package com.example.cafeorderapp

data class dtJSONTitleAndDate(
//    val id: Int,
    val date: String?,
//    val date_gmt: String?,
//    val guid: GuidRendered,
//    val modified: String?,
//    val modified_gmt: String?,
//    val slug: String?,
//    val status: String?,
//    val type: String?,
//    val link: String?,
    val title: TitleRendered // menuju subclass
//    val content: ContentRendered, // menuju subclass
//    val excerpt: ExcerptRendered, // menuju subclass
//    val author: Int,
//    val featured_media: Int,
//    val comment_status: String?,
//    val ping_status: String?,
//    val sticky: Boolean,
//    val template: String?,
//    val format: String?,
//    val meta: MetaRendered // menuju subclass
)

data class TitleRendered (
    val rendered: String?
)

//data class GuidRendered (
//    val rendered: String?
//)

//data class ContentRendered (
//    val rendered: String?,
//    val protected: Boolean
//)

//data class ExcerptRendered (
//    val rendered: String?,
//    val protected: Boolean
//)
//
//data class MetaRendered (
//    val spay_email: String?,
//    val jetpack_publicize_message: String?,
//    val jetpack_is_tweetstorm: Boolean,
//    val jetpack_publicize_feature_enabled: Boolean
//)