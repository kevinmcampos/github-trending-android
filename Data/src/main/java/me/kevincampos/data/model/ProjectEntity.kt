package me.kevincampos.data.model

class ProjectEntity(
        val id: String,
        val name: String,
        val fullName: String,
        val starCount: String,
        val dateCreated: String,
        val ownerName: String,
        val ownerAvatarUrl: String,
        val isBookmarked: Boolean
)