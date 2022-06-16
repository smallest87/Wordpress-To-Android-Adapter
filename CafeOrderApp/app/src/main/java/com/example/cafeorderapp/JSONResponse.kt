package com.example.cafeorderapp

import com.google.gson.annotations.SerializedName

data class JSONResponse(
    val id: Int,
    val date: String?,
    val date_gmt: String?,
    val guid: GuidRendered,
    val modified: String?,
    val modified_gmt: String?,
    val slug: String?,
    val status: String?,
    val type: String?,
    val link: String?,
    val title: TitleRendered,
    val content: ContentRendered,
    val excerpt: ExcerptRendered,
    val author: Int,
    val featured_media: Int,
    val comment_status: String?,
    val ping_status: String?,
    val sticky: Boolean,
    val template: String?,
    val format: String?,
    val meta: MetaRendered
//    val categories: Array<t>,
//    val tags

)

data class TitleRendered (
    val rendered: String?
)

data class GuidRendered (
    val rendered: String?
)

data class ContentRendered (
    val rendered: String?,
    val protected: Boolean
)

data class ExcerptRendered (
    val rendered: String?,
    val protected: Boolean
)

data class MetaRendered (
    val spay_email: String?,
    val jetpack_publicize_message: String?,
    val jetpack_is_tweetstorm: Boolean,
    val jetpack_publicize_feature_enabled: Boolean
)
//data class PostResponse(
//    @SerializedName("title")
//    val title: Title
//)
//data class Title (
//    val rendered: String?
//)