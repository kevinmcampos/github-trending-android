package me.kevincampos.data.mapper

import me.kevincampos.data.model.ProjectEntity
import me.kevincampos.data.test.factory.ProjectFactory
import me.kevincampos.domain.model.Project
import org.junit.Test
import kotlin.test.assertEquals

class ProjectMapperTest {

    private val projectMapper = ProjectMapper()

    @Test
    fun mapsFromEntityMapsData() {
        val entity = ProjectFactory.makeProjectEntity()
        val model = projectMapper.mapFromEntity(entity)
        assertEqualData(entity, model)
    }

    @Test
    fun mapsToEntityMapsData() {
        val model = ProjectFactory.makeProject()
        val entity = projectMapper.mapFromDomain(model)
        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: ProjectEntity, model: Project) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.fullName, model.fullName)
        assertEquals(entity.starCount, model.starCount)
        assertEquals(entity.dateCreated, model.dateCreated)
        assertEquals(entity.ownerName, model.ownerName)
        assertEquals(entity.ownerAvatarUrl, model.ownerAvatarUrl)
        assertEquals(entity.isBookmarked, model.isBookmarked)
    }

}