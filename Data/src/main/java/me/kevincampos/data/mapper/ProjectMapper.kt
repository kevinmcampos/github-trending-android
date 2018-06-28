package me.kevincampos.data.mapper

import me.kevincampos.data.model.ProjectEntity
import me.kevincampos.domain.model.Project
import javax.inject.Inject

class ProjectMapper @Inject constructor() : EntityMapper<ProjectEntity, Project> {

    override fun mapFromEntity(entity: ProjectEntity): Project {
        return Project(entity.id, entity.name, entity.fullName, entity.starCount,
                entity.dateCreated, entity.ownerName, entity.ownerAvatarUrl, entity.isBookmarked)
    }

    override fun mapFromDomain(domain: Project): ProjectEntity {
        return ProjectEntity(domain.id, domain.name, domain.fullName, domain.starCount,
                domain.dateCreated, domain.ownerName, domain.ownerAvatarUrl, domain.isBookmarked)
    }

}